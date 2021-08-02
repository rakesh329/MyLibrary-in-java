import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * class MyLibrary the complete logic about the Library exist
 *
 * @author (Namballa Rakesh)
 * @version (v3,05/11/2020)
 */

public class MyLibrary 
{
    private final BorrowerDatabase borrowerDatabase;
    private final BookDatabase bookDatabase;
    private int countBooks;

    /**
     * Default constructor for objects of class MyLibrary
     * initialising  variables borrowerDatabase,bookDatabase,counterBook
     */
    public MyLibrary()
    {
        bookDatabase = new BookDatabase();
        borrowerDatabase = new BorrowerDatabase(bookDatabase);
        countBooks = 0;
    }

    /**
     * A mainMenu method - To start the "MyLibrary"
     * Depending on the required options it executes various cases
     */
    public void mainMenu()
    {
        String option = "0";
        while (!option.equals("5"))
        {
            option = displayMainMenu();
            choice(option);
        }
    }

    /**
     * A borrowerMenu method - To manage Borrowers
     * Depending on the player options it executes various cases
     * @param currentBorrower - specify the borrower
     */
    public void borrowerMenu(Borrower currentBorrower)
    {
        String option = "0";
        while (!option.equals("4"))
        {
            option = displayMenu();
            method(option, currentBorrower);
        }
    }

    /**
     * A displayMainMenu method - To display the user all the options available in MyLibrary
     * @return the users selected option
     */
    public String displayMainMenu()
    {
        String option;
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the My Library");
        System.out.println("=================================");
        System.out.println("(1) Register New Borrower");
        System.out.println("(2) Manage Borrower");
        System.out.println("(3) List All Borrowers");
        System.out.println("(4) Display Help");
        System.out.println("(5) Exit Library");
        System.out.println("Choose an option: ");
        option = console.nextLine();
        return option;

    }
    
    /**
     * A choice method - To use the case depending on the selected option
     *
     * @param option parameter for a choosing the required case
     */
    public void choice(String option) {
        switch (option)
        {
            case "1":
                this.borrowerDatabase.addBorrower();
                break;
            case "2":
                this.borrowerDatabase.resistedBorrower();
                System.out.println("Select a number to manage the borrower : ");

                int name_pos = this.selectBorrower();


                Borrower currentBorrower = this.borrowerDatabase.getSelectedBorrower(name_pos);
                this.borrowerMenu(currentBorrower);
                break;
            case "3":
                this.borrowerDatabase.borrowerDetails();
                break;
            case "4":
                displayHelp();
                break;
            case "5":
                System.out.println("Exit");
                writeChanges();
                break;
            default:
                System.out.println("Enter between 1-5");
                break;
        }
    }

    /**
     * A writeChanges method - To update the given Borrower.txt file when exiting
     */

    private void writeChanges()
    {
        try {
            PrintWriter writer = new PrintWriter("Borrowers.txt");
            for (Borrower borrower : borrowerDatabase.getBorrowers())
            {
                writer.print(borrower.getName() + "," +
                        borrower.getID() + "," +
                        borrower.getAge());
                for (Book book : borrower.getAllBooks()) 
                {
                    writer.print("," + book.getTitle() + "," + book.getAuthor() + "," + book.getRating());
                }
                writer.print("\n");
            }
            writer.close();
        } catch (FileNotFoundException exception)
        {
            System.out.println("Error: The file output cant be created");
        }
    }

    /**
     *A displayMenu method - To display the user all the options to manage the borrower
     * @return borrower selected option
     */
    public String displayMenu()
    {
        String borrowerOption;
        System.out.println("Select an option: ");
        System.out.println("===================");
        System.out.println("(1) Borrow a Book");
        System.out.println("(2) Return a Book");
        System.out.println("(3) List Borrowed Books");
        System.out.println("(4) Return to Main Menu");
        Scanner console = new Scanner(System.in);
        borrowerOption = console.nextLine();
        return borrowerOption;

    }

    /**
     *  A selectBorrower method - To specify the required borrower to manage
     * @return selected number
     */
    public int selectBorrower()
    {
        int number;
        Scanner console = new Scanner(System.in);
        number = console.nextInt();
        return number;

    }

    /**
     * A method method - To use the cases depending on the selected option and specific borrower
     * @param option required option
     * @param currentBorrower specified borrower
     */
    public void method(String option, Borrower currentBorrower)
    {
        ArrayList<Book> allBooks = this.bookDatabase.getBooks();
        int i;
        switch (option)
        {
            case "1":
                System.out.println("**********Available Books********** ");
                for (i = 0; i < allBooks.size(); i++)
                {
                    System.out.println((i + 1)+"." + " " + allBooks.get(i).display());
                }

                int number = selectBook("borrow", i);
                Book selectedBook = allBooks.get(number - 1);

                countBooks += 1;
                if (countBooks > 2) {
                    System.out.println("Cannot borrow books more than twice on a single visit!");
                    break;
                }

                if (currentBorrower.getAge() > 18)
                {
                    currentBorrower.addBook(selectedBook);
                }
                else if (selectedBook.getRating().equals("General"))
                {
                    currentBorrower.addBook(selectedBook);
                }
                else
                    {
                    System.out.println("You cannot borrow books rated as Adult");
                    break;
                }

                break;
            case "2":
                if (currentBorrower.getAllBooks().size() == 0)
                {
                    System.out.println("No books assigned to return for user: " + currentBorrower.getName());
                    break;
                }
                System.out.println("Books on loan : ");
                for (i = 0; i < currentBorrower.getAllBooks().size(); i++)
                {
                    System.out.println((i + 1) + ". " + currentBorrower.getAllBooks().get(i).getTitle() + "," + currentBorrower.getAllBooks().get(i).getAuthor()+","+
                            currentBorrower.getAllBooks().get(i).getRating());
                }
                int bookNumber = selectBook("return", i);
                currentBorrower.removeBook(bookNumber - 1);
                break;
            case "3":
                currentBorrower.display();
                break;
            case "4":
                System.out.println("Exit to main menu");
                countBooks = 0;
                break;

            default:
                System.out.println("Enter between 1-4");
                break;
        }
    }

    /**
     * A selectBook method - select the book to borrow
     * @param option selected book
     * @param i variable to print index
     * @return the number of the book
     */
    public int selectBook(String option, int i)
    {
        System.out.println("Select the number to " + option + " the book : ");
        int number;
        Scanner console = new Scanner(System.in);
        number = console.nextInt();
        if (i >= number && number > 0) 
        {
            return number;
        } else
            {
            System.out.println("Please select options from above shown numbers only");
            return selectBook(option, i);
        }
    }

    /**
     * A display method - To provide information about the MyLibrary
     */
    public void displayHelp()
    {
        System.out.println("If you are a new borrower first register");
        System.out.println("Only two books can be borrowed at a time!");
        System.out.println("One book can be borrowed any number of times");
        System.out.println("You can check all the borrowed books ");
    }


}