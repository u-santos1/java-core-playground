package patterns.behavioral;

import java.util.ArrayList;
import java.util.List;

// 1. A Interface do Observador (Quem quer ser avisado)
interface ISubscriber {
    void update(String videoTitle);
}

// 2. A Interface do Publicador/Sujeito (Quem avisa)
interface IChannel {
    void subscribe(ISubscriber subscriber);
    void unsubscribe(ISubscriber subscriber);
    void notifySubscribers();
}

// 3. Sujeito Concreto (O Canal do YouTube)
class YouTubeChannel implements IChannel {
    // Lista onde guardamos todos que querem ser avisados
    private List<ISubscriber> subscribers = new ArrayList<>();
    private String latestVideoTitle;

    @Override
    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    // O "coração" do padrão: avisa todo mundo da lista
    @Override
    public void notifySubscribers() {
        for (ISubscriber sub : subscribers) {
            sub.update(latestVideoTitle);
        }
    }

    // Método que muda o estado (ação principal) e dispara a notificação
    public void uploadVideo(String title) {
        this.latestVideoTitle = title;
        System.out.println("\n[Canal] Novo vídeo publicado: " + title);
        notifySubscribers();
    }
}

// 4. Observador Concreto (Os Usuários inscritos)
class User implements ISubscriber {
    private String name;

    public User(String name) {
        this.name = name;
    }

    // O que o usuário faz quando recebe o aviso
    @Override
    public void update(String videoTitle) {
        System.out.println("  -> Notificação para " + name + ": Corra para assistir '" + videoTitle + "'!");
    }
}

// 5. Cliente (Main)
public class Observer {
    public static void main(String[] args) {
        System.out.println("--- Testando o Padrão Observer ---\n");

        // Criamos o Publicador (O Canal)
        YouTubeChannel canalJava = new YouTubeChannel();

        // Criamos os Observadores (Os Usuários)
        User user1 = new User("Wesley");
        User user2 = new User("Maria");
        User user3 = new User("João");

        // Inscrevendo pessoas no canal
        System.out.println("Ações: Wesley e Maria se inscreveram no canal.");
        canalJava.subscribe(user1);
        canalJava.subscribe(user2);

        // O canal lança um vídeo. Todos os inscritos são avisados automaticamente!
        canalJava.uploadVideo("Entendendo Padrões de Projeto em Java!");

        // João se inscreve, mas Maria resolve cancelar a inscrição
        System.out.println("\nAções: João se inscreveu. Maria cancelou a inscrição.");
        canalJava.subscribe(user3);
        canalJava.unsubscribe(user2);

        // Novo vídeo! Repare que Maria não será mais avisada, mas João sim.
        canalJava.uploadVideo("O Poder do Padrão Observer na Prática");
    }
}
