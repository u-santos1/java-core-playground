import java.util.concurrent.CompletableFuture;
public class CompletableFutureDemo {
    public static void main(String[] args) {
        
        System.out.println("Iniciando processamento na thread: " + Thread.currentThread().getName());

        CompletableFuture.supplyAsync(() -> {
            System.out.println("Buscando dados no banco... Thread: " + Thread.currentThread().getName());
            dormir(2000);
            return "Dados do Banco";
        })
                .thenApply(dados -> {
                    System.out.println("Processador " + dados + "... Thread: " + Thread.currentThread().getName());
                    return dados.toUpperCase();
                })
                .thenAccept(resultadoFinal -> {
                    System.out.println("Resultado Final: " + resultadoFinal);
                });
        System.out.println("Processamento assincrono disparado. A thread principal nao ficou bloqueada");
        dormir(3000);
    }
    private static void dormir(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
