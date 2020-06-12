package com.cg.seleniumTests;
import com.cg.seleniumTests.ReadValue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PassFailPercentage{
	static String url = "http://34.221.243.81:9000/petclinic-Dev/owners/find.html";
	private static int errorCode = 0;
	private static int fail;
	private static int totalCases;
	private static int pass;
	//private static int acceptable = 60;
	private static float passPercentage;
	private static float failPercentage;
	
	public static void main(String[] args) throws InterruptedException {
		ReadValue readValue = new ReadValue();
		Double acceptableValue = (Double) readValue.ReadValue(1, 1);
		System.out.println(acceptableValue);
		Double acceptable = acceptableValue;
// calling the test cases in the main method
		testCaseOne();
		testCaseTwo();
		testCaseThree();
		System.out.println("total number of test cases =" + totalCases);
		System.out.println("number of passed test cases =" + pass);
		System.out.println("number of failed test cases =" + fail);
		
		int difference = totalCases-fail;
		System.out.println("difference=" + difference);
		
//calculating the pass percentage
		passPercentage = (difference * 100/totalCases);
		System.out.println("Pass percentage = " + passPercentage);
//calculating the fail percenatge		
		failPercentage = (fail * 100/totalCases);
		System.out.println("Fail percentage = " + failPercentage);
		
		if(passPercentage >= acceptable) {
			System.out.println("exit status is 0");
			System.exit(0);
		}else {
			System.out.println("exit status is 2");
			System.exit(2);
		}
		
	}
	public static void testCaseOne() throws InterruptedException {
		totalCases++;
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get(url );
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@class='btn btn-default']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='firstName']")).clear();
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Thomas");
		driver.findElement(By.xpath("//input[@id='lastName']")).clear();
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Jefferson");
		driver.findElement(By.xpath("//input[@id='address']")).clear();
		driver.findElement(By.xpath("//input[@id='address']")).sendKeys("456 Clark Street");
		driver.findElement(By.xpath("//input[@id='city']")).clear();
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Chicago");
		driver.findElement(By.xpath("//input[@id='telephone']")).clear();
		driver.findElement(By.xpath("//input[@id='telephone']")).sendKeys("3452987654");
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		System.out.println("user addition successful !");
		driver.navigate().to(url);
		driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
		WebElement Owner = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]"));
		String outputValue = Owner.getText();
		if(outputValue.contains("Stacy Jefferson")) {
			System.out.println("Owner Found");
			pass++;
		}else {
			System.out.println("Owner not found");
			errorCode = 2;
			//System.out.println(errorCode);
			fail++;
		}
		driver.quit();
	}
	
	public static void testCaseTwo() {
		totalCases++;
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		//if (driver.getPageSource().contains("Last name")) {
			if (driver.getPageSource().contains("Yashaswini")) {
			System.out.println("TEXT IS PRESENT ON THE WEB PAGE");
			pass++;
			}
			else {
			System.out.println("ERROR WHILE FINDING THE TEXT - Failed");
			errorCode = 2;
			fail++;
			}
		driver.quit();
	}
	
	private static void testCaseThree() throws InterruptedException {
		totalCases++;
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.findElement(By.xpath("//span[contains(text(),'Find owners')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Find Owner')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Carlos Estaban')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Edit Owner')]")).click();
		js.executeScript("document.getElementById('city').value='London'");
		driver.findElement(By.xpath("//button[contains(text(),'Update Owner')]")).click();
		WebElement city = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[1]"));
				                String outputValue1 = city.getText();
				                if (outputValue1.contentEquals("London")) {
				                        System.out.println("Test Passed!");
				                        pass++;
				                } else {
				                        System.out.println("Test Failed");
				                        errorCode = 2;
				                        fail++;
				                }
		driver.quit();
	}
	
}
