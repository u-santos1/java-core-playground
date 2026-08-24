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
    private int size;     // Tamanho da lista

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
        // TODO: Implemente a busca
        return null;
    }

    public E remove(int index) {
        // TODO: Implemente a remoção
        return null;
    }

    public int size() {
        return size;
    }
}
