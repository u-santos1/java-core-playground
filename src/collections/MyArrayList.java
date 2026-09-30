package collections;


public class MyArrayList<E> {

    
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
    
        this.elements = new Object[DEFAULT_CAPACITY];
    
    }

    public void add(E element) {
    
        if (size == elements.length) {
            int novaCapacidade = elements.length * 2;
            Object[] arrayMaior = new Object[novaCapacidade];

            // Usando System.arraycopy (mais rápido e nativo) em vez de laço for
            System.arraycopy(elements, 0, arrayMaior, 0, elements.length);
            
            elements = arrayMaior;
        }
        elements[size] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index invalido " + index);
        }
        return (E) elements[index];
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Índice fora dos limites: " + index);
        }
        E removeElement = (E)elements[index];
        
        // Calculando quantos itens precisam ser deslocados
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            // Deslocando (shift) os itens para a esquerda usando System.arraycopy
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        
        elements[size - 1] = null; // Evitando Memory Leak
        size--;

        return removeElement;
    }

    public int size() {
    
        return size;
    }
}
