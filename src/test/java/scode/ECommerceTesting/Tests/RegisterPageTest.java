package scode.ECommerceTesting.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import scode.ECommerce.TestComponents.BaseTest;
import scode.ECommerceTesting.pageobjects.LoginPage;
import scode.ECommerceTesting.pageobjects.ProductsPage;
import scode.ECommerceTesting.pageobjects.RegisterPage;

public class RegisterPageTest extends BaseTest {

	@Test()
	public void registerPageVisting() {

		RegisterPage register = login.gotoRegisterPage("button");
//		RegisterPage register = login.gotoRegisterPage("link");
		Assert.assertEquals(register.getRegisterHeaderText(), "Register");
	}

	@Test(dataProvider = "getData")
	public void register(HashMap<String, String> data) {
		RegisterPage register = login.gotoRegisterPage("link");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("email"), data.get("phno"),
				data.get("occupation"), data.get("password"), data.get("confirmpassword"), data.get("gender"), isMajor);
		Assert.assertEquals(register.getRegistrationText(), "Registered Successfully");
		Assert.assertEquals(register.getAccountCreatedText(), "Account Created Successfully");
		LoginPage login = register.gotoLoginPageAfter();
		ProductsPage prod = login.loginApplication(data.get("email"), data.get("password"));
		Assert.assertEquals(prod.getLoginToastText(), "Login Successfully");
		Assert.assertTrue(prod.isSignoutVisible());
	}

	@Test(groups = { "ErrorHandling" })
	public void emptyData() {

		RegisterPage register = login.gotoRegisterPage("link");
		register.register("", "", "", "", "", "", "", "", false);

		Assert.assertEquals(register.getFNameErrorText(), "*First Name is required");
		Assert.assertEquals(register.getEmailErrorText(), "*Email is required");
		Assert.assertEquals(register.getPhNoErrorText(), "*Phone Number is required");
		Assert.assertEquals(register.getPasswordErrorText(), "*Password is required");
		Assert.assertEquals(register.getConfirmPasswordErrorText(), "Confirm Password is required");
		Assert.assertEquals(register.getCheckBoxErrorText(), "*Please check above checkbox");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void phnoWithChars(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("errorhandlingemail"), "123456789f",
				data.get("occupation"), data.get("password"), data.get("confirmpassword"), data.get("gender"), isMajor);
		Assert.assertEquals(register.getPhNoErrorText(), "*only numbers is allowed");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void phnoWithLessthanTen(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("errorhandlingemail"), "12345",
				data.get("occupation"), data.get("password"), data.get("confirmpassword"), data.get("gender"), isMajor);
		Assert.assertEquals(register.getPhNoErrorText(), "*Phone Number must be 10 digit");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void phnoWithMorethanTen(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("errorhandlingemail"), "1234567890123",
				data.get("occupation"), data.get("password"), data.get("confirmpassword"), data.get("gender"), isMajor);
		Assert.assertEquals(register.getPhNoErrorText(), "*Phone Number must be 10 digit");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void passwordMismatch(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("errorhandlingemail"), data.get("phno"),
				data.get("occupation"), data.get("password"), "151Fa04124@45", data.get("gender"), isMajor);
		Assert.assertEquals(register.getConfirmPasswordErrorText(),
				"Password and Confirm Password must match with each other.");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void invalidEmail(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), "S@gmail.c", data.get("phno"), data.get("occupation"),
				data.get("password"), data.get("confirmpassword"), data.get("gender"), isMajor);
		Assert.assertEquals(register.getErrorToastText(), "Enter Valid Email!");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void invalidEmailFormat(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), "S", data.get("phno"), data.get("occupation"),
				data.get("password"), data.get("confirmpassword"), data.get("gender"), isMajor);
		Assert.assertEquals(register.getEmailErrorText(), "*Enter Valid Email");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void emptyLastName(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), "", data.get("errorhandlingemail"), data.get("phno"),
				data.get("occupation"), data.get("password"), data.get("confirmpassword"), data.get("gender"), isMajor);
		Assert.assertEquals(register.getErrorToastText(), "Last Name is required!");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void invalidPhNo(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("errorhandlingemail"), "123456789qdf",
				data.get("occupation"), data.get("password"), data.get("confirmpassword"), data.get("gender"), isMajor);
		Assert.assertEquals(register.getPhNoErrorText(), "*only numbers is allowed");
		Assert.assertEquals(register.getSecondPhNoErrorText(), "*Phone Number must be 10 digit");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" }, dependsOnMethods = { "register" })
	public void duplicateEmail(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("email"), data.get("phno"),
				data.get("occupation"), data.get("password"), data.get("confirmpassword"), data.get("gender"), isMajor);
		Assert.assertEquals(register.getErrorToastText(), "User already exisits with this Email Id!");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void passwordLength(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("errorhandlingemail"), data.get("phno"),
				data.get("occupation"), data.get("passlength"), data.get("passlength"), data.get("gender"), isMajor);
		Assert.assertEquals(register.getErrorToastText(), "Password must be 8 Character Long!");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void numericPassword(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("errorhandlingemail"), data.get("phno"),
				data.get("occupation"), data.get("numericpassword"), data.get("numericpassword"), data.get("gender"),
				isMajor);
		Assert.assertEquals(register.getErrorToastText(),
				"Please enter 1 Special Character, 1 Capital 1, Numeric 1 Small");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void passwordwithNoCaps(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("errorhandlingemail"), data.get("phno"),
				data.get("occupation"), data.get("passwordwithNoCaps"), data.get("passwordwithNoCaps"),
				data.get("gender"), isMajor);
		Assert.assertEquals(register.getErrorToastText(),
				"Please enter 1 Special Character, 1 Capital 1, Numeric 1 Small");

	}

	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void passwordwithNoDigits(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("errorhandlingemail"), data.get("phno"),
				data.get("occupation"), data.get("passwordwithNoDigits"), data.get("passwordwithNoDigits"),
				data.get("gender"), isMajor);
		Assert.assertEquals(register.getErrorToastText(),
				"Please enter 1 Special Character, 1 Capital 1, Numeric 1 Small");

	}
	
	@Test(dataProvider = "getData", groups = { "ErrorHandling" })
	public void passwordwithNoSmall(HashMap<String, String> data) {

		RegisterPage register = login.gotoRegisterPage("button");
		boolean isMajor = data.get("age").equalsIgnoreCase("true");
		register.register(data.get("fname"), data.get("lname"), data.get("errorhandlingemail"), data.get("phno"),
				data.get("occupation"), data.get("passwordwithNoSmall"), data.get("passwordwithNoSmall"),
				data.get("gender"), isMajor);
		Assert.assertEquals(register.getErrorToastText(),
				"Please enter 1 Special Character, 1 Capital 1, Numeric 1 Small");

	}
	
	@Test()
	public void gotoLogin() {
		RegisterPage register = login.gotoRegisterPage("button");
		LoginPage login = register.gotoLoginPage();
		Assert.assertEquals(login.getLoginHeaderText(), "Log in");
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "voshrrtybe1svhruy@gmail.com");
		map.put("errorhandlingemail", "vosegstyhrgeruy@gmail.com");
		map.put("passwordwithNoDigits", "aswS@qwe");
		map.put("passwordwithNoCaps", "12345678s@");
		map.put("passwordwithNoSmall", "12345678S@");
		map.put("numericpassword", "12345678");
		map.put("passlength", "123");
		map.put("password", "151Fa04124@4517");
		map.put("confirmpassword", "151Fa04124@4517");
		map.put("fname", "Santhosh");
		map.put("lname", "Sai");
		map.put("gender", "Male");
		map.put("phno", "9062517617");
		map.put("occupation", "Doctor");
		map.put("fname", "Santhosh");
		map.put("age", "true");

//		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\scode\\ECommerece\\Data\\PurchaseOrder.json");
		return new Object[][] { { map } };
	}
}
