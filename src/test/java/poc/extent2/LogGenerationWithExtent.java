package poc.extent2;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class LogGenerationWithExtent {

    ExtentReports extentReports;
    ExtentTest extentTest;

    @BeforeTest
    public void startReport(){
        System.out.println("BeforeTest - startReport - Running");
        System.out.println(" - - - - - - - - - - - - - - - - - - -");
        extentReports = new ExtentReports(System.getProperty("user.dir")+"/test-output/Loggeneration.html", true);
    }

    @Test
    public void generateExtentLogs(){
        extentTest = extentReports.startTest("generateExtentLogs");
        extentTest.log(LogStatus.INFO, "Starting the report log generation.");
        extentTest.log(LogStatus.INFO, "I am in actual test method.");
        extentTest.log(LogStatus.INFO, "Here we will write the actual test case logic.");
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        System.out.println("AfterTest - endReport - Running");
        System.out.println(" ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~");

        extentTest.log(LogStatus.INFO, "Ending the test.");
        extentTest.log(LogStatus.INFO, "Flush will push data to report.");
        extentTest.log(LogStatus.INFO, "Close will clear all the resources of extent object.");
        extentReports.endTest(extentTest);


        extentReports.flush();

        extentReports.close();
    }
}
