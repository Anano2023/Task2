package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasteListPage {
    WebDriver driver;

    public PasteListPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getNoteMessage() {
        return driver.findElement(By.cssSelector("body > div.wrap > div.container > div.content > div.post-view.js-post-view > div.details > div.info-bar > div")).getText();
    }
    public String getCodeMatchText(){
        return driver.findElement(By.cssSelector("body > div.wrap > div.container > div.content > div.post-view.js-post-view > div.highlighted-code> div > div")).getText();
    }
}
