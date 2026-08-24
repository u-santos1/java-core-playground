
public class ThreadBasics {

    static class MinhasThred extends Thread {
        @Override
        public void run() {
            System.out.println("Thread estendida rodando " + Thread.currentThread().getName());
        }
    }

    static class MeuRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable rodando: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrompida");
            }
            System.out.println("Runnable finalizou: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thred principal: " + Thread.currentThread().getName());

        MinhasThred thred1 = new MinhasThred();
        thred1.run();

        Thread thread2 = new Thread(new MeuRunnable(), "Thred-Runnable");
        thread2.start();

        thread2.join();
        System.out.println("Fim da execucao");

    }
}