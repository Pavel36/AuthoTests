package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.MainPage;
import page.TemplatesListPage;
import step.CreateTemplateStep;
import step.FromMainToCreateStep;
import step.LoginStep;

import java.util.concurrent.TimeUnit;

public class CrTemplateTest {
    private WebDriver driver;
    private LoginStep loginStep;
    private CreateTemplateStep createTemplateStep;

    @BeforeMethod
    public void start() {
        System.setProperty("webdriver.chrome.driver","C:\\yandexdriver.exe");
        driver = new ChromeDriver();
        driver.get("http://qa-lab.relex-dev.ru:8090/");

    }

    @Test
    public void createTemplateTest() {
        loginStep = new LoginStep(driver);
        MainPage mainPage = loginStep.enterLoginAndPassword("p_petrov", "12345678");
        mainPage.checkThatPageIsLoaded("Петров Петр");
        createTemplateStep = new CreateTemplateStep(driver);
        createTemplateStep.setTemplatePage(new FromMainToCreateStep().switchPages(mainPage));
        TemplatesListPage templatesListPageFin = createTemplateStep.enterTemplateFields("DELDEL",
                1,true,1,"Hahahha", "14 апреля 2021 г.",
                "14 апреля 2021 г.", 5, 'W', 2,3,4,2, "Is it you?");
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        templatesListPageFin.checkTemplateCreated("DELDEL");

    }

    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}
