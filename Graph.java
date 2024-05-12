import java.util.Collection;
import java.util.HashMap;

/***
 * 
 * @author tai
 *
 * @param <E>
 */
public class Graph<E> {
  private HashMap<String, Vertex<E>> map; // map of words

  /***
   * Constructor method
   */
  public Graph() {
    this.map = new HashMap<>();
  }

  /***
   * Method to set map
   * 
   * @param map
   */
  public void setMap(HashMap<String, Vertex<E>> map) {
    this.map = map;
  }

/***
 * Method to add a vertex
 * @param word
 */
  public void add(Vertex<E> word) {
    map.put(word.getWord(), word);
  }

  /***
   * Method to get vertex
   * @param word
   * @return
   */
  public Vertex<E> getVertex(String word) {
    return map.get(word);
  }
  
  public Collection<Vertex<E>> getVertices() {
    return map.values();
}

/***
 * Method to get edges
 * @param word
 */
  public void getEdges(String word) {
    Vertex<E> vertex = map.get(word);
    if (vertex != null) {
      vertex.getEdgesAsString();
    }

  }



}
