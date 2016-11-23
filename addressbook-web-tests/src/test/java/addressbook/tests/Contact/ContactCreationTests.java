package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.model.GroupData;
import addressbook.tests.TestBase;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.File;

import org.openqa.selenium.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getContactHelper().gotoAddContactPage();
        app.getContactHelper().createContact(new ContactData("Дмитрий", "Вадимович", "Ковалев", "wavesrcomn", "Пенза, Гагарина 11а", "ООО \"КБ Ренессанс Кредит\"", "+79093170708", "wavesrcomn@gmail.com", "1991", null));
        app.getNavigationHelper().gotoHomePage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }

}
