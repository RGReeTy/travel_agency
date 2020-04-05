package by.epam.pharmacy.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String login;
	private String password;
	private String email;
	private int accessLevel;
	private int account;
	
	private ArrayList<Product> pharmacyList = new ArrayList<>();// shoooppingCart

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public ArrayList<Product> getPharmacyList() {
		return pharmacyList;
	}

	public void setPharmacyList(ArrayList<Product> pharmacyList) {
		this.pharmacyList = pharmacyList;
	}

	public void addProduct(Product product) {
		pharmacyList.add(product);
	}

	public void removeProduct(Product product) {
		pharmacyList.remove(product);
	}

	public void removeAllProducts() {
		pharmacyList.removeAll(pharmacyList);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accessLevel;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + account;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pharmacyList == null) ? 0 : pharmacyList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accessLevel != other.accessLevel)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (account != other.account)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pharmacyList == null) {
			if (other.pharmacyList != null)
				return false;
		} else if (!pharmacyList.equals(other.pharmacyList))
			return false;
		return true;
	}
}