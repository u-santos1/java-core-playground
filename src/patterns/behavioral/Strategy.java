// Deixei sem o "package" aqui também para acompanhar a alteração que você fez no Observer.java

// 1. A Interface da Estratégia (O Contrato)
// Define o comportamento comum que pode ser trocado.
interface PaymentStrategy {
    void pay(double amount);
}

// 2. Estratégias Concretas (As diferentes implementações do algoritmo)
class PixPayment implements PaymentStrategy {
    private String pixKey;

    public PixPayment(String pixKey) {
        this.pixKey = pixKey;
    }

    @Override
    public void pay(double amount) {
        System.out.println("✅ Pagamento de R$ " + amount + " realizado via PIX (Chave: " + pixKey + ").");
    }
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;

    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    @Override
    public void pay(double amount) {
        System.out.println("✅ Pagamento de R$ " + amount + " cobrado no Cartão de Crédito final " 
            + cardNumber.substring(cardNumber.length() - 4) + " (Titular: " + name + ").");
    }
}

// 3. O Contexto (A classe que *usa* a estratégia, mas não sabe como ela funciona por dentro)
class ShoppingCart {
    private double totalAmount = 0;
    
    // O carrinho guarda uma referência para a interface da estratégia (o algoritmo de pagamento)
    private PaymentStrategy paymentMethod;

    public void addItem(double price) {
        totalAmount += price;
    }

    // Permite trocar a estratégia em tempo de execução!
    public void setPaymentMethod(PaymentStrategy paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Executa a estratégia
    public void checkout() {
        if (paymentMethod == null) {
            System.out.println("❌ Erro: Por favor, selecione uma forma de pagamento antes do checkout.");
            return;
        }
        paymentMethod.pay(totalAmount);
    }
}

// 4. Cliente (Main)
public class Strategy {
    public static void main(String[] args) {
        System.out.println("--- Testando o Padrão Strategy ---\n");

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(50.0);
        cart.addItem(150.0);
        System.out.println("Valor total no carrinho: R$ 200.0\n");

        // O cliente escolhe pagar com PIX (injetando a estratégia no carrinho)
        System.out.println("Opção 1: Cliente escolheu PIX");
        cart.setPaymentMethod(new PixPayment("meu-email@teste.com"));
        cart.checkout();

        // O cliente muda de ideia e decide pagar com Cartão de Crédito
        System.out.println("\nOpção 2: Cliente mudou de ideia e escolheu Cartão de Crédito");
        cart.setPaymentMethod(new CreditCardPayment("1234567890123456", "WESLEY SILVA"));
        cart.checkout();
    }
}
