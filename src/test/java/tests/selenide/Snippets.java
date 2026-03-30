package tests.selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.bidi.network.Cookie;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Snippets {

    void browser_command_examples() {

        open("https://google.com"); // открытие абсолютного URL
        open("/customer/orders"); // открытие относительного URL

        Selenide.back(); // кнопка назад в браузеле
        Selenide.refresh(); // кнопка перезагрузить страницу в браузере

        Selenide.clearBrowserCookies(); // очистка Cookies
        Selenide.clearBrowserLocalStorage(); // очистка Local storage
        executeJavaScript("sessionStorage.clear();"); // очистка Session storage, пока команда в Selenide не реализована

        Selenide.confirm();
        Selenide.dismiss();
        // очень мало, где данная часть реализована, в работе использоваться не будет

        Selenide.closeWindow(); // закрывает активное окно (если открыто одно окно, то при закрытии, закрывает и браузер)
        Selenide.closeWebDriver(); // закрывает полностью все окна браузера

        Selenide.switchTo().frame("iframe"); // перемещение меджу фреймами
        Selenide.switchTo().defaultContent(); // возвращение в искомую позицию
        // сам frame уже устарел, но возможна работа с iframe, эти команды могут пригодиться

        Selenide.switchTo().window("main"); // перемещение между окнами

        var cookie=new Cookie("foo","11234");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        // способ добавления значений cookie, Selenide еше не реализовал эту команду
    }

    void selectors_examples() {
        $("div").click(); // лучший вариант
        element("div").click();

        $("div", 2).click(); // ищет второй элемент с 'div'

        $x("//h1/div").click(); // лучший вариант
        $(byXpath("//h1/div")).click();
        // поиск по Xpath локатору

        $(byText("full text")).click(); // поиск по тексту
        $(withText("ull tex")).click(); // поиск по тексту (частично)

        $(byTagAndText("div", "full text")).click();
        $(withTagAndText("div", "full text")).click();
        // Поиск по тэгу и тексту (так же по частичному тексту)

        $("").parent().click(); // Клик по родительскому элементу (поднимаемся на 1 уровень вверх)
        $("").sibling(0).click(); // Клик по соседнему элементу с индексом 0 относительно текущего
        $("").preceding(0).click(); // Клик по предыдущему (расположенному выше) соседнему элементу с индексом 0
        $("").closest("div").click(); // Клик по ближайшему родительскому элементу, соответствующему селектору "div"
        $("").ancestor("div").click(); // Клик по любому родителю (на любом уровне выше), соответствующему селектору "div"
        $("div:last-child").click(); // // Клик по последнему дочернему элементу div внутри родителя (CSS псевдокласс :last-child)

        $("div").$("h1").find(byText("full text")).click();

        $(byAttribute("innerText", "full text")).click();
        $("[abc=x]").click(); // лучший вариант
        // Поиск по отрибуту

        $(byId("button")).click();
        $("#button").click(); // лучший вариант
        // Поиск по id

        $(byClassName("button")).click();
        $(".button").click(); // лучший вариант
        // Поиск по class

    }

    void actions_examples() {
        $("").click(); // кликнуть по элементу
        $("").doubleClick(); // двойной клик по элементу
        $("").contextClick(); // правый клик


    }
}
