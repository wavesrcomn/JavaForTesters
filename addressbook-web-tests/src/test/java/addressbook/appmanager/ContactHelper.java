package addressbook.appmanager;


import addressbook.model.ContactData;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Selection;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, Boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getAddress());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            if (isElementPresent(By.name("selected[]"))) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void sendContactForm() {
        click(By.xpath("//*[@id='content']//input[@value='Enter']"));
    }

    public void selectContact() {
        click(By.xpath("//*[@name='selected[]']"));
    }

    public void submitContactDeletion() {
        click(By.xpath("//*[@value='Delete']"));
        submitAlert();
    }

    public void initContactModification() {
        click(By.xpath("//*[@id='maintable']//tr[2]/td[8]/a"));
    }

    public void submitContactModification() {
        click(By.xpath("//*[@value='Update']"));
    }

    public void gotoAddContactPage() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact) {
        gotoAddContactPage();
        fillContactForm(contact, true);
        sendContactForm();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//*[@name='selected[]']"));
    }
}
