package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static tests.TestData.*;

public class RegistrationWithTestDataTests extends TestBase {
    //String firstName = "Alex",
   //        lastName = "May",
     //     userEmail = "email@mail.com";

    //@BeforeAll
    //static void beforeAll() {
        //Configuration.browserSize = "1600x900";
        //Configuration.baseUrl = "https://demoqa.com";
        //Configuration.pageLoadStrategy = "eager"; //при долгой загрузке не падает тест
        //Configuration.holdBrowserOpen = true; //не закрывается браузер

        @Test
        void fillFormTest () {

            //String firstName = "Alex",
             //       lastName = "May",
              //      userEmail = "email@mail.com";


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
            $("#currentAddress").setValue("Address 1");

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
            $(".modal-content").shouldHave(text("Address Address 1"));
            $(".modal-content").shouldHave(text("State and City NCR Noida"));

        }
    }