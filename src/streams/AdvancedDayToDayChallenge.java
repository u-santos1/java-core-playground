package streams;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AdvancedDayToDayChallenge {

    // --- CLASSES DE DOMÍNIO (Simulando Banco de Dados) ---
    static class Funcionario {
        private Long id;
        private String nome;
        private String departamento;
        private BigDecimal salario;
        private List<String> habilidades; // Ex: ["Java", "Spring", "SQL"]

        public Funcionario(Long id, String nome, String departamento, BigDecimal salario, List<String> habilidades) {
            this.id = id;
            this.nome = nome;
            this.departamento = departamento;
            this.salario = salario;
            this.habilidades = habilidades;
        }

        public Long getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getDepartamento() {
            return departamento;
        }

        public BigDecimal getSalario() {
            return salario;
        }

        public List<String> getHabilidades() {
            return habilidades;
        }

        @Override
        public String toString() {
            return nome;
        }
    }

    // Simulando uma exceção de negócio da sua API (Ex: pra retornar HTTP 404)
    static class EntidadeNaoEncontradaException extends RuntimeException {
        public EntidadeNaoEncontradaException(String message) {
            super(message);
        }
    }

    // --- REPOSITÓRIO ---
    public static List<Funcionario> buscarTodosOsFuncionarios() {
        return Arrays.asList(
                new Funcionario(1L, "Wesley", "Backend", new BigDecimal("8000"),
                        Arrays.asList("Java", "Spring", "SQL")),
                new Funcionario(2L, "Ana", "Frontend", new BigDecimal("7500"),
                        Arrays.asList("React", "TypeScript", "CSS")),
                new Funcionario(3L, "Carlos", "Backend", new BigDecimal("9000"),
                        Arrays.asList("Python", "Django", "SQL")),
                new Funcionario(4L, "Bia", "DevOps", new BigDecimal("11000"),
                        Arrays.asList("AWS", "Docker", "Kubernetes")),
                new Funcionario(5L, "João", "Frontend", new BigDecimal("6000"), Arrays.asList("Vue", "JavaScript")));
    }

    public static Optional<Funcionario> buscarPorId(Long id) {
        return buscarTodosOsFuncionarios().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst();
    }

    public static void main(String[] args) {
        List<Funcionario> funcionarios = buscarTodosOsFuncionarios();

        System.out.println("--- DESAFIOS INTERMEDIÁRIOS (DIA A DIA) ---");

        // DESAFIO 1 (STREAMS - GROUPING BY):
        // O RH pediu um relatório agrupando os funcionários por Departamento.
        // TODO: Use a lista 'funcionarios', aplique um
        // .collect(Collectors.groupingBy(...))
        // Dica: A chave do Map será o Departamento (String) e o valor será a Lista de
        // Funcionários (List<Funcionario>)

       Map<String, List<Funcionario>> funcionariosPorDepartamento = funcionarios.stream()
       .collect(Collectors.groupingBy(Funcionario::getDepartamento));
        System.out.println("Funcionários por departamento: " + funcionariosPorDepartamento);

        // DESAFIO 2 (STREAMS - FLATMAP):
        // A empresa quer saber TODAS as tecnologias que o time domina (sem repetir).
        // TODO: Pegue a lista 'funcionarios', use .stream(), depois .flatMap() para
        // extrair as listas de habilidades de cada um,
        // e por fim use .collect(Collectors.toSet()) para remover duplicatas.

        Set<String> todasTecnologias = funcionarios.stream()
                .flatMap(f -> f.getHabilidades().stream())
                .collect(Collectors.toSet());

        System.out.println("Tecnologias dominadas pela empresa: " +
                todasTecnologias);

        // DESAFIO 3 (STREAMS - ANYMATCH):
        // Temos alguém que saiba "AWS" na empresa para cuidar de uma emergência de
        // servidor?
        // TODO: Use .stream(), .anyMatch(...) verificando se a lista de habilidades do
        // funcionário contém "AWS".

        boolean temosEspecialistaAws = funcionarios.stream()
                .anyMatch(f -> f.getHabilidades().contains("AWS"));
        System.out.println("Temos especialista AWS na empresa? " +
                temosEspecialistaAws);

        // DESAFIO 4 (OPTIONAL - ORELSETHROW):
        // Padrão ouro em REST APIs: Buscar um funcionário no banco. Se não existir,
        // lançar uma exceção de negócio.
        // TODO: Use o método 'buscarPorId(99L)' fornecido acima e aplique o
        // .orElseThrow(...)
        // Dica: Use uma expressão lambda para lançar a
        // 'EntidadeNaoEncontradaException'.

        try {
            // Funcionario func = buscarPorId(99L).orElseThrow(...);
            // System.out.println("Encontrado: " + func.getNome());
            Funcionario func = buscarPorId(99L)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario nao encontrado"));
            System.out.println("Encontrado: " + func.getNome());
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Erro esperado pego com sucesso: " + e.getMessage());
        }
    }
}
