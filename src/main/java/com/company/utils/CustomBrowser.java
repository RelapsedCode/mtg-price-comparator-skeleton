package com.company.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.company.dataProvider.ConfigFileReader;

import lombok.SneakyThrows;

public class CustomBrowser {

	public static WebDriverWait wait = null;
	public static WebDriverWait wait2 = null;
	public static WebDriverWait wait3 = null;

	private static final ConfigFileReader configFileReader = new ConfigFileReader();

	@SneakyThrows
	public static WebDriver createDriverObject() {
		String[] a = { "enable-automation" };
		String[] b = { "False" };
		//        System.setProperty("webdriver.chrome.whitelistedIps", "");
		System.setProperty("webdriver.chrome.driver", ConfigFileReader.getPropertyValue("chromedriverPath"));
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBinary(ConfigFileReader.getPropertyValue("chromiumPath"));
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("incognito");
		//        chromeOptions.addArguments("--headless");
		//        chromeOptions.addArguments("user-agent=\"" + RandomUserAgent.getRandomUserAgent() + "\"");
		chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
		WebDriver staticWebDriverFromCustomBrowser = new ChromeDriver(chromeOptions);
		wait = new WebDriverWait(staticWebDriverFromCustomBrowser, Duration.ofSeconds(ConfigFileReader.getImplicitlyWait()));
		wait2 = new WebDriverWait(staticWebDriverFromCustomBrowser, Duration.ofSeconds(Long.parseLong(ConfigFileReader.getPropertyValue("simpleWait"))));
		wait3 = new WebDriverWait(staticWebDriverFromCustomBrowser, Duration.ofSeconds(Long.parseLong(ConfigFileReader.getPropertyValue("simpleWait2"))));
		return staticWebDriverFromCustomBrowser;

	}
}
