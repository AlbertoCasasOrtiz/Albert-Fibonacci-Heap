package main;

import java.util.ArrayList;

import fibonacciHeap.FibonacciHeap;
import node.Node;

public class Main {

	public static void main(String[] args) {
		//Pruebas
		
		//Creamos el montículo
		FibonacciHeap<Integer> fib = new FibonacciHeap<Integer>();
		//Creamos un nodo auxiliar para comprobar los resultados
		@SuppressWarnings("unused")
		Node<Integer> aux;
		//Insertamos 11 elementos
		for(int i = 0; i < 11; i++)
			fib.insert(i);
		//Extraemos el mínimo
		aux = fib.extractMin();
		//Consultamos cual es el nuevo mínimo
		aux = fib.getMin();

		//Buscamos el nodo 3
		ArrayList<Node<Integer>> array = fib.findNode(3);
		//Decrecemos el elemento buscado
		fib.decreaseElement(array.get(0), -1);
		//Consultamos el nuevo mínimo
		fib.getMin();
		
		//Insertamos un nuevo nodo
		fib.insert(1000);
		//Consultamos el nuevo mínimo
		aux = fib.getMin();
		
		//Buscamos el nodo 6
		array = fib.findNode(6);
		//Decrecemos el elemento buscado
		fib.decreaseElement(array.get(0), -2);
				
		//Buscamos el nodo 7
		array = fib.findNode(7);
		//Decrecemos el elemento buscado
		fib.decreaseElement(array.get(0), -3);
		
		//Consultamos el nuevo mínimo
		aux = fib.getMin();
		
		//Extraemos el mínimo
		aux = fib.extractMin();
		
	}
}
	



