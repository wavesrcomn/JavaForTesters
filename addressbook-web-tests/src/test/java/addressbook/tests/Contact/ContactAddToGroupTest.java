package addressbook.tests.Contact;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import addressbook.tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroupTest extends TestBase{
    private int contactId;
    private int groupId;

    @BeforeMethod
    public void ensurePrecondition() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts){
            if (groups.size() != contact.getGroups().size()) {
                contactId = contact.getId();
                groups.remove(contact.getGroups());
                groupId = groups.iterator().next().getId();
                break;
            }
        }
        if (contactId == 0){
            app.goTo().groupPage();
            GroupData group = new GroupData()
                    .withName("Тест 1")
                    .withHeader("Абракадабра")
                    .withFooter("Описание");
            app.group().create(group);
            groupId = app.db().groups().iterator().next().getId();
            contactId = contacts.iterator().next().getId();
        }
    }

    @Test
    public void testContactAddToGroup() {
        ContactData contact = app.db().contactById(contactId);
        app.goTo().homePage();
        app.contact().addContactToGroup(contactId, groupId);
        assertThat(contact.getGroups().size() + 1,
                equalTo(app.db().contactById(contactId).getGroups().size()));
    }
}
