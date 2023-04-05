package org.lisenokacademy.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AppiumUtils {
	
	public AppiumDriverLocalService service;
public Double getFormattedAmount(String amount) {
		
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}

public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
	
	//System.getProperty("user.dir")+"/src/test/java/org/lisenokacademy/testData/eCommerce.json"
	
	//convert json file content to json string
	String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
			ObjectMapper mapper=new ObjectMapper();

	List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){

	});

	return data;

	}

public  AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
	
	service = new AppiumServiceBuilder().withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/appium.js"))
			.withIPAddress(ipAddress).usingPort(port).build();
	return service;
}
	


public void waitForElementToAppear(WebElement elem, AppiumDriver driver) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
     wait.until(ExpectedConditions.attributeContains((elem), "text", "Cart"));
}

public String getScreenshot(String testCaseName, AppiumDriver driver) throws IOException {
	
	File source = driver.getScreenshotAs(OutputType.FILE);
	String destinationFile = System.getProperty("user.dir")+"/reports"+testCaseName+".png";
	FileUtils.copyFile(source, new File(destinationFile));
	return destinationFile;

}

}