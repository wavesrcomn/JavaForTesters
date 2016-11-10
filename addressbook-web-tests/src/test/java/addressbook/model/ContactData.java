package addressbook.model;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String address;
    private final String company;
    private final String mobile;
    private final String email;
    private final String byear;
    private String group;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String address, String company, String mobile, String email, String byear, String group) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.address = address;
        this.company = company;
        this.mobile = mobile;
        this.email = email;
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

    public String getByear() {
        return byear;
    }

    public String getGroup() {
        return group;
    }
}
