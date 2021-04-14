package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CreateTemplateTest {

  private WebDriver driver;

  @Before
  public void start() {
    System.setProperty("webdriver.chrome.driver","C:\\yandexdriver.exe");
    driver = new ChromeDriver();
  }

  @Test
  public void CreateTemplateTest() {
    Properties authProps = new Properties();
    Properties templateProps = new Properties();

    try {
      FileInputStream authParams = new FileInputStream("src/test/resources/config.properties");
      FileInputStream tempParams = new FileInputStream("src/test/resources/templateParams.properties");
      authProps.load(authParams);
      templateProps.load(tempParams);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    driver.get(authProps.getProperty("url"));

    //Авторизация под админом
    new WebDriverWait(driver, 10).until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Логин']")));
    driver.findElement(By.cssSelector("input[placeholder='Логин']")).sendKeys(authProps.getProperty("login"));
    driver.findElement(By.cssSelector("input[placeholder='Пароль']")).sendKeys(authProps.getProperty("password"));
    driver.findElement(By.xpath("//span[contains(text(),'Войти')]")).click();

    WebElement header = driver.findElement(By.xpath("//h1"));
    new WebDriverWait(driver, 10).until(
            ExpectedConditions.textToBePresentInElement(header, "Мои результаты"));
    Assert.assertEquals(header.getText(), "Мои результаты");
    Assert.assertEquals(driver.findElement(By.className("pl-10")).getText(), "Петров Петр");

    //Клик по элементу меню "Шаблоны опросов"
    driver.findElement(By.xpath("//*[contains(text(), 'Шаблоны опросов')]")).click();

    //Клик по кнопке создания опроса
    new WebDriverWait(driver, 10).until(
            ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'note_add')]")));
    driver.findElement(By.xpath("//*[contains(text(), 'note_add')]")).click();

    //Название
    driver.findElement(By.cssSelector("input[placeholder='Название']")).sendKeys(templateProps.getProperty("surveyName"));

    //Описание
    driver.findElement(By.cssSelector("textarea[placeholder='Описание']")).sendKeys(templateProps.getProperty("description"));

    //Тип
    driver.findElement(By.xpath("//*[contains(text(), "+templateProps.getProperty("type")+")]/../..")).click();

    //Самооценка
    if(templateProps.getProperty("withSelfRating")!=null)
    {
      if (templateProps.getProperty("withSelfRating")=="true")
      {
        driver.findElement(By.className("mat-checkbox-inner-container")).click();
      }
    }


    //Публикация
    driver.findElement(By.xpath("//*[contains(text(), 'анонимно от всех')]")).click();
    new WebDriverWait(driver, 10).until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'mat-select-content')]")));
    driver.findElement(By.xpath("//*[contains(@class, 'mat-select-content')]/mat-option["+templateProps.getProperty("publish")+"]")).click();

    //Дата начала
    WebElement startDate = driver.findElement(By.cssSelector("input[placeholder='Дата начала']"));
    startDate.clear();
    if(templateProps.getProperty("startDate")!=null)
    {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
      try {
        Date today = dateFormat.parse(templateProps.getProperty("startDate"));
        startDate.sendKeys(dateFormat.format(today));
      }
      catch (ParseException e) {
        System.out.println(e.getMessage());
      }
    }
      else
    {
      Date today = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy");
      startDate.sendKeys(dateFormat.format(today) + "г.");
    }


    //Дата окончания
    WebElement endDate = driver.findElement(By.cssSelector("input[placeholder='Дата окончания']"));
    endDate.clear();
    if(templateProps.getProperty("endDate")!=null)
    {
      SimpleDateFormat dateFormat = new SimpleDateFormat("d MM yyyy");
      try {
        Date today = dateFormat.parse(templateProps.getProperty("endDate"));
        endDate.sendKeys(dateFormat.format(today));
      }
      catch (ParseException e) {
        System.out.println(e.getMessage());
      }
    }
    else
    {
      Date today = new Date();
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
      startDate.sendKeys(dateFormat.format(today) + "г.");
    }

    //Период
    driver.findElement(By.cssSelector("input[placeholder='Период']")).sendKeys(templateProps.getProperty("period"));

    //Размерность периода
    if(templateProps.getProperty("periodType")!=null)
    {
      driver.findElement(By.cssSelector("*[formcontrolname='periodType']")).click();
      new WebDriverWait(driver, 10).until(
              ExpectedConditions.elementToBeClickable(By.cssSelector("*[value='"+templateProps.getProperty("periodType")+"'] ")));
      driver.findElement(By.cssSelector("*[value='"+templateProps.getProperty("periodType")+"'] ")).click();
    }

    //Срок начала ответов
    if(templateProps.getProperty("startAnswer")!=null) {
      WebElement startAnswer =driver.findElement(By.cssSelector("input[placeholder='Срок начала ответов (через ... дней)']"));
      startAnswer.clear();
      startAnswer.sendKeys(templateProps.getProperty("startAnswer"));
    }

    //Срок окончания ответов
    if(templateProps.getProperty("endAnswer")!=null) {
      WebElement endAnswer = driver.findElement(By.cssSelector("input[placeholder='Срок окончания ответов (через ... дней)']"));
      endAnswer.clear();
      endAnswer.sendKeys(templateProps.getProperty("endAnswer"));
    }

    //Срок публикации
    if(templateProps.getProperty("datePublish")!=null) {
      WebElement datePublish = driver.findElement(By.cssSelector("input[placeholder='Срок публикации результатов (через ... дней)']"));
      datePublish.clear();
      datePublish.sendKeys(templateProps.getProperty("datePublish"));
    }

    //Номер вопроса
    driver.findElement(By.xpath("//*[contains(@placeholder, '№')]")).click();
    new WebDriverWait(driver, 10).until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'mat-select-content')]")));
    driver.findElement(By.xpath("//*[contains(@class, 'mat-select-content')]/mat-option[1]")).click();

    //Тип вопроса
    driver.findElement(By.xpath("//*[contains(@placeholder, 'Тип')]")).click();
    new WebDriverWait(driver, 10).until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'mat-select-content')]")));
    driver.findElement(By.xpath("//*[contains(@class, 'mat-select-content')]/mat-option["+templateProps.getProperty("questionType")+"]")).click();

    //Текст вопроса
    driver.findElement(By.cssSelector("input[placeholder='Текст вопроса']")).sendKeys(templateProps.getProperty("questionText"));

    //Добавить вопрос
    new WebDriverWait(driver, 10).until(
            ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class, 'ng-valid')]/button[1]")));
    driver.findElement(By.xpath("//*[contains(@class, 'ng-valid')]/button[1]")).click();

    //Сохранить
    driver.findElement(By.xpath("//*[contains(@class, 'button-group')]/button[1]")).click();

    //Проверка, что шаблон создан
    new WebDriverWait(driver, 10).until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Поиск по названию']")));

    //Ищем по названию шаблона
    driver.findElement(By.cssSelector("input[placeholder='Поиск по названию']")).sendKeys(templateProps.getProperty("surveyName"));
    driver.findElement(By.xpath("//*[contains(@class, 'search-header')]/button[1]")).click();
    driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

    //Если таблица содержит хотя бы одну строку с таким названием, тест выполнен
    Assert.assertEquals(driver.findElement(By.xpath("//*[contains(text(), '"+templateProps.getProperty("surveyName")+"') " +
            "and @class = 'mat-list-item-content']")).getText(),templateProps.getProperty("surveyName"));
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

}
