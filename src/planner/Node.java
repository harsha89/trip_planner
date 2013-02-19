package planner;

public class Node implements Comparable<Node>{
	
	public Node(int id){
		this.setId(id); 
	}
	
	private int id = -1;
	private int weight = 0;
	private Node pre = null;
	private int state = 0;	//0-not discovered, 1-discovered, 2-finished
	public Node() {
		this(0);
	}
	
	@Override
	public int compareTo(Node node){
		return (getWeight()<node.getWeight() ? -1 : (getWeight()==node.getWeight() ? 0 : 1));
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the pre
	 */
	public Node getPre() {
		return pre;
	}

	/**
	 * @param pre the pre to set
	 */
	public void setPre(Node pre) {
		this.pre = pre;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
}
