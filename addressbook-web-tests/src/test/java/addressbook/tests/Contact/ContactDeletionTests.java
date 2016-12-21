package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.Groups;
import addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0){
            app.goTo().homePage();
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
                    .inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactDeletion(){
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
