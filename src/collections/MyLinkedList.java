package collections;

public class MyLinkedList<E> {

    // A classe do "Nó" que guarda o valor e aponta para o próximo
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    private Node<E> head; // Primeiro elemento
    private Node<E> tail; // Último elemento
    private int size; // Tamanho da lista

    // Construtor: Inicializa a lista vazia
    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Método de inserção (que expliquei na mensagem anterior)
    public void add(E element) {
        Node<E> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Indece invalido " + index);
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Indece invalido " + index);
        }
        E removedElement;

        if (index == 0) {
            removedElement = head.element;
            head = head.next;
            if (size == 1) {
                tail = null;
            }
        } else {
                Node<E> previous = head;
                for (int i = 0; i < index - 1; i++) {
                    previous = previous.next;
                }
                removedElement = previous.next.element;
                previous.next = previous.next.next;

                if (index == size - 1) {
                    tail = previous;
                }
        }
        size--;
        return removedElement;
    }

    public int size() {
        return size;
    }
}
