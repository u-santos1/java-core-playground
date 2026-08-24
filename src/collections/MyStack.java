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
        // TODO: Implemente a inserção no topo
        // 1. Crie um novo Node com o elemento.
        // 2. O 'next' desse novo nó tem que apontar para quem era o 'top' antigo.
        //    (O novo prato fica em cima do prato que já estava lá)
        // 3. Atualize a variável 'top' para ser esse novo nó.
        // 4. Aumente o size.
    }

    // Método POP: Remove e retorna o elemento do topo
    public E pop() {
        // TODO: Implemente a remoção do topo
        // 1. Verifique se a pilha está vazia (size == 0). Se sim, pode jogar um erro (ex: EmptyStackException ou RuntimeException).
        // 2. Guarde o elemento que está no 'top' em uma variável (para poder retornar depois).
        // 3. Faça a variável 'top' apontar para o 'top.next' (ou seja, o prato de baixo).
        // 4. Diminua o size.
        // 5. Retorne o elemento guardado.
        return null;
    }

    // Método PEEK: Apenas "espia" o topo, sem remover
    public E peek() {
        // TODO: Retorne o elemento do 'top', mas sem mexer na pilha.
        // (Lembre-se de checar se está vazia primeiro!)
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
