package ie.lyit.testers;

import java.io.*;
import ie.lyit.serialize.*;
import java.io.Serializable;

public class CustomerSerializerTester implements Serializable {


	public static void main(String[] args)
	{
		//array
		CustomerSerializer customerSerializer = new CustomerSerializer();

		//new menu
		Menu menuObj = new Menu();
		do{
			menuObj.display();
			menuObj.readOption();

			//menu options
			switch(menuObj.getOption())
			{
			case 1: customerSerializer.add();break;
			case 2: customerSerializer.list();break;
			case 3: customerSerializer.view();break;
			case 4: customerSerializer.edit();break;
			case 5: customerSerializer.delete();break;
			case 6: break;
			default:System.out.println("Invalid Option");
			}


		}while(menuObj.getOption() !=6);

		customerSerializer.writeRecordsToFile();
	}
}
