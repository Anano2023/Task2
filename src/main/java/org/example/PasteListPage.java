package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;

import java.time.Duration;
import java.util.List;

public class PasteListPage {
    WebDriver driver;
    @FindBy(xpath = "//div[@class='info-top']")
    WebElement titleMatch;
    @FindBy(xpath ="//div[@class='source bash']" )
    private List<WebElement> codeLines;

    public PasteListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getNoteMessage() {
        //Browser page title matches 'Paste Name / Title'
        return titleMatch.getText();}

    public String getCodeMatchText(){

        StringBuilder codeText = new StringBuilder();
        for (WebElement line : codeLines) {
            codeText.append(line.getText()).append("\n");
        }
        return codeText.toString().trim();  // Trim to remove the last newline characte
    }
}