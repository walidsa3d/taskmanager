package com.tasks.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
@NamedQueries({
	@NamedQuery(name="user.findAll",query="SELECT u FROM User u"),
	@NamedQuery(name="user.findByCreds",query="SELECT u FROM User u where u.userName=:username and u.userPassword=:password")
})

public class User {
	private int userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private List<Task> userTasks;
	private byte[] userPic;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@OneToMany(mappedBy="taskOwner",fetch=FetchType.EAGER)
	public List<Task> getUserTasks() {
		return userTasks;
	}
	public void setUserTasks(List<Task> userTasks) {
		this.userTasks = userTasks;
	}
	@Lob
	@Column(length=10000000)
	public byte[] getUserPic() {
		return userPic;
	}
	public void setUserPic(byte[] userPic) {
		this.userPic = userPic;
	}

}
