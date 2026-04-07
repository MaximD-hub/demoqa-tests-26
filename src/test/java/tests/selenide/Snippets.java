package tests.selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.bidi.network.Cookie;

import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
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

        var cookie = new Cookie("foo","11234");
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

        $("").hover(); // поднести мышну, но не нажимать

        $("").setValue("text"); // записать текст в поле для ввода
        $("").append("text"); // добавить текст в конец другого в поле
        $("").clear(); // очистить, чаще работает с ошибкой
        $("").setValue(""); // очистить

        $("").sendKeys("c"); // Нажатие клавиши
        actions().sendKeys("c").perform(); // Нажатие клавиши
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); // Ctrl + F

        $("").pressEnter();
        $("").pressEscape();
        $("").pressTab();

        actions().moveToElement($("div")).clickAndHold().moveByOffset(300,200).release().perform();
        // actions() — инициализирует объект Actions, который позволяет строить цепочки сложных эмуляций (клавиатура, мышь)
        //moveToElement($("div")) — наводит курсор мыши на центр указанного элемента
        //clickAndHold() — нажимает левую кнопку мыши и удерживает её нажатой
        //moveByOffset(300, 200) — передвигает мышь из текущей точки на указанную
        //release() — отпускает левую кнопку мыши в текущей позиции курсора
        //perform() — фактически запускает выполнение всех вышеперечисленных команд в браузере.

        $("").selectOption("dropdown_option"); // для работы со старыми dropdown которые имеют options у себя в HTML
        $("").selectRadio("radio_options"); //

    }

    void assertions_examples() {
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        // Элемент «должен быть» каким-то (видимым, скрытым, задизейбленным)
        // Пример: $("button").shouldBe(enabled); — кнопка должна быть активна
        $("").shouldHave(text("text"));
        $("").shouldNotHave(text("text"));
        // Элемент «должен иметь» что-то внутри (текст, CSS-класс, значение в value)
        // Пример: $("h1").shouldHave(text("Welcome")); — заголовок должен иметь текст
        $("").should(appear);
        $("").shouldNot(appear);
        // Логика: Элемент «должен» (появиться, исчезнуть)
        // Пример: $(".loader").should(disappear); — лоадер должен исчезнуть

        $("").shouldBe(visible, Duration.ofSeconds(30)); // с условием ожидания
    }

    void conditions_examples() {
        $("").shouldBe(visible); // Проверка на видимость
        $("").shouldBe(hidden); // Проверка, что элемента нет на экране в данный момент

        $("").shouldHave(text("abc"));
        // Логика: Содержит ли элемент эту подстроку
        // Особенности: Игнорирует регистр (ABC и abc — одно и то же)
        // Проверка пройдет, если в элементе написано «123 abc 456»
        $("").shouldHave(exactText("abc"));
        // Логика: Текст в элементе совпадает символ в символ
        // Особенности: Игнорирует регистр, но не допускает лишних символов
        // Если в элементе написано «abc », «abc!» или «123 abc» — тест упадет
        $("").shouldHave(textCaseSensitive("abc"));
        // Логика: Содержит ли элемент подстроку с учетом регистра
        // Особенности: Если в коде abc, а ты ищешь ABC — тест упадет
        // Полезно для проверки специфических названий брендов или кодов
        $("").shouldHave(exactTextCaseSensitive("abc"));
        // Логика: Полное совпадение + учет регистра
        //Особенности: Самый строгий метод. Текст должен быть идентичен и по содержанию, и по регистру
        // Никаких лишних пробелов или заглавных букв
        $("").should(matchText("[0-9]abc$"));
        // Логика: Проверка текста по Регулярному выражению (RegEx)
        // Особенности: Позволяет проверять динамические данные по шаблону.
        // Например, твой пример [0-9]abc$ означает:
        // «строка должна заканчиваться на abc, а перед ними должна быть любая цифра от 0 до 9»
        // Применение: Идеально для проверки каунтеров (например, «Найдено 15 товаров»),
        // где число постоянно меняется

        // Проверка стилей и классов
        $("").shouldHave(cssClass("red"));
        // Проверяет, есть ли у элемента в атрибуте class указанное имя
        //Нюанс: У элемента может быть 10 классов (btn btn-primary red), проверка пройдет, если среди них есть red
        $("").shouldHave(cssValue("font-size","12"));
        // Проверяет конкретное CSS-свойство (размер шрифта, цвет, отступы)
        // Применение: Идеально для проверки того, что цена в бэкдропе реально красного цвета или зачеркнута

        // Проверка значений (для полей ввода / Input)
        $("").shouldHave(value("25"));
        // Проверяет содержимое атрибута value (то, что введено в поле).
        // Нюанс: Частичное совпадение. Если в поле "255", проверка на "25" пройдет
        $("").shouldHave(exactValue("25"));
        // Строгое совпадение значения в поле ввода. Никаких лишних цифр или пробелов
        $("").shouldBe(empty);
        // Проверяет, что элемент пустой (нет текста внутри или нет значения в input)
        // Применение: Проверить, что поле выбора размера в одежде изначально пустое

        // Проверка атрибутов (HTML-теги)
        $("").shouldHave(attribute("disabled"));
        // Проверяет наличие самого атрибута
        // Применение: Проверка кнопки «-» в каунтере, когда она заблокирована (серого цвета)
        $("").shouldHave(attribute("name", "example"));
        // Проверяет конкретное значение конкретного атрибута (например, name, id, placeholder, src)
        // Применение: Проверить, что у товара в бэкдропе верная ссылка на изображение в атрибуте src
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));
        // Проверка значения атрибута по регулярному выражению
        //Применение: Если ID или имя элемента генерируются динамически,
        // но имеют общую структуру (например, id="item_12345")

        $("").shouldBe(checked); // Lля проверки чекбоксов

        $("").should(exist); // Проверяет начилие элемента в доме, но не проверяет его видимость

        // проверяют состояние активности элемента: disabled подтверждает, что элемент заблокирован для взаимодействия
        // (например, серая кнопка), а enabled — что элемент активен и доступен для клика или ввода данных
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);

    }

    void collections_examples() {

        $$("div");

        $$x("//div");

        $$("div").filterBy(text("123")).shouldHave(size(1)); // фильтр, у которых есть значение
        $$("div").excludeWith(text("123")).shouldHave(size(1)); // фильтр, у которых НЕТ данных значение

        $$("div").first().click(); // переход к первому элементу
        elements("div").first().click(); // аналог
        // $("div").click(); - делает тоже самое, но выше описывает что именно делаем для читателя кода

        $$("div").last().click(); // переход к последнему элементу
        $$("div").get(1).click(); // второй элемент (начало с 0)
        $("div",1).click(); // аналог
        $$("div").findBy(text("123")).click(); // фильтрует и берет первый элемент

        // проверки (assertions)
        $$("").shouldHave(size(0)); // коллекция должна быть пустой (0 элементов)
        $$("").shouldBe(CollectionCondition.empty); // аналог, коллекция пустая (более читаемая форма)

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
        // элементы содержат тексты "Alfa", "Beta", "Gamma" (порядок важен, частичное совпадение), не больше и не меньше 3-х элементов
        $$("").shouldHave(exactTexts(("Alfa", "Beta", "Gamma"));
        // элементы строго равны текстам (порядок важен, без лишних символов)

        $$("").shouldHave(textsInAnyOrder("Alfa", "Beta", "Gamma"));
        // тексты присутствуют, но порядок не важен (частичное совпадение)
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder(("Alfa", "Beta", "Gamma"));
        // точное совпадение текстов + регистр важен + порядок не важен

        $$("").shouldHave(itemWithText("Gamma")); // в коллекции есть хотя бы один элемент с текстом "Gamma"

        $$("").shouldHave(sizeGreaterThan(0)); // количество элементов больше 0 (коллекция не пустая)
        $$("").shouldHave(sizeGreaterThanOrEqual(1)); // количество элементов больше или равно 1
        $$("").shouldHave(sizeLessThan(3)); // количество элементов меньше 3
        $$("").shouldHave(sizeLessThanOrEqual(2)); // количество элементов меньше или равно 2
    }

    void file_operation_examples() fhrows FileNotFoundException {

        File file1 = $("a.fileLink").download(); // только для <a href="..">
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER));

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')");
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
        long fortytwo = executeJavaScript("return arguments[0]+arguments[1];", 6, 7);

    }

}
