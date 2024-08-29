package scode.ECommerceTesting.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import scode.ECommerece.AbstractComponents.Utility;

public class ProductsPage extends Utility {

	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".card")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement animation;

	@FindBy(id = "toast-container")
	WebElement loginToast;

	@FindBy(css = ".fa-sign-out")
	WebElement signOut;

	@FindBy(css = "label[class*='blink_me']")
	WebElement blinkTextElm;

	@FindBy(id = "res")
	WebElement showingResultsText;

	@FindBy(xpath = "(//input[@placeholder='search'])[2]")
	WebElement searchElm;

	@FindBy(css = "div[aria-label='No Products Found']")
	WebElement noProductsToast;
	
	@FindBy(css = "form div[class='py-2 ml-3'] div:nth-child(3) input")
	WebElement maleFilter;

	@FindBy(css = "form div[class='py-2 ml-3'] div:nth-child(4) input")
	WebElement femaleFilter;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.id("toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		return getProductList().stream().filter(p -> p.findElement(By.tagName("b")).getText().equals(productName))
				.findFirst().orElse(null);
	}

	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToDisappear(animation);
	}

	public String getLoginToastText() {
		waitForElementToAppear(toastContainer);
		return loginToast.getText();
	}

	public boolean isSignoutVisible() {
		waitForElementToDisappear(driver.findElement(toastContainer));
		return signOut.isDisplayed();
	}

	public String getBlinkText() {
		return blinkTextElm.getText();
	}

	public int getProductscount() throws InterruptedException {
		Thread.sleep(1000);
		return driver.findElements(By.cssSelector(".card")).size();
	}

	public String getShowingResultsText() throws InterruptedException {
		Thread.sleep(1000);
		return showingResultsText.getText();
	}

	public void searchFor(String prodName) {
		searchElm.clear();
		searchElm.sendKeys(prodName, Keys.RETURN);
	}

	public String getNoProductsToastText() {
		waitForWebElementToAppear(noProductsToast);
		return noProductsToast.getText();
	}

	public void clear() {
		Actions action = new Actions(driver);
		action.click(searchElm);
		action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).sendKeys(Keys.RETURN).build().perform();

	}


	public void filterByGender(ArrayList<String> filter) {
		// TODO Auto-generated method stub
		if(filter.contains("Male"))
			maleFilter.click();
		if(filter.contains("Female"))
			femaleFilter.click();
		
	}
	

}
