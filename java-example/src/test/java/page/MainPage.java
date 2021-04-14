package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private By hdr = By.xpath("//h1");
    private By userName = By.className("pl-10");
    private By templateNav = By.xpath("//*[contains(text(), 'Шаблоны опросов')]");

    public MainPage(WebDriver driver) { this.driver = driver; }

    public String getUserName () {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.textToBePresentInElement(driver.findElement(hdr), "Мои результаты"));
        return driver.findElement(userName).getText();
    }

    public void checkThatPageIsLoaded(String userName) {
        Assert.assertEquals(getUserName(), userName);
    }

    public TemplatesListPage goToTemplatesPage() {
        driver.findElement(templateNav).click();
        return new TemplatesListPage(driver);
    }
}
