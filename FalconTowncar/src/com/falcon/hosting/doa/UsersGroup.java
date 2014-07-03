package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users_groups database table.
 * 
 */
@Entity
@Table(name="users_groups")
@NamedQuery(name="UsersGroup.findAll", query="SELECT u FROM UsersGroup u")
public class UsersGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Group
	@ManyToOne
	private Group group;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public UsersGroup() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}