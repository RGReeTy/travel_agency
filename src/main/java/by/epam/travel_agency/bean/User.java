package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type User.
 */
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

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Gets id user.
     *
     * @return the id user
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * Sets id user.
     *
     * @param id_user the id user
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets id discount.
     *
     * @return the id discount
     */
    public int getId_discount() {
        return id_discount;
    }

    /**
     * Sets id discount.
     *
     * @param id_discount the id discount
     */
    public void setId_discount(int id_discount) {
        this.id_discount = id_discount;
    }

    /**
     * Gets level access.
     *
     * @return the level access
     */
    public int getLevel_access() {
        return level_access;
    }

    /**
     * Sets level access.
     *
     * @param level_access the level access
     */
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