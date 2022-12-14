package order;

import main.BrowserRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import pom.HomePage;

@RunWith(Parameterized.class)
public class MakeOrder {

    @Rule
    public BrowserRule browserRule = new BrowserRule();

    private String placeButton;

    public MakeOrder(String placeButton) {
        this.placeButton = placeButton;
    }


    @Parameters
    public static Object[][] getButton() {
        return new Object[][] {
                {"верхняя кнопка"},
                {"нижняя кнопка"},
        };
    }

    @Test
    public void makeOrderFromHeaderButton() {
        MakeOrder makeOrder = new MakeOrder(placeButton);
        placeButton = makeOrder.placeButton;
        HomePage homePage = new HomePage(browserRule.getDriver());

        homePage.open()
                .clickAcceptCookieButton()
                .clickOrderButton(placeButton);
        homePage.orderPage().setUserInfo("Имя", "Фамилия","Адрес", "Сокольники", "+79050481997")
                            .clickNextButton();
        homePage.rentPage().setRentInfo("05.10.2022", "двое суток", "чёрный жемчуг", "Комментарий")
                            .clickOrderButton()
                            .clickOkButton();

        Assert.assertTrue(homePage.rentPage().isOrderIsPlaced());
    }
}
