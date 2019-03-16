package fibonacciHeap;

import java.util.ArrayList;

import array.Array;
import node.Node;

public class FibonacciHeap<T extends Comparable<T>> {
	private Node<T> min;
	private int numNodes;
	private int nodesInRoot;
	
	/*
	 * Constructor for the class FibonacciHeap.
	 * Creates an empty heap.
	 */
	public FibonacciHeap(){ 
		this.min = null;
		this.numNodes = 0;
		this.nodesInRoot = 0;
	}
	
	
	//Getters
	
	/*
	 * Gets the number of nodes in the heap.
	 */
	public int getNumNodes(){
		return this.numNodes;
	}
	
	/*
	 * Gets the min element in the heap.
	 * It does not remove the element.
	 * Null if the heap is empty.
	 */
	public Node<T> getMin(){
		return this.min;
	}
	
	/*
	 * Gets the number of nodes in the root list.
	 */
	public int getNodesInRoot(){
		return this.nodesInRoot;
	}
	
	
	//Methods for FibonacciHeap
	
	/*
	 * Inserts an element in the heap.
	 */
	public void insert(T elem){
		if(elem != null){
			//Inicializate the node to insert.
			Node<T> inserted = new Node<T>(elem);
			inserted.setDegree(0);
			inserted.setParent(null);
			inserted.insertSon(null);
			inserted.setMark(false);
			//If the heap is empty, insert the node as the minimum.
			if(this.min == null){
				this.min = inserted;
			//else...
			}else{
				//Concatenate the node in the roots list.
				Node<T> leftMin = this.min.getLeftBrother();
				inserted.setRightBrother(min);
				inserted.setLeftBrother(leftMin);
				//If the inserted is smaller than the minimum is the new minimum.
				if(inserted.getElem().compareTo(this.min.getElem()) < 0)
					this.min = inserted;
			}
			//Update the number of nodes.
			this.numNodes++;
			this.nodesInRoot++;
		}
	}	
	
	/*
	 * Gets the minimum element of the heap and removes it.
	 * Null if it is empty.
	 */
	public Node<T> extractMin(){
		//Check if the heap is empty.
		if(this.min != null){
			Node<T> extracted = this.min;
			Node<T> son = extracted.getFirstSon();
			//Insert the sons of the node to extract in the roots list.
			for(int i = 0; i < extracted.getNumSons(); i++){
				Node<T> rightSon = son.getRightBrother();
				this.insertInRoot(son);
				son = rightSon;
			}
			//If the node is the unic node in the heap the heap is empty,
			if(extracted == extracted.getRightBrother())
				this.min = null;
			//else... assign any node as the min.
			else
				this.min = extracted.getRightBrother();
			
			//Delete the extracted of the root list.
			deleteFromRoot(extracted);
			//If there are more nodes than the extracted, consolidate.
			if(this.min != null)
				consolidate();
			
			//Update the number of nodes.
			this.numNodes--;
			return extracted;
		}else return null;
	}
	
	/*
	 * Method which decreases an element in the Heap.
	 */
	public void decreaseElement(Node<T> decrease, T k){
		//Decrease only if the node is greather than the new value.
		if(k.compareTo(decrease.getElem()) < 0){
			decrease.setElem(k);
			Node<T> parent = decrease.getParent();
			//if the node updated is smaller than the parent cut it and cascading cut.
			if(parent != null && decrease.getElem().compareTo(parent.getElem()) < 0){
				this.cut(decrease, parent);
				this.cascadingCut(parent);
			}
			//Update the minimum.
			if(decrease.getElem().compareTo(this.min.getElem()) < 0){
				this.min = decrease;
			}
		}
	}
	
	/*
	 * Returns true if a element exists in the heap.
	 */
	public boolean existNode(T elem){
		return findNode(elem).isEmpty() ? false: true;
	}
	
	/*
	 * Returns an ArrayList with the nodes in the heap which contains an element.
	 */
	public ArrayList<Node<T>> findNode(T elem){
		Node<T> current = this.min;
		ArrayList<Node<T>> result = new ArrayList<Node<T>>();
		for(int i = 0; i < this.nodesInRoot; i++){
			if(current.getElem().compareTo(elem) == 0){
				result.add(current);
				if (current.getNumSons() != 0)
				result = findNode(current.getFirstSon(), elem, result);
			}else if (current.getNumSons() != 0){
				result = findNode(current.getFirstSon(), elem, result);
			}
			current = current.getRightBrother();
		}
		return result;
	}
	
	/*
	 * Links two different Fibonacci Heaps.
	 * The result will be in the heap which calls the function.
	 */
	public void union(FibonacciHeap<T> fib2){
		if(this != null && fib2 != null){
			if(this.getMin() == null && fib2.getMin() != null){
				this.min = fib2.getMin();
				this.numNodes += fib2.numNodes;
				this.nodesInRoot += fib2.nodesInRoot;
			}
			else if (this.getMin() != null && fib2.getMin() != null){
				Node<T> leftMin = this.min.getLeftBrother();
				Node<T> leftMinfib2 = fib2.getMin().getRightBrother();
				this.min.setLeftBrother(fib2.getMin());
				fib2.getMin().setRightBrother(this.min);
				leftMin.setRightBrother(leftMinfib2);
				leftMinfib2.setLeftBrother(leftMin);
				this.numNodes += fib2.numNodes;
				this.nodesInRoot += fib2.nodesInRoot;
			}
		
		}
	}
	
