import java.util.Optional;

class Usuario {
    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() { return nome; }
    
    // O email pode ser nulo na base de dados! 
    // Uma ótima prática no Java 8+ é retornar Optional no Getter quando algo pode ser nulo.
    // 'Optional.ofNullable' diz: "Toma aqui o valor. Se ele for null, eu já entrego um Optional.empty() para você"
    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}

public class OptionalExamples {
    
    // Simulando uma busca no banco de dados que pode NÃO encontrar o usuário
    public static Optional<Usuario> buscarUsuarioNoBanco(int id) {
        if (id == 1) {
            // Optional.of() -> Use quando você tem CERTEZA ABSOLUTA que não é nulo.
            return Optional.of(new Usuario("Wesley", "wesley@email.com"));
        } else if (id == 2) {
            return Optional.of(new Usuario("Maria", null)); // Maria não cadastrou email
        } else {
            // Optional.empty() -> O usuário não existe. 
            // O grande ganho: nós NÃO retornamos "null", nós retornamos uma caixa VAZIA!
            return Optional.empty(); 
        }
    }

    public static void main(String[] args) {
        System.out.println("--- 🛡️ Testando Java Optional ---\n");

        // -------------------------------------------------------------
        // Cenário 1: Tudo perfeito (Usuário existe e tem email)
        // -------------------------------------------------------------
        System.out.println("Cenário 1: Buscando Usuário ID = 1");
        Optional<Usuario> optUser1 = buscarUsuarioNoBanco(1);
        
        // .ifPresent -> Faz a pergunta mágica: "Se a caixa não estiver vazia, faça isso com quem está lá dentro"
        optUser1.ifPresent(u -> System.out.println("Encontrado: " + u.getNome()));

        // .map -> Pega o objeto que está dentro do Optional e o transforma em outra coisa
        String nomeMaiusculo = optUser1
            .map(u -> u.getNome().toUpperCase()) // Pega o Usuário, extrai o Nome, e transforma em Maiúsculo
            .orElse("DESCONHECIDO");             // Se por acaso estivesse vazio, retornaria isso.
        System.out.println("Nome Transformado: " + nomeMaiusculo);


        // -------------------------------------------------------------
        // Cenário 2: Tratando valor nulo escondido (Maria não tem email)
        // -------------------------------------------------------------
        System.out.println("\nCenário 2: Buscando Usuário ID = 2 (Sem Email)");
        Optional<Usuario> optUser2 = buscarUsuarioNoBanco(2);
        
        // .flatMap -> É usado porque o método getEmail() JÁ devolve um Optional.
        // Se usássemos apenas .map, ficaríamos com uma aberração tipo: Optional<Optional<String>>.
        String emailDaMaria = optUser2
            .flatMap(Usuario::getEmail)
            .orElse("Email não cadastrado no sistema!"); // Como o email dela era null, essa linha assume o controle!
            
        System.out.println("Email da Maria: " + emailDaMaria);


        // -------------------------------------------------------------
        // Cenário 3: O Usuário NÃO EXISTE (O Famoso NullPointerException foi evitado!)
        // -------------------------------------------------------------
        System.out.println("\nCenário 3: Buscando Usuário ID = 99 (Não existe)");
        Optional<Usuario> optUser99 = buscarUsuarioNoBanco(99);

        // .orElse -> "Tente pegar o valor da caixa. Se estiver vazia, me dê esse valor Padrão aqui"
        Usuario usuarioPadrao = optUser99.orElse(new Usuario("Usuário Visitante", "visitante@site.com"));
        System.out.println("O sistema não achou, então assumiu como: " + usuarioPadrao.getNome());

        // .orElseThrow -> "Se a caixa estiver vazia, pare o sistema e estoure um Erro imediatamente!"
        try {
            System.out.println("Tentando forçar a busca do ID 99...");
            optUser99.orElseThrow(() -> new IllegalArgumentException("❌ ERRO GRAVE: O Usuário não existe!"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
