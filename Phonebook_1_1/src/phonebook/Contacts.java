package phonebook;

import javafx.scene.image.Image;

public class Contacts {

    String firstname, lastname, email, address, title, organization, phone1, phone2;
    String image;

    @Override
    public String toString() {
        return firstname + "," + lastname + "," + email + "," + address + "," + title + "," + organization + "," + phone1 + "," + phone2 + "," + image + "-";

    }

    public String fullName() {
        return this.firstname + " " + this.lastname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone2() {
        return phone2;
    }

    public Contacts(String firstname, String lastname, String phone1, String phone2, String email, String address, String title, String organization, String image) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.title = title;
        this.organization = organization;
        this.image= image;

    }
}
