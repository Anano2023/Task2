package org.example;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeOptions;

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
      Assert.assertTrue(pasteListPage.getCodeMatchText().contains("git config --global user.name  \"New Sheriff in Town\"\n"+
              "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
              "git push origin master --force"));
      driver.quit();
   }


}
