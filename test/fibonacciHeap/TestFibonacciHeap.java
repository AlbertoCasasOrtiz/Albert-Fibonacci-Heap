package fibonacciHeap;

import fibonacciHeap.node.Node;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFibonacciHeap {

    @Test
    public void TestFibonacciHeap(){

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
        //Test if new minimum is 2.
        assertEquals(1, (int) aux.getElem());

        //Buscamos el nodo 3
        ArrayList<Node<Integer>> array = fib.findNode(3);
        //Decrecemos el elemento buscado
        fib.decreaseElement(array.get(0), -1);
        //Consultamos el nuevo mínimo
        aux = fib.getMin();
        assertEquals(-1, (int) aux.getElem());

        //Insertamos un nuevo nodo
        fib.insert(1000);
        //Consultamos el nuevo mínimo
        aux = fib.getMin();
        assertEquals(-1, (int) aux.getElem());

        //Buscamos el nodo 6
        array = fib.findNode(6);
        //Decrecemos el elemento buscado
        fib.decreaseElement(array.get(0), -2);
        assertEquals(-2, (int) fib.getMin().getElem());

        //Buscamos el nodo 7
        array = fib.findNode(7);
        //Decrecemos el elemento buscado
        fib.decreaseElement(array.get(0), -3);
        assertEquals(-3, (int) fib.getMin().getElem());

        //Extraemos el mínimo
        aux = fib.extractMin();
        assertEquals(-3, (int) aux.getElem());
    }

}
