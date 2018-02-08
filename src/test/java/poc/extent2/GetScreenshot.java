package poc.extent2;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class GetScreenshot {

    public static String capture(WebDriver driver, String screenShotName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePathAndName = System.getProperty("user.dir")+"/ErrorScreenshot/"+screenShotName+".png";
        File targetFile = new File(targetFilePathAndName);
        FileUtils.copyFile(sourceFile, targetFile);

        return targetFilePathAndName;
    }


}
