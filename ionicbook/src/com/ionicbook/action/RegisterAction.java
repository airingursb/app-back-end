package com.ionicbook.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.ionicbook.dao.UserDao;
import com.ionicbook.dao.UserDaoImpl;
import com.ionicbook.entity.User;
import com.ionicbook.util.JsonResponse;
import com.opensymphony.xwork2.ActionContext;

public class RegisterAction {

	private JsonResponse jsonResponse = new JsonResponse();
	private String userAccount;
	private String userPassword;
	private String userPhone;

	public String execute() throws IOException{
		Gson gson = new Gson();
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		UserDao dao = new UserDaoImpl();
		
		User user = new User();
		user.setUserAccount(userAccount);
		user.setUserPassword(userPassword);
		user.setUserPhone(userPhone);
		
		dao.saveUser(user);
	
		jsonResponse.setStatus(200);
		
		out.print(gson.toJson(jsonResponse));
		return null;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
	
	
}
