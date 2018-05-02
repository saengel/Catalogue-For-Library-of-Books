import java.util.ArrayList;

/***
 * Library Class
 * @author Sarah Engel and Chana Crystal
 * @version 2/11/2016
 *
 */
public class Library 
{
	private ArrayList <Book> books; //to hold our book objects
	private ArrayList <Customer> clients; //to hold our customer objects
	
	/***
	 * Constructor
	 * @param none
	 */
	public Library()
	{
		books = new ArrayList <Book> ();
		clients = new ArrayList <Customer> ();		
	}
	
	//ACCESSORS
	
	/**
	 * find book based on author
	 * @param author
	 * @return ArrayList of books written by that author
	 */
	public ArrayList <Book> findAllByAuthor(String author){
		ArrayList <Book> writtenByThisAuthor = new ArrayList <Book> ();
		for (int i = 0; i < books.size(); i++)
		{
			if (books.get(i).getAuthor().equals(author))
			{
				 writtenByThisAuthor.add(books.get(i)); 
			}
		}
		
		if (writtenByThisAuthor.size() == 0)
		{
			System.out.println(author + " is not found in our library");
			return null;	
		}
		
		return writtenByThisAuthor;

	}
	
	/**
	 * find book based on title
	 * @param title
	 * @return book
	 */
	public Book findBookByTitle(String title){
		Book foundBook; //initializing our return object
		for (int i = 0; i < books.size(); i++)
		{
			if (books.get(i).getTitle().equals(title))
			{
				foundBook = books.get(i);
				return foundBook;
			}
		}
		System.out.println("This book does not exist in our library");
		return null;
	}
	
	/**
	 * find book based on genre
	 * @param genre
	 * @return ArrayList of books in that genre
	 */
	public ArrayList <Book> findAllInGenre(String genre){
		ArrayList <Book> allInGenre = new ArrayList <Book> ();
		for (int i = 0; i < books.size(); i++)
		{
			if (books.get(i).getGenre().equals(genre))
			{
				allInGenre.add(books.get(i)); 
				
			}
		}
		if (allInGenre.isEmpty())
		{
			System.out.println(genre + " is not found in our library");
			return null;
		}
		
		return allInGenre;
		
	}
	
	/***
	 * This method finds where the book is physically located
	 * @param title
	 * @return shelfNumber
	 */
	public String find(String title)
	{
		Book desiredObject = findBookByTitle(title); //finding the book by title, yields a book obj
		if(desiredObject == null)
		{
			System.out.println("error! Book not found");
			return null;
		}
		else
		{
		return desiredObject.getShelfNumber(); //returns the found book's shelfNumber
		}
	}
	
	/***
	 * Finding a customer
	 * @param customerName
	 * @return customer
	 */
	public Customer findCustomer (String customerName)
	{
		Customer foundCustomer; //initializing our return object
		for (int i = 0; i < clients.size(); i++)
		{
			if (clients.get(i).getCustomer().equals(customerName))
			{
				foundCustomer = clients.get(i);
				return foundCustomer;
			}
		}
		System.out.println(customerName + " does not have an account with us.");
		return null;
	}
	
	
	/**
	 * Checking out a book
	 * @param customerName
	 * @param bookName
	 * @return void
	 */
	public void checkoutTheBook (String customerName, String bookName)
	{
		Book toCheckout = findBookByTitle(bookName);
		Customer gettingBook = findCustomer(customerName);
		if (isIn(toCheckout) == true)
		{
			toCheckout.checkOut(customerName);
			gettingBook.checkingOut(toCheckout);
		}
		else
		{
			System.out.println("Book is already out");
			return;
		}
	}
	
	/**
	 * Checking IN a book
	 * @param customerName
	 * @param bookName
	 * @return void
	 */
	public void checkIn(String customerName, String bookName)
	{
		//TODO (if we have time )is book late? tell customer!
		Customer client = findCustomer(customerName);
		Book newBook = findBookByTitle(bookName);
		
		if(client == null || newBook == null )
		{
			System.out.println("Error");
			return;
		}
		
		client.checkIn(newBook);
		newBook.returnBook(customerName);
	}
	
	/**
	 * Checking IN a book
	 * @param customerOBJ
	 * @param bookOBJ
	 * @return void
	 */
	public void checkIn(Customer c, Book b)
	{

		if(c == null || b == null )
		{
			System.out.println("Error");
			return;
		}
		
		c.checkIn(b);
		b.returnBook(c.getCustomer());
	}
	
	/**
	 * Checking out a book calling by object
	 * @param cust, Customer object
	 * @param bookToCheckOut, Book object
	 * @return void
	 */
	public void checkoutTheBook(Customer cust, Book bookToCheckOut)
	{
		if (isIn(bookToCheckOut)){
			cust.checkingOut(bookToCheckOut);
			bookToCheckOut.checkOut(cust.getCustomer());
		}
		else{
			System.out.println("This book is unavailable");
		}
		
	}
	
	
	/***
	 * Checks if the books is available in the library to be checked out
	 * @param book
	 * @return boolean T/F, t = in, f = out
	 */
	public boolean isIn(Book book)
	{
		boolean status = true;
		if(book.getStatus()== false)
		{
			System.out.println(book.getTitle() + " is already checked out");
			status = false;
		}
		else if (book.getStatus()== true)
		{
			System.out.println(book.getTitle() + " is available");
			status = true;
		}
		
		return status;
	}
	
	/***
	 * toString method, prints all the books in the library (author, title and genre)
	 * prints all the customer in the library and all their checked out books.
	 */
	public String toString()
	{
		String result = "These books are in the Library: ";
		for (int i = 0; i < books.size(); i++)
		{
			result += "\n" + books.get(i).getTitle() + " by " + books.get(i).getAuthor() + " " 
					+ "of the " + books.get(i).getGenre() + " genre \n" + "in the following"
					+ " location: " + books.get(i).getShelfNumber();
		}
		result += "\n ----------------------------------- \nThese are the Library's customers:";
		for (int i = 0; i < clients.size(); i++)
		{
			result += "\n" + clients.get(i).getCustomer() + " has the following books checked out " 
					+ clients.get(i).getBooks() + ". \n";
		}

		return result;
	}
	
	/**
	 * ADD
	 * @param addition
	 */
	public void addBook(Book addition)
	{
		books.add(addition);	
	}	
	
	/**
	 * add
	 * @param name
	 */
	public void addCustomer(String name)
	{
		for (int i = 0; i < clients.size(); i++)
		{
				if (clients.get(i).getCustomer().equals(name))
				{
					System.out.println("You're already registered");
				}
				else
				{
					Customer enter = new Customer (name);
					clients.add(enter);
				}
		}
		//teacher edit:
		Customer enter = new Customer (name);
		clients.add(enter);
	}
}

