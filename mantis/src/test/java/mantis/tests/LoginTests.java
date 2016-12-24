package mantis.tests;

import mantis.appmanager.HttpSession;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests extends TestBase{

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login("administrator", "admin"));
        Assert.assertTrue(session.isLoggegInAs("administrator"));
    }
}
