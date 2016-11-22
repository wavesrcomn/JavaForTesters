package addressbook.tests.Group;

import addressbook.model.GroupData;
import addressbook.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("Тест 1", null, "Тест 3"));
        app.getNavigationHelper().gotoGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before + 1);
    }

}
