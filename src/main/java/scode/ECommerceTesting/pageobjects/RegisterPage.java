package scode.ECommerceTesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import scode.ECommerece.AbstractComponents.Utility;

public class RegisterPage extends Utility {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = ".login-title")
	WebElement registerHeader;

	public String getRegisterHeaderText() {
		return registerHeader.getText();
	}
}
