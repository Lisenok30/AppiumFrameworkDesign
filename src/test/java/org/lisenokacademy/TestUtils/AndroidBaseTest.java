package org.lisenokacademy.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.lisenokacademy.pageObjects.android.FormPage;
import org.lisenokacademy.utils.AppiumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends AppiumUtils {

	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/lisenokacademy/resources/data.properties");
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		prop.load(fis);
		//String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		
		service = startAppiumServer(ipAddress, Integer.parseInt(port));
		//service.start();

		// AndroidDriver
		// Appium code -> Appium Server -> Mobile
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 6 Pro");
		options.setChromedriverExecutable("/Users/lidiautesheva/Downloads/chromedriver");
		//options.setApp("/Users/lidiautesheva/eclipse-workspace/Appium/src/test/java/resources/ApiDemos-debug.apk");
		//options.setApp("/Users/lidiautesheva/eclipse-workspace/Appium/src/test/java/resources/General-Store.apk");
		options.setApp(System.getProperty("user.dir")+"/src/test/java/org/lisenokacademy/resources/General-Store.apk");

		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);

	}
	
	public void longPressActions(WebElement elem) {

		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) elem).getId(),"duration",2000
				));


	}

	public void scrollToEndAction() {

		
		boolean canScrollMore;
		do {

			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					"left", 100, "top", 100, "width", 200, "height", 200,
					"direction", "down",
					"percent", 3.0

					));

		}while(canScrollMore);
	}
	
	public void swipeAction(WebElement elem, String direction) {
		
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)elem).getId(), 
		    "direction", "left",
		    "percent", 0.75
		));
		
	}
	
	public void dragDropAction(WebElement elem) {

		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
		    "elementId", ((RemoteWebElement) elem).getId(),
		    "endX", 842,
		    "endY", 736
		));
	}
	
	public void scrollToText(String text) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
	}
	
	public Double getFormattedAmount(String amount) {
		
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}


	@AfterClass(alwaysRun = true)
	public void tearDown() {

		driver.quit();
		service.stop();

	}

}
