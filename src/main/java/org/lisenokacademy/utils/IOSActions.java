package org.lisenokacademy.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends AppiumUtils {
	
	IOSDriver driver;
	public IOSActions(IOSDriver driver) {
		//super(driver);
		this.driver=driver;
		
	}

	public void longPressActions(WebElement elem) {

		Map<String, Object>params = new HashMap <>();
		params.put("element", ((RemoteWebElement)elem).getId());
		params.put("duration", 5);
		driver.executeScript("mobile:touchAndHold", params);
	}

	public void scrollToWebElement(WebElement elem) {

		Map<String, Object>params = new HashMap <>();
		params.put("element", ((RemoteWebElement)elem).getId());
		params.put("direction", "down");
		driver.executeScript("mobile:scroll", params);
	}
	
	public void swipeAction(WebElement elem, String direction) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("direction","left");	
		driver.executeScript("mobile: swipe", params);
		
	}
	
	
}
