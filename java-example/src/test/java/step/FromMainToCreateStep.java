package step;

import page.CreateTemplatePage;
import page.MainPage;
import page.TemplatesListPage;

public class FromMainToCreateStep {

    public CreateTemplatePage switchPages(MainPage mainPage)
    {
        TemplatesListPage templatesListPage = mainPage.goToTemplatesPage();
        CreateTemplatePage createTemplatePage = templatesListPage.createTemplatePage();
        return createTemplatePage;
    }
}
