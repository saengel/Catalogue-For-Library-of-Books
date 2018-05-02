/**
 * tester for library
 * @author sengel and CCrystal
 * @author we took some tips from the class debugging discussion with Nora, CC, SE, TG, IZ, and MN
 * @author Mrs Goldenberg wrote most of parse
 * @version 2/11/2016  
 *
 */
import java.io.*;
import java.util.*;
public class tester {

	public static void main(String[] args) throws IOException {
		
		//creates new Library object
		Library cAndSBooks = new Library ();
		readBooks(cAndSBooks);//what do we have when we call readBooks()?
		System.out.println("");
		
		//creating a customer
		cAndSBooks.addCustomer("Jimmy");
		Customer jimmy = cAndSBooks.findCustomer("Jimmy");	
		
		//checking out a few books
		cAndSBooks.checkoutTheBook("Jimmy", "The Monstrous Regiment");
		cAndSBooks.checkoutTheBook("Jimmy", "Cupertino High School 1997");
		System.out.println("\n****Books above are being checked out****\n");
		System.out.println("Jimmy has: "+jimmy.getBooks());
		cAndSBooks.findBookByTitle("The Monstrous Regiment").getDueDate();
		
		System.out.println("");
		
		System.out.println("Welcome to C and S Books, here's our Library!");
		System.out.println(cAndSBooks.toString()); //so we see the books we have
		

		
		//testing findAllByAuthor
		System.out.println("\nSearching for all the books written by Ann McCaffrey."
				+ "Expected result DragonSong, DragonSinger, DragonDrums");
		System.out.println(cAndSBooks.findAllByAuthor("Ann McCaffrey"));
		
		//testing findAllByAuthor when given wrong parameters, ERROR
		System.out.println("\nSearching for all the books written by Mrs.Goldenberg. "
				+ " Expected result is an error.");
		System.out.println(cAndSBooks.findAllByAuthor("Mrs. Goldenberg"));
		
		//testing find all by genre
		System.out.println("\nSearching for all the books in the fantasy genre."
				+ "Expected result DragonSong, DragonSinger, DragonDrums, Monstrous Regiment"
				+ ", Witches Abroad, Acheron.");
		System.out.println(cAndSBooks.findAllInGenre("Fantasy"));
		
		//testing findAllByGenre to get an ERROR
		System.out.println("\nSearching for all the books in the fine literature genre."
				+ "Expected result is an error");
		System.out.println(cAndSBooks.findAllInGenre("Fine Literature"));
	
		//testing findByTitle 
		System.out.println("\nSearching for The Monstrous Regiment. Expected result is "
				+ "The Monstrous Regiment");
		System.out.println(cAndSBooks.findBookByTitle("The Monstrous Regiment").getTitle());
		
		//testing findByTitle to get an error
		System.out.println("\nSearching for Harry Potter. Expected result is "
				+ "an error");
		cAndSBooks.findBookByTitle("Harry Potter");
		
		//testing isIn which requires a BOOK OBJ as a parameter, first find the book
		System.out.println("\nTesting isIn(), expected result is available");
		Book testBook = cAndSBooks.findBookByTitle("Acheron");
		cAndSBooks.isIn(testBook);
		
		//testing isIn with a checked out book
		System.out.println("\nTesting isIn, expected result is checked out");
		cAndSBooks.checkoutTheBook("Jimmy", testBook.getTitle());
		cAndSBooks.isIn(testBook);	
	
		//testing getAuthor for Acheron. Expected result is Sherilyn Kenyon
		System.out.println("\nTesting getAuthor for Acheron. "
				+ "Expected result is Sherilyn Kenyon");
		System.out.println(testBook.getAuthor());
		
		//testing getGenre for Acheron. Expected result is Fantasy
		System.out.println("\nTesting getGenre for Acheron. "
				+ "Expected result is Fantasy");
		System.out.println(testBook.getGenre());
		
		//testing getShelfNumber for Acheron. Expected result is bedroom 6
		System.out.println("\nTesting getShelfNumber for Acheron. "
				+ "Expected result is bedroom 6");
		System.out.println(testBook.getShelfNumber());
		
		//testing getTitle for Acheron. Expected result is Acheron
		System.out.println("\nTesting getTitle for for Acheron. "
				+ "Expected result is Acheron");
		System.out.println(testBook.getTitle());
		
		//testing whoHasMe for Acheron. Expected result is Jimmy
		System.out.println("\nTesting whoHasMe for for Acheron. "
				+ "Expected result is Jimmy");
		System.out.println(testBook.getWhoHasMe());
		
		//testing toString for Acheron.
		System.out.println("\nTesting toString for Acheron");
		System.out.println(testBook.toString());
		
		//testing getBooks() for jimmy.
		System.out.println("\nTesting getBooks, expected result- all the books Jimmy has checked out");
		System.out.println(jimmy.getBooks());
		
		//testing getCustomer() for jimmy.
		System.out.println("\nTesting getCustomer() for jimmy");
		System.out.println(jimmy.getCustomer());
		
		//testing toString for customer object jimmy
		System.out.println("\nTesting toString for jimmy");
		System.out.println(jimmy.toString());
		
		//testing find customer (string param) for Jimmy
		System.out.println("\nTesting find customer for Jimmy");
		System.out.println(cAndSBooks.findCustomer("Jimmy"));
		
		//testing find customer (string param) for Zehavushkua , RESULT IS ERROR
		System.out.println("\nTesting find customer for Zehavushkua");
		System.out.println(cAndSBooks.findCustomer("Zehavushkua"));
		
		//finding a book on shelf
		System.out.println("\nTesting to find Jews of Europe on a shelf, expecting living room 5");
		System.out.println(cAndSBooks.find("Jews of Europe"));
		
		//finding a book NOT in the library
		System.out.println("\nTesting to find a book on a shelf, expecting an error");
		System.out.println(cAndSBooks.find("Aliens of Europe"));
		
		//checking out a book
		Book monstrous = cAndSBooks.findBookByTitle("The Monstrous Regiment"); //finding a book
		monstrous.checkOut("Jimmy"); //updating books status
		cAndSBooks.checkoutTheBook(jimmy, monstrous);//updating library's records
		System.out.println("\nTesting to see if The Monstrous Regiment is checked in"
				+ ". Expected result is out");
		System.out.println( cAndSBooks.isIn(monstrous));
		
		//checking in the book
		monstrous.returnBook(monstrous.getTitle());//updating book's status
		cAndSBooks.checkIn(jimmy, monstrous);
		System.out.println("\nTesting to see if The Monstrous Regiment is checked in"
				+ ". Expected result is in");
		System.out.println(cAndSBooks.isIn(monstrous));
		

		
		//TODO
		/* BOOK:  getDueDate- do we have to checkItOut first?
		 * COMMENT IN ALL FILES, find TODOs and DOoooooooo
		 * 
		 * Try and break the methods and see what happens
		 * Do we still need to build a dueDate alert?
		 */
		
	}
	/**
	 * Reads from a file, saves it line by line, parses the lines so it can create a book obj
	 * @param cAndSbBooks
	 * @throws IOException
	 */
	static void readBooks(Library cAndSBooks) throws IOException{
		
		BufferedReader in = null; //buffer = could load

		try{
		
		in = new BufferedReader(new FileReader("C:\\Users\\Lighty\\workspace\\Library.txt"));

		String line;

		while( (line=in.readLine()) != null){
			System.out.println(line);//echo line you read - remove this line when the code works
			
			//parse the line into separate author, title, genre, and location strings
			//create a book using the 4 strings
			//save the book in books
			 
			int first = 0;
			int last = line.indexOf(',');
			String title = line.substring(first, last);
			line = line.substring (last +2); 
			last = line.indexOf(',');
			String auth = line.substring(first, last);
			line = line.substring (last +2); 
			last = line.indexOf(',');
			String gen = line.substring(first, last);
			String location = line.substring(last+2);
			Book enter = new Book (title, auth, gen, location);
			cAndSBooks.addBook(enter);
		}

		} finally{

		if(in != null)

		in.close();

		}

		}
}
