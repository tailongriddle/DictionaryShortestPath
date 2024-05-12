import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 
 * @author tai
 *
 * @param <E>
 */
public class Vertex<E> {
  private String word; // word associated with vertex
  private Set<Edge<E>> edges; // edges associated with vertex
  
  public Vertex() {
    this.edges = new HashSet<>();
}
  /***
   * Getter for word
   * @return
   */
  public String getWord() {
    return this.word;

  }
  
  /***
   * Getter for set of edges
   * 
   * @return
   */
  public Set<Edge<E>> getEdges() {
    return this.edges;

  }
  
  /***
   * Setter for word
   * 
   * @param word
   */
  public void setWord(String word) {
    this.word = word;

  }
  
  
  /***
   * Setter for word
   * 
   * @param word
   */
  public void addEdge(Edge<E> edge) {
    
    this.edges.add(edge);
    
    

  }
  
  /***
   * 
   * Gets edges as a string
   * 
   * @return
   */
  public List<String> getEdgesAsString() {
    List<String> edgeStrings = new ArrayList<>();
    for (Edge<E> edge : edges) {
        String neighborWord = edge.getNeighbor().getWord();
        double weight = edge.getWeight();
        String edgeString = word + " -> " + neighborWord + " (weight: " + weight + ")";
        edgeStrings.add(edgeString);
    }
    return edgeStrings;
}
}
