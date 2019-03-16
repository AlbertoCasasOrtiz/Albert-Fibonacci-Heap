package node;

public class Node<T extends Comparable<T>> {
	private T elem;
	private int degree;
	private int numSons;
	private boolean mark;
	private Node<T> parent;
	private Node<T> firstSon;
	private Node<T> leftBrother;
	private Node<T> rightBrother;

	/*
	 * Constructor for the class Node.
	 */
	public Node(T elem) {
		this.elem = elem;
		this.degree = 0;
		this.numSons = 0;
		this.mark = false;
		this.parent = null;
		this.firstSon = null;
		this.leftBrother = this;
		this.rightBrother = this;
	}
	
	
	//Setters
	
	/*
	 * Sets an element of the tipe T into the node.
	 */
	public void setElem(T elem) {
		this.elem = elem;
	}	
	
	/*
	 * Sets a degree into the node.
	 */
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	/*
	 * Sets a number of sons into the node.
	 */
	public void setNumSons(int numSons) {
		this.numSons = numSons;
	}
	
	/*
	 * Sets a mark into the node.
	 */
	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
	/*
	 * Sets a parent for the node.
	 */
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
	
	/*
	 * Sets a son for the node.
	 */
	public void insertSon(Node<T> son) {
		if(son != null){
			if(this.firstSon == null){
				this.firstSon = son;
				son.setLeftBrother(son);
				son.setRightBrother(son);
			}
			else{
				Node<T> leftFirst = this.firstSon.getLeftBrother();
				son.setRightBrother(this.firstSon);
				son.setLeftBrother(leftFirst);
			}
			son.setParent(this);
			this.numSons++;
			if(son.getDegree()+1 > this.getDegree()){
				degree = son.getDegree()+1;
				this.setDegree(degree);
			}
		}
		else if(son == null){
			this.firstSon = null;
		}
	}
	
	/*
	 * Sets a first son for the node.
	 */
	public void setFirstSon(Node<T> firstSon){
		this.firstSon = firstSon;
	}
	
	/*
	 * Sets a left brother for the node.
	 */
	public void setLeftBrother(Node<T> leftBrother) {
		this.leftBrother = leftBrother;
		if(leftBrother != null && leftBrother.getRightBrother() != this)
			leftBrother.setRightBrother(this);
	}

	/*
	 * Sets a right brother for the node.
	 */
	public void setRightBrother(Node<T> rightBrother) {
		this.rightBrother = rightBrother;
		if(rightBrother != null && rightBrother.getLeftBrother() != this)
			rightBrother.setLeftBrother(this);
	}
	
	
	//Getters
	
	/*
	 * Gets the element of the node.
	 */
	public T getElem() {
		return elem;
	}

	/*
	 * Gets the degree of the node.
	 */
	public int getDegree() {
		return degree;
	}
	
	/*
	 * Gets the number of sons of the node.
	 */
	public int getNumSons() {
		return numSons;
	}
	
	/*
	 * Gets the mark of the node.
	 */
	public boolean isMark() {
		return mark;
	}
	
	/*
	 * Gets the parent of the node.
	 */
	public Node<T> getParent() {
		return parent;
	}
	
	/*
	 * Gets the first son of the node.
	 */
	public Node<T> getFirstSon() {
		return firstSon;
	}
	
	/*
	 * Gets the left brother of the node.
	 */
	public Node<T> getLeftBrother() {
		return leftBrother;
	}
	
	/*
	 * Gets the right brother of the node.
	 */
	public Node<T> getRightBrother() {
		return rightBrother;
	}

	/*
	 * ToString method for node.(non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [elem=" + elem + "]";
	}
}
