package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/XNBCvZKXHTg.jpg");
        ContactData contact = new ContactData()
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
                .withGroup("Тест 1")
                .withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

}
