import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Theme3 {
    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");

        capabilities.setCapability("app","C:\\Users\\FBI\\IdeaProjects\\ApiumTraining\\JavaAppiumAutomation\\apks\\org.wikipedia.apk"); //Дом
        //capabilities.setCapability("app","D:\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");  //Работа


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After

    public void tearDawn(){
        driver.quit();
    }

    @Test

    public void findTitleWithoutTimeOut() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not Find Search Wikipedia",
                5);

        String search_line = "Appium";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Can not Find search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Appium']"),
                "Can not Find Search Wikipedia topic searching by" + search_line,
                5);


                String title_before = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                0
        );

        Assert.assertEquals(
                "Article title not equals the java title",
                "Appium",
                title_before
        );


    }




    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutinSeconds){
        WebElement element = waitForElementPresent(by,error_message,timeOutinSeconds);
        return element.getAttribute(attribute);
    }


//    private String GetAttribute(By by, String attribute, String error_message){
//        WebElement element = ElementPresent(by,error_message);
//        return element.getAttribute(attribute);
//    }
//
//    private WebElement ElementPresent(By by, String error_message){
//
//        return ElementPresent(by,error_message);
//
//    }


}
