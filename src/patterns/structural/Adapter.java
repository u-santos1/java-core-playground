// O padrão Adapter (Estrutural) atua como um "tradutor" ou "benjamim (T)" de tomada.
// Ele permite que duas classes com interfaces totalmente incompatíveis trabalhem juntas,
// sem que você precise alterar o código original de nenhuma das duas.

// 1. O Alvo (Target) - A interface que o nosso sistema JÁ CONHECE e espera usar.
// Imagine que a parede da nossa casa antiga só aceita tomadas de 2 pinos.
interface TomadaDeDoisPinos {
    void ligarNaTomadaDeDoisPinos();
}

// Uma implementação padrão que já funciona na nossa casa antiga
class AparelhoVelho implements TomadaDeDoisPinos {
    @Override
    public void ligarNaTomadaDeDoisPinos() {
        System.out.println("🔌 Aparelho velho ligado DIRETAMENTE na tomada de 2 pinos.");
    }
}

// 2. O Adaptado (Adaptee) - A classe nova ou de terceiros que QUEREMOS usar, mas não encaixa.
// Imagine que você comprou um Notebook importado que tem um plugue gigante de 3 pinos chatos.
class PlugueDeTresPinos {
    public void ligarNaTomadaDeTresPinos() {
        System.out.println("💻 Notebook de última geração ligado via plugue de 3 pinos!");
    }
}

// 3. O Adaptador (Adapter) - A Mágica!
// Ele diz para o sistema: "Confia em mim, eu sou uma TomadaDeDoisPinos" (implementa a interface),
// mas por dentro, ele esconde e traduz o comando para o PlugueDeTresPinos novo.
class AdaptadorTresParaDois implements TomadaDeDoisPinos {
    
    private PlugueDeTresPinos plugueNovo;

    // Quando criamos o adaptador, nós "espetamos" o plugue incompatível nele
    public AdaptadorTresParaDois(PlugueDeTresPinos plugueNovo) {
        this.plugueNovo = plugueNovo;
    }

    // Quando a parede (o sistema) tentar chamar o método antigo de 2 pinos, nós traduzimos!
    @Override
    public void ligarNaTomadaDeDoisPinos() {
        System.out.println("⚙️  [Adaptador] Convertendo a entrada de 3 pinos para o padrão de 2 pinos...");
        plugueNovo.ligarNaTomadaDeTresPinos(); // Delegação do trabalho real
    }
}

// 4. Cliente (Main)
public class Adapter {
    public static void main(String[] args) {
        System.out.println("--- Testando o Padrão Adapter ---\n");

        System.out.println("Cenário 1: Ligando aparelho antigo na parede antiga");
        // Funciona direto, as peças se encaixam nativamente.
        TomadaDeDoisPinos ventiladorVelho = new AparelhoVelho();
        ventiladorVelho.ligarNaTomadaDeDoisPinos();

        System.out.println("\n------------------------------------------------");

        System.out.println("\nCenário 2: Ligando o Notebook Novo (3 pinos) na parede antiga (2 pinos)");
        
        // 1. Temos o notebook novo com plugue incompatível.
        PlugueDeTresPinos notebookNovo = new PlugueDeTresPinos();
        
        // Se tentarmos plugar direto na parede, o Java não deixa compilar:
        // TomadaDeDoisPinos tomada = notebookNovo; // ERRO: Tipos incompatíveis!
        
        // 2. Colocamos o notebook NO ADAPTADOR
        TomadaDeDoisPinos adaptador = new AdaptadorTresParaDois(notebookNovo);
        
        // 3. Agora ligamos o ADAPTADOR na parede! 
        // O sistema acha que é de 2 pinos, mas a mágica acontece lá dentro.
        adaptador.ligarNaTomadaDeDoisPinos();
    }
}
