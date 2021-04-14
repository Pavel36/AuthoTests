package test;

import model.AuthorizationData;
import model.TemplateFieldsData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.CreateTemplatePage;
import page.MainPage;
import page.TemplatesListPage;
import step.CreateTemplateStep;
import step.FromMainToCreateStep;
import step.LoginStep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CrTemplateTest_DDT {
    private WebDriver driver;
    private LoginStep loginStep;
    private CreateTemplateStep createTemplateStep;
    private AuthorizationData authorizationData;
    private TemplateFieldsData templateFieldsData;

    @BeforeMethod
    public void start() {
        System.setProperty("webdriver.chrome.driver","C:\\yandexdriver.exe");
        driver = new ChromeDriver();
        driver.get("http://qa-lab.relex-dev.ru:8090/");

    }

    @DataProvider
    public Iterator<Object[]> validAuthorizationDataFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/logins.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            authorizationData = new AuthorizationData();
            authorizationData.setLogin(split[0]);
            authorizationData.setPassword(split[1]);
            authorizationData.setName(split[2]);
            list.add(new Object[] {authorizationData});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validTemplateParamsFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/logins.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(";");
            templateFieldsData = new TemplateFieldsData();
            templateFieldsData.setSurveyName(split[0]);
            templateFieldsData.setType(split[1]);
            templateFieldsData.setName(split[2]);
            list.add(new Object[] {templateFieldsData});
            line = reader.readLine();
        }
        return list.iterator();
    }



    @Test
    public void createTemplateTest() {
        loginStep = new LoginStep(driver);
        MainPage mainPage = loginStep.enterLoginAndPassword(authorizationData.getLogin(), authorizationData.getPassword());
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
