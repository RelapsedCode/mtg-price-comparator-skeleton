package com.company;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.company.dataProvider.ConfigFileReader;
import com.company.models.Card;
import com.company.models.Seller;
import com.company.pages.LocatorsOfElements;
import com.company.pages.mtg.CardMarket;
import com.company.utils.BufferReader;
import com.company.utils.CustomBrowser;

import lombok.SneakyThrows;

public class StepDefinitionMTG implements LocatorsOfElements {

	final Logger logger = LogManager.getLogger(StepDefinitionMTG.class.getName());

	ArrayList<Card> cardList = new ArrayList<Card>();
	ArrayList<Seller> sellersList = new ArrayList();
	LinkedHashMap<String, Seller> mapOfSellers = new LinkedHashMap<>();
	WebDriver staticWebDriverFromCustomBrowser = CustomBrowser.createDriverObject();

	CardMarket cardMarket = new CardMarket(staticWebDriverFromCustomBrowser);

	JavascriptExecutor executor = (JavascriptExecutor) cardMarket.getDriver();
	Actions actions = new Actions(staticWebDriverFromCustomBrowser);
	String favItemName = null;
	String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
	double cardPriceMin;
	double cardPriceMax;
	Card currentCard = new Card();

	@SneakyThrows
	public void navigateToHome() {
		staticWebDriverFromCustomBrowser.get(ConfigFileReader.getPropertyValue("url"));
	}

	@SneakyThrows
	public void getCardsFromBasket() {
		staticWebDriverFromCustomBrowser.get(ConfigFileReader.getPropertyValue("url3"));
		List<WebElement> CardsInTheBasketStartFromOneList = staticWebDriverFromCustomBrowser.findElements(By.xpath(CardsInTheBasketStartFromOne));
		for (int i = 1; i < CardsInTheBasketStartFromOneList.size(); i++) {
			System.out.println(CardsInTheBasketStartFromOneList.get(i).getAttribute("data-name"));
		}
	}

	@SneakyThrows
	public void login() {
		CustomBrowser.wait.until(ExpectedConditions.visibilityOf(cardMarket.loginUsername));
		cardMarket.loginUsername.sendKeys(ConfigFileReader.getPropertyValue("usernameMTG"));
		cardMarket.loginPass.sendKeys(ConfigFileReader.getPropertyValue("passwordMTG"));
		Thread.sleep(1500);
		actions.moveToElement(cardMarket.loginBtn).click().perform();
		CustomBrowser.wait.until(ExpectedConditions.visibilityOf(cardMarket.acceptCookies));
		Thread.sleep(1500);
		actions.moveToElement(cardMarket.acceptCookies).click().perform();
	}

	@SneakyThrows
	public void clearShoppingCard() {
		Thread.sleep(1500);
		staticWebDriverFromCustomBrowser.get(ConfigFileReader.getPropertyValue("url3"));
		try {
			CustomBrowser.wait.until(ExpectedConditions.visibilityOf(cardMarket.removeAllFromShoppingCard));
			Thread.sleep(1500);
			cardMarket.removeAllFromShoppingCard.click();
		} catch (Exception e) {
			logger.warn("BASKET IS ALREADY EMPTY");
		}
	}

	@SneakyThrows
	public void searchAndNavigateToCard(String cardName) {
		currentCard.setName(cardName);
		CustomBrowser.wait.until(ExpectedConditions.visibilityOf(cardMarket.productSearchInput));
		Thread.sleep(1500);
		actions.moveToElement(cardMarket.productSearchInput).click().perform();
		cardMarket.productSearchInput.clear();
		cardMarket.productSearchInput.sendKeys(cardName);
		Thread.sleep(1500);
		cardMarket.searchBtn.click();
		CustomBrowser.wait.until(ExpectedConditions.visibilityOf(cardMarket.firstResult));
		Thread.sleep(1500);
		actions.moveToElement(cardMarket.firstResult).click().perform();
	}

