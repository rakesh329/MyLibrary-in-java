import java.util.ArrayList;
/**
 * class Borrower the complete information about the Borrower exist.
 *
 * @author (Namballa Rakesh)
 * @version (v2,05/11/2020)
 */
public class Borrower 
{
    private String name;
    private int age;
    private int ID;
    private int borrowCount;
    private ArrayList<Book> books;

    /**
     * Default constructor for objects of class Borrower
     * initialising instance variables name,age,ID,borrowCount and arraylist of books
     */
    public Borrower()
    {
        books = new ArrayList<>();
        name = "";
        age = 0;
        ID = 0;
        borrowCount = 0;
    }

    /**
     * Non-default constructor for objects of class Borrower
     * @param newName name
     * @param newID ID
     * @param newAge age
     */

    public Borrower(String newName, int newID, int newAge) {
        books = new ArrayList<>();
        name = newName;
        ID = newID;
        age = newAge;
        borrowCount = 0;
    }

    /**
     * A display method - To display each borrower in an order defined with his books borrowed
     *
     */
    public void display() {
        System.out.println("Name : " + this.name + "( ID# : " + this.ID + " Age: " + this.age + ")");
        if (books.size() > 0)
        {
            System.out.println("Books on loan: ");
            for (int i = 0; i < books.size(); i++)
            {
                System.out.println("       " + (i + 1) + ". " + books.get(i).getTitle() + ", " + books.get(i).getAuthor()
                        + ", " + books.get(i).getRating());

            }
        } else
            {
            System.out.println("User has no books");
        }
    }

    /**
     * A displayManager method - To display the user names
     * @param position
     */
    public void displayManager(int position)
    {
        System.out.println("    " + (position + 1) + "." + " " + this.name);
    }
    
    /**
     * An Accessor getName - To know the name of the borrower
     * @return name
     */
    public String getName() {
        return name;
    }

    public int getBorrowCount() {
        return borrowCount;
    }
    
    /**
     * A Mutator setName - To set the Name for a borrower
     */
    public boolean setName(String newName) {
        if (newName.trim().length() > 0) {
            name = newName;
            return true;
        } else
            return false;
    }
    
    /**
     * An Accessor getId - To know the ID of the borrower
     * @return ID
     */
    public int getID() {
        return ID;
    }
    
    /**
     * A Mutator setID - To set the ID for a borrower
     */
    public boolean setID(int newID) {
        if (newID >= 1 && newID <= 100) {
            ID = newID;
            return true;
        } else
            return false;
    }
    
    /**
     * An Accessor getAge - To know the age of the borrower
     * @return age
     */
    public int getAge() {
        return age;
    }
    
    /**
     * A Mutator setAge - To set the Age for a borrower
     */
    public boolean setAge(int newAge)
    {
        if (newAge > 5 && newAge < 110) {
            age = newAge;
            return true;
        } else
            return false;

    }

    /**
     * A addBook method - To add the required book to the borrower
     * @param book book
     */
    public void addBook(Book book)
    {
        this.books.add(book);
        this.borrowCount = this.borrowCount + 1;
    }
    
    /**
     * A removeBook method - To remove the required book to the borrower
     * @param index book
     */
    public void removeBook(int index)
    {
        this.books.remove(index);
    }
    
    /**
     * An Accessor getAllBooks - To know all the books
     * @return books arraylist
     */
    public ArrayList<Book> getAllBooks()
    {
        return this.books;
    }
}

