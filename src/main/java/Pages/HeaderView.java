package Pages;

import java.io.FileInputStream;


import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Base.Base;

public class HeaderView extends Base{
	
	By email=By.xpath("//input[@type='email']");
	By next=By.xpath("//input[@type='submit']");
	By pass=By.name("passwd");
	By acc=By.id("user-name");
	By yes=By.xpath("//input[@value='Yes']");
	By culture=By.xpath("//button[contains(text(),'Culture')]");
	By h=By.xpath("//div[@class='workspaces__title']");
	By c=By.xpath("//span[@class='workspaces__count']");
	By prac=By.xpath("//button[contains(text(),'Practices & Markets')]");
	By corp=By.xpath("//button[contains(text(),'Corporate Functions')]");
	By hr=By.xpath("//button[contains(text(),'Human Resources')]");
	
	
	public void login() {
		logger = report.createTest("Login into Becognizant.");
		try {
		wait(20,email);
		driver.findElement(email).sendKeys(prop.getProperty("email"));
		driver.findElement(next).click();
		wait(20,pass);
		driver.findElement(pass).sendKeys(prop.getProperty("password"));
		driver.findElement(next).click();
		Thread.sleep(1000);
		reportPass("Email and Password Verified sucessfully");
		wait(120,yes);
		driver.findElement(yes).click();
		//Verify Title
		if (driver.getTitle().contains("Be.Cognizant"))
			// Pass
			System.out.println("Page title contains Be.Cognizant");
		else
			// Fail
			System.out.println("Page title doesn't contains Be.Cognizant");
		String name=driver.findElement(acc).getText();
		System.out.println("The name for the Acoount is: "+name);
		Screenshot("Account");
		reportPass("Be.Cognizant Page is reached sucessfully");
		
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	public void getData() throws Exception {
		logger = report.createTest("Get the values under each header when called.");
		try {
		FileInputStream file=new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\Data.xlsx");
		workbook=new XSSFWorkbook(file);
		sh=workbook.getSheet("Data");
		String testData=String.valueOf(sh.getRow(1).getCell(4));
		if(testData.matches("Culture")) {
			wait(20,culture);
			driver.findElement(culture).click();
			Screenshot("Culture");
			List<WebElement> header = driver.findElements(h);
			List<WebElement> counts = driver.findElements(c);
			for (int i = 0; i <3; i++) {
				System.out.println("************************************************");
				System.out.println("      "+header.get(i).getText()+" - "+counts.get(i).getText());
				System.out.println("************************************************");
				String xpath1="//a[@data-unily-workspace-name='"+header.get(i).getText()+"']";
				List<WebElement> items = driver.findElements(By.xpath(xpath1));
				for (int j = 0; j <items.size(); j++) {
					System.out.println(items.get(j).getAttribute("data-unily-target-title"));
				}
				System.out.println("\n");
				System.out.println("No of items: "+items.size());
			}
			
			
		} else if (testData.matches("Practice")) {
			wait(20,prac);
			driver.findElement(prac).click();
			Screenshot("Practice");
			Thread.sleep(5000);
			List<WebElement> header = driver.findElements(h);
			List<WebElement> counts = driver.findElements(c);
			for (int i = 3; i <6; i++) {
				System.out.println("************************************************");
				System.out.println("      "+header.get(i).getText()+" - "+counts.get(i).getText());
				System.out.println("************************************************");
				String xpath1="//a[@data-unily-workspace-name='"+header.get(i).getText()+"']";
				List<WebElement> items = driver.findElements(By.xpath(xpath1));
				for (int j = 0; j <items.size(); j++) {
					System.out.println(items.get(j).getAttribute("data-unily-target-title"));
				}
				System.out.println("\n");
				System.out.println("No of items: "+items.size());
			}
			
			
		} else if (testData.matches("Corporate")) {
			wait(20,corp);
			driver.findElement(corp).click();
			Screenshot("Corporate");
			Thread.sleep(5000);
			List<WebElement> header = driver.findElements(h);
			List<WebElement> counts = driver.findElements(c);
			for (int i = 6; i <11; i++) {
				System.out.println("************************************************");
				System.out.println("      "+header.get(i).getText()+" - "+counts.get(i).getText());
				System.out.println("************************************************");
				String xpath1="//a[@data-unily-workspace-name='"+header.get(i).getText()+"']";
				List<WebElement> items = driver.findElements(By.xpath(xpath1));
				for (int j = 0; j <items.size(); j++) {
					System.out.println(items.get(j).getAttribute("data-unily-target-title"));
				}
				System.out.println("\n");
				System.out.println("No of items: "+items.size());
			}
			
		} else if (testData.matches("HR")) {
			wait(20,hr);
			driver.findElement(hr).click();
			Screenshot("HR");
			Thread.sleep(5000);
			List<WebElement> header = driver.findElements(h);
			List<WebElement> counts = driver.findElements(c);
			for (int i = 11; i <15; i++) {
				System.out.println("************************************************");
				System.out.println("      "+header.get(i).getText()+" - "+counts.get(i).getText());
				System.out.println("************************************************");
				String xpath1="//a[@data-unily-workspace-name='"+header.get(i).getText()+"']";
				List<WebElement> items = driver.findElements(By.xpath(xpath1));
				for (int j = 0; j <items.size(); j++) {
					System.out.println(items.get(j).getAttribute("data-unily-target-title"));
				}
				System.out.println("\n");
				System.out.println("No of items: "+items.size());
			}
			
		} 
		reportPass("All items under header is obtained successfully");
		
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}
	
}
