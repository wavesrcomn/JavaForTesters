package addressbook.model;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String title;
    private final String address;
    private final String company;
    private final String mobile;
    private final String email;
    private final String email2;
    private final String email3;
    private final String byear;
    private final String group;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String title, String address, String company, String mobile, String email, String email2, String email3, String byear, String group) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.title = title;
        this.address = address;
        this.company = company;
        this.mobile = mobile;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.byear = byear;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAddress() {
        return address;
    }

    public String getCompany() {
        return company;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getByear() {
        return byear;
    }

    public String getGroup() {
        return group;
    }

    public String getTitle() {
        return title;
    }
}