	//Auxiliar methods
	
	/*
	 * Auxiliar method which onsolidates the heap.
	 * Sets a tree as a consolidate tree, which have at least 
	 * one node for each degree.
	 */
	private void consolidate(){
		//Create an array of nodes. It will contain nodes in the position of
		//its degrees.
		Array<Node<T>> arr= new Array<Node<T>>();
		Node<T> x = this.min;
		Node<T> p = this.min;
		int NodesInRoot = this.nodesInRoot;
		//For every node in the roots list...
		for(int i = 0; i < NodesInRoot; i++){
			int d = x.getDegree();
			Node<T> z = x.getRightBrother();
			//If exists another node of his degree...
			while(arr.get(d) != null){
				Node<T> y = arr.get(d);
				if(x.getElem().compareTo(y.getElem()) > 0){
					Node<T> aux = x;
					x = y;
					y = aux;
					if(y == p)
						p = y.getRightBrother();
				}
				//Set the smaller son of the greather.
				this.link(y, x);
				//Sets the array in his position a null.
				arr.insert(d, null);
				d++;
			}
			//Insert the node in the degree list.
			arr.insert(d, x);
			//We may change x when we link nodes. Set x as the next original value.
			x = z;
		}
		this.min = null;
		//Update the minimum element.
		for(int i = 0; i < arr.getSize(); i++){
			if(arr.get(i) != null){
				if(this.min == null){
					this.min = arr.get(i);
				}else if(arr.get(i).getElem().compareTo(this.min.getElem())<0)
					this.min = arr.get(i);
			}
		}
	}
	
	/*
	 * Auxiliar method which sets a node from the root lists as son 
	 * of another node of the root list.
	 */
	private void link(Node<T> y, Node<T> x){
		this.deleteFromRoot(y);
		x.insertSon(y);
		y.setMark(false);
	}
	
	/*
	 * Auxiliar method which extract a node from the sons lists of
	 * another node and inserts it in the root list.
	 */
	private void cut(Node<T> decreased, Node<T> parent){
		this.deleteFromSons(decreased);
		this.insertInRoot(decreased);
		decreased.setMark(false);
	}
	
	/*
	 * Cuts the parents that have lost two sons and send them to the
	 * root lists.
	 */
	private void cascadingCut(Node<T> y){
		Node<T> z = y.getParent();
		//If the node as a parent...
		if(z != null){
			//If is not marked, mark it.
			if(y.isMark() == false)
				y.setMark(true);
			//If is marked, cut and cascading cut.
			else{
				cut(y, z);
				cascadingCut(z);
			}
		}
	}
	
	/*
	 * Auxiliar method which inserts a node into the
	 * root list.
	 */
	private void insertInRoot(Node<T> inserted){
		inserted.setParent(null);
		inserted.setMark(false);
		Node<T> leftMin = this.min.getLeftBrother();
		inserted.setRightBrother(min);
		inserted.setLeftBrother(leftMin);
		if(inserted.getElem().compareTo(this.min.getElem()) < 0)
			this.min = inserted;
		this.nodesInRoot++;
	}	
	
	/*
	 * Auxiliar method which deletes a node from the
	 * root list.
	 */
	private void deleteFromRoot(Node<T> deleted){
		Node<T> leftBrother = deleted.getLeftBrother();
		leftBrother.setRightBrother(deleted.getRightBrother());
		deleted.setLeftBrother(null);
		deleted.setRightBrother(null);
		this.nodesInRoot--;
	}

	/*
	 * Auxiliar method which updates the degree of a node that has lost
	 * a son.
	 */
	private int updateSon(Node<T> parent){
		if(parent.getNumSons()!= 0){
			int grade = 0;
			Node<T> son = parent.getFirstSon();
			for(int i = 0; i < parent.getNumSons(); i++){
				if(son.getDegree() > grade)
					grade = son.getDegree();
				son = son.getLeftBrother();
			}
			return grade+1;
		}else return 0; 
	}
	
	/*
	 * Auxiliar method which deletes a node from the sons lists of it
	 * parent.
	 */
	private void deleteFromSons(Node<T> son){
		Node<T> parent = son.getParent();
		if(son.getLeftBrother() == son){
			parent.setDegree(0);
			parent.insertSon(null);
		}else{
			if(parent.getFirstSon() == son)
				parent.setFirstSon(son.getLeftBrother());
			Node<T> leftBrother = son.getLeftBrother();
			Node<T> rightBrother = son.getRightBrother();
			leftBrother.setRightBrother(rightBrother);
			rightBrother.setLeftBrother(leftBrother);
		}
		parent.setNumSons(parent.getNumSons()-1);
		parent.setDegree(this.updateSon(parent));
		son.setParent(null);
	}
	
	/*
	 * Auxiliar recursive method which finds an elements in the descendents
	 * of a node.
	 */
	private ArrayList<Node<T>> findNode(Node<T> current, T elem, ArrayList<Node<T>> result){
		for(int i = 0; i < current.getParent().getNumSons(); i++){
			if(current.getElem() == elem){
				result.add(current);
				if (current.getNumSons() != 0)
				result = findNode(current.getFirstSon(), elem, result);
			}else if (current.getNumSons() != 0){
				result = findNode(current.getFirstSon(), elem, result);
			}
			current = current.getRightBrother();
		}
		return result;
	}
}
