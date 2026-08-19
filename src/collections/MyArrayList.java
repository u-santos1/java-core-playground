package collections;

public class MyArrayList<E> {

    // Como você modelaria o armazenamento interno dessa classe?
    // Dica: Precisamos de um array comum e algo para rastrear quantos itens realmente existem.
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        // TODO: Inicialize o seu array interno aqui
    }

    public void add(E element) {
        // TODO: Adicione o elemento no final da lista. 
        // Pergunta: O que acontece se o 'elements' já estiver cheio?
    }

    public E get(int index) {
        // TODO: Retorne o elemento na posição index.
        // Cuidado: E se passarem um índice negativo ou maior que o size?
        return null;
    }

    public E remove(int index) {
        // TODO: Remova o elemento da posição index e retorne-o.
        // Dica: Depois de remover, como preencher o "buraco" deixado no array?
        return null;
    }

    public int size() {
        // TODO: Retorne a quantidade atual de elementos inseridos
        return 0;
    }
}
