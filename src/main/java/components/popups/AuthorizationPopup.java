package components.popups;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizationPopup extends AbsCommon implements IPopup{
    public AuthorizationPopup(WebDriver driver) {
        super(driver);
    }
    private String login = System.getProperty("login");
    private String password = System.getProperty("password");
    @Override
    public void popupShouldBeVisible() {

    }
    @Override
    public void popupShouldNotBeVisible() {

    }
    public void enterEmail() {
        driver.findElement(By.xpath("//div[./input[@name='email']]")).click();
        WebElement mailClick = driver.findElement(By.xpath("//input[@name='email']"));
        waitTools.waitForCondition(ExpectedConditions.visibilityOf(mailClick));
        mailClick.sendKeys(login);
    }
    public void enterPassword() {
        driver.findElement(By.xpath("//div[./input[@type='password']]")).click();
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
    }
    public void enterLoginButton() {
        driver.findElement(By.cssSelector("#__PORTAL__ button")).click();
    }

}
