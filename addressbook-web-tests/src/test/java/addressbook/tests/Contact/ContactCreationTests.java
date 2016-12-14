package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.tests.TestBase;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstname("Дмитрий")
                .withMiddlename("Вадимович")
                .withLastname("Ковалев")
                .withNickname("wavesrcomn")
                .withTitle("Рабочий")
                .withAddress("Пенза, Гагарина 11а")
                .withCompany("ООО \"КБ Ренессанс Кредит\"")
                .withMobile("+79093170708")
                .withEmail("wavesrcomn@gmail.com")
                .withEmail2("twisterbox@mail.ru")
                .withByear("1991")
                .withGroup("Тест 1");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

}
