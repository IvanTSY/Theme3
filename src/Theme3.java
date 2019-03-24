import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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

    public void saveFirstArticleToMyList(){

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not Find Search Wikipedia",
                15);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "java",
                "Can not Find search input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can not Find Search Wikipedia",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not Find Article title",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find menu",
                5
        );
//добавляем в список 1й раз
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Can not find element menu 'add'",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Con not find 'Got it'btn",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "can not find to clear text field",
                5
        );
        String name_of_folder = "Learning Programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "can not find input text field",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Can not find element menu 'add'",
                5
        );
//вернулись на главный экран
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find close button",
                5
        );
//нажимаем поиск
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Can not Find Search Wikipedia",
                15);

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "appium",
                "Can not Find search input appium",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Can not Find Search Wikipedia in search appium",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Can not Find Article title",
                5);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find menu in second search",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Can not find element menu 'add'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Can not find element menu 'add'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='"+name_of_folder+"']"),
                "Can not add , by click on element'"+ name_of_folder +"'",
                5);


        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find close button in second search appium",
                5
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not invisible close button in second search appium",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can not find navigation btn to my list",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@text = '"+name_of_folder+"']"),
                "Can not find creatin folder",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can not Find Article Java title in MyList ",
                5);

        waitForElementPresent(
                By.xpath("//*[@text='Appium']"),
                "Can not Find Article Appium title in MyList",
                5);

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Can not find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Article is not delete",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Appium']"),
                "Can not Find Article Appium title in MyList",
                5);

        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/view_page_title_text'][@text='Appium']"),
                "Can not Find Article Appium title in MyList",
                5);

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "Wrong article or not find appium article in state",
                "Appium",
                article_title
        );






    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    //перегрузка метода (если таймаут не обязательно увеличивать , стандарт - 5сек)
    private WebElement waitForElementPresent(By by, String error_message){

        return waitForElementPresent(by,error_message,5);

    }
    //упрощаем написание тестов\ действие клик
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.click();
        return element;
    }
    //отправляем текст(вводим)
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    //Ожидание отсутствия элемента

    private boolean waitForElementNotPresent (By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    //Отчистка элемента
    private WebElement waitForElementAndClear (By by, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.clear();
        return element;
    }

    protected  void swipeElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }







}
