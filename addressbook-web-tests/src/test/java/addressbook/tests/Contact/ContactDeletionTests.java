package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    private void ensurePrecondition() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("Дмитрий")
                    .withMiddlename("Вадимович")
                    .withLastname("Ковалев")
                    .withNickname("wavesrcomn")
                    .withTitle("Рабочий")
                    .withAddress("Пенза, Гагарина 11а")
                    .withCompany("ООО \"КБ Ренессанс Кредит\"")
                    .withMobilePhone("+79093170708")
                    .withEmail("wavesrcomn@gmail.com")
                    .withEmail2("twisterbox@mail.ru")
                    .withByear("1991")
                    .withGroup("Тест 1"));
        }
    }

    @Test
    public void testContactDeletion(){
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
