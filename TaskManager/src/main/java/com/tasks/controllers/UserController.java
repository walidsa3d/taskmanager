package com.tasks.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.tasks.entities.User;
import com.tasks.services.IUserService;

@ManagedBean(name="userc", eager=true)
@SessionScoped
public class UserController {
	@EJB
	IUserService ius;
	private User selectUser;
	private User newUser;
	private List<User> userList;
	private User loggedUser;
	private boolean isLogged;
	private String password;
	private String username;
	private byte[] pic;
	private UploadedFile uFile;
	
	@PostConstruct
	public void init(){
		isLogged=false;
		setUserList(userList);
		newUser=new User();
		loggedUser=new User();
	}
	public User getSelectUser() {
		return selectUser;
	}
	public void setSelectUser(User selectUser) {
		this.selectUser = selectUser;
	}
	public User getNewUser() {
		return newUser;
	}
	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public String register(){
		newUser.setUserPic(pic);
		ius.save(newUser);
		return "/pages/task.jsf?faces-redirect=true";
	}
    public String login(){
    	loggedUser=ius.findUserByCredentials(username, password);
    	if(loggedUser !=null){
    		isLogged=true;
    		return "/pages/task.jsf?faces-redirect=true";
    		
    		 	}
    	else return "/pages/error.jsf?faces-redirect=true";
    	
    }
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/pages/login.jsf?faces-redirect=true";
    	
    }
	public User getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	public boolean isIslogged() {
		return isLogged;
	}
	public void setIslogged(boolean islogged) {
		this.isLogged = islogged;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void handleFileUpload(FileUploadEvent event) throws IOException {
            InputStream in = event.getFile().getInputstream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                bos.write(bytes, 0, read);
            }
            bytes = bos.toByteArray();
            pic=bytes;

    }
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public UploadedFile getuFile() {
		return uFile;
	}
	public void setuFile(UploadedFile uFile) {
		this.uFile = uFile;
	}

}
