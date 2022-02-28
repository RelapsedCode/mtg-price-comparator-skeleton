package com.company.pages;

public interface LocatorsOfElements {

	double DELTA = 1e-15;
	String delimiter = ";";
	String listOfCardPrices = "//div[@class=\"col-offer\"]/*/div[@class=\"d-flex flex-column\"]/div[@class=\"d-flex align-items-center justify-content-end\"]/span[@class=\"font-weight-bold color-primary small text-right text-nowrap\"]";
	String buyBtnWhichAreAvailable = "//button[@data-original-title=\"Put in shopping cart\"]";
	String shippingFeesList = "//*[@class=\"shipping-price\"]";

	String shippingPriceFromEachSeller = "//*[@class=\"shipping-price\"]";
	String articlesPriceFromEachSeller = "//*[@class=\"item-value\"]";
	String totalPriceFromEachSeller = "//*[@class=\"strong total\"]";
	String articlesCountFromEachSeller = "//*[@class=\"article-count\"]";
	String namesOfEachSeller = "//*[@class=\"d-flex has-content-centered mr-1\"]//a[contains(@href, '/en/Magic/Users/')]";
	String nameOfEachSingle = "//*[contains(@href, '/en/Magic/Products/Singles/')]";
	String CardsInTheBasketStartFromOne = "//tr[contains(@data-name, \"\")]";

}
