
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

public class ProducerConsumer {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);
        Thread produtor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.produzir(i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.consumir();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        produtor.start();
        consumidor.start();
    }
}

class Buffer {
    private Queue<Integer> fila = new LinkedList<>();
    private int capacidade;

    public Buffer(int capacidade) {
        this.capacidade = capacidade;
    }

    public synchronized void produzir(int valor) throws InterruptedException {
        while (fila.size() == capacidade) {
            System.out.println("Buffer cheio. Produto aquardando...");
            wait();
        }
        fila.add(valor);
        System.out.println("Produzindo: " + valor);
        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException {
        while (fila.isEmpty()) {
            System.out.println("Buffer vazio. Consumidor aquardando...");
            wait();
        }
        int valor = fila.poll();
        System.out.println("Consumido: " + valor);
        notifyAll();
        return valor;
    }
}
