package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = -3860204572515015519L;
    private int id_user;
    private String login;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private int id_discount;
    private int level_access;

    public User() {

    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId_discount() {
        return id_discount;
    }

    public void setId_discount(int id_discount) {
        this.id_discount = id_discount;
    }

    public int getLevel_access() {
        return level_access;
    }

    public void setLevel_access(int level_access) {
        this.level_access = level_access;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id_user == user.id_user &&
                id_discount == user.id_discount &&
                level_access == user.level_access &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user, login, email, firstname, lastname, phone, id_discount, level_access);
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", id_discount=" + id_discount +
                ", level_access=" + level_access +
                '}';
    }
}