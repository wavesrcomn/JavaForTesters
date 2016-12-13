package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().countContactCount();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Дмитрий", "Вадимович", "Ковалев", "wavesrcomn", "Рабочий", "Пенза, Гагарина 11а", "ООО \"КБ Ренессанс Кредит\"", "+79093170708", "wavesrcomn@gmail.com", null, null,"1991", null));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().submitContactDeletion();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().countContactCount();
        Assert.assertEquals(after, before - 1);
    }

}
