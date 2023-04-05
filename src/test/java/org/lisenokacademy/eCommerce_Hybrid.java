package org.lisenokacademy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.lisenokacademy.TestUtils.AndroidBaseTest;
import org.lisenokacademy.pageObjects.android.CartPage;
import org.lisenokacademy.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;




public class eCommerce_Hybrid extends AndroidBaseTest{
	
	@Test(dataProvider = "getData", groups = {"Smoke"})
	public void FillForm(HashMap<String, String> input) throws InterruptedException {
		
		
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		
	
//	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//	    	wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		Double sum = cartPage.getProductSum();
		Double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(sum, displayFormattedSum);
		cartPage.acceptTermsConditions();
		cartPage.submitOrder();
	
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void preSup() {
		
		formPage.setActivity();	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>>data = getJsonData(System.getProperty("user.dir")+"/src/test/java/org/lisenokacademy/testData/eCommerce.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}, {data.get(2)}};
	}

}
