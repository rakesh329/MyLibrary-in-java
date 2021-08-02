import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * class BorrowerDatabase the complete information about the BorrowerDatabase exist.
 *
 * @author (Namballa Rakesh)
 * @version (v4,06/11/2020)
 */

public class BorrowerDatabase 
{
    private ArrayList<Borrower> borrowers;
    String name;
    int ID;
    int age;
    /**
     * A BorrowerDatabase method
     * @param bookDatabase
     */

    public BorrowerDatabase(BookDatabase bookDatabase)
    {
        this.borrowers = new ArrayList<>();
        name = "";
        ID = 0;
        age = 0;
        this.readFile(bookDatabase);
    }

    /**
     * A addBorrower method to add a new borrower
     */
    public void addBorrower()
    {
        name = enterName();
        ID = enterID();
        age = enterAge();
        borrowers.add(new Borrower(name, ID, age));
    }
    
    /**
     * A addBorrower method - takes the required parameters and add the borrower
     * @param newName name
     * @param newID Id
     * @param newAge age
     * @return currentBorrower
     */
    public Borrower addBorrower(String newName, int newID, int newAge)
    {
        Borrower currentBorrower = new Borrower(newName, newID, newAge);
        this.borrowers.add(currentBorrower);
        return currentBorrower;
    }

    /**
     * A borrowerDetails method - shows the details of the borrower
     */

    public void borrowerDetails()
    {
        displayBorrower();
    }

    /**
     * A displayBorrower method - display all the borrower
     */
    public void displayBorrower()
    {
        for (Borrower Borrower : borrowers)
            Borrower.display();
    }

    /**
     * a enterName method - validates the input name and take the name
     * @return name
     */
    public String enterName()
    {
        int i = 0;
        Scanner console = new Scanner(System.in);
        System.out.print("Enter name: ");
        name = console.nextLine().trim();
        if (name.isEmpty())
        {
            System.out.println("Name cannot be empty!");
            name = enterName();
        }
        char[] chars = name.toCharArray();
        if (name.contains(","))
        {
            System.out.println("Please avoid comas!");
            name = enterName();
        }
        for (i = 0; i < borrowers.size(); i++)
        {
            if (borrowers.get(i).getName().equals(name))
            {
                System.out.println("Sorry!, Name already exist try different name");
                name = enterName();
            }
        }
        for (char c : chars)
        {
            if ((!Character.isLetter(c) && !Character.isWhitespace(c)) && Character.isDigit(c)) {
                System.out.println("Sorry!, Name can only contain Alphabets and Spaces");
                name = enterName();
            }
        }
        return name;

    }
    
    /**
     * a enterID method - validates the input ID and take the ID
     * @return ID
     */
    public int enterID()
    {
        int i = 0;
        String id_from_user;
        Scanner console = new Scanner(System.in);
        System.out.print("Enter ID: ");
        try {
            ID = console.nextInt();
        }
        catch (Exception e)
        {
            System.out.println("Prove numerical values only for ID");
            ID = enterID();
        }
        if (ID == 0) {
            System.out.println("ID cannot be empty!");
            ID = enterID();
        }
        if (ID < 1 || ID > 100) {
            System.out.println("ID should be in the Range of 0 to 100");
            System.out.print("Enter ID: ");
            ID = enterID();
        }
        for (i = 0; i < borrowers.size(); i++)
        {
            if (borrowers.get(i).getID() == ID)
            {
                System.out.println("Sorry!, ID already exists, enter different ID");
                ID = enterID();
            }
        }
        return ID;
    }
    
    /**
     * a enterAge method - validates the input age and take the Age
     * @return Age
     */
    public int enterAge()
    {
        int i = 0;
        Scanner console = new Scanner(System.in);
        System.out.print("Enter your age: ");

        try
        {
            age = console.nextInt();
        }
        catch (Exception e)
        {
            System.out.println("Provide numerical values only for Age");
            age = enterAge();
        }
        if (ID == 0)
        {
            System.out.println("AGE cannot be empty!");
            ID = enterID();
        }
        if (age < 5 || age > 110)
        {
            System.out.println("Age should be in the Range of 5 to 110");
            age = enterAge();
        }
        return age;
    }
    
    /**
     * A resistedBorrower method - Shows all the registered borrowers
     */
    public void resistedBorrower()
    {
        System.out.println("These are the registered borrowers");
        System.out.println("************************************");
        for (int i = 0; i < borrowers.size(); i++)
        {
            borrowers.get(i).displayManager(i);
        }
    }

    /**
     * A getSelectedBorrower method - get the borrower depending on the position
     * @param pos
     * @return
     */
    public Borrower getSelectedBorrower(int pos)
    {
        return this.borrowers.get(pos - 1);
    }
    
    /**
     * A readFile method - Reads the txt file
     */
    public void readFile(BookDatabase bookDatabase)
    {
        try {
            FileReader reader = new FileReader("Borrowers.txt");

            try {
                Scanner parser = new Scanner(reader);
                while (parser.hasNextLine()) 
                {
                    String line = parser.nextLine();
                    String[] word = line.split(",");
                    Borrower currentBorrower = this.addBorrower(word[0], Integer.parseInt(word[1]), Integer.parseInt(word[2]));
                    for (int i = 1; word.length / 3 > i; i++) 
                    {
                        currentBorrower.addBook(bookDatabase.getBook(word[3 * i]));
                    }
                }

            } 
            catch (Exception exception)
            {
                System.out.println("Error: Unknown" + exception);
            } 
            finally
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
     * An Accessor getBorrowers - To know  the borrowers
     * @return borrowers
     */

    public ArrayList<Borrower> getBorrowers()
    {
        return borrowers;
    }
}
