import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/***
 * 
 * @author tai
 *
 */
public class ShortestPath {

  /***
   * 
   * Method that initializes the graph
   * 
   * @param <E>
   * @param filename
   * @param wordList
   * @return
   */
  public static <E> ArrayList<Vertex<E>> initialize(String filename,
      ArrayList<Vertex<E>> wordList) {

    // read file and load words into the HashMap
    File f = new File(filename); // create new File
    Scanner theScanner = null; // create new Scanner

    try {
      theScanner = new Scanner(f); // initialize Scanner
      while (theScanner.hasNextLine()) { // While you are not done reading the file...
        String line = theScanner.nextLine(); // set this as next line in file

        Vertex<E> newVertex = new Vertex<E>(); // Create a new Vertex object for each word
        newVertex.setWord(line); // set word in vertex
        wordList.add(newVertex); // add vertex to wordList
      }

    } catch (IOException ex) { // catch errors
      ex.printStackTrace(); // print errors
    } finally {
      if (theScanner != null) { // if scanner is not null...
        theScanner.close(); // close scanner
      }
    }
    return wordList;
  }

  /***
   * 
   * Method that constructs the graph of words
   * 
   * @param <E>
   * @return
   */
  public static <E> Graph<E> GraphConstructor(ArrayList<Vertex<E>> wordList) {

    Graph<E> toReturn = new Graph<E>(); // initialize graph to return as the map of words

    int counter = 0; // counter starts at 0
    int weight = 0; // weight starts at 0


    for (Vertex<E> word : wordList) {// for each vertex in the list of words...
      for (Vertex<E> checkWord : wordList) {// for each vertex in the list of words..
        if (!word.getWord().equals(checkWord.getWord())) { // if it is not the exact same word...
          if (word.getWord().length() == checkWord.getWord().length()) {
            counter = 0; // reset counter to 0
            weight = 0; // reset weight to 0
            for (int c = 0; c < word.getWord().length(); c++) { // for each character in the word...
              if (counter < 2) {
                if (word.getWord().charAt(c) != checkWord.getWord().charAt(c)) { // if characters
                                                                                 // are different...
                  weight = Math
                      .abs((int) word.getWord().charAt(c) - (int) checkWord.getWord().charAt(c)); // find the difference in the character values
                  counter++; //add one to counter
                }
              } else { //if the characters are the same...
                break; //break from loop
              }
            }

            if (counter == 1) { // if there is EXACTLY one different character...
              Edge<E> toAdd = new Edge<E>(); // create edge to add
              toAdd.setNeighbor(checkWord); // set the vertex of the edge
              toAdd.setWeight(weight); // set the weight of the edge to the difference in values between the characters
              word.addEdge(toAdd); // add edge to word

            }



          }
        }


      }
      System.out.println(word.getWord()); // print word once initialized
      toReturn.add(word); // add word to graph



    }
    return toReturn; // return graph

  }

