package collections;

public class MyHashMap<K, V> {

    // Uma classe interna para representar os pares chave/valor (Nó/Entry)
    // Se houver colisão (chaves diferentes caírem na mesma posição do array),
    // usaremos o 'next' para criar uma lista encadeada (chaining).
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // O array principal, que chamamos de "tabela de buckets"
    private Node<K, V>[] buckets;
    private int size; // Quantidade de pares inseridos
    private static final int DEFAULT_CAPACITY = 16; // O tamanho inicial do array

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        // Inicializa o array de buckets. 
        // Em Java, criar arrays de tipos genéricos dá um pouco de trabalho, por isso o cast.
        this.buckets = new Node[DEFAULT_CAPACITY];
    }

    /**
     * Função auxiliar crucial: transforma a chave em um índice válido do array.
     */
    private int getBucketIndex(K key) {
        if (key == null) return 0;
        // O Math.abs previne números negativos do hashCode()
        // O % (módulo) garante que o índice vai de 0 até (buckets.length - 1)
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public void put(K key, V value) {
        // TODO: Implementar a inserção
        // 1. Descobrir em qual índice (bucket) essa chave deve entrar usando getBucketIndex()
        // 2. Olhar para esse bucket: está vazio?
        //    - Se sim: cria o novo Node lá.
        //    - Se não: percorre a lista encadeada nesse bucket. A chave já existe?
        //         - Se a chave existe: apenas atualize o 'value'.
        //         - Se chegou no final e não achou a chave: adicione um novo Node no final.
        // 3. Aumentar o size (se adicionou um nó novo).
    }

    public V get(K key) {
        // TODO: Implementar a busca
        // 1. Descobrir o índice (bucket) da chave.
        // 2. Procurar na lista encadeada daquele bucket pelo Node que tem a mesma chave.
        // 3. Se achar, retornar o valor. Se não achar, retornar null.
        return null;
    }

    public V remove(K key) {
        // TODO: Implementar a remoção
        // 1. Descobrir o índice (bucket).
        // 2. Encontrar o Node na lista e removê-lo (lembre-se de religar os ponteiros da lista!).
        // 3. Se removeu, diminuir o size e retornar o valor.
        return null;
    }

    public int size() {
        return size;
    }
}
