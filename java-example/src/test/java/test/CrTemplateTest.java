package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.MainPage;
import page.TemplatesListPage;
import step.CheckStep;
import step.CreateTemplateStep;
import step.LoginStep;

import java.util.concurrent.TimeUnit;

public class CrTemplateTest {
    private WebDriver driver;
    private LoginStep loginStep;
    private CreateTemplateStep createTemplateStep;
    private CheckStep checkStep;

    @BeforeMethod
    public void start() {
        System.setProperty("webdriver.chrome.driver","C:\\yandexdriver.exe");
        driver = new ChromeDriver();
        driver.get("http://qa-lab.relex-dev.ru:8090/");

    }

    @Test
    public void createTemplateTest() {
        //логин
        loginStep = new LoginStep(driver);
        MainPage mainPage = loginStep.enterLoginAndPassword("p_petrov", "12345678");
        mainPage.checkThatPageIsLoaded("Петров Петр");

        //клик по вкладке шаблоны опросов, переход на страницу шаблоны опросов
        TemplatesListPage templatesListPage = mainPage.goToTemplatesPage();

        //подсчитываем сколько шаблонов с нужным именем существует
        checkStep = new CheckStep(driver);
        checkStep.setTemplatesListPage(templatesListPage);
        int countBefore = checkStep.countTemplates("DELDEL");

        //создаем новый шаблон
        createTemplateStep = new CreateTemplateStep(driver);
        createTemplateStep.setTemplatePage(templatesListPage.createTemplatePage());
        TemplatesListPage templatesListPageFin = createTemplateStep.enterTemplateFields("DELDEL",
                1,true,1,"Hahahha", "20 апреля 2021 г.",
                "21 апреля 2021 г.", 5, 'W', 2,3,4,2, "Is it you?");
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

        //подсчитываем сколько шаблонов существует после добавления нового
        checkStep.setTemplatesListPage(templatesListPageFin);
        int countAfter = checkStep.countTemplates("DELDEL");
        checkStep.checkTemplateCreatedByCount(countBefore+1,countAfter);

    }

    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}
