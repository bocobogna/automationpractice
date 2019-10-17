package intern.pageObject.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class NavigationHeader {

    private SelenideElement logo = $("img.logo.img-responsive");
    private SelenideElement cart = $x("//b[contains(text(),'Cart')]");
    private SelenideElement categoryWomen = $x("//a[@class='sf-with-ul'][contains(text(),'Women')]");
    private SelenideElement categoryDress = $x("//ul[contains(@class, 'sf-menu')]/li/a[@title='Dresses']");

    public NavigationHeader goToDressesPage(){
        categoryDress
                .shouldBe(visible)
                .should(matchText("Dresses".toUpperCase()))
                .click();
        return this;
    }

}
