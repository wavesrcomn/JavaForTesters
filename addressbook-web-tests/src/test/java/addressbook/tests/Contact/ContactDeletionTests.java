package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Дмитрий", "Вадимович", "Ковалев", "wavesrcomn", "Пенза, Гагарина 11а", "ООО \"КБ Ренессанс Кредит\"", "+79093170708", "wavesrcomn@gmail.com", "1991", null));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().submitContactDeletion();
        app.getNavigationHelper().gotoHomePage();
    }

}
