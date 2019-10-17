package intern.pageObject;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public MainPage open() {
        Selenide.open("");
        return this;
    }

}
