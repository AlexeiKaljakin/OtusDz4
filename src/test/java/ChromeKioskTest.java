import driverfactory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.WaitTools;

import java.util.List;

public class ChromeKioskTest {
    private String kioskUrl = System.getProperty("kiosk.url");
    private WebDriver driver;
    private WaitTools waitTools;

    @BeforeEach
    public void init() {
        driver = new WebDriverFactory("--kiosk").create();
        waitTools = new WaitTools(driver);
        driver.get(kioskUrl);
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void kioskChrome() {
        List<WebElement> imageList = driver.findElements(By.cssSelector(".content-overlay"));
        waitTools.waitForCondition(ExpectedConditions.stalenessOf(imageList.get(0)));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView()", imageList.get(0));

        imageList.get(0).click();

        WebElement oneElement = driver.findElement(By.cssSelector(".pp_hoverContainer"));
        waitTools.waitForCondition(ExpectedConditions.stalenessOf(oneElement));
        boolean factResult = driver.findElement(By.cssSelector("div.pp_pic_holder.light_rounded")).isDisplayed();
        Assertions.assertTrue(factResult);
    }

}




