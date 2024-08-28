package scode.ECommerceTesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

	@FindBy(id = "firstName")
	WebElement fnameElement;

	@FindBy(id = "lastName")
	WebElement lnameElement;

	@FindBy(id = "userEmail")
	WebElement emailElement;

	@FindBy(id = "userMobile")
	WebElement phnoElement;

	@FindBy(css = ".custom-select")
	WebElement occupationElement;

	@FindBy(css = "input[value='Male']")
	WebElement maleElement;

	@FindBy(css = "input[value='Female']")
	WebElement femaleElement;

	@FindBy(id = "userPassword")
	WebElement passwordElement;

	@FindBy(id = "confirmPassword")
	WebElement confirmPasswordElement;

	@FindBy(css = "input[type='checkbox']")
	WebElement cboxElement;

	@FindBy(id = "login")
	WebElement registerElement;

	@FindBy(css = ".text-reset")
	WebElement loginElement;

	@FindBy(xpath = "//input[@type='firstName']/parent::div/div/div")
	WebElement fnameError;

	@FindBy(xpath = "//input[@type='email']/parent::div/div/div")
	WebElement emailError;

	@FindBy(xpath = "//input[@type='text']/parent::div/div/div[1]")
	WebElement phnoError;
	
	@FindBy(xpath = "//input[@type='text']/parent::div/div/div[2]")
	WebElement phnoError1;

	@FindBy(xpath = "//input[@id='userPassword']/parent::div/div/div")
	WebElement passwordError;

	@FindBy(xpath = "//input[@id='confirmPassword']/parent::div/div/div")
	WebElement confirmPasswordError;

	@FindBy(xpath = "//input[@type='checkbox']/parent::div/parent::div/child::div[3]/div")
	WebElement checkboxError;

	@FindBy(css = "div[aria-label='Registered Successfully']")
	WebElement successToast;
	
	@FindBy(css = "div[role='alert']")
	WebElement errorToast;

	@FindBy(css = ".headcolor")
	WebElement registerSuccessHeaderElement;

	@FindBy(css = ".text-reset")
	WebElement loginButton;

	public String getRegisterHeaderText() {
		return registerHeader.getText();
	}

	public void register(String fname, String lname, String email, String phno, String occupation, String password,
			String confirmPassword, String gender, boolean isOlder) {
		fnameElement.sendKeys(fname);
		lnameElement.sendKeys(lname);
		emailElement.sendKeys(email);
		phnoElement.sendKeys(phno);
		if (!occupation.equals("")) {
			Select oc = new Select(occupationElement);
			oc.selectByVisibleText(occupation);
		}
		passwordElement.sendKeys(password);
		confirmPasswordElement.sendKeys(confirmPassword);
		if (isOlder)
			cboxElement.click();
		if (gender.equalsIgnoreCase("Male"))
			maleElement.click();
		else if (gender.equalsIgnoreCase("Female"))
			femaleElement.click();
		registerElement.click();

	}

	public String getRegistrationText() {
		waitForWebElementToAppear(successToast);
		return successToast.getText();
	}

	public String getAccountCreatedText() {
		return registerSuccessHeaderElement.getText();
	}

	public LoginPage gotoLoginPage() {
		loginButton.click();
		waitForElementToDisappear(successToast);
		return new LoginPage(driver);
	}

	public String getFNameErrorText() {
		return fnameError.getText();
	}

	public String getEmailErrorText() {
		return emailError.getText();
	}

	public String getPhNoErrorText() {
		return phnoError.getText();
	}
	
	public String getSecondPhNoErrorText() {
		return phnoError1.getText();
	}

	public String getPasswordErrorText() {
		return passwordError.getText();
	}

	public String getConfirmPasswordErrorText() {
		return confirmPasswordError.getText();
	}

	public String getCheckBoxErrorText() {
		return checkboxError.getText();
	}
	
	public String getErrorToastText() {
		return errorToast.getText();
	}

}
