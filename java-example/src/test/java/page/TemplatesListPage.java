package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TemplatesListPage {

    private WebDriver driver;
    private By inputSearch = By.cssSelector("input[placeholder='Поиск по названию']");
    private By tableRow = By.xpath("//*[contains(text(), 'DELDEL')]");
    private By btnSearch = By.xpath("//*[contains(@class, 'search-header')]/button[1]");
    private By btnAdd = By.xpath("//*[contains(text(), 'note_add')]");
    private By table = By.tagName("mat-table");
    private By row = By.tagName("mat-row");
    private By select = By.cssSelector("mat-select");
    private By selectItemsPerPage = By.xpath("//*[contains(@class, 'mat-select-content ')]/mat-option[4]");


    public TemplatesListPage(WebDriver driver) {
        this.driver = driver;
    }

    public CreateTemplatePage createTemplatePage() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(btnAdd));
        driver.findElement(btnAdd).click();
        return new CreateTemplatePage(driver);
    }

    public int countTemplates(String surveyName) {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(inputSearch));
        driver.findElement(inputSearch).sendKeys(surveyName);
        driver.findElement(btnSearch).click();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.findElement(select).click();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.visibilityOfElementLocated(By.tagName("mat-option")));
        driver.findElement(selectItemsPerPage).click();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        List<WebElement> list1 = driver.findElement(table).findElements(row);
        int count1 = list1.size();
        return count1;
    }

    public String getTemplateNameFromList(String surveyName) {
        driver.findElement(inputSearch).sendKeys(surveyName);
        driver.findElement(btnSearch).click();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

        return driver.findElement(By.xpath("//*[contains(text(), '"+surveyName+"') and @class = 'mat-list-item-content']")).getText();
    }

    public void checkTemplateCreatedByCount(int templatesBefore, int templatesAfter) {
        Assert.assertEquals(templatesBefore,templatesAfter);
    }

    public void checkTemplateCreated(String surveyName) {
        Assert.assertEquals(getTemplateNameFromList(surveyName), surveyName);
    }
}
