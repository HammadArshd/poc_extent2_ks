package poc.extent2;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CapturingScreenshot {

    ExtentReports extentReports;
    ExtentTest extentTest;
    WebDriver webDriver;

    @BeforeTest
    public void init(){
        extentReports = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentScreenshot.html", true);
    }

    @Test
    public void captureScreenshot(){
        extentTest = extentReports.startTest("captureScreenshot");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("http://kipslms.qaserver/");
        String pageTitle = webDriver.getTitle();
        Assert.assertEquals("xHome Page | KIPS LMS", pageTitle);
        extentTest.log(LogStatus.PASS, "test Passed titles are equal");
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE){
            String screenShotPath = GetScreenshot.capture(webDriver, "screenShotForExtentReport");
            extentTest.log(LogStatus.FAIL, result.getThrowable());
            extentTest.log(LogStatus.FAIL, "Screenshot Below: " + extentTest.addScreenCapture(screenShotPath));
        }
        extentReports.endTest(extentTest);
    }

    @AfterTest
    public void endReport(){
        extentReports.flush();
        extentReports.close();
    }


}
