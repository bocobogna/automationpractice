package intern.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static intern.utils.DataResources.headerLocator;

public class LoginPage implements AtCheck {

    SelenideElement emailInput = $("#email");
    SelenideElement passwordInput = $("#passwd");
    SelenideElement submitLoginButton = $("#SubmitLogin");
    SelenideElement header = $(headerLocator);

    @Override
    public LoginPage isAt(String headerTxt) {
        header
                .should(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public LoginPage loginUser(String userEmail, String userPassword){
        emailInput
                .shouldBe(visible)
                .val(userEmail)
                .shouldHave(value(userEmail));
        passwordInput
                .shouldBe(visible)
                .val(userPassword)
                .shouldNot(empty);
        submitLoginButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
