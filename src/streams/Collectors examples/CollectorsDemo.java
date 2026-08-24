import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.IntSummaryStatistics;

class Funcionario {
    String nome;
    String departamento;
    int salario;

    public Funcionario(String nome, String departamento, int salario) {
        this.nome = nome;
        this.departamento = departamento;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return nome + " (R$ " + salario + ")";
    }
}

public class CollectorsDemo {
    public static void main(String[] args) {
        System.out.println("--- 🚀 Testando Java Stream Collectors ---\n");

        // Nossa base de dados simulada
        List<Funcionario> funcionarios = Arrays.asList(
            new Funcionario("Wesley", "TI", 5000),
            new Funcionario("Maria", "TI", 7000),
            new Funcionario("João", "RH", 4000),
            new Funcionario("Ana", "RH", 4500),
            new Funcionario("Pedro", "Vendas", 3000)
        );

        // 1. Collectors.toList() -> O mais comum de todos. Coleta os resultados em uma Lista (List<T>)
        List<String> nomesDaTI = funcionarios.stream()
            .filter(f -> f.departamento.equals("TI")) // Filtra quem é da TI
            .map(f -> f.nome)                         // Pega só o nome (String)
            .collect(Collectors.toList());            // Joga numa Lista de String
            
        System.out.println("1. Funcionários da TI (toList): " + nomesDaTI);


        // 2. Collectors.joining() -> Junta um monte de Strings em uma só.
        String todosNomes = funcionarios.stream()
            .map(f -> f.nome)
            .collect(Collectors.joining(", ", "[ ", " ]")); // Delimitador, Prefixo e Sufixo
            
        System.out.println("\n2. Todos os nomes juntos (joining): " + todosNomes);


        // 3. Collectors.groupingBy() -> O MAIS PODEROSO! Agrupa os dados gerando um Map (Dicionário).
        // A chave será o Departamento, e o valor será a lista de funcionários daquele departamento.
        Map<String, List<Funcionario>> porDepartamento = funcionarios.stream()
            .collect(Collectors.groupingBy(f -> f.departamento));
            
        System.out.println("\n3. Agrupado por Departamento (groupingBy): ");
        porDepartamento.forEach((dept, lista) -> {
            System.out.println("   [" + dept + "] -> " + lista);
        });


        // 4. Collectors.summarizingInt() -> Gera várias estatísticas de uma vez só! (Média, Soma, Max, Min)
        IntSummaryStatistics stats = funcionarios.stream()
            .collect(Collectors.summarizingInt(f -> f.salario));
            
        System.out.println("\n4. Estatísticas de Salário da Empresa (summarizingInt):");
        System.out.println("   Total da folha de pagamento: R$ " + stats.getSum());
        System.out.println("   Média salarial: R$ " + stats.getAverage());
        System.out.println("   Maior salário: R$ " + stats.getMax());
        System.out.println("   Menor salário: R$ " + stats.getMin());
        System.out.println("   Qtd de funcionários analisados: " + stats.getCount());
    }
}
