package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
        if (contactData.getBDay() != null) {attach(By.name("photo"), contactData.getPhotoUrl());}
        type(By.name("company"), contactData.getCompany());
        type(By.name("title"), contactData.getTitle());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());
        if (contactData.getBDay() != null) {new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBDay());}
        if (contactData.getBMonth() != null) {new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBMonth());}
        type(By.name("byear"), contactData.getBYear());
        if (contactData.getADay() != null) {new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getADay());}
        if (contactData.getAMonth() != null) {new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getAMonth());}
        type(By.name("ayear"), contactData.getAYear());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        type(By.name("address2"), contactData.getSecondAddress());
        type(By.name("phone2"), contactData.getSecondHome());
        type(By.name("notes"), contactData.getNotes());
    }

    public void sendContactForm() {
        click(By.name("submit"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void submitContactDeletion() {
        click(By.xpath("//*[@value='Delete']"));
        submitAlert();
    }

    public void submitContactModification() {
        click(By.xpath("//*[@value='Update']"));
    }

    public void gotoAddContactPage() {
        click(By.linkText("add new"));
    }

    public void returnToHomePage() { click(By.linkText("home"));
    }

    public void create(ContactData contact) {
        gotoAddContactPage();
        fillContactForm(contact, true);
        sendContactForm();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact){
        selectContactById(contact.getId());
        submitContactDeletion();
        contactCache = null;
        returnToHomePage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements){
            String lastname = element.findElement(By.xpath("td[2]")).getText();
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String allEmails = element.findElement(By.xpath("td[5]")).getText();
            String allPhones = element.findElement(By.xpath("td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname)
                    .withAddress(address)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        int id = Integer.parseInt(wd.findElement(By.name("id")).getAttribute("value"));
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(id)
                .withFirstname(firstname)
                .withLastname(lastname)
                .withAddress(address)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }

    private void initContactModificationById(int id) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
    }

    public ContactData infoFromView(ContactData contact) {
        gotoContactViewById(contact.getId());
        String allInfo = wd.findElement(By.id("content")).getText();
        wd.navigate().back();
        return new ContactData()
                .withAllInfo(allInfo);
    }

    public void gotoContactViewById(int id) {
        click(By.cssSelector(String.format("a[href='view.php?id=%s']", id)));
    }

    public void addContactToGroup(int contactId, int groupId) {
        selectContact(contactId);
        selectGroupToAdd(groupId);
        submitAddition();
    }

    public void selectContact(int contactId) {
        click(By.cssSelector("input[id='" + contactId + "']"));
    }

    public void selectGroupToAdd(int groupId) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(groupId));
    }

    public void submitAddition() {
        click(By.xpath("//*[@value='Add to']"));
    }

    public void removeContactFromGroup(int contactId, int groupId) {
        selectGroupToRemoveFrom(groupId);
        selectContact(contactId);
        submitRemoval();
        isRemovalFinished();
    }

    public void selectGroupToRemoveFrom(int groupId){
        new Select(wd.findElement(By.name("group"))).selectByValue(Integer.toString(groupId));
    }

    private void submitRemoval() {
        click(By.name("remove"));
    }

    private void isRemovalFinished() {
        if (!isElementPresent(By.className("msgbox"))) {
            WebDriverWait wait = new WebDriverWait(wd, 5);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("msgbox")));
        }
    }
}
