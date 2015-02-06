package com.tasks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="task")
@NamedQueries({
	@NamedQuery(name="task.findAll",query="select t from Task t"),
	@NamedQuery(name="task.findByUserAndState", query="Select t from Task t WHERE t.taskOwner.userId=:id and t.taskState=:state"),
	@NamedQuery(name="task.findByState",query="Select t from Task t WHERE t.taskState=:state")
})
public class Task {
private int taskId;
private String taskName;
private String taskDescription;
private String taskPriority;
private String taskState;
private User taskOwner;

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
public int getTaskId() {
	return taskId;
}
public void setTaskId(int taskId) {
	this.taskId = taskId;
}
@Column(name="name")
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
@Column(name="description")
public String getTaskDescription() {
	return taskDescription;
}
public void setTaskDescription(String taskDescription) {
	this.taskDescription = taskDescription;
}
public String getTaskPriority() {
	return taskPriority;
}
public void setTaskPriority(String taskPriority) {
	this.taskPriority = taskPriority;
}
public String getTaskState() {
	return taskState;
}
public void setTaskState(String taskState) {
	this.taskState = taskState;
}
@ManyToOne
@JoinColumn(name="owner")
public User getTaskOwner() {
	return taskOwner;
}
public void setTaskOwner(User taskOwner) {
	this.taskOwner = taskOwner;
}
}
