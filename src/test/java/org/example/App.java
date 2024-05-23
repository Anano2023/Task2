package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeOptions;
import org.w3c.dom.Document;

public class App
{
   private WebDriver driver;
   private final String LINK ="https://pastebin.com/";
   PasteBinPage pasteBinPage;
   PasteListPage pasteListPage;

   @BeforeClass
   public void setUp(){
      WebDriverManager.edgedriver().setup();
      EdgeOptions options = new EdgeOptions();
      options.addArguments("start-maximized");

      driver = new EdgeDriver(options);
      driver.get(LINK);

      pasteBinPage = new PasteBinPage(driver);
      pasteListPage = new PasteListPage(driver);
   }
   @Test
   public void createNewPasteText(){
      pasteBinPage.writeTextInNewPasteField("git config --global user.name  \"New Sheriff in Town\"\n"+
              "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
              "git push origin master --force");
      pasteBinPage.selectSyntaxtHighlighting();
      pasteBinPage.selectPasteExpirationDropdown();
      pasteBinPage.writeTextInPasteTitle("how to gain dominance among developers");
      pasteBinPage.clickOnCreateNewPaste();
      Assert.assertTrue(pasteListPage.getNoteMessage().contains("how to gain dominance among developers"));

      Assert.assertTrue(pasteListPage.getCodeMatchText().contains("git config --global user.name  \"New Sheriff in Town\"\n" +
             "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
             "git push origin master --force"));

      /**   Assert text COLOR
       **/
      // Example CSS selector for the styled text within quotes
      WebElement el1 = driver.findElement(By.cssSelector("span.st0"));
      WebElement el2 = driver.findElement(By.cssSelector(".li1:nth-child(2) > .de1 > .st0"));

      // Execute JavaScript to retrieve computed style of the element
      String color1 = (String) ((JavascriptExecutor) driver).executeScript(
                      "return window.getComputedStyle(arguments[0]).color", el1);
      String color2 = (String) ((JavascriptExecutor) driver).executeScript(
              "return window.getComputedStyle(arguments[0]).color", el2);

      // Example expected color (replace with your expected color value)
      String expectedColor = "rgb(255, 0, 0)"; // Red color

      // Example expected text content (replace with your expected text)
      String expectedText1 = "\"New Sheriff in Town\""; // Text within quotes to verify
      String expectedText2 = "\"Legacy code\"";

      // Get the text content of the styled element
      String actualText1 = el1.getText();
      String actualText2 = el2.getText();

      // Assert that the color and text content match the expected values
            Assert.assertEquals(color1, expectedColor);
            Assert.assertEquals(color2, expectedColor);
            Assert.assertEquals(actualText1, expectedText1);
            Assert.assertEquals(actualText2, expectedText2);
   }
   public void tearDown(){
      driver.quit();
   }
}