	@SneakyThrows
	public void buyMeACard() {
		List<WebElement> buyCardBtns = staticWebDriverFromCustomBrowser.findElements(By.xpath(buyBtnWhichAreAvailable));
		List<WebElement> pricesList = staticWebDriverFromCustomBrowser.findElements(By.xpath(listOfCardPrices));
		CustomBrowser.wait.until(ExpectedConditions.visibilityOf(pricesList.get(0)));
		cardPriceMin = Double.parseDouble(pricesList.get(0).getText().substring(0, pricesList.get(0).getText().length() - 2).replaceAll(",", "."));
		currentCard.setMinPrice(cardPriceMin);
		cardPriceMax = Double.parseDouble(pricesList.get(4).getText().substring(0, pricesList.get(4).getText().length() - 2).replaceAll(",", "."));
		currentCard.setMaxPrice(cardPriceMax);
		cardList.add(currentCard);
		executor.executeScript("arguments[0].scrollIntoView(true);", buyCardBtns.get(0));
		CustomBrowser.wait.until(ExpectedConditions.elementToBeClickable(buyCardBtns.get(0)));
		Thread.sleep(1500);
		executor.executeScript("arguments[0].click();", buyCardBtns.get(0));
		logger.info(currentCard.getName() + ": " + currentCard.getMinPrice());
	}

	public void PrintPricesFromSellers() {
		staticWebDriverFromCustomBrowser.get(ConfigFileReader.getPropertyValue("url3"));
//		Seller seller = null;
		List<WebElement> namesOfEachSellerList = staticWebDriverFromCustomBrowser.findElements(By.xpath(namesOfEachSeller));
		List<WebElement> articlesCountFromEachSellerList = staticWebDriverFromCustomBrowser.findElements(By.xpath(articlesCountFromEachSeller));
		List<WebElement> articlesPriceFromEachSellerList = staticWebDriverFromCustomBrowser.findElements(By.xpath(articlesPriceFromEachSeller));
		List<WebElement> shippingPriceFromEachSellerList = staticWebDriverFromCustomBrowser.findElements(By.xpath(shippingPriceFromEachSeller));
		List<WebElement> totalPriceFromEachSellerList = staticWebDriverFromCustomBrowser.findElements(By.xpath(totalPriceFromEachSeller));

		for (int i = 0; i < namesOfEachSellerList.size(); i++) {
			Seller seller = new Seller();
			seller = mapOfSellers.get(namesOfEachSellerList.get(i).getText());
			seller.setItemsNumber(Integer.parseInt(articlesCountFromEachSellerList.get(i).getText().substring(0, 1)));
			seller.setItemsValue(Double.parseDouble(articlesPriceFromEachSellerList.get(i).getText().substring(0, articlesPriceFromEachSellerList.get(i).getText().length() - 2).replaceAll(",", ".")));
			seller.setShippingValue(Double.parseDouble(shippingPriceFromEachSellerList.get(i).getText().substring(0, shippingPriceFromEachSellerList.get(i).getText().length() - 2).replaceAll(",", ".")));
			seller.setTotalValue(Double.parseDouble(totalPriceFromEachSellerList.get(i).getText().substring(0, totalPriceFromEachSellerList.get(i).getText().length() - 2).replaceAll(",", ".")));

			logger.info("Seller name: " + seller.getName());
			logger.info("Contents: " + seller.getItemsNumber());
			logger.info("Article value: " + seller.getItemsValue());
			logger.info("Shipping value: " + seller.getShippingValue());
			logger.info("Total value: " + seller.getTotalValue() + "\n");
		}
	}

	public void printTotalPrice() {
		try {
			staticWebDriverFromCustomBrowser.get(ConfigFileReader.getPropertyValue("url3"));
			CustomBrowser.wait.until(ExpectedConditions.visibilityOf(cardMarket.randomItemsValueOnly));
			logger.info("Article values: " + cardMarket.randomItemsValueOnly.getText());
			logger.info("Shipping values: " + cardMarket.randomItemsShippingOnly.getText());
			logger.info("Total values: " + cardMarket.totalCost.getText());
		} catch (Exception e) {
			logger.warn("NO ITEMS IN THE BASKET");
		}
	}

