package com.ionicbook.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.ionicbook.dao.UserDao;
import com.ionicbook.dao.UserDaoImpl;
import com.ionicbook.util.JsonResponse;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction {
	
	private JsonResponse jsonResponse = new JsonResponse();
	private String userAccount;
	private String userPassword;

	public String execute() throws IOException{
		Gson gson = new Gson();
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		UserDao dao = new UserDaoImpl();
		boolean logined = dao.login(userAccount, userPassword);
		
		if(logined) {
			jsonResponse.setStatus(200);
			int userId = dao.getUserIdByUserAccount(userAccount);
			jsonResponse.setUserId(userId);
		} else {
			jsonResponse.setStatus(500);
		}
	
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
	
	
}
