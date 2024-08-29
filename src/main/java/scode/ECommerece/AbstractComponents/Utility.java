package scode.ECommerece.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import scode.ECommerceTesting.pageobjects.CartPage;
import scode.ECommerceTesting.pageobjects.LoginPage;
import scode.ECommerceTesting.pageobjects.OrdersPage;

public class Utility {

	WebDriver driver;
	
	public Utility(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orders;
	
	@FindBy(css = ".fa-sign-out")
	WebElement signoutBtn;

	public void waitForElementToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForWebElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}	
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage gotoCart() {
		cart.click();
		return new CartPage(driver);
	}
	
	public OrdersPage gotoOrdesr() {
		orders.click();
		return new OrdersPage(driver);
	}
	
	public LoginPage signout() {
		signoutBtn.click();
		return new LoginPage(driver);
	}
	
}
