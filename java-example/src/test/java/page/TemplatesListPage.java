package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TemplatesListPage {

    private WebDriver driver;
    private By inputSearch = By.cssSelector("input[placeholder='Поиск по названию']");
    private By tableRow = By.xpath("//*[contains(text(), 'DELDEL')]");
    private By btnSearch = By.xpath("//*[contains(@class, 'search-header')]/button[1]");
    private By btnAdd = By.xpath("//*[contains(text(), 'note_add')]");

    public TemplatesListPage(WebDriver driver) {
        this.driver = driver;
    }

    public CreateTemplatePage createTemplatePage() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(btnAdd));
        driver.findElement(btnAdd).click();
        return new CreateTemplatePage(driver);
    }

    public String getTemplateNameFromList(String surveyName) {
        driver.findElement(inputSearch).sendKeys(surveyName);
        driver.findElement(btnSearch).click();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

        return driver.findElement(By.xpath("//*[contains(text(), '"+surveyName+"') and @class = 'mat-list-item-content']")).getText();
    }

    public void checkTemplateCreated(String surveyName) {
        Assert.assertEquals(getTemplateNameFromList(surveyName), surveyName);
    }
}
