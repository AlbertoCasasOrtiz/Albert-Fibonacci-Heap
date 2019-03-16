package array;

/**
 * Implementation of a resizable Array.
 * @param <T> Type of the elements in the array.
 */
public class Array<T> {
	/** Size of the array.*/
	int size;
	/** Elements in the array. */
	Object elem[];
	
	/*
	 * Constructor for the class Array.
	 * Creates an empty array.
	 */
	public Array(){
		this.size = 2;
		this.elem = new Object[size];
	}

	/*
	 * Get the element in the position index of the array.
	 */
	@SuppressWarnings("unchecked")
	public T get(int index){
		if(index < this.size && this.elem[index] != null){
			return (T) this.elem[index];
		}else return null;
	}
	
	/*
	 * Get size of the array.
	 */
	public int getSize(){
		return this.size;
	}

	/*
	 * Inserts an element in the array in index position.
	 */
	public void insert(int index, T elem){
		if(index < this.size)
			this.elem[index] = elem;
		else{
			while(size <= index)
				this.resize();
			this.elem[index] = elem;
		}
	}
	
	/*
	 * Delete an element of the array.
	 */
	public void delete(int index){
		if(index < this.size){
			this.elem[index] = null;
		}
	}

	/*
	 * Private element which resizes the array if it is full.
	 */
	private void resize(){
		Object[] elemNew = new Object[this.size * 2];
		for(int i = 0; i < this.size; i++)
			elemNew[i] = this.elem[i];
		this.size *= 2;
		this.elem = elemNew;
	}
	
	

}
