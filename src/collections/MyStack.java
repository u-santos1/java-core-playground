package collections;

// Uma Pilha (Stack) segue a regra LIFO (Last-In, First-Out): O último a entrar é o primeiro a sair.
// Pense em uma pilha de pratos: você sempre coloca um prato no TOPO e sempre tira o prato do TOPO.
public class MyStack<E> {

    // Para fazer a pilha, podemos usar a mesma ideia de "Nós" da LinkedList,
    // mas só precisamos controlar o "topo" (top).
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    private Node<E> top; // Aponta sempre para o prato que está no topo da pilha
    private int size;

    public MyStack() {
        this.top = null;
        this.size = 0;
    }

    // Método PUSH: Empilha um novo elemento
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Método POP: Remove e retorna o elemento do topo
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        E element = top.element;
        top = top.next;
        size--;
        return element;
    }

    // Método PEEK: Apenas "espia" o topo, sem remover
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return top.element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
