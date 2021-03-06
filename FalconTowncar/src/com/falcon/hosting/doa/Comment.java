package com.falcon.hosting.doa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.sun.jmx.snmp.Timestamp;


/**
 * The persistent class for the comments database table.
 * 
 */
@Entity
@Table(name="comments")
@NamedQueries({
	@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c"),
	@NamedQuery(name="Comment.findAllAsc", query="SELECT c FROM Comment c ORDER BY c.datetime ASC")
})

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String comment;

	//bi-directional many-to-one association to Job
	@ManyToOne
	@JoinColumn(name="jobs_id")
	private Job job;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	
	public Date getDate(){
		return datetime;
	}
	
	public void setDate(Date date){
		this.datetime = date;
	}

	public Comment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}