package intern.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage implements AtCheck{

    private SelenideElement header = $x("//h1[@itemprop='name']");
    private SelenideElement productQuantity = $("#quantity_wanted");
    private SelenideElement productSize = $("#group_1");
    private SelenideElement productColor; //
    private SelenideElement addToCart = $x("//span[contains(text(),'Add to cart')]");
    private SelenideElement addToWishlist = $("#wishlist_button");
    private SelenideElement productPrice = $("#our_price_display");


    @Override
    public ProductPage isAt(String headerTxt) {
        header
                .shouldBe(visible)
                .should(matchText(headerTxt));
        return this;
    }

    public ProductPage setProductQuantity(Integer quantity){
        productQuantity
                .shouldBe(visible)
                .val(String.valueOf(quantity))
                .shouldHave(value(String.valueOf(quantity)));
        return this;
    }

    public ProductPage setProductSize (String size){
        String sizeValue = null;
        switch (size) {
            case "S":
                sizeValue = "1";
                break;
            case "M":
                sizeValue = "2";
                break;
            case "L":
                sizeValue = "3";
                break;
        }
        productSize
                .selectOptionContainingText(size);
        productSize
                .shouldHave(value(sizeValue));
        return this;
    }

    public ProductPage setProductColor (String color){
        productColor = $x("//a[@title='" + color + "']");
        productColor
                .shouldBe(visible)
                .click();
        return this;
    }

    public ProductPage addProductToCart(){
        addToCart
                .shouldBe(visible)
                .click();
        return  this;
    }

    public Float getProductPrice(){
        return Float.valueOf(productPrice
                .shouldBe(visible)
                .getText()
                .substring(1));
    }

    public ProductPage addProductToWishlist(){
        addToWishlist
                .shouldBe(visible)
                .click();
        return this;
    }
}
