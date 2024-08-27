package scode.ECommerceTesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import scode.ECommerece.AbstractComponents.Utility;

public class ForgotPasswordPage extends Utility {

	WebDriver driver;

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(tagName = "h3")
	WebElement header;

	@FindBy(css = "input[type='email']")
	WebElement emailField;

	@FindBy(id = "userPassword")
	WebElement passwordField;

	@FindBy(id = "confirmPassword")
	WebElement confirmPasswordField;

	@FindBy(css = "button[type='submit']")
	WebElement submitButton;

	@FindBy(xpath = "//input[@type='email']/parent::div/div/div")
	WebElement emailError;

	@FindBy(xpath = "//input[@id='userPassword']/parent::div/div/div")
	WebElement passwordError;

	@FindBy(xpath = "//input[@id='confirmPassword']/parent::div/div/div")
	WebElement confirmPasswordError;

	@FindBy(css = "div[role='alert']")
	WebElement errorToast;
	
	@FindBy(css = "a[href*='login']")
	WebElement loginLink;
	
	@FindBy(css ="a[href*='register']")
	WebElement registerLink;

	public String getHeaderText() {
		return header.getText();
	}

	public boolean isHeaderDisplayed() {
		return header.isDisplayed();
	}

	public LoginPage updatePassword(String email, String password, String confirmPassword) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);
		submitButton.click();
		return new LoginPage(driver);
	}

	public String getEmailErrorText() {
		return emailError.getText();
	}

	public String getPasswordErrorText() {
		return passwordError.getText();
	}

	public String getConfirmPasswordErrorText() {
		return confirmPasswordError.getText();
	}

	public String getErrorToastText() {
		waitForWebElementToAppear(errorToast);
		return errorToast.getText();
	}
	
	public LoginPage gotoLoginPage() {
		loginLink.click();
		return new LoginPage(driver);
	}

	public RegisterPage gotoRegister() {
		registerLink.click();
		return new RegisterPage(driver);
	}

}
