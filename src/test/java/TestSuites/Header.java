package TestSuites;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Base;
import Pages.HeaderView;

public class Header extends Base{
	
	HeaderView ha= new HeaderView();
	@BeforeTest
	public void invokeBrowser() {
		logger = report.createTest("Executing Test Cases");

		ha.invokeBrowser();
		reportPass("Browser is Invoked");
	}

	@Test(priority = 1)
	public void testCases() throws Exception {

		ha.openURL();
		ha.login();
		ha.getData();
		reportPass("All steps passed successfuly");
	}

	@AfterTest
	public void closeBrowser() throws IOException {
		reportPass("Browser is closed successfuly");
		ha.endReport();
		ha.closeBrowser();
	}
}
