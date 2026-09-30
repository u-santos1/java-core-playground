
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(() -> {
            System.out.println("Tarefa 1 (Runnable) executada por: " + Thread.currentThread().getName());
        });
        Callable<Integer> tarefaComplexa = () -> {
            System.out.println("Tarefa 2 (Callable) executada por: " + Thread.currentThread().getName());
            Thread.sleep(2000);
            return 42;
        };

        Future<Integer> resultado = executor.submit(tarefaComplexa);

        try {
            System.out.println("Aquardando resultado da tarefa 2...");
            Integer valor = resultado.get();
            System.out.println("Resultado da tarefa 2: " + valor);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }
}