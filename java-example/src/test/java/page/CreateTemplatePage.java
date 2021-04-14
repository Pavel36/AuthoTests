package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateTemplatePage {
    private WebDriver driver;
    private By inputSurveyName = By.cssSelector("input[placeholder='Название']");
    private By inputDescription = By.cssSelector("textarea[placeholder='Описание']");
    private By inputType = By.xpath("//*[contains(text(), 360)]/../..");
    private By selectWithSelfRating = By.className("mat-checkbox-inner-container");
    private By selectPublish = By.xpath("//*[contains(text(), 'анонимно от всех')]");
    private By inputStartDate = By.cssSelector("input[placeholder='Дата начала']");
    private By inputEndDate = By.cssSelector("input[placeholder='Дата окончания']");
    private By inputPeriod = By.cssSelector("input[placeholder='Период']");
    private By inputPeriodType = By.cssSelector("*[formcontrolname='periodType']");
    private By selectContent = By.xpath("//*[contains(@class, 'mat-select-content')]");
    //private By inputAccessSurvey = ;
    private By inputStartAnswer = By.cssSelector("input[placeholder='Срок начала ответов (через ... дней)']");
    private By inputEndAnswer = By.cssSelector("input[placeholder='Срок окончания ответов (через ... дней)']");
    private By inputDatePublish = By.cssSelector("input[placeholder='Срок публикации результатов (через ... дней)']");
    private By selectQuestionNumber = By.xpath("//*[contains(@placeholder, '№')]");
    private By selectQuestionNumberFirst = By.xpath("//*[contains(@class, 'mat-select-content')]/mat-option[1]");
    private By selectQuestionType = By.xpath("//*[contains(@placeholder, 'Тип')]");
    private By selectQuestionText = By.cssSelector("input[placeholder='Текст вопроса']");
    private By btnCreate = By.xpath("//*[contains(@class, 'button-group')]/button[1]");

    public CreateTemplatePage(WebDriver driver) {
        this.driver = driver;
    }

    public CreateTemplatePage enterSurveyName(String surveyName)
    {
        driver.findElement(inputSurveyName).sendKeys(surveyName);
        return this;
    }

    public CreateTemplatePage enterDescription(String description)
    {
        driver.findElement(inputDescription).sendKeys(description);
        return this;
    }

    public CreateTemplatePage enterType(Integer type)
    {
        driver.findElement(By.xpath("//*[contains(text(), "+type+")]/../..")).click();
        return this;
    }

    public CreateTemplatePage enterSelfRating(boolean selfRating)
    {
        if (selfRating)
        {
            driver.findElement(selectWithSelfRating).click();
        }
        return this;
    }

    public CreateTemplatePage enterPublish(Integer publish)
    {
        driver.findElement(selectPublish).click();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class, 'mat-select-content')]/mat-option["+publish+"]")));
        driver.findElement(By.xpath("//*[contains(@class, 'mat-select-content')]/mat-option["+publish+"]")).click();
        return this;
    }

    public CreateTemplatePage enterStartDate(String startDateParam)
    {
        WebElement startDate = driver.findElement(inputStartDate);
        startDate.clear();
        startDate.sendKeys(startDateParam);
        return this;
    }

    public CreateTemplatePage enterEndDate(String endDateParam)
    {
        WebElement endDate = driver.findElement(inputEndDate);
        endDate.clear();
        endDate.sendKeys(endDateParam);
        return this;
    }

    public CreateTemplatePage enterPeriod(Integer period)
    {
        driver.findElement(inputPeriod).sendKeys(period.toString());
        return this;
    }

    public CreateTemplatePage enterPeriodType(char periodType)
    {
        driver.findElement(inputPeriodType).click();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("*[value='"+ periodType +"'] ")));
        driver.findElement(By.cssSelector("*[value='"+ periodType +"'] ")).click();
        return this;
    }

    public CreateTemplatePage enterStartAnswer(Integer startAnswerParam)
    {
        WebElement startAnswer =driver.findElement(inputStartAnswer);
        startAnswer.clear();
        startAnswer.sendKeys(startAnswerParam.toString());
        return this;
    }

    public CreateTemplatePage enterEndAnswer(Integer endAnswerParam)
    {
        WebElement endAnswer = driver.findElement(inputEndAnswer);
        endAnswer.clear();
        endAnswer.sendKeys(endAnswerParam.toString());
        return this;
    }

    public CreateTemplatePage enterDatePublish(Integer datePublishParam)
    {
        WebElement datePublish = driver.findElement(inputDatePublish);
        datePublish.clear();
        datePublish.sendKeys(datePublishParam.toString());
        return this;
    }

    public CreateTemplatePage enterQuestionNumber()
    {
        driver.findElement(selectQuestionNumber).click();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(selectQuestionNumberFirst));
        driver.findElement(selectQuestionNumberFirst).click();
        return this;
    }

    public CreateTemplatePage enterQuestionType(Integer questionType)
    {
        driver.findElement(selectQuestionType).click();
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class, 'mat-select-content')]/mat-option["+questionType+"]")));
        driver.findElement(By.xpath("//*[contains(@class, 'mat-select-content')]/mat-option["+questionType+"]")).click();
        return this;
    }

    public CreateTemplatePage enterQuestionText(String questionText)
    {
        driver.findElement(selectQuestionText).sendKeys(questionText);
        return this;
    }

    public TemplatesListPage pressCreateButton() {
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeClickable(btnCreate));
        driver.findElement(btnCreate).click();
        return new TemplatesListPage(driver);
    }

}
