package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Дмитрий", "Вадимович", "Ковалев", "wavesrcomn", "Пенза, Гагарина 11а", "Рабочий", "ООО \"КБ Ренессанс Кредит\"", "+79093170708", "wavesrcomn@gmail.com", "1991", null));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().selectContact(0);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Дмитрий", "Вадимович", "Ковалев", "wavesrcomn", "Пенза, Гагарина 11а", "Рабочий", "ООО \"КБ Ренессанс Кредит\"", "+79093170708", "wavesrcomn@gmail.com", "1991", null), false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();
    }

}