  /***
   * 
   * Method that finds the shortest path
   * 
   * @param <E>
   * @param wordMap
   * @param startWord
   * @param targetWord
   */
  public static <E> void findShortestPath(Graph<E> wordMap, String startWord, String targetWord) {
    
    
    
    Vertex<E> startVertex = wordMap.getVertex(startWord); // create start vertex for shortest path
    if (startVertex == null) { // if the start vertex is null...
      System.out.println("Start word \"" + startWord + "\" not found in the dictionary."); // word not found
      return; // end search
    }

    Vertex<E> targetVertex = wordMap.getVertex(targetWord); // create the end vertex for shortest path
    if (targetVertex == null) { // if end vertex is null...
      System.out.println("Target word \"" + targetWord + "\" not found in the dictionary."); // word not found
      return; // end search
    }

    
    
    if (targetVertex.getWord().length() != startVertex.getWord().length()) {
      System.out.println("Words \"" + startWord + "\" and \"" + targetWord + "\" are not the same length. Please input two words that are the same length. "); // word not found
      return; //end search
    }
    Map<Vertex<E>, Double> distances = new HashMap<>(); //create map of distances
    Map<Vertex<E>, Vertex<E>> previous = new HashMap<>(); //create map of root vertices
    PriorityQueue<Vertex<E>> pq =
        new PriorityQueue<>((v1, v2) -> Double.compare(distances.get(v1), distances.get(v2))); // create priority queue of visited vertices

    // Initialize distances to infinity and previous vertices to null
    for (Vertex<E> vertex : wordMap.getVertices()) { // for each vertex in the map
      distances.put(vertex, Double.POSITIVE_INFINITY); // set each initial value to infinity
      previous.put(vertex, null); // set each root to null
    }

    distances.put(startVertex, 0.0); // add the start vertex + distance (which is always 0)
    pq.offer(startVertex); // insert start vertex in priority queue

    while (!pq.isEmpty()) { // while the priority queue is NOT empty...
      Vertex<E> currentVertex = pq.poll();  // the current vertex is removed + retrieved from queue
      if (currentVertex.equals(targetVertex)) { // if the current vertex is the end vertex...
        printShortestPath(previous, targetVertex, distances.get(targetVertex)); //print path
        return; // end search
      }
      
      printShortestPath(previous, targetVertex, distances.get(targetVertex)); //print path anyways to check how many paths are being checked


      for (Edge<E> edge : currentVertex.getEdges()) { // for each edge of the current vertex...
        Vertex<E> neighborVertex = edge.getNeighbor(); // set the neighbor vertex to the neighbor of the current edge
        double edgeWeight = edge.getWeight(); // get the weight of that edge
        
        
         
        
        double distanceThroughCurrent = distances.get(currentVertex) + edgeWeight; //add the current vertex distance with the current edge weight

        if (distanceThroughCurrent < distances.get(neighborVertex)) { // if the distances through the current is less than the distance of the neighbor...
          distances.put(neighborVertex, distanceThroughCurrent); //add the neighbor vertex to distances, and set its distance to the distances through the current
          previous.put(neighborVertex, currentVertex); //add the neighbor vertex to the list of visited vertices
          pq.offer(neighborVertex); // insert the neighbor vertex in the priority queue
        }
      }
    }

    System.out.println("No path from " + startWord + " to " + targetWord); // if no path is found, print this
  }
  
