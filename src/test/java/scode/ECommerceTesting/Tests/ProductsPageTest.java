package scode.ECommerceTesting.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import scode.ECommerce.TestComponents.BaseTest;
import scode.ECommerceTesting.pageobjects.LoginPage;
import scode.ECommerceTesting.pageobjects.ProductsPage;

public class ProductsPageTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void logout(HashMap<String, String> data) {

		ProductsPage prod = login.loginApplication(data.get("email"), data.get("password"));
		Assert.assertTrue(prod.isSignoutVisible());
		LoginPage login = prod.signout();
		Assert.assertEquals(login.getLoginHeaderText(), "Log in");
		Assert.assertEquals(login.getLogoutText(), "Logout Successfully");

	}

	@Test(dataProvider = "getData")
	public void validateBlinkText(HashMap<String, String> data) {

		ProductsPage prod = login.loginApplication(data.get("email"), data.get("password"));
		Assert.assertTrue(prod.isSignoutVisible());
		Assert.assertEquals(prod.getBlinkText(), "User can only see maximum 9 products on a page");

	}

	@Test(dataProvider = "getData")
	public void validateProducts(HashMap<String, String> data) throws InterruptedException {

		ProductsPage prod = login.loginApplication(data.get("email"), data.get("password"));
		Assert.assertTrue(prod.isSignoutVisible());
		Assert.assertEquals(prod.getProductscount(), 3);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));

	}

	@Test(dataProvider = "getData")
	public void searchForProduct(HashMap<String, String> data) throws InterruptedException {

		ProductsPage prod = login.loginApplication(data.get("email"), data.get("password"));
		Assert.assertTrue(prod.isSignoutVisible());
		Assert.assertEquals(prod.getProductscount(), 3);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));

		prod.searchFor(data.get("prodName1"));
		Assert.assertEquals(prod.getProductscount(), 1);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));

		prod.searchFor(data.get("prodName2"));
		Assert.assertEquals(prod.getProductscount(), 1);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));

		prod.searchFor(data.get("prodName3"));
		Assert.assertEquals(prod.getProductscount(), 1);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));

		prod.searchFor(data.get("prodName4"));
		Assert.assertTrue(prod.getShowingResultsText().contains("0"));
		Assert.assertEquals(prod.getNoProductsToastText(), "No Products Found");

		prod.searchFor("ZA");
		Assert.assertEquals(prod.getProductscount(), 1);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));

		prod.searchFor("IPHONE");
		Assert.assertEquals(prod.getProductscount(), 1);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));

		prod.clear();
		Assert.assertEquals(prod.getProductscount(), 3);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));

	}

	@Test(dataProvider = "getData")
	public void filterCategories(HashMap<String, String> data) throws InterruptedException {

		ProductsPage prod = login.loginApplication(data.get("email"), data.get("password"));
		Assert.assertTrue(prod.isSignoutVisible());
		Assert.assertEquals(prod.getProductscount(), 3);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));
		
		ArrayList<String> filter = new ArrayList<String>();
		filter.add("Male");
		filter.add("Female");

		prod.filterByGender(filter);
		Assert.assertEquals(prod.getProductscount(), 3);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));
		prod.filterByGender(filter);

		filter.clear();
		filter.add("Male");

		prod.filterByGender(filter);
		Assert.assertEquals(prod.getProductscount(), 2);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));
		prod.filterByGender(filter);

		filter.clear();
		filter.add("Female");

		prod.filterByGender(filter);
		Assert.assertEquals(prod.getProductscount(), 1);
		Assert.assertTrue(prod.getShowingResultsText().contains(String.valueOf(prod.getProductscount())));
		prod.filterByGender(filter);

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "santhoshsai4517@gmail.com");
		map.put("password", "151Fa04124@4517");
		map.put("prodName1", "ZARA COAT 3");
		map.put("prodName2", "ADIDAS ORIGINAL");
		map.put("prodName3", "IPHONE 13 PRO");
		map.put("prodName4", "PIXEL 9");

//		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\scode\\ECommerece\\Data\\PurchaseOrder.json");
		return new Object[][] { { map } };
	}
}
