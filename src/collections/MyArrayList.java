package collections;


public class MyArrayList<E> {

    
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
    
        this.elements = new Object[DEFAULT_CAPACITY];
    
    }

    public void add(E element) {
    
        if (size == elements.length){
            int novaCapacidade = elements.length * 2;
            Object[] arrayMaior = new Object[novaCapacidade];

            for (int i = 0; i < elements.length; i++){
                arrayMaior[i] = elements[i];
            }
        elements = arrayMaior;
        }
        elements[size] = element;
        size++;
    }

    public E get(int index) {
        
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index invalido " + index);
        }
        return (E) elements[index];
    }

    public E remove(int index) {
        
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Índice fora dos limites: " + index);
        }
        E removeElement = (E)elements[index];
        for (int i = index; i < size - 1; i++){
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;

        return removeElement;
    }

    public int size() {
    
        return size;
    }
}
