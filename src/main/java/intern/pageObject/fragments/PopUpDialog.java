package intern.pageObject.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class PopUpDialog {

    private SelenideElement popUpMessageDialog = $x("//p[@class='fancybox-error']");
    private SelenideElement popUpCloseButton = $x("//a[@class='fancybox-item fancybox-close']");

    public PopUpDialog checkPopUpMessage(String popUpMessage) {
        popUpMessageDialog
                .shouldBe(visible);
        assertThat(popUpMessageDialog.text().contentEquals(popUpMessage))
                .as("Product Added to Wishlist Message")
                .isTrue();
        return this;
    }

    public PopUpDialog closePopUpMessageDialog(){
        popUpCloseButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
