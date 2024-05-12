import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 
 * Interface for shortest path
 * 
 * @author tai
 *
 */
public class ShortestPathInterface extends ShortestPath {


  /***
   * 
   * Main menu method
   * 
   * 
   */
  public static void MainMenu() {
    System.out.println("MAIN MENU: ");
    System.out.println("1) Find edges for word");
    System.out.println("2) Find shortest path between two words");
    System.out.println("3) Find shortest path between two words using A*");

    System.out.println("Type 'exit' to quit");
    System.out.println("Choose an option: ");

  }



  /***
   * 
   * 
   * Main method for interface
   * 
   * @param <E>
   * @param args
   */
  public static <E> void main(String[] args) {

    System.out.println("Hello! Please wait...Initializing Dictionary..."); // Welcome user

    ArrayList<Vertex<E>> wordList = new ArrayList<Vertex<E>>(); // Create list of words
    Graph<E> wordMap; // create map for words

    initialize("Dict.txt", wordList); // initialize text file into list
    wordMap = GraphConstructor(wordList); // initialize list into map
    System.out.println("Dictionary Initialized."); // tell user the dictionary is initialized


    while (true) { // while true...

      BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); // create
                                                                                   // buffered
                                                                                   // reader (input)

      MainMenu(); // run main menu

      String choice; // create string to hold user's choice

      try { // try this...
        choice = input.readLine().trim(); // trim space from choice
        if (choice.contains("1")) { // if choice is 1

          System.out.println("Please type your word: "); // prompt user to submit a word
          String word = input.readLine().trim(); // trim space from word
          if (word.equalsIgnoreCase("exit")) { // if user chooses to exit...
            break; // end loop
          }

          printEdges(wordMap, word); // print edges based on user's choice


        } else if (choice.contains("2")) { // if choice is 2
          System.out.println("Please type your first word: "); // prompt user to submit a word
          String word = input.readLine().trim(); // trim space from word
          System.out.println("Please type your second word: "); // prompt user to submit a word
          String word2 = input.readLine().trim();// trim space from word

          if (word.equalsIgnoreCase("exit")) { // if user chooses to exit...
            break; // end loop
          }

          findShortestPath(wordMap, word, word2); // print edges


        } else if (choice.equalsIgnoreCase("3")) { // if user chooses to exit...
          System.out.println("Please type your first word: "); // prompt user to submit a word
          String word = input.readLine().trim(); // trim space from word
          System.out.println("Please type your second word: "); // prompt user to submit a word
          String word2 = input.readLine().trim();// trim space from word

          if (word.equalsIgnoreCase("exit")) { // if user chooses to exit...
            break; // end loop
          }
          
          findShortestPathA(wordMap, word, word2); // print edges

        }
        
        
        else if (choice.equalsIgnoreCase("exit")) { // if user chooses to exit...
          break; // end program
        }


        else { // if user types anything other than 1 or 2...
          System.out.println("Invalid choice."); // print invalid choice
        }
      } catch (IOException e) { // catch errors
        e.printStackTrace(); // print errors
      }



    }
    System.out.print("Goodbye!"); // tell user goodbye



  }
}
