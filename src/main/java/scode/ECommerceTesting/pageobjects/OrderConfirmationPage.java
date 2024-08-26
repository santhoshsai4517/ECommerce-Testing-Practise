package scode.ECommerceTesting.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import scode.ECommerece.AbstractComponents.Utility;

public class OrderConfirmationPage extends Utility {

	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(tagName = "h1")
	WebElement header;
	
	@FindBy(css = "label[class='ng-star-inserted']")
	WebElement orderNum;
	
	By toastContainer = By.id("toast-container");

	public boolean confirmOrder(String message) {
		waitForElementToAppear(toastContainer);
		return header.getText().equals(message);
	}
	
	public String orderId() {
		return orderNum.getText();
	}
	
}
