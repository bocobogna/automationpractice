package intern.pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import intern.enums.MyAccountOption;

import static com.codeborne.selenide.Condition.*;
import static intern.utils.DataResources.*;
import static com.codeborne.selenide.Selenide.*;

public class MyAccountPage implements AtCheck {

    SelenideElement header = $(headerLocator);
    ElementsCollection myAccountOptions = $$x("//div[@class='row addresses-lists']//span");

    @Override
    public MyAccountPage isAt(String headerTxt) {
        header
                .should(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public void listAction(MyAccountOption listOption){
        myAccountOptions
                .findBy(text(listOption.getOption()))
                .click();
    }

}
