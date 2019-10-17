package intern.pageObject.fragments;

import com.codeborne.selenide.SelenideElement;
import intern.pageObject.AtCheck;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainHeader implements AtCheck{

    private SelenideElement signInLink = $x("//a[contains(@title, 'Log')]");
    private SelenideElement myAccountLink = $("a.account");

    @Override
    public MainHeader isAt(String buttonText) {
        signInLink
                .shouldBe(visible)
                .should(matchText(buttonText));
        return this;
    }

    public MainHeader goToSignInPage(){
        signInLink
                .shouldBe(visible)
                .click();
        return this;
    }

    public MainHeader signOut(){
        signInLink
                .shouldBe(visible)
                .click();
        return this;
    }

    public MainHeader goToMyAccount(){
        myAccountLink
                .shouldBe(visible)
                .click();
        return this;
    }
}
