// O padrão Decorator (Estrutural) permite adicionar novos comportamentos a um objeto
// dinamicamente em tempo de execução, embrulhando-o em um objeto "Decorador".
// É a alternativa perfeita para evitar a criação de milhares de subclasses
// (ex: PizzaComBorda, PizzaComBordaEBacon, PizzaComBordaBaconEQueijo).

// 1. O Componente Base (A Interface)
interface Pizza {
    String getDescricao();
    double getPreco();
}

// 2. O Componente Concreto (A base real de tudo, o núcleo)
class PizzaMassaFina implements Pizza {
    @Override
    public String getDescricao() {
        return "Pizza de Massa Fina";
    }

    @Override
    public double getPreco() {
        return 20.0;
    }
}

// 3. O Decorador Base (O truque mágico)
// Ele implementa a mesma interface, MAS também "engole" (guarda) um objeto dessa mesma interface.
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizzaEmbrulhada; // A pizza que está sendo decorada (escondida aqui dentro)

    public PizzaDecorator(Pizza pizza) {
        this.pizzaEmbrulhada = pizza;
    }

    // Por padrão, ele apenas delega a chamada para quem está lá dentro
    @Override
    public String getDescricao() {
        return pizzaEmbrulhada.getDescricao(); 
    }

    @Override
    public double getPreco() {
        return pizzaEmbrulhada.getPreco();
    }
}

// 4. Decoradores Concretos (Os Adicionais do nosso pedido!)
class BordaRecheada extends PizzaDecorator {
    public BordaRecheada(Pizza pizza) {
        super(pizza); // Repassa a pizza para o construtor do pai esconder
    }

    @Override
    public String getDescricao() {
        // Pega a descrição de quem está dentro e SOMA a descrição da borda
        return super.getDescricao() + ", com Borda Recheada";
    }

    @Override
    public double getPreco() {
        // Adiciona 5 reais no preço final
        return super.getPreco() + 5.0; 
    }
}

class Bacon extends PizzaDecorator {
    public Bacon(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", Extra Bacon";
    }

    @Override
    public double getPreco() {
        // Adiciona 3.50 no preço final
        return super.getPreco() + 3.5; 
    }
}

// 5. Cliente (Main)
public class Decorator {
    public static void main(String[] args) {
        System.out.println("--- Testando o Padrão Decorator ---\n");

        System.out.println("Pedido 1: Cliente quer uma pizza simples.");
        Pizza pizza1 = new PizzaMassaFina();
        System.out.println("Desc: " + pizza1.getDescricao());
        System.out.println("Preço: R$ " + pizza1.getPreco());

        System.out.println("\n------------------------------------------------");

        System.out.println("\nPedido 2: Cliente quer pizza com borda recheada.");
        // Embrulhamos a pizza simples dentro da BordaRecheada!
        Pizza pizza2 = new BordaRecheada(new PizzaMassaFina());
        System.out.println("Desc: " + pizza2.getDescricao());
        System.out.println("Preço: R$ " + pizza2.getPreco());

        System.out.println("\n------------------------------------------------");

        System.out.println("\nPedido 3: O Monstrão (Borda Recheada + Dose Dupla de Bacon).");
        // Vamos embrulhando a pizza uma dentro da outra, como bonecas russas (Matrioska)
        Pizza pizzaMonstro = new PizzaMassaFina();
        pizzaMonstro = new BordaRecheada(pizzaMonstro);
        pizzaMonstro = new Bacon(pizzaMonstro);
        pizzaMonstro = new Bacon(pizzaMonstro); // Sim, você pode colocar DOIS adicionais iguais!
        
        System.out.println("Desc: " + pizzaMonstro.getDescricao());
        System.out.println("Preço: R$ " + pizzaMonstro.getPreco());
    }
}
