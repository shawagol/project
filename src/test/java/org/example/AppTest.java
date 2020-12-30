package org.example;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.By;



public class AppTest {
    private static Logger logger;
    private static WebDriver driver;
    public static final String PASSWORD = "123zxc321cxz";
    public static final String LOGIN = "user_diary";
    public static final String URL = "https://www.diary.ru";

    @BeforeAll
    public static void setting() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        //      logger = logger.getLogger();

    }

    @AfterAll
    public static void quitDriver() {
        driver.quit();

    }

    @Test
    public void testAuth() {
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement authorization = driver.findElement(By.id("drop-login"));
        authorization.click();
        WebElement inputLogin = driver.findElement(By.id("usrlog2"));
        inputLogin.click();
        inputLogin.sendKeys(LOGIN);
        WebElement inputPass = driver.findElement(By.id("usrpass2"));
        inputPass.click();
        inputPass.sendKeys(PASSWORD);
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"loginform\"]/form/button"));
        submit.click();
        WebElement userMenu = driver.findElement(By.id("drop"));
        userMenu.click();
        WebElement exitUser = driver.findElement(By.xpath("//*[@id=\"body\"]/div[2]/div/ul[2]/li[10]/div/div/div[1]/a[2]"));
        exitUser.click();


    }
    @Test
    public  void newCreateDiary() {
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement authorization = driver.findElement(By.id("drop-login"));
        authorization.click();
        WebElement inputLogin = driver.findElement(By.id("usrlog2"));
        inputLogin.click();
        inputLogin.sendKeys(LOGIN);

        WebElement inputPass = driver.findElement(By.id("usrpass2"));
        inputPass.click();
        inputPass.sendKeys(PASSWORD);
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"loginform\"]/form/button"));
        submit.click();
        WebElement newEntry = driver.findElement(By.xpath("//*[@id=\"body\"]/div[2]/div/ul[2]/li[2]/a"));
        newEntry.click();

        WebElement checkLink = driver.findElement(By.linkText("user_diary"));
        new WebDriverWait(driver, 4).until(ExpectedConditions.visibilityOf(checkLink));

        WebElement title = driver.findElement(By.id("postTitle"));
        title.click();
        title.sendKeys("Запись");

        WebElement message = driver.findElement(By.id("message"));
        message.click();
        message.sendKeys("Тестовая запись");

        WebElement buttonNewEntry = driver.findElement(By.id("draft_save"));
        buttonNewEntry.click();
        WebElement draft = driver.findElement(By.xpath("//*[@id=\"myDraftLink\"]/a"));
        draft.click();

        WebElement deleteDraft = driver.findElement(By.cssSelector("img[title='Удалить']"));
        deleteDraft.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement deleteYes = driver.findElement(By.cssSelector("input[value='Да']"));
        deleteYes.click();
    }
}