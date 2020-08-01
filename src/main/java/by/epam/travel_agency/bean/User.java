package by.epam.travel_agency.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type User.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -3860204572515015519L;

    private int idUser;
    private String login;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String phone;
    private int idDiscount;
    private int levelAccess;

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
    public int getIdUser() {
        return idUser;
    }

    /**
     * Sets id user.
     *
     * @param idUser the id user
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
    public int getIdDiscount() {
        return idDiscount;
    }

    /**
     * Sets id discount.
     *
     * @param idDiscount the id discount
     */
    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    /**
     * Gets level access.
     *
     * @return the level access
     */
    public int getLevelAccess() {
        return levelAccess;
    }

    /**
     * Sets level access.
     *
     * @param levelAccess the level access
     */
    public void setLevelAccess(int levelAccess) {
        this.levelAccess = levelAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser &&
                idDiscount == user.idDiscount &&
                levelAccess == user.levelAccess &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, login, email, firstname, lastname, phone, idDiscount, levelAccess);
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + idUser +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", id_discount=" + idDiscount +
                ", level_access=" + levelAccess +
                '}';
    }
}