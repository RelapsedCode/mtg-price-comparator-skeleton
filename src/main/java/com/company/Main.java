package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.company.utils.BufferReader;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) throws Exception {

		StepDefinitionMTG stepDefinitionMTG = new StepDefinitionMTG();
		stepDefinitionMTG.navigateToHome();
		stepDefinitionMTG.login();
		//		StepDefinitionMTG.clearShoppingCard();

		Scanner sc = new Scanner(System.in);
		List<String> menu = new ArrayList<>();

		menu.add("\n0. Exit.");
		menu.add("\n1. Buy the cheapest card from random sellers.");
		menu.add("2. Buy cards from the list from the specified sellers and print the cost values.");
		menu.add("3. Only print the cost values.");
		menu.add("4. Save to CSV.");
		menu.add("5. Get cards from basket.");
		menu.add("6. Clear basket.");
		menu.add("7. Close the driver.");

		boolean isRunning = true;

		while (isRunning) {
			menu.forEach(option -> System.out.println(option));
			System.out.print("\nEnter the selected func(): ");
			int option = sc.nextInt();
			sc.nextLine();
			switch (option) {
				case 0:
					isRunning = false;
					stepDefinitionMTG.close();
					break;
				case 1:
					stepDefinitionMTG.clearShoppingCard();
					List<String> cardsList = BufferReader.splitTheCSV("tables/cards-full-tobuy.csv");
					for (int i = 0; i < cardsList.size(); i++) {
						stepDefinitionMTG.searchAndNavigateToCard(cardsList.get(i).trim());
						stepDefinitionMTG.buyMeACard();
					}
					logger.info("PRICE WHEN BUYING FROM RANDOM PEOPLE:");
//					stepDefinitionMTG.PrintPricesFromSellers();
					stepDefinitionMTG.printTotalPrice();
					//					stepDefinitionMTG.clearShoppingCard();
					//					stepDefinitionMTG.close();
					break;
				case 2:
					//					stepDefinitionMTG.clearShoppingCard();
					List<String> sellersList = BufferReader.splitTheCSV("tables/sellers.csv");
					for (int i = 0; i < sellersList.size(); i++) {
						stepDefinitionMTG.navigateToSellerSingles(sellersList.get(i).trim());
					}
					logger.info("PRICE WHEN BUYING FROM SPECIFIC SELLERS:");
					stepDefinitionMTG.PrintPricesFromSellers();
					stepDefinitionMTG.printTotalPrice();
					//					stepDefinitionMTG.clearShoppingCard();
					//					stepDefinitionMTG.close();
					break;
				case 3:
					stepDefinitionMTG.PrintPricesFromSellers();
					stepDefinitionMTG.printTotalPrice();
					break;
				case 4:
					stepDefinitionMTG.createCSVFile();
					break;
				case 5:
					stepDefinitionMTG.getCardsFromBasket();
					break;
				case 6:
					stepDefinitionMTG.clearShoppingCard();
					break;
				case 7:
					stepDefinitionMTG.close();
					break;
			}
		}
	}
}