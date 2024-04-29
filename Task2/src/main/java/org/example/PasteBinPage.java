package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasteBinPage {
    WebDriver driver;
    public PasteBinPage(WebDriver driver){
        this.driver = driver;
    }
    public String writeTextInNewPasteField(String text){
        driver.findElement(By.cssSelector("#postform-text")).sendKeys(text);
        return driver.findElement(By.cssSelector("#postform-text")).getText();
    }
    public void selectSyntaxtHighlighting(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("#select2-postform-format-container")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span/span[1]/span[2]"))).click();
    }
    public void selectPasteExpirationDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.id("select2-postform-expiration-container")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span[2]/span/span[2]/ul/li[3]"))).click();
    }

    public void writeTextInPasteTitle(String text) {
        driver.findElement(By.id("postform-name")).sendKeys(text);
    }
    public void clickOnCreateNewPaste() {
        driver.findElement(By.xpath("//button[@class='btn -big']")).click();
    }
}
