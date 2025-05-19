package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationWithFakerTests extends TestBase {

        @Test
        void fillFormTest () {

            Faker faker = new Faker(new Locale("en-US"));

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String userEmail = faker.internet().emailAddress();

            String streetAddress = faker.address().streetAddress();


            open("/automation-practice-form");
            //убирает рекламу
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");

            //просто обычные поля
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(userEmail);

            //чек-бокс
            $("#genterWrapper").$(byText("Male")).click();
            //поле
            $("#userNumber").setValue("79991113399");

            //datetime
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").$(byText("September")).click();
            $(".react-datepicker__year-select").$(byText("2010")).click();
            $(".react-datepicker__month").$(byText("3")).click();

            //поисковая строка, где высвечиваются значения когда пишешь текст
            $("#subjectsInput").setValue("Eng").pressEnter();

            //чекбокс
            $("#hobbiesWrapper").$(byText("Reading")).click();

            //поле
            $("#currentAddress").setValue(streetAddress);

            // загрузить картинку
            $("#uploadPicture").uploadFromClasspath("img.png");

            //дропдауны
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();

            //дропдауны
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Noida")).click();

            // кликаем на кнопку submit
            $("#submit").click();

            //проверяем значения
            $(".modal-content").shouldHave(text("Student Name " + firstName + " " + lastName));
            $(".modal-content").shouldHave(text("Student Email " + userEmail));
            $(".modal-content").shouldHave(text("Gender Male"));
            $(".modal-content").shouldHave(text("Mobile 7999111339"));
            $(".modal-content").shouldHave(text("Date of Birth 03 September,2010"));
            $(".modal-content").shouldHave(text("Subjects English"));
            $(".modal-content").shouldHave(text("Hobbies Reading"));
            $(".modal-content").shouldHave(text("Picture img.png"));
            $(".modal-content").shouldHave(text("Address " + streetAddress));
            $(".modal-content").shouldHave(text("State and City NCR Noida"));

        }
    }