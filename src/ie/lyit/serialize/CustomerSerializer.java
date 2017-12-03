package ie.lyit.serialize;
import java.util.ArrayList;
import ie.lyit.hotel.Customer;
import java.io.*;
import java.util.*;

public class CustomerSerializer {

	private final String FILENAME = "cust.ser";	

	private ArrayList <Customer> customers;


	// Default Constructor
	public CustomerSerializer(){

		// Construct customer ArrayList
		customers = new ArrayList<Customer>();
	}


	public void add(){
		// Create a customer object
		Customer customer = new Customer();
		// Read its details
		customer.read();	
		// And add it to the customer ArrayList
		customers.add(customer);
	}


	public void list(){
		// for every customer object in customer
		for(Customer tmpCustomer:customers)
			// display it
			System.out.println(tmpCustomer);
	}


	public Customer view(){
		Scanner keyboard = new Scanner(System.in);		

		// Read the number of the book to be viewed from the user
		System.out.println("ENTER NUMBER OF Customers : ");
		int customerToView=keyboard.nextInt();

		// for every customer object in customer
		for(Customer tmpCustomer:customers){
			// if it's number equals the number of the customerToView
			if(tmpCustomer.getNumber() == customerToView){
				// display it
				System.out.println(tmpCustomer);
				return tmpCustomer;
			}
		}
		// if we reach this code the customer was not found so return null
		return null;		
	}


	public void delete(){	
		// Call view() to find, display, & return the customer to delete
		Customer tempCustomer = view();
		// If the customer != null, i.e. it was found then...
		if(tempCustomer != null)
			// ...remove it from customer
			customers.remove(tempCustomer);
	}


	public void edit(){	
		// Call view() to find, display, & return the customer to edit
		Customer tempCustomer = view();
		// If the customer != null, i.e. it was found then...
		if(tempCustomer != null){
			// get it's index
			int index=customers.indexOf(tempCustomer);
			// read in a new book and...
			tempCustomer.read();
			// reset the object in books
			customers.set(index, tempCustomer);
		}
	}



	public void writeRecordsToFile(){
		ObjectOutputStream os=null;
		try {
			// Serialize the ArrayList...
			FileOutputStream fileStream = new FileOutputStream(FILENAME);

			os = new ObjectOutputStream(fileStream);

			os.writeObject(customers);
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file to store customer.");
		}
		catch(IOException ioE){
			System.out.println(ioE.getMessage());
		}
		finally {
			try {
				os.close();
			}
			catch(IOException ioE){
				System.out.println(ioE.getMessage());
			}
		}
	}

	// This method will deserialize the customer ArrayList when called, 
	// i.e. it will restore the ArrayList from the file cust.ser
	@SuppressWarnings("unchecked")
	public void readRecordsFromFile(){
		ObjectInputStream is=null;

		try {
			// Deserialize the ArrayList...
			FileInputStream fileStream = new FileInputStream(FILENAME);

			is = new ObjectInputStream(fileStream);

			customers = (ArrayList<Customer>)is.readObject();	
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file to store customer.");
		}
		catch(IOException ioE){
			System.out.println(ioE.getMessage());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}


	}
}
