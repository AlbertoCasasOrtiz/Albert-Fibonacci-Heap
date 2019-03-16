package array;

public class Array<T> {
	int size;
	Object elem[];
	
	/*
	 * Constructor for the class Array.
	 * Crates an empty array.
	 */
	public Array(){
		this.size = 2;
		this.elem = new Object[size];
	}
	
	
	//Getters
	
	/*
	 * Gets the element in the position index of the array.
	 */
	@SuppressWarnings("unchecked")
	public T get(int index){
		if(index < this.size && this.elem[index] != null){
			return (T) this.elem[index];
		}else return null;
	}
	
	/*
	 * Gets the total size of the array.
	 */
	public int getSize(){
		return this.size;
	}
	
	
	//Methods of the array.
	
	/*
	 * Inserts an element in the array in the position of index.
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
	 * Deletes an element of the array.
	 */
	public void delete(int index){
		if(index < this.size){
			this.elem[index] = null;
		}
	}
	
	
	//Auxiliar methods
	
	/*
	 * Private element which resizes the array if it is full.
	 */
	private void resize(){
		Object elemNew[] = new Object[this.size*2];
		for(int i = 0; i < this.size; i++)
			elemNew[i] = this.elem[i];
		this.size *= 2;
		this.elem = elemNew;
	}
	
	

}
