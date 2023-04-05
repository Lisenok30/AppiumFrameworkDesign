package org.lisenokacademy.pageObjects.android;

import java.util.List;

import org.lisenokacademy.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{
	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms;		
	
	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptButton;
	
	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkBox;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement buttonProceed;
	
	
	public List<WebElement>getProductList(){
		return productList;
	}
	
	public double getProductSum() {
		int productCount = productList.size();
		double sum = 0;

		for(int i = 0; i < productCount; i++) {
			
			String amountString = productList.get(i).getText();
			
			Double price = getFormattedAmount(amountString);
			sum = sum + price;
			
		}
		
		return sum;
		
	}
	
	public double getTotalAmountDisplayed() {
		return getFormattedAmount(totalAmount.getText());
		
	}
	
	public void acceptTermsConditions() {
		longPressActions(terms);
		acceptButton.click();
	}
	
	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	
	}

	public void submitOrder() {
		checkBox.click();
		buttonProceed.click();
	}
}
