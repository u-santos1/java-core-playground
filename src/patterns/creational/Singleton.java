// O padrão Singleton (Criacional) garante que uma classe tenha APENAS UMA ÚNICA INSTÂNCIA
// durante toda a execução do programa e fornece um ponto global de acesso a ela.
// É extremamente usado na vida real para: Conexões com Banco de Dados, Logs e Configurações Globais.

class DatabaseConnection {
    // 1. A instância única precisa ser privada e ESTÁTICA (pertence à classe em si).
    // O 'volatile' é um detalhe avançado do Java para evitar problemas se 
    // duas threads tentarem criar a instância ao EXATO mesmo tempo.
    private static volatile DatabaseConnection instanciaUnica;

    // Um atributo qualquer só para provarmos que estamos usando o mesmo objeto
    private String nomeBanco;

    // 2. O GRANDE SEGREDO: O construtor tem que ser PRIVADO!
    // Assim, nenhum outro programador consegue fazer "new DatabaseConnection()" acidentalmente.
    private DatabaseConnection() {
        this.nomeBanco = "BancoDeDados_Producao";
        // Essa mensagem prova se o objeto foi criado mais de uma vez.
        System.out.println("⚠️ [INFO] CONEXÃO CARA E PESADA CRIADA! (Isso deve aparecer apenas UMA vez)");
    }

    // 3. O Ponto de Acesso Global: O famoso método 'getInstance()'
    // É através desse método que o resto do sistema pede o objeto.
    public static DatabaseConnection getInstance() {
        // Double-checked locking: verifica se já existe. Se não existir, cria a primeira vez.
        if (instanciaUnica == null) {
            synchronized (DatabaseConnection.class) {
                if (instanciaUnica == null) {
                    instanciaUnica = new DatabaseConnection();
                }
            }
        }
        // Se a instância já existia, ele pula todo o 'if' acima e simplesmente te devolve a que já estava na memória.
        return instanciaUnica;
    }

    public void executarQuery(String query) {
        System.out.println("Executando query: '" + query + "' no banco [" + nomeBanco + "]");
    }
}

// 4. Cliente (Main)
public class Singleton {
    public static void main(String[] args) {
        System.out.println("--- Testando o Padrão Singleton ---\n");

        System.out.println("Tela 1: Um usuário entrou no sistema e precisa carregar dados.");
        // Repare: nós não damos "new". Nós pedimos a instância.
        DatabaseConnection conexaoDaTela1 = DatabaseConnection.getInstance();
        conexaoDaTela1.executarQuery("SELECT * FROM usuarios");

        System.out.println("\nTela 2: Minutos depois, outro usuário abriu o relatório de vendas.");
        // O sistema pede a instância de novo.
        DatabaseConnection conexaoDaTela2 = DatabaseConnection.getInstance();
        conexaoDaTela2.executarQuery("SELECT * FROM vendas_do_dia");

        // PROVA REAL: Vamos verificar se eles ocupam o EXATO MESMO espaço de memória!
        System.out.println("\n--- PROVA DE FOGO ---");
        System.out.println("A conexaoDaTela1 é exatamente o mesmo objeto na memória que a conexaoDaTela2?");
        
        // Em Java, o operador '==' verifica se é o mesmo endereço físico de memória.
        if (conexaoDaTela1 == conexaoDaTela2) { 
            System.out.println("✅ SIM! O Singleton funcionou. É exatamente a mesma instância, economizando muita RAM e processamento.");
        } else {
            System.out.println("❌ NÃO! Eles são objetos diferentes. O Singleton falhou.");
        }
    }
}
