package addressbook.appmanager;


import addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getAddress());
        type(By.name("company"), contactData.getCompany());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
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
}
