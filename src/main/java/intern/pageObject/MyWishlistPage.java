package intern.pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoAlertPresentException;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static intern.utils.DataResources.headerLocator;
import static org.assertj.core.api.Assertions.assertThat;

public class MyWishlistPage implements AtCheck {

    SelenideElement header = $(headerLocator);
    SelenideElement wishlistsTable = $("table.table.table-bordered");
    SelenideElement deleteWishlistLink = $x("//i[@class='icon-remove']");
    SelenideElement myWishlistLink = $x("//a[contains(text(),'My wishlist')]");
    SelenideElement wishlistProductName = $("#s_title");
    SelenideElement wishlistProductQuantity = $x("//input[contains(@id, 'quantity')]");
    SelenideElement wishlistProductAtributes = $x("//div[@class='product_infos']//a[@title='Product detail']");

    @Override
    public MyWishlistPage isAt(String headerTxt) {
        header
                .should(visible)
                .should(matchText(headerTxt.toUpperCase()));
        return this;
    }

    public MyWishlistPage checkWishlistsTable(){
        wishlistsTable
                .shouldBe(visible)
                .$$("tr")
                .shouldHave(sizeGreaterThanOrEqual(2));
        wishlistsTable
                .$$("th")
                .shouldHaveSize(6)
                .texts()
                .contains(".*Name.*Qty.*Viewed.*Created.*Direct Link.*Delete.*");
        wishlistsTable
                .$$("tr td")
                .shouldHaveSize(6);
        return this;
    }

    public MyWishlistPage goToMyWishlist(){
        myWishlistLink
                .shouldBe(visible)
                .click();
        return this;
    }

    public MyWishlistPage checkCartProductName(String productName) {
        assertThat(wishlistProductName.text().contains(productName))
                .as("Product in Wishlist - Name")
                .isTrue();
        return this;
    }

    public MyWishlistPage checkCartProductQuantity(Integer productQuantity) {
        assertThat(wishlistProductQuantity.getValue().contains(String.valueOf(productQuantity)))
                .as("Product in Wishlist - Quantity")
                .isTrue();
        return this;
    }

    public MyWishlistPage checkCartProductAttributes(String productColor, String productSize){
        assertThat(wishlistProductAtributes.text().contentEquals(productSize + ", " + productColor))
                .as("Product in Cart - Attributes (Color, Size)")
                .isTrue();
        return  this;
    }

    protected boolean isAlertPresent() {
        try {
            Selenide.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public MyWishlistPage deleteWishList(){
        deleteWishlistLink
                .shouldBe(visible)
                .click();
        if(isAlertPresent()){
            Selenide.switchTo().alert().accept();
        }
        return  this;
    }
}