	@SneakyThrows
	public void navigateToSellerSingles(String sellerName) {
		Card cardInTheSeller;
		Seller seller = new Seller();
		ArrayList<Card> cardsList = new ArrayList<>();
		Select select;

		seller.setName(sellerName);

		staticWebDriverFromCustomBrowser.get(ConfigFileReader.getPropertyValue("url2") + sellerName + "/Offers/Singles");
		//		executor.executeScript("arguments[0].scrollIntoView(true);", cardMarket.sellerSingles);
		Thread.sleep(1500);
		staticWebDriverFromCustomBrowser.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		//		actions.moveToElement(cardMarket.sellerSingles).click().perform();
		actions.moveToElement(cardMarket.sellerSinglesLanguage).click().perform();
		staticWebDriverFromCustomBrowser.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		select = new Select(cardMarket.sellerSinglesLanguage);
		select.selectByIndex(1);
		actions.moveToElement(cardMarket.sellerMinCardCondition).click().perform();
		staticWebDriverFromCustomBrowser.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		select = new Select(cardMarket.sellerMinCardCondition);
		select.selectByIndex(3);

		List<String> cardsNamesFromCSV = BufferReader.splitTheCSV("tables/cards-full-tobuy.csv");
		for (int i = 0; i < cardsNamesFromCSV.size(); i++) {
			cardInTheSeller = new Card();
			cardInTheSeller.setName(cardsNamesFromCSV.get(i).trim());
			cardMarket.sellerSinglesName.clear();
			cardMarket.sellerSinglesName.sendKeys(cardsNamesFromCSV.get(i).trim());
			Thread.sleep(1500);
			cardMarket.sellerSinglesName.sendKeys(Keys.ENTER);
			List<WebElement> buyCardBtns = staticWebDriverFromCustomBrowser.findElements(By.xpath(buyBtnWhichAreAvailable));
			List<WebElement> namesOfTheAvailableCards = staticWebDriverFromCustomBrowser.findElements(By.xpath(nameOfEachSingle));
			int rowItemId = isCardNameValid(namesOfTheAvailableCards);

			if ((buyCardBtns.isEmpty())) {    //|| !(cardsNamesFromCSV.get(i).trim().equals(namesOfTheAvailableCards.get(0).getText().trim()))) {
				cardInTheSeller.setAvailable(false);
			} else if (rowItemId != 999) {
				Thread.sleep(1500);
				executor.executeScript("arguments[0].scrollIntoView(true);", buyCardBtns.get(rowItemId));
				CustomBrowser.wait.until(ExpectedConditions.elementToBeClickable(buyCardBtns.get(rowItemId)));
				executor.executeScript("arguments[0].click();", buyCardBtns.get(rowItemId));
				cardMarket.sellerSinglesName.clear();
				cardInTheSeller.setAvailable(true);
				cardInTheSeller.setPrice(Double.parseDouble(cardMarket.listOfCardPrices.getText().substring(0, cardMarket.listOfCardPrices.getText().length() - 2).replaceAll(",", ".")));
			} else if (rowItemId == 999) {
				cardInTheSeller.setAvailable(false);
				logger.warn("The card: " + cardsNamesFromCSV.get(i).trim() + " is not available in seller: " + sellerName);
			}
			cardsList.add(cardInTheSeller);
		}

		seller.setAvailableCards(cardsList);
		mapOfSellers.put(sellerName, seller);
	}

	public int isCardNameValid(List<WebElement> namesOfTheAvailableCards) {
		for (int i = 0; i < namesOfTheAvailableCards.size(); i++) {
			if (!(namesOfTheAvailableCards.get(i).getText().contains("Art Series:"))) {
				return i;
			}
		}
		return 999;
	}

	@SneakyThrows
	public void createCSVFile() {
		final String SAMPLE_CSV_FILE = "tables/output.csv";
		CSVPrinter csvFilePrinter = null;
		try {
			csvFilePrinter = CSVFormat.EXCEL.withHeader("Seller Name", "Card Name", "Card Cost", "isAvailable", "Number of Items", "Items Value", "Shipping Cost", "Total Amount")
					.print(Paths.get(SAMPLE_CSV_FILE), StandardCharsets.UTF_8);
			for (Map.Entry<String, Seller> entry : mapOfSellers.entrySet()) {
				Seller seller = entry.getValue();
				List<Card> cards = seller.getAvailableCards();
				csvFilePrinter.printRecord(entry.getKey());
				for (Card card : cards) {
					csvFilePrinter.printRecord(seller.getName(), card.getName(), card.getPrice(), card.isAvailable());
				}
				csvFilePrinter.printRecord(Arrays.asList(seller.getName(), "", "", "", seller.getItemsNumber(), seller.getItemsValue(), seller.getShippingValue(), seller.getTotalValue()));
				csvFilePrinter.flush();
			}
			csvFilePrinter.flush();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void close() {
		staticWebDriverFromCustomBrowser.close();
		staticWebDriverFromCustomBrowser.quit();
	}

}

