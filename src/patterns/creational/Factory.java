// O padrão Factory (Criacional) isola e esconde a lógica pesada de criação de objetos.
// Em vez do cliente usar "new Objeto()" espalhado pelo código, ele apenas pede
// para uma "Fábrica" criar o objeto para ele passando um parâmetro (como uma String ou Enum).

// 1. A Interface Comum (O Produto)
// Todos os objetos que a fábrica criar devem seguir essa mesma regra.
interface Notificacao {
    void enviar(String mensagem);
}

// 2. Os Produtos Concretos (As diferentes implementações)
class NotificacaoEmail implements Notificacao {
    @Override
    public void enviar(String mensagem) {
        System.out.println("📧 Enviando E-MAIL: " + mensagem);
    }
}

class NotificacaoSMS implements Notificacao {
    @Override
    public void enviar(String mensagem) {
        System.out.println("📱 Enviando SMS: " + mensagem);
    }
}

class NotificacaoPush implements Notificacao {
    @Override
    public void enviar(String mensagem) {
        System.out.println("🔔 Enviando Notificação PUSH (App): " + mensagem);
    }
}

// 3. A Fábrica (Factory)
// Centraliza toda a regra de QUAL objeto criar. Se amanhã criarmos uma NotificacaoWhatsApp,
// só precisamos adicionar 2 linhas AQUI dentro da fábrica. O resto do sistema (Cliente) continua intacto.
class NotificacaoFactory {
    
    // Método estático que fabrica o objeto baseado em um texto
    public static Notificacao criarNotificacao(String tipo) {
        if (tipo == null || tipo.isEmpty()) {
            return null;
        }
        
        switch (tipo.toUpperCase()) {
            case "EMAIL":
                return new NotificacaoEmail();
            case "SMS":
                return new NotificacaoSMS();
            case "PUSH":
                return new NotificacaoPush();
            default:
                // Se pedir algo que a fábrica não sabe fazer, ela avisa.
                throw new IllegalArgumentException("❌ Erro da Fábrica: Tipo de notificação desconhecido -> " + tipo);
        }
    }
}

// 4. Cliente (Main)
public class Factory {
    public static void main(String[] args) {
        System.out.println("--- Testando o Padrão Factory ---\n");

        // Repare na "Mágica": O cliente NÃO USA a palavra "new" para criar o Email, SMS ou Push.
        // Ele não precisa saber qual é o nome exato da classe (se é NotificacaoEmail ou EmailNotification).
        // Ele apenas grita para a fábrica: "ME DÊ UM OBJETO DE EMAIL!"
        
        System.out.println("Cenário 1: O sistema precisa enviar um Email");
        Notificacao notif1 = NotificacaoFactory.criarNotificacao("EMAIL");
        notif1.enviar("Bem-vindo ao nosso sistema!");

        System.out.println("\nCenário 2: O sistema precisa enviar um SMS");
        Notificacao notif2 = NotificacaoFactory.criarNotificacao("SMS");
        notif2.enviar("Seu código de verificação é 1234.");

        System.out.println("\nCenário 3: O sistema precisa enviar Push no Celular");
        Notificacao notif3 = NotificacaoFactory.criarNotificacao("PUSH");
        notif3.enviar("Você tem uma nova mensagem de Maria.");
        
        // Testando a segurança (pedindo um tipo que a fábrica ainda não produz)
        System.out.println("\nCenário 4: Tentando criar um tipo inexistente (WhatsApp)");
        try {
            Notificacao notifErro = NotificacaoFactory.criarNotificacao("WHATSAPP");
            notifErro.enviar("Olá!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
