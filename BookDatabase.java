import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * class BookDatabase the complete information about the BookDatabase exist.
 *
 * @author (Namballa Rakesh)
 * @version (v2,05/11/2020)
 */

public class BookDatabase 
{
    private final ArrayList<Book> books;
    
    /**
     * Default constructor for objects of class BookDatabase
     * initialising  arraylist of books
     */
    public BookDatabase()
    {
        this.books = new ArrayList<>();
        this.readFile();
    }

    /**
     * A addBooks method - To add a book
     */
    public void addBooks()
    {
        this.books.add(new Book("hacking the world", "Moh", "Adult"));
    }

    /**
     * A addBooks method - To add a book specifying the title,author and rating
     * @param newTitle Title
     * @param newAuthor Author
     * @param newRating Rating
     */
    public void addBooks(String newTitle, String newAuthor, String newRating)
    {
        books.add(new Book(newTitle, newAuthor, newRating));
    }
    
    /**
     * A displayBooks method - to display all books
     */
    public void displayBooks()
    {
        for (Book currentBook : this.books)
            System.out.println(currentBook.display());
    }
    
    /**
     * A readFile method - Reads the txt file
     */
    public void readFile() {
        try {
            FileReader reader = new FileReader("books.txt");
            try 
            {
                Scanner parser = new Scanner(reader);
                while (parser.hasNextLine())
                {
                    String line = parser.nextLine();
                    String[] word = line.split(",");
                    Book book = new Book(word[0], word[1], word[2]);
                    this.books.add(book);
                }
            } 
            catch (Exception exception) 
            {
                System.out.println("Error: Unknown");
            } finally 
            {
                reader.close();
            }
        } 
        catch (FileNotFoundException exception) 
        {
            System.out.println("Error: File cant be found");
        } 
        catch (IOException exception) 
        {
            System.out.println("Error: cant close the file");
        }

    }

    /**
     * An Accessor getBook - To know the book
     * @param title title
     * @return currentBook
     */
    public Book getBook(String title)
    {
        for (Book currentBook : books)
        {
            if (currentBook.getTitle().equals(title))
            {
                return currentBook;
            }
        }
        return null;
    }
    
    /**
     * An Accessor getBooks - To know  the books
     * @return books arraylist
     */
    public ArrayList<Book> getBooks() {
        return books;
    }
}

