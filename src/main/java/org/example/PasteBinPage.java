package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PasteBinPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(css ="#postform-text")
    private WebElement pasteTextField;
    @FindBy(id="postform-name")
    WebElement inputText;
    @FindBy(xpath = "//button[@class='btn -big']")
    WebElement clickOnButton;

    private static final String PASTE_EXPIRATION_VALUE = "select2-postform-expiration-container";
    private static final String PASTE_SYNTAX_HIGHLIGHTING = "#select2-postform-format-container";
    private static final String SYNTAX_OPTIONS_DROPDOWN = "/html/body/span/span[1]/span[2]";
    private static final String PASTE_OPTIONS_VALUE = "/html/body/span[2]/span/span[2]/ul/li[3]";

    public PasteBinPage(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    public String writeTextInNewPasteField(String text){
        pasteTextField.sendKeys(text);
        return pasteTextField.getText();
    }
    public void selectSyntaxtHighlighting(String value){
        //Syntax Highlighting: "Bash"
        WebElement selectHighlightingDropDown = driver.findElement(By.cssSelector(PASTE_SYNTAX_HIGHLIGHTING));
        selectHighlightingDropDown.click();
        String xpath = String.format(SYNTAX_OPTIONS_DROPDOWN, value);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        element.click();
    }

    public void selectPasteExpirationDropdown(String value) {
        //Paste Expiration: "10 Minutes"
        WebElement selectExpirationDropDown = driver.findElement(By.id(PASTE_EXPIRATION_VALUE));
        selectExpirationDropDown.click();
        String xpath = String.format(PASTE_OPTIONS_VALUE, value);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        element.click();
    }

    public void writeTextInPasteTitle(String text) {
        // Paste Name / Title: "how to gain dominance among developers"
       inputText.sendKeys(text);
    }
    public void clickOnCreateNewPaste() {
        clickOnButton.click();
    }
}
