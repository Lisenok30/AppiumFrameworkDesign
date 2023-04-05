package org.lisenokacademy;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.lisenokacademy.TestUtils.IOSBaseTest;
import org.lisenokacademy.pageObjects.ios.AlertViewsPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class IOSBasics extends IOSBaseTest{
	
	@Test
	public void IOSBasicsTest() {
		
		//Xpath, classname, IOS, iosClassChain, accessibility id
		
		AlertViewsPage alertViewsPage = homePage.selectAlertViews();
		alertViewsPage.fillTextLabel("Hello World");
		String actualMessage = alertViewsPage.getConfirmMessage();
		AssertJUnit.assertEquals(actualMessage, "A message should be a short, complete sentence.");
		alertViewsPage.submitForm();
		
		
	}

}
