package streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PraticaFinalStreams {

    // --- CLASSES DE DOMÍNIO ---

    static class Livro {
        private String titulo;
        private String categoria;

        public Livro(String titulo, String categoria) {
            this.titulo = titulo;
            this.categoria = categoria;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getCategoria() {
            return categoria;
        }

        @Override
        public String toString() {
            return titulo;
        }
    }

    static class Autor {
        private Long id;
        private String nome;
        private List<Livro> livrosPublicados;

        public Autor(Long id, String nome, List<Livro> livrosPublicados) {
            this.id = id;
            this.nome = nome;
            this.livrosPublicados = livrosPublicados;
        }

        public Long getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public List<Livro> getLivrosPublicados() {
            return livrosPublicados;
        }

        @Override
        public String toString() {
            return nome;
        }
    }

    // --- EXCEÇÃO CUSTOMIZADA ---
    static class AutorNaoEncontradoException extends RuntimeException {
        public AutorNaoEncontradoException(String message) {
            super(message);
        }
    }

    // --- BANCO DE DADOS MOCK ---
    public static List<Autor> buscarAutoresNoBanco() {
        return Arrays.asList(
                new Autor(1L, "Robert C. Martin", Arrays.asList(
                        new Livro("Clean Code", "Tecnologia"),
                        new Livro("Clean Architecture", "Tecnologia"))),
                new Autor(2L, "J.R.R. Tolkien", Arrays.asList(
                        new Livro("O Senhor dos Anéis", "Fantasia"),
                        new Livro("O Hobbit", "Fantasia"))),
                new Autor(3L, "Martin Fowler", Arrays.asList(
                        new Livro("Refactoring", "Tecnologia"))));
    }

    public static Optional<Autor> buscarAutorPorId(Long id) {
        return buscarAutoresNoBanco().stream()
                .filter(autor -> autor.getId().equals(id))
                .findFirst();
    }

    public static void main(String[] args) {
        List<Autor> autores = buscarAutoresNoBanco();

        System.out.println("--- PRÁTICA FINAL: STREAMS E OPTIONAL ---");

        // --------------------------------------------------------------------------------------
        // EXERCÍCIO 1: orElseThrow
        // Objetivo: Tente buscar o Autor de ID 99 usando o método 'buscarAutorPorId'.
        // Se não encontrar, lance a exceção 'AutorNaoEncontradoException'.
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- Exercício 1 ---");
        try {
            // TODO: Descomente a linha abaixo e implemente a lógica usando orElseThrow
            // Autor autorEncontrado = buscarAutorPorId(99L).orElseThrow(...);
            Autor autorEncontrado = buscarAutorPorId(1L)
                    .orElseThrow(() -> new AutorNaoEncontradoException("Autor nao encontrado"));
            System.out.println("Autor encontrado: " + autorEncontrado.getNome());
        } catch (AutorNaoEncontradoException e) {
            System.out.println("Erro esperado disparado: " + e.getMessage());
        }

        // --------------------------------------------------------------------------------------
        // EXERCÍCIO 2: flatMap
        // Objetivo: A biblioteca quer uma lista simples (List<Livro>) contendo TODOS os
        // livros
        // de TODOS os autores misturados.
        // Dica: Use autores.stream().flatMap(...) e transforme a lista de livros de
        // cada autor
        // em um stream. Depois use collect para criar a lista final.
        // --------------------------------------------------------------------------------------
        System.out.println("\n--- Exercício 2 ---");

        List<Livro> todosOsLivros = autores.stream()
                .flatMap(autor -> autor.getLivrosPublicados().stream())
                .collect(Collectors.toList());
        // TODO: Implemente a lógica aqui
        // List<Livro> todosOsLivros = ...

        System.out.println("Todos os livros na biblioteca: " + todosOsLivros);

        // --------------------------------------------------------------------------------------
        // EXERCÍCIO 3: groupingBy
        // Objetivo: Agora que você tem a lista com 'todosOsLivros' (do exercício 2), o
        // gerente
        // da biblioteca quer que você agrupe esses livros por 'Categoria'.
        // Dica: Use o stream na lista de livros gerada acima e aplique o
        // Collectors.groupingBy(Livro::getCategoria)
        // --------------------------------------------------------------------------------------

        // TODO: Implemente a lógica aqui
        // Map<String, List<Livro>> livrosPorCategoria = ...

        Map<String, List<Livro>> livrosPorCategoria = todosOsLivros.stream()
                .collect(Collectors.groupingBy(Livro::getCategoria));
        System.out.println("Livros agrupados por categoria: " + livrosPorCategoria);

    }
}
