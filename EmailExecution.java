package com.email.module1;
import java.util.Scanner;

import com.email.customException.InvalidChoiceException;
public class EmailExecution 
{



	public static void main(String[] args)
	{
		Email e1 = new EmailImpl();
		while(true)
		{
			Scanner ip = new Scanner(System.in);
			System.out.println("Enter tour Choice :\n1.Add Employe\n2.Get Data From DataBase\n3.Delete Data From Database\n4.exit");
			int choice =ip.nextInt();
			if(choice>0 && choice<5)
			{
				switch(choice)
				{
				case 1 : 
					e1.addEmployee();
					break;
				case 2 : e1.getDataFromDataBase();
				break;
				case 3 :System.out.println("Enter Employe Id");
				e1.deleteDataFromDataBase(ip.nextInt());
				break;
				case 4 : 
					System.out.println("Thank You....!");
					System.exit(0);
				default:
					System.out.println("Invalid Choice");
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

	}
}
