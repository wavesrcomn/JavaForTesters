package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.io.File;

import org.openqa.selenium.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.getNavigationHelper().gotoAddContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Дмитрий", "Вадимович", "Ковалев", "wavesrcomn", "Пенза, Гагарина 11а", "ООО \"КБ Ренессанс Кредит\"", "+79093170708", "wavesrcomn@gmail.com", "1991"));
        app.getContactHelper().sendContactForm();
    }

}