  /***
   * 
   * Method that finds the shortest path
   * 
   * @param <E>
   * @param wordMap
   * @param startWord
   * @param targetWord
   */
  public static <E> void findShortestPathA(Graph<E> wordMap, String startWord, String targetWord) {
    
    
    
    Vertex<E> startVertex = wordMap.getVertex(startWord); // create start vertex for shortest path
    if (startVertex == null) { // if the start vertex is null...
      System.out.println("Start word \"" + startWord + "\" not found in the dictionary."); // word not found
      return; // end search
    }

    Vertex<E> targetVertex = wordMap.getVertex(targetWord); // create the end vertex for shortest path
    if (targetVertex == null) { // if end vertex is null...
      System.out.println("Target word \"" + targetWord + "\" not found in the dictionary."); // word not found
      return; // end search
    }

    
    
    if (targetVertex.getWord().length() != startVertex.getWord().length()) {
      System.out.println("Words \"" + startWord + "\" and \"" + targetWord + "\" are not the same length. Please input two words that are the same length. "); // word not found
      return; //end search
    }
    Map<Vertex<E>, Double> distances = new HashMap<>(); //create map of distances
    Map<Vertex<E>, Vertex<E>> previous = new HashMap<>(); //create map of root vertices
    PriorityQueue<Vertex<E>> pq =
        new PriorityQueue<>((v1, v2) -> Double.compare(distances.get(v1), distances.get(v2))); // create priority queue of visited vertices

    // Initialize distances to infinity and previous vertices to null
    for (Vertex<E> vertex : wordMap.getVertices()) { // for each vertex in the map
      distances.put(vertex, Double.POSITIVE_INFINITY); // set each initial value to infinity
      previous.put(vertex, null); // set each root to null
    }

    distances.put(startVertex, 0.0); // add the start vertex + distance (which is always 0)
    pq.offer(startVertex); // insert start vertex in priority queue

    while (!pq.isEmpty()) { // while the priority queue is NOT empty...
      Vertex<E> currentVertex = pq.poll();  // the current vertex is removed + retrieved from queue
      if (currentVertex.equals(targetVertex)) { // if the current vertex is the end vertex...
        printShortestPath(previous, targetVertex, distances.get(targetVertex)); //print path
        return; // end search
      }
      
      printShortestPath(previous, targetVertex, distances.get(targetVertex)); //print path anyways to check how many paths are being checked


      for (Edge<E> edge : currentVertex.getEdges()) { // for each edge of the current vertex...
        Vertex<E> neighborVertex = edge.getNeighbor(); // set the neighbor vertex to the neighbor of the current edge
        double edgeWeight = edge.getWeight(); // get the weight of that edge
        
        // A* Algorithm Edit
        
        //Find which char is different
        
        int charInt = 0; // position of the different char
        
        for (int c = 0; c < currentVertex.getWord().length(); c++) { // look at each char in the current vertex
          if (currentVertex.getWord().charAt(c) != neighborVertex.getWord().charAt(c)) { // if it is the different char
            charInt = c; // set charInt to that position
            c = currentVertex.getWord().length(); // end loop
          }
        }
        
        double charDis = 1;

        
        if (targetVertex.getWord().charAt(charInt) == neighborVertex.getWord().charAt(charInt)) {
          charDis = 0;
        }
         

        
        
       
         
        
        double distanceThroughCurrent = distances.get(currentVertex) + edgeWeight; //add the current vertex distance with the current edge weight

        if (distanceThroughCurrent + charDis < distances.get(neighborVertex)) { // if the distances through the current is less than the distance of the neighbor...
          distances.put(neighborVertex, distanceThroughCurrent); //add the neighbor vertex to distances, and set its distance to the distances through the current
          previous.put(neighborVertex, currentVertex); //add the neighbor vertex to the list of visited vertices
          pq.offer(neighborVertex); // insert the neighbor vertex in the priority queue
        }
      }
    }

    System.out.println("No path from " + startWord + " to " + targetWord); // if no path is found, print this
  }


  /***
   * 
   * Method that helps print the shortest path
   * 
   * @param <E>
   * @param previous
   * @param targetVertex
   * @param pathLength
   */
  private static <E> void printShortestPath(Map<Vertex<E>, Vertex<E>> previous,
      Vertex<E> targetVertex, double pathLength) {
    ArrayList<Vertex<E>> path = new ArrayList<>(); // create an arraylist to represent the graph
    Vertex<E> currentVertex = targetVertex; // current vertex is the target 
    while (currentVertex != null) { // while the current vertex is not null...
      path.add(currentVertex); // add to the path
      currentVertex = previous.get(currentVertex); // current vertex is now the next in the list of visited vertices
    }
    System.out.print("Shortest path: "); 
    for (int i = path.size() - 1; i >= 0; i--) { // for each vertex in path...
      System.out.print(path.get(i).getWord()); // print word associated with each vertex
      if (i > 0) { // if i is greater than 0...
        System.out.print(" -> "); // print arrow
      }
    }
    System.out.println(); // print blank line
    System.out.println("Path length: " + pathLength); // print path length
  }

  /***
   * Method to printEdges
   * 
   * @param <E>
   * @param wordMap
   * @param word
   */
  public static <E> void printEdges(Graph<E> wordMap, String word) {
    Vertex<E> vertex = wordMap.getVertex(word); // vertex is the word given
    if (vertex != null) { // if it is not null
      System.out.println("Edges of word \"" + word + "\":"); 
      for (Edge<E> edge : vertex.getEdges()) { // for each edge in the word
        String neighborWord = edge.getNeighbor().getWord(); // print the word on the edge
        double weight = edge.getWeight(); // print the weight of that edge
        System.out.println(word + " -> " + neighborWord + " (weight: " + weight + ")");
      }
    } else {
      System.out.println("Word \"" + word + "\" not found in the dictionary.");
    }
  }



}
