package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.findByEmail", query="SELECT u FROM User u where u.email = :email")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	//bi-directional many-to-one association to UsersGroup
	@OneToMany(mappedBy="user")
	private List<UsersGroup> usersGroups;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UsersGroup> getUsersGroups() {
		return this.usersGroups;
	}

	public void setUsersGroups(List<UsersGroup> usersGroups) {
		this.usersGroups = usersGroups;
	}

	public UsersGroup addUsersGroup(UsersGroup usersGroup) {
		getUsersGroups().add(usersGroup);
		usersGroup.setUser(this);

		return usersGroup;
	}

	public UsersGroup removeUsersGroup(UsersGroup usersGroup) {
		getUsersGroups().remove(usersGroup);
		usersGroup.setUser(null);

		return usersGroup;
	}

}