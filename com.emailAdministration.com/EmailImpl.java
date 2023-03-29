package com.email.module1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.email.customException.InvalidChoiceException;

public class EmailImpl implements Email
{
	private static int id = 130; 
	private static String firstName;
	private static String lastName;
	private static String password;
	private static String mail;
	private  static String department;
	private static int deptNumber;
	private static String companyName="voot";

	@Override
	public String generateEmail() 
	{
		mail =  firstName.toLowerCase()+lastName.toLowerCase()+"@"+companyName+".com";
		return mail;
	}

	@Override
	public String getPassword() 
	{
		String pass="";
		for(int i = 0;i<2;i++)
		{
			String a = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			pass+=a.charAt((int) (Math.random()*a.length()));
			String b = "1234567890";
			pass+=b.charAt((int) (Math.random()*b.length()));
			String c = "abcdefghijklmnopqrstuvwxyz";
			pass+=c.charAt((int) (Math.random()*c.length()));
			String d = "!@#$%&";
			pass+=d.charAt((int) (Math.random()*d.length()));
		}	
		password=pass;
		return pass;
	}

	@Override
	public void getDetails()
	{
		System.out.println("Id : "+id);
		System.out.println("Name : "+firstName+" "+lastName);
		System.out.println("Department : "+ department);
		System.out.println("Email : "+mail);
		System.out.println("Password : "+password);
		System.out.println("Department Number : "+deptNumber);

	}

	public void saveDataInDatabase(int id,String name1,String dept,int deptNumber,String email,String pass)
	{

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emaildatabase","root","root");
			PreparedStatement preparedStatement= connection.prepareStatement("insert into emplemail values(?,?,?,?,?,?)");// ? -> placeholder
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name1);
			preparedStatement.setString(3, dept);
			preparedStatement.setInt(4, deptNumber);
			preparedStatement.setString(5, email);
			preparedStatement.setString(6, pass);
			preparedStatement.execute();
			connection.close();
			System.out.println("Data Saved Succesfully");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void addEmployee() 
	{

		Scanner ip = new Scanner(System.in);
		System.out.println("Enter First Name : ");
		firstName = ip.next();
		System.out.println("Enter Last Name : ");
		lastName = ip.next();
		String lname = lastName.toLowerCase();
		System.out.println("Enter Department : ");
		department = ip.next();
		System.out.println("Enter Department Number : ");
		deptNumber = ip.nextInt();
		String name1 = firstName+" "+lastName;
		Email e1 = new EmailImpl();
		System.out.println("\nEnter tour Choice :\n1.Generate Mail and Generate Password\n2.Save Data Into DataBase \n3.Get Details \n4.Return to main menu");
		int choice = ip.nextInt();
		if(choice>0 && choice<5)
		{
			while(true)
			{
				switch(choice)
				{
				case 1 : 
					e1.generateEmail();
					e1.getPassword();
					System.out.println("Generated Succesfully..!");
					break;
				case 2 : 
					String m = e1.generateEmail();
					String p = e1.getPassword();
					e1.saveDataInDatabase(id, name1, department, deptNumber, m,p );
					break;
				case 3 :
					e1.getDetails();
					break;
				case 4 : 
					id++;
					return;
				default:
					System.out.println("Invalid Choice");
				}



			}
		}
		else
		{
			try {
				String mess = "Please Enter Valid Choice";
				throw new InvalidChoiceException(mess);
			}
			catch (Exception e) 
			{

				System.out.println(e.getMessage());
				System.out.println();

			}


		}



	}

	@Override
	public void getDataFromDataBase() 
	{

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/emaildatabase","root","root");
			PreparedStatement preparedStatement= connection.prepareStatement("select * from emplemail");
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) 
			{
				System.out.println("-------------------------------------------------------------------");
				System.out.println("Employee Id "+resultSet.getInt(1));
				System.out.println("Employee name "+resultSet.getString(2));
				System.out.println("Employee Department Name "+resultSet.getString(3));
				System.out.println("Employee Department Number "+resultSet.getInt(4));
				System.out.println("Employee Email "+resultSet.getString(5));
				System.out.println("Employee Password "+resultSet.getString(6));
				System.out.println("-------------------------------------------------------------------");
			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteDataFromDataBase(int id) 
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/emaildatabase","root","root");
			PreparedStatement preparedStatement= connection.prepareStatement("delete from emplemail where Empl_ID=?");
			preparedStatement.setInt(1,id);
			preparedStatement.execute();
			connection.close();
			System.out.println("Data Deleted");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}
}
