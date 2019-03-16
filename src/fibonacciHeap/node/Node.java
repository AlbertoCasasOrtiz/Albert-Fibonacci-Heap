package fibonacciHeap.node;

/**
 * Node of a FibonacciHeap
 * @param <T> Type of data in the node. Must be a children of the class Comparable.
 */
public class Node<T extends Comparable<T>> {
	/** Element of this node. */
	private T elem;
	/** Degree of this node. */
	private int degree;
	/** Number of children of this node. */
	private int numChildren;
	/** Set if this node is marked. */
	private boolean mark;
	/** Parent of this node.*/
	private Node<T> parent;
	/** First child of this node. */
	private Node<T> firstChild;
	/** Left brother of this node. */
	private Node<T> leftBrother;
	/** Right brother of this node. */
	private Node<T> rightBrother;

	/*
	 * Constructor for the class Node.
	 */
	public Node(T elem) {
		this.elem = elem;
		this.degree = 0;
		this.numChildren = 0;
		this.mark = false;
		this.parent = null;
		this.firstChild = null;
		this.leftBrother = this;
		this.rightBrother = this;
	}

	/*
	 * Set an element of the type T into the node.
	 */
	public void setElem(T elem) {
		this.elem = elem;
	}	
	
	/*
	 * Set the degree into the node.
	 */
	public void setDegree(int degree) {
		this.degree = degree;
	}
	
	/*
	 * Set the number of children into the node.
	 */
	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}
	
	/*
	 * Mark the node.
	 */
	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
	/*
	 * Set parent for the node.
	 */
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
	
	/*
	 * Set a child for the node.
	 */
	public void insertChild(Node<T> child) {
		if(child != null){
			if(this.firstChild == null){
				this.firstChild = child;
				child.setLeftBrother(child);
				child.setRightBrother(child);
			}
			else{
				Node<T> leftFirst = this.firstChild.getLeftBrother();
				child.setRightBrother(this.firstChild);
				child.setLeftBrother(leftFirst);
			}
			child.setParent(this);
			this.numChildren++;
			if(child.getDegree()+1 > this.getDegree()){
				degree = child.getDegree()+1;
				this.setDegree(degree);
			}
		}
		else {
			this.firstChild = null;
		}
	}
	
	/*
	 * Set first child of the node.
	 */
	public void setFirstChild(Node<T> firstChild){
		this.firstChild = firstChild;
	}
	
	/*
	 * Set left brother of the node.
	 */
	public void setLeftBrother(Node<T> leftBrother) {
		this.leftBrother = leftBrother;
		if(leftBrother != null && leftBrother.getRightBrother() != this)
			leftBrother.setRightBrother(this);
	}

	/*
	 * Set right brother of the node.
	 */
	public void setRightBrother(Node<T> rightBrother) {
		this.rightBrother = rightBrother;
		if(rightBrother != null && rightBrother.getLeftBrother() != this)
			rightBrother.setLeftBrother(this);
	}

	/*
	 * Get the element of the node.
	 */
	public T getElem() {
		return elem;
	}

	/*
	 * Get the degree of the node.
	 */
	public int getDegree() {
		return degree;
	}
	
	/*
	 * Get the number of children in the node.
	 */
	public int getNumChildren() {
		return numChildren;
	}
	
	/*
	 * Get the mark of the node.
	 */
	public boolean isMark() {
		return mark;
	}
	
	/*
	 * Get the parent of the node.
	 */
	public Node<T> getParent() {
		return parent;
	}
	
	/*
	 * Gets the first child of the node.
	 */
	public Node<T> getFirstChild() {
		return firstChild;
	}
	
	/*
	 * Get the left brother of the node.
	 */
	public Node<T> getLeftBrother() {
		return leftBrother;
	}
	
	/*
	 * Get the right brother of the node.
	 */
	public Node<T> getRightBrother() {
		return rightBrother;
	}

	/*
	 * ToString method for node.
	 */
	@Override
	public String toString() {
		return "Node [elem=" + elem + "]";
	}
}
