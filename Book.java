/**
 * class Book the complete information about the Book exist.
 *
 * @author (Namballa Rakesh)
 * @version (v3,05/11/2020)
 */
public class Book 
{
    private String title;
    private String author;
    private String rating;
    /**
     * Default constructor for objects of class Book
     * initialising instance variables title,author,rating
     */
    public Book() 
    {
        title = "XXX";
        author = "YYY";
        rating = "ZZZ";
    }
    
    /**
     * Non-default constructor for objects of class Dice
     * @param newTitle title
     * @param newAuthor author
     * @param newRating rating
     */
    public Book(String newTitle, String newAuthor, String newRating)
    {
        if (newTitle.trim().length() > 0 && newAuthor.trim().length() > 0 && newRating.trim().length() > 0) {
            title = newTitle;
            author = newAuthor;
            rating = newRating;
        }
        else {
            title = "XXX";
            author = "YYY";
            rating = "ZZZ";

        }
    }

    /**
     * A display method - To display each book in an order defined
     * @return title,author,rating
     */
    public String display()
    {
        return ( title + ", " + author  + ", "  + rating);
    }

    /**
     * An Accessor getTitle - To know the title of the book
     * @return title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * An Accessor getAuthor - To know the author of the book
     * @return author
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * An Accessor getRating - To know the Rating of the book
     * @return rating
     */
    public String getRating(){
        return rating;
    }
    
    /**
     * A Mutator setTitle - To set the Title for a book
     */
    public boolean setTitle(String newTitle){
        if(newTitle.trim().length() > 0){
            title = newTitle;
            return true;
        }
        else
            return false;

    }
    /**
     * A Mutator setAuthor - To set the Author for a book
     */
    public boolean setAuthor(String newAuthor){
        if(newAuthor.trim().length() > 0)
        {
            author = newAuthor;
            return true;
        }
        else
            return false;

    }
   
    /**
     * A Mutator setRating - To set the Author for a book
     */
    public boolean setRating(String newRating){
        if(newRating.trim().length() > 0)
        {
            if (newRating.equals("General") || newRating.equals("Adult"))
            {
                rating = newRating;
            }
            else {
                System.out.println("Rating can only be either 'General' or 'Adult'");
            }
            return true;
        }
        else
            return false;


    }

}