package addressbook.appmanager;


import addressbook.model.ContactData;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Selection;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, Boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("address"), contactData.getAddress());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("byear"), contactData.getByear());

        if (creation) {
            if (isElementPresent(By.name("selected[]"))) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void sendContactForm() {
        click(By.name("submit"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public int countContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements){
            String lastname = element.findElement(By.xpath("td[2]")).getText();
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String email = "";
            if (isElementPresent(By.xpath("//*[@name=\"entry\"]/td[5]/a[1]"))) {
                email = element.findElement(By.xpath("td[5]/a[1]")).getText();
            }
            String email2 = "";
            if (isElementPresent(By.xpath("//*[@name=\"entry\"]/td[5]/a[2]"))) {
                email2 = element.findElement(By.xpath("td[5]/a[2]")).getText();
            }
            String email3 = "";
            if (isElementPresent(By.xpath("//*[@name=\"entry\"]/td[5]/a[3]"))) {
                email3 = element.findElement(By.xpath("td[5]/a[3]")).getText();
            }
            ContactData contact = new ContactData(firstname, null, lastname, null, null, address, null, "+79093170708", email, email2, email3, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
