package lambdaTestSelenium101;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class TestCase {
	//WebDriver driver;
	RemoteWebDriver driver;
	
	@Parameters({"browsername","testname","platformName"})
	@BeforeTest
	public void setup(String browser, String name, String platform) {
		String username = System.getenv("LT_USERNAME");
		String accessKey = System.getenv("LT_ACCESS_KEY");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName",browser);
		capabilities.setCapability("browserVersion", "dev");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", username);
		ltOptions.put("accessKey", accessKey);
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("platformName", platform);
		ltOptions.put("network", true);
		ltOptions.put("build", "LambdaTest");
		ltOptions.put("project", "Selenium101");
		ltOptions.put("name", name);
		ltOptions.put("accessibility", true);
		capabilities.setCapability("LT:Options", ltOptions);
		String url = "https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub";
		try {
			driver = new RemoteWebDriver(new URL(url),capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void TestScenario1() throws InterruptedException {
		driver.get("https://www.lambdatest.com/selenium-playground");
		
		WebElement simpleFormDemotext= driver.findElement(By.linkText("Simple Form Demo"));
		simpleFormDemotext.click();
		String url = driver.getCurrentUrl();
		boolean verifyURL = url.contains("simple-form-demo");
		assertTrue(verifyURL,"url does not contain simple-form-demo");
		
		String str = "WelcometoLambdaTest";
		WebElement textBox = driver.findElement(By.xpath("//input[@placeholder='Please enter your Message']"));
		textBox.sendKeys(str);
		
		WebElement checkValuebutton = driver.findElement(By.xpath("//button[text()='Get Checked Value']"));
		checkValuebutton.click();
		
		WebElement outputtext = driver.findElement(By.id("message"));
		assertEquals(str,outputtext.getText(),"Not Matched");
	}
	
	@Test
	public void TestScenario2() throws InterruptedException {
		driver.get("https://www.lambdatest.com/selenium-playground");
		
		WebElement DragandDropSliderstext= driver.findElement(By.linkText("Drag & Drop Sliders"));
		DragandDropSliderstext.click();
		
		WebElement slider = driver.findElement(By.xpath("//h4[text()=' Default value 15']/../div/input"));
	    Actions actions = new Actions(driver);
		actions.dragAndDropBy(slider,215, 0).build().perform();

		WebElement slideroutput = driver.findElement(By.xpath("//h4[text()=' Default value 15']/../div/output"));
		assertEquals(slideroutput.getText(),"95","Not moved to 95");
		
	}
	
	@Test
	public void TestScenario3() throws InterruptedException {
		driver.get("https://www.lambdatest.com/selenium-playground");
		
		WebElement InputFormSubmit= driver.findElement(By.linkText("Input Form Submit"));
		InputFormSubmit.click();
		
		WebElement SubmitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
		SubmitButton.click();
		
		WebElement NameField = driver.findElement(By.id("name"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String validationMessage = (String) js.executeScript("return arguments[0].validationMessage;", NameField);
        Assert.assertTrue(validationMessage.equals("Please fill out this field.")?true:false);
        
        NameField.sendKeys("xxxxxx");
        
        WebElement EMail = driver.findElement(By.id("inputEmail4"));
        EMail.sendKeys("xxxx@gmail.com");
        
        WebElement PasswordFiled = driver.findElement(By.name("password"));
        PasswordFiled.sendKeys("1234assdf");
		
        WebElement CompanyField = driver.findElement(By.name("company"));
        CompanyField.sendKeys("ABC");
        
        WebElement WebsiteField = driver.findElement(By.name("website"));
        WebsiteField.sendKeys("abc.in");
        
        WebElement CountryDropDown = driver.findElement(By.name("country"));
        Select select = new Select(CountryDropDown);
        select.selectByValue("US");
        
        WebElement CityField = driver.findElement(By.name("city"));
        CityField.sendKeys("LA");
        
        WebElement Add1Field = driver.findElement(By.name("address_line1"));
        Add1Field.sendKeys("nswaio");
        
        WebElement Add2Field = driver.findElement(By.name("address_line2"));
        Add2Field.sendKeys("fwaliejoi");
        
        WebElement StateField = driver.findElement(By.xpath("//input[@placeholder='State']"));
        StateField.sendKeys("fwaliejoi");
    
        WebElement ZipCodeField = driver.findElement(By.xpath("//input[@placeholder='Zip code']"));
        ZipCodeField.sendKeys("648484");
        
        SubmitButton.click();
        
        WebElement ResultMessage = driver.findElement(By.xpath("//p[@class='success-msg hidden']"));
        assertEquals(ResultMessage.getText(),"Thanks for contacting us, we will get back to you shortly.","Not Matching");
        
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
