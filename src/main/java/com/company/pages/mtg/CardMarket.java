package com.company.pages.mtg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.company.dataProvider.ConfigFileReader;

public class CardMarket {

    ConfigFileReader configFileReader;
    String url = null;

    static String title = "";
    WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public CardMarket(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        configFileReader = new ConfigFileReader();
    }

    @FindBy(xpath = "//*[@id=\"ProductSearchInput\"]")
    public WebElement productSearchInput;

    @FindBy(xpath = "//*[@id=\"CookiesConsent\"]/div/div/form/button")
    public WebElement acceptCookies;

    @FindBy(xpath = "//*[@id=\"search-btn\"]")
    public WebElement searchBtn;

    @FindBy(xpath = "//*[@id=\"AutoCompleteResult\"]/a[1]/div")
    public WebElement autoCompleteResult;

    @FindBy(xpath = "//div[@class=\"table-body\"]/div[1]")
    public WebElement firstResult;

    @FindBy(xpath = "//*[@id=\"language[1]-61eef3bbc7deb\"]")
    public WebElement idLanguageEng;

    @FindBy(xpath = "//*[@type=\"checkbox\" and @name=\"sellerType[1]\"]")
    public WebElement sellerTypePersonal;

    @FindBy(xpath = "//*[@type=\"checkbox\" and @name=\"sellerType[0]\"]")
    public WebElement sellerTypePro;

    @FindBy(xpath = "//*[@type=\"checkbox\" and @name=\"sellerType[2]\"]")
    public WebElement sellerTypePower;

    @FindBy(xpath = "//*[@id=\"articleFilterSellerReputation\"]/div/select")
    public WebElement minSellerRep;

    @FindBy(xpath = "//*[@id=\"articleFilterSellerReputation\"]/div/select/option[3]")
    public WebElement minSellerRepValueGood;

    @FindBy(xpath = "//*[@id=\"articleFilterProductCondition\"]")
    public WebElement minCondition;

    @FindBy(xpath = "//*[@id=\"articleFilterProductCondition\"]/div/div/select/option[5]")
    public WebElement minConditionLightlyPlayed;

    @FindBy(xpath = "//*[@id=\"articleFilterProductCondition\"]/div/div/select/option[4]")
    public WebElement minConditionGood;

    @FindBy(xpath = "//div[@class=\"col-offer\"]/*/div[@class=\"d-flex flex-column\"]/div[@class=\"d-flex align-items-center justify-content-end\"]/span[@class=\"font-weight-bold color-primary small text-right text-nowrap\"]")
    public WebElement listOfCardPrices;

    @FindBy(xpath = "//*[@id=\"header-login\"]/div[1]/div/input")
    public WebElement loginUsername;

    @FindBy(xpath = "//*[@id=\"header-login\"]/div[2]/div/input")
    public WebElement loginPass;

    @FindBy(xpath = "//*[@id=\"header-login\"]/input[3]")
    public WebElement loginBtn;

    @FindBy(xpath = "//button[@data-original-title=\"Put in shopping cart\"]")
    public WebElement buyBtnWhichAreAvailable;

    @FindBy(xpath = "//*[@id=\"cart\"]")
    public WebElement shoppingCard;

    @FindBy(xpath = "//input[@type=\"submit\" and @value=\"Remove all articles\" and @title=\"Remove all articles\"]")
    public WebElement removeAllFromShoppingCard;

    @FindBy(xpath = "//*[@class=\"strong item-value\"]")
    public WebElement randomItemsValueOnly;

    @FindBy(xpath = "//*[@class=\"strong ship-cost\"]")
    public WebElement randomItemsShippingOnly;

    @FindBy(xpath = "/html/body/main/div[3]/div[2]/div[2]/div[2]/div/div[7]/span[2]")
    public WebElement totalCost;

    @FindBy(xpath = "//*[@class=\"list-inline\"]/li[@data-index=\"0\"]")
    public WebElement sellerSingles;

    @FindBy(xpath = "//input[@type=\"text\" and @name=\"name\"]")
    public WebElement sellerSinglesName;

    @FindBy(xpath = "//div[@class=\"table-body\"]//div[@class=\"col-seller col-12 col-lg-auto\"]")
    public WebElement sellerSinglesFirstResult;

    @FindBy(xpath = "//*[@name=\"idLanguage\"]")
    public WebElement cardsLanguage;

    @FindBy(xpath = "//*[@name=\"idLanguage\"]//option[@value=\"1\"]")
    public WebElement cardsLanguageEng;


    @FindBy(xpath = "//*[@name=\"idLanguage\"]")
    public WebElement sellerSinglesLanguage;

    @FindBy(xpath = "//*[@name=\"idLanguage\"]//option[@value=\"1\"]")
    public WebElement sellerSinglesLanguageEng;

    @FindBy(xpath = "//*[@name=\"condition\"]")
    public WebElement sellerMinCardCondition;

    @FindBy(xpath = "//*[@name=\"condition\"]//option[@value=\"4\"]")
    public WebElement sellerMinCardConditionGood;

}