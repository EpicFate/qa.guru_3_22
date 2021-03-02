package tests;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import helpers.Layer;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Layer("web")

public class demoqa_com extends TestBase {
    @Feature("Form fill tests")
    @Story("Student registration")
    @Test
    @Owner("EpicFate")
    @DisplayName("Регистрация студента")
    @Link(url = "https://demoqa.com/automation-practice-form", name = "https://demoqa.com/automation-practice-form")

    void StudentRegistrationForm() {
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService());

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = fakeValuesService.bothify("????##@gmail.com"),
                mobile = fakeValuesService.regexify("[0-9]{10}"),
                StateCurrentAddress = faker.address().country(),
                CityCurrentAddress = faker.address().city(),
                gender = "Male",
                dayOfBirth = "25",
                monthOfBirth = "January",
                yearOfBirth = "1995",
                subject1 = "Arts",
                subject2 = "Accounting",
                hobby1 = "Sports",
                hobby2 = "Reading",
                hobby3 = "Music",
                picture = "image.png",
                state = "Haryana",
                city = "Karnal";

        step("Открывем страницу регистрации студента", (step) -> {
        open("https://demoqa.com/automation-practice-form");
        });

        step("Вводим ФИО", (step) -> {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        });

        step("Вводим почту", (step) -> {
        $("#userEmail").setValue(email);
        });

        step("Вводим номер телофна", (step) -> {
        $("#userNumber").setValue(mobile);
        });

        step("Выбираем гендер", (step) -> {
        $("#genterWrapper").$(byText(gender)).click();
        });

        step("Вводим дату рождения", (step) -> {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth).click();
        });

        step("Выбираем предметы", (step) -> {
        $("#subjectsInput").val(subject1);
        $(".subjects-auto-complete__menu").$(byText(subject1)).click();
        $("#subjectsInput").val(subject2);
        $(".subjects-auto-complete__menu").$(byText(subject2)).click();
        });

        step("Выбираем хобби", (step) -> {
        $("#hobbiesWrapper").$(byText(hobby1)).click();
        $("#hobbiesWrapper").$(byText(hobby2)).click();
        $("#hobbiesWrapper").$(byText(hobby3)).click();
        });

        step("Загружаем картинку", (step) -> {
        $("#uploadPicture").uploadFromClasspath(picture);
        $("#currentAddress").setValue(StateCurrentAddress + CityCurrentAddress);
        });

        step("Выбираем Страну и город", (step) -> {
        $("#state").click();
        $("#state").$(byText(state)).click();
        $("#city").click();
        $("#city").$(byText(city)).click();
        $("#submit").click();
        });

        step("Проверяем введённые данные", (step) -> {
        $(".table-responsive").shouldHave(text("Label Values"));
        $(".table-responsive").shouldHave(text("Student Name" +  " " + firstName + " " + lastName));
        $(".table-responsive").shouldHave(text("Student Email" + " " + email));
        $(".table-responsive").shouldHave(text("Gender" + " " + gender));
        $(".table-responsive").shouldHave(text("Mobile" + " " + mobile));
        $(".table-responsive").shouldHave(text("Date of Birth" + " " + dayOfBirth+ " " + monthOfBirth + "," + yearOfBirth));
        $(".table-responsive").shouldHave(text("Subjects" + " " + subject1 + ", " + subject2));
        $(".table-responsive").shouldHave(text("Hobbies" + " " + hobby1 + ", " + hobby2 + ", " + hobby3));
        $(".table-responsive").shouldHave(text("Picture" + " " + picture));
        $(".table-responsive").shouldHave(text("Address" + " " + StateCurrentAddress + CityCurrentAddress));
        $(".table-responsive").shouldHave(text("State and City" + " " + state + " " + city));
        });
    }

    @Test
    @Feature("radio_buttons")
    @Owner("EpicFate")
    @DisplayName("radio_button_Yes-or-Impressive")
    @Link(url = "https://demoqa.com/radio-button", name = "https://demoqa.com/radio-button")

    void radio_button() {

        step("Открывем страницу c radio_button", (step) -> {
        open("https://demoqa.com/radio-button");
        });

        step("Нажимаем на Yes", (step) -> {
        $(byText("Yes")).click();
        });

        step("Проверяем что нажали Yes", (step) -> {
        $(".text-success").shouldHave(text("Yes"));
        });

        step("Нажимаем Impressive", (step) -> {
        $(byText("Impressive")).click();
        });

        step("проверяем что нажали Impressive", (step) -> {
        $(".text-success").shouldHave(text("Impressive"));
        });
    }
}
