package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DataOfContactViewTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
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
    public void testContactPhone() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoFromView = app.contact().infoFromView(contact);

        assertThat(mergeInfoFromEditForm(contactInfoFromEditForm), equalTo(mergeInfoFromView(contactInfoFromView)));
    }

    private String mergeInfoFromEditForm(ContactData contact) {
        return Stream.of(
                contact.getFirstname(),
                contact.getLastname(),
                contact.getAddress(),
                mergePhones(contact),
                mergeEmails(contact))
                .filter((s) -> !s.equals(""))
                .map(DataOfContactViewTest::cleaned)
                .collect(Collectors.joining(""));
    }

    private String mergeInfoFromView(ContactData contact) {
        return Stream.of(
                contact.getAllInfo())
                .filter((s) -> !s.equals(""))
                .map(DataOfContactViewTest::cleaned)
                .collect(Collectors.joining());
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter((s) -> !s.equals(""))
                .map(DataOfContactListElementTest::cleaned)
                .collect(Collectors.joining(""));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String data){
        return data.replaceAll("\\s", "").replaceAll("[-()]", "").replaceAll("[HMW]:", "");
    }
}