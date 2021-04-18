package step;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import page.CreateTemplatePage;
import page.TemplatesListPage;

public class CheckStep {
    private TemplatesListPage templatesListPage;

    public CheckStep(WebDriver driver) {
        templatesListPage = PageFactory.initElements(driver, TemplatesListPage.class);
    }

    public void setTemplatesListPage(TemplatesListPage templatesListPage){
        this.templatesListPage = templatesListPage;
    }

    public int countTemplates(String SurveyName) {
       return templatesListPage.countTemplates(SurveyName);
    }

    public void checkTemplateCreatedByCount(int templatesBefore, int templatesAfter) {
        Assert.assertEquals(templatesBefore,templatesAfter);
    }
}
