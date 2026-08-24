import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExamples {
    public static void main(String[] args) {
        System.out.println("--- 🌊 Testando Java Streams ---\n");

        // Nossa lista inicial de números
        List<Integer> numeros = Arrays.asList(5, 1, 8, 2, 9, 3, 7, 4, 6, 10);
        System.out.println("Lista Original: " + numeros);

        // 1. FILTER (Filtro) - O "Porteiro"
        // Retorna apenas os elementos que passam no teste (ex: apenas números PARES)
        List<Integer> pares = numeros.stream()
            .filter(n -> n % 2 == 0)
            .collect(Collectors.toList());
        System.out.println("\n1. Apenas os Pares (filter): " + pares);

        // 2. MAP (Transformação) - O "Tradutor"
        // Pega CADA elemento da lista e transforma em outra coisa.
        // Exemplo: Multiplicar todos os números por 10
        List<Integer> multiplicados = numeros.stream()
            .map(n -> n * 10)
            .collect(Collectors.toList());
        System.out.println("2. Todos multiplicados por 10 (map): " + multiplicados);

        // 3. SORTED & LIMIT (Ordenação e Limite)
        // Muito útil para fazer paginação ou pegar os "Top X"
        List<Integer> top3Maiores = numeros.stream()
            .sorted((a, b) -> b.compareTo(a)) // Ordena de forma decrescente
            .limit(3)                         // Corta a lista e pega só os 3 primeiros
            .collect(Collectors.toList());
        System.out.println("3. Top 3 maiores números (sorted + limit): " + top3Maiores);

        // 4. ANYMATCH (O "Investigador")
        // Ele não devolve uma lista, ele devolve um booleano (true/false) rápido.
        // É super otimizado: se ele achar o primeiro, já para de procurar.
        boolean temMaiorQue8 = numeros.stream()
            .anyMatch(n -> n > 8);
        System.out.println("4. Existe algum número > 8? (anyMatch): " + (temMaiorQue8 ? "Sim" : "Não"));

        // 5. REDUCE (Redução / Acumulador)
        // Pega a lista inteira e "espreme" para gerar UM ÚNICO VALOR no final.
        // Exemplo: Somar todos os números. O "0" é o valor inicial do acumulador.
        int somaTotal = numeros.stream()
            .reduce(0, (acumulador, numeroAtual) -> acumulador + numeroAtual);
        System.out.println("5. Soma de todos os números (reduce): " + somaTotal);


        // -------------------------------------------------------------
        // --- 🚀 COMBO: O Verdadeiro Poder do Encadeamento ---
        // -------------------------------------------------------------
        System.out.println("\n--- 🚀 COMBO: O Poder do Encadeamento ---");
        
        // Problema Clássico de Entrevista: 
        // "Pegue os números ímpares, multiplique eles por 2, e me dê a soma total"
        // Fazer isso com "for" e "if" daria umas 7 linhas. Com Stream, vira uma frase!
        
        int resultadoCombo = numeros.stream()
            .filter(n -> n % 2 != 0) // 1º Passo: Filtra só os ímpares [5, 1, 9, 3, 7]
            .map(n -> n * 2)         // 2º Passo: Multiplica por 2 [10, 2, 18, 6, 14]
            .reduce(0, Integer::sum); // 3º Passo: Soma tudo (10+2+18+6+14)
            
        System.out.println("Soma dos ímpares multiplicados por 2: " + resultadoCombo);
    }
}
