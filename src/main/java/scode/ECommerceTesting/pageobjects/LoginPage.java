package scode.ECommerceTesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import scode.ECommerece.AbstractComponents.Utility;

public class LoginPage extends Utility {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	public WebElement userEmail;

	@FindBy(id = "userPassword")
	public WebElement password;

	@FindBy(id = "login")
	public WebElement submit;

	@FindBy(css = "div[class*='ng-trigger-flyInOut']")
	public WebElement errorToast;

	@FindBy(xpath = "//input[@type='email']/parent::div/div/div")
	WebElement emailError;

	@FindBy(xpath = "//input[@type='password']/parent::div/div/div")
	WebElement passwordError;
	
	@FindBy(css = ".forgot-password-link")
	WebElement forgotPassword;
	
	@FindBy(css = ".login-title")
	WebElement loginHeader;


	public ProductsPage loginApplication(String email, String pass) {
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		return new ProductsPage(driver);
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public String getErrorText() {
		waitForWebElementToAppear(errorToast);
		return errorToast.getText();

	}

	public String getEmailErrorText() {
		return emailError.getText();
	}

	public String getPasswordErrorText() {
		return passwordError.getText();
	}

	public boolean areErrorsPresent(String emailErrorText, String passwordErrorText) {
		
		return emailError.getText().equals(emailErrorText) && passwordError.getText().equals(passwordErrorText);
	}

	public ForgotPasswordPage gotoForgotPassword() {
		forgotPassword.click();
		return new ForgotPasswordPage(driver);
	}

	public String getPasswordUpdateText() {
		waitForWebElementToAppear(errorToast);
		return errorToast.getText();
	}
	
	public String getLoginHeaderText() {
		return loginHeader.getText();
	}

}
