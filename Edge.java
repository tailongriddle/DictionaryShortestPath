
/***
 * Edge 
 * @author tai
 *
 */
public class Edge<E> {
  private Vertex<E> neighbor; //what the word is connected to
  private double weight; //weight of the word
  
  
  
  /***
   * Getter for weight
   * @return
   */
  public double getWeight() {
    return this.weight;

  }
  
  /***
   * Getter for set of edges
   * 
   * @return
   */
  public Vertex<E> getNeighbor() {
    return this.neighbor;

  }
  
  /***
   * Setter for word
   * 
   * @param word
   */
  public void setWeight(double weight) {
    this.weight = weight;

  }
  
  
  /***
   * Setter for neighbor
   * 
   * @param word
   */
  public void setNeighbor(Vertex<E> neighbor) {
    this.neighbor = neighbor;

  }
  
  
  
  
  
  
  
  

}
