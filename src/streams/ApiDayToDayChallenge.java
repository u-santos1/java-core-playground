package streams;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ApiDayToDayChallenge {

    // --- CLASSES DE DOMÍNIO (Simulando o que vem do Banco de Dados) ---
    static class Cliente {
        private Long id;
        private String nome;
        private Optional<String> email;

        public Cliente(Long id, String nome, String email) {
            this.id = id;
            this.nome = nome;
            this.email = Optional.ofNullable(email);
        }

        public Long getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public Optional<String> getEmail() {
            return email;
        }
    }

    static class Pedido {
        private String numero;
        private BigDecimal valorTotal;
        private String status; // "PAGO", "PENDENTE", "CANCELADO"
        private Cliente cliente;

        public Pedido(String numero, BigDecimal valorTotal, String status, Cliente cliente) {
            this.numero = numero;
            this.valorTotal = valorTotal;
            this.status = status;
            this.cliente = cliente;
        }

        public String getNumero() {
            return numero;
        }

        public BigDecimal getValorTotal() {
            return valorTotal;
        }

        public String getStatus() {
            return status;
        }

        public Cliente getCliente() {
            return cliente;
        }
    }

    // --- SIMULANDO O SEU REPOSITÓRIO / BANCO DE DADOS ---
    public static List<Pedido> buscarTodosOsPedidosNoBanco() {
        Cliente c1 = new Cliente(1L, "Wesley", "wesley@gmail.com");
        Cliente c2 = new Cliente(2L, "Maria", null); // Maria não tem email
        Cliente c3 = new Cliente(3L, "João", "joao@empresa.com");

        return Arrays.asList(
                new Pedido("PED-001", new BigDecimal("150.00"), "PAGO", c1),
                new Pedido("PED-002", new BigDecimal("99.90"), "PENDENTE", c2),
                new Pedido("PED-003", new BigDecimal("350.50"), "PAGO", c3),
                new Pedido("PED-004", new BigDecimal("25.00"), "CANCELADO", c1),
                new Pedido("PED-005", new BigDecimal("500.00"), "PAGO", c2));
    }

    public static void main(String[] args) {
        List<Pedido> pedidos = buscarTodosOsPedidosNoBanco();

        System.out.println("--- DESAFIO PRÁTICO PARA O DIA A DIA ---");

        // DESAFIO 1 (STREAMS):
        // Eu quero uma Lista contendo APENAS o número dos pedidos (String) que estão
        // com status "PAGO"
        // TODO: Use a lista 'pedidos', aplique um .filter() e depois um .map() e
        // .collect()

        List<String> numerosPedidosPagos = pedidos.stream()
                .filter(p -> p.getStatus().equals("PAGO"))
                .map(Pedido::getNumero)
                .collect(Collectors.toList());
        System.out.println("Pedidos Pagos: " + numerosPedidosPagos);

        // DESAFIO 2 (STREAMS + REDUCE):
        // Qual é o Faturamento Total (soma do valor) de todos os pedidos "PAGO"?
        // TODO: .filter() por "PAGO", .map() para pegar o valorTotal, e
        // .reduce(BigDecimal.ZERO, BigDecimal::add)

        BigDecimal faturamentoTotal = pedidos.stream()
                .filter(p -> p.getStatus().equals("PAGO"))
                .map(Pedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Faturamento Total Pagos: " + faturamentoTotal);

        // DESAFIO 3 (OPTIONAL):
        // Pegue o Pedido "PED-002" (que é da Maria). Tente extrair o email dela.
        // Se ela não tiver email, retorne a String: "email_nao_informado@sistema.com"
        // TODO: use pedido.getCliente().getEmail().orElse(...)

        String emailNF = pedidos.stream()
                .filter(p -> "PED-002".equals(p.getNumero()))
                .findFirst()
                .map(p -> p.getCliente().getEmail().orElse("email_nao_informado@sistema.com"))
                .orElse("Pedido nao encontrado");

        System.out.println("Email para envio de NF da Maria: " + emailNF);
    }
}
