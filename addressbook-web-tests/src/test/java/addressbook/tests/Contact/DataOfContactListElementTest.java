package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.model.Groups;
import addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DataOfContactListElementTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().contacts().size() == 0){
            Groups groups = app.db().groups();
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

    @Test (enabled = false)
    public void testDataOfContactListElement() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter((s) -> ! s.equals(""))
                .map(DataOfContactListElementTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}