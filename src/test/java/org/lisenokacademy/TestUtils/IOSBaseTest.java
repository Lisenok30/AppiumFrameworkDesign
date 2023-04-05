package org.lisenokacademy.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.lisenokacademy.pageObjects.ios.HomePage;
import org.lisenokacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseTest extends AppiumUtils {

	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homePage;

	@BeforeClass
	public void ConfigureAppium() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/lisenokacademy/resources/data.properties");
		prop.load(fis);
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		service = startAppiumServer(ipAddress, Integer.parseInt(port));
		//service.start();

		// AndroidDriver
		// Appium code -> Appium Server -> Mobile
		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 12 Pro");
		//options.setApp("/Users/lidiautesheva/eclipse-workspace/Appium/src/test/java/resources/UIKitCatalog.app");
		options.setApp(System.getProperty("user.dir")+"/src/test/java/org/lisenokacademy/resources/UIKitCatalog.app");
		//options.setApp("/Users/lidiautesheva/eclipse-workspace/Appium/src/test/java/resources/TestApp 3.app");
		///Users/lidiautesheva/Library/Developer/Xcode/DerivedData/UIKitCatalog-djqzhbxmpupmdcedrcbabrdbdcsz/Build/Products/Debug-iphonesimulator/UIKitCatalog.app
		
		
		// Appium - Webdriver Agent -> IOS Apps.
		options.setPlatformVersion("14.5");
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));
		
		
		
	
		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		homePage = new HomePage(driver);

	}

	
	

	@AfterMethod
	@AfterClass
	public void tearDown() {

		driver.quit();
		service.stop();

	}

}
