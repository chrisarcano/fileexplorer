package com.ocp.io;

import java.util.Map;

import lombok.Data;

import java.util.HashMap;

@Data
public class UserManager {
	
	Map<String, User> users = new HashMap<>();
	
	public void createUser(String name, String email) {
		users.put(email, User.builder().name(name).email(email).build());
		System.out.println(name + " was added. Current user count: " + users.size());
	}
	
}
