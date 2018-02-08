package poc.extent2;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;

public class GenerateExtentReport {

    ExtentReports extentReports;
    ExtentTest extentTest;

    @BeforeTest
    public void startReport(){
        System.out.println("BeforeTest - startReport - Running");
        System.out.println(" - - - - - - - - - - - - - - - - - - -");
        extentReports = new ExtentReports(System.getProperty("user.dir")+"/test-output/MyOwnReport.html", true);
        extentReports.addSystemInfo("Selenium Version", "2.46");
        extentReports.addSystemInfo("Environment", "Development")
                .addSystemInfo("HostName", "Hammad");
        extentReports.loadConfig(new File(System.getProperty("user.dir")+"/src/test/resources/extent-config.xml"));
    }

    @Test
    public void demoReportPass(){
        extentTest = extentReports.startTest("demoReportPass");
        Assert.assertTrue(true);
        extentTest.log(LogStatus.PASS, "Assert Pass as condition is True");
    }


    @Test
    public void demoReportFail(){
        extentTest = extentReports.startTest("demoReportFail");
        Assert.assertTrue(false);
        extentTest.log(LogStatus.FAIL, "Assert Fail as condition is False");
    }

    @AfterMethod
    public void getResult(ITestResult result){
        System.out.println("AfterMethod - getResult - Running");
        System.out.println(" = = = = = = = = = = = = = = = = = = = ");

        if (result.getStatus() == ITestResult.FAILURE){
            extentTest.log(LogStatus.FAIL, result.getThrowable());
        }
        extentReports.endTest(extentTest);
    }

    @AfterTest
    public void endReport(){
        System.out.println("AfterTest - endReport - Running");
        System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");


        extentReports.flush();
        extentReports.close();
    }



}
