package Utils;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

public class BrowsersUtils {
    public static String getText(WebElement element) {

        return element.getText().trim();
    }

    public static void selectBy(WebElement location, String value, String methodName) {
        Select select = new Select(location);
        switch (methodName) {
            case "text":
                select.selectByVisibleText(value);
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            default:
                System.out.println("Your method name is not correct");
               Assert.assertTrue(false);
        }
    }

    public static String getTitleWithJS(WebDriver driver) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return document.title").toString().trim();
    }

    public static void clickWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    public static void scrollWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static void switchById(WebDriver driver) {
        String mainPageID = driver.getWindowHandle();
        Set<String> allPagesID = driver.getWindowHandles();

        for (String id : allPagesID) {
            if (!id.equals(mainPageID)) {
                driver.switchTo().window(id);
            }//only switch to the last window by ID
        }
    }
    public static void switchByTitle(WebDriver driver, String title){
        Set<String> allPagesId= driver.getWindowHandles();
        for (String id : allPagesId){
            driver.switchTo().window(id);
            if (driver.getTitle().contains(title)){
                break;// stop switching
            }
        }
    }
    public static void getScreenShotTestNG(WebDriver driver, String packageName) {
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String location =System.getProperty("st/test/java"+packageName+"/");
        try {
            FileUtils.copyFile(file,new File(location+System.currentTimeMillis()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void getScreenshotForCucumber(WebDriver driver, Scenario scenario){
        Date currentDate=new Date();
        String screenshotFileName=currentDate.toString()
                .replace(" ","-").replace(":","-");
        if (scenario.isFailed()){
            File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotFile,new File
                        ("src/test/java/screenshot"+screenshotFileName+".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
