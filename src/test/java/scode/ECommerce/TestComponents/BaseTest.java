package scode.ECommerce.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import scode.ECommerceTesting.pageobjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage login;

	public WebDriver initializeDriver() throws FileNotFoundException, IOException {

		Properties prop = new Properties();
		prop.load(new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\scode\\ECommerce\\resources\\GolbalData.properties"));

		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("Browser");

		if (browser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (browser.contains("headless"))
				options.addArguments("headless");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1920, 1080));
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws FileNotFoundException, IOException {
		driver = initializeDriver();
		login = new LoginPage(driver);
		login.goTo();
		return login;
	}

	@AfterMethod(alwaysRun = true)
	public void after() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "//reports//" + testcaseName + ".png"));
		return System.getProperty("user.dir") + "//reports//" + testcaseName + ".png";
	}

}
