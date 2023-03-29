package com.email.module1;

public interface Email
{
	String generateEmail();
	String getPassword();
	void getDetails();
	void saveDataInDatabase(int id,String name1,String dept,int deptNumber,String email,String pass);
	void addEmployee();
	void getDataFromDataBase();
	void deleteDataFromDataBase(int id);

}



