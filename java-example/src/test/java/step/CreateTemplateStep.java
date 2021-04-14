package step;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import page.CreateTemplatePage;
import page.TemplatesListPage;

public class CreateTemplateStep {
    private CreateTemplatePage templatePage;

    public CreateTemplateStep(WebDriver driver) {
        templatePage = PageFactory.initElements(driver,CreateTemplatePage.class);
    }

    public void setTemplatePage(CreateTemplatePage templatePage) {
        this.templatePage = templatePage;
    }

    public TemplatesListPage enterTemplateFields (String surveyName, Integer type, boolean withSelfRating, Integer publish,
                                                  String description, String startDate, String endDate,
                                                  Integer period, char periodType, Integer startAnswer, Integer endAnswer,
                                                  Integer datePublish, Integer questionType, String questionText)
    {
        templatePage.enterSurveyName(surveyName);
        templatePage.enterType(type);
        templatePage.enterSelfRating(withSelfRating);
        templatePage.enterPublish(publish);
        templatePage.enterDescription(description);
        templatePage.enterStartDate(startDate);
        templatePage.enterEndDate(endDate);
        templatePage.enterPeriod(period);
        templatePage.enterPeriodType(periodType);
        templatePage.enterStartAnswer(startAnswer);
        templatePage.enterEndAnswer(endAnswer);
        templatePage.enterDatePublish(datePublish);
        templatePage.enterQuestionNumber();
        templatePage.enterQuestionType(questionType);
        templatePage.enterQuestionText(questionText);
        return templatePage.pressCreateButton();
    }

}
