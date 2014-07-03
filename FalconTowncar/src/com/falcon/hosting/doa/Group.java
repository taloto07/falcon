package com.falcon.hosting.doa;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the groups database table.
 * 
 */
@Entity
@Table(name="groups")
@NamedQuery(name="Group.findAll", query="SELECT g FROM Group g")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="group_name")
	private String groupName;

	//bi-directional many-to-one association to UsersGroup
	@OneToMany(mappedBy="group")
	private List<UsersGroup> usersGroups;

	public Group() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<UsersGroup> getUsersGroups() {
		return this.usersGroups;
	}

	public void setUsersGroups(List<UsersGroup> usersGroups) {
		this.usersGroups = usersGroups;
	}

	public UsersGroup addUsersGroup(UsersGroup usersGroup) {
		getUsersGroups().add(usersGroup);
		usersGroup.setGroup(this);

		return usersGroup;
	}

	public UsersGroup removeUsersGroup(UsersGroup usersGroup) {
		getUsersGroups().remove(usersGroup);
		usersGroup.setGroup(null);

		return usersGroup;
	}

}