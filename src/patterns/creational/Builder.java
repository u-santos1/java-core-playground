// O padrão Builder (Criacional) ajuda a criar objetos complexos passo a passo.
// É muito útil quando um objeto tem muitos atributos (especialmente opcionais)
// e criar um construtor com dezenas de parâmetros ficaria feio e difícil de ler (chamado de Telescoping Constructor).

class Computador {
    // Atributos obrigatórios
    private String processador;
    private int memoriaRam; // em GB
    private int discoRigido; // em GB

    // Atributos opcionais
    private String placaDeVideo;
    private boolean temTecladoLuminoso;
    private boolean temResfriamentoAgua;

    // Construtor PRIVADO: Ninguém consegue dar "new Computador()".
    // Isso obriga todo mundo a usar o nosso Builder para criar o Computador!
    private Computador(ComputadorBuilder builder) {
        this.processador = builder.processador;
        this.memoriaRam = builder.memoriaRam;
        this.discoRigido = builder.discoRigido;
        this.placaDeVideo = builder.placaDeVideo;
        this.temTecladoLuminoso = builder.temTecladoLuminoso;
        this.temResfriamentoAgua = builder.temResfriamentoAgua;
    }

    // A classe Builder fica aninhada e estática (Esse é o jeito mais moderno e seguro no Java)
    public static class ComputadorBuilder {
        // Obrigatórios (Passados no construtor do Builder)
        private String processador;
        private int memoriaRam;
        private int discoRigido;

        // Opcionais (Inicializados com valor padrão)
        private String placaDeVideo = "Vídeo Integrado (Placa Mãe)";
        private boolean temTecladoLuminoso = false;
        private boolean temResfriamentoAgua = false;

        public ComputadorBuilder(String processador, int memoriaRam, int discoRigido) {
            this.processador = processador;
            this.memoriaRam = memoriaRam;
            this.discoRigido = discoRigido;
        }

        // Métodos encadeados (Fluent Interface) para configurar os atributos opcionais
        public ComputadorBuilder comPlacaDeVideo(String placaDeVideo) {
            this.placaDeVideo = placaDeVideo;
            return this; // Retornar 'this' é o grande truque para encadear os métodos!
        }

        public ComputadorBuilder comTecladoLuminoso(boolean temTecladoLuminoso) {
            this.temTecladoLuminoso = temTecladoLuminoso;
            return this;
        }

        public ComputadorBuilder comResfriamentoAgua(boolean temResfriamentoAgua) {
            this.temResfriamentoAgua = temResfriamentoAgua;
            return this;
        }

        // O método final que "constrói" de fato e devolve o objeto real
        public Computador build() {
            return new Computador(this);
        }
    }

    @Override
    public String toString() {
        return "Computador {" +
                "\n  Processador: '" + processador + '\'' +
                ",\n  Memória RAM: " + memoriaRam + "GB" +
                ",\n  HD/SSD: " + discoRigido + "GB" +
                ",\n  Placa de Vídeo: '" + placaDeVideo + '\'' +
                ",\n  Teclado Luminoso: " + (temTecladoLuminoso ? "Sim" : "Não") +
                ",\n  Water Cooler: " + (temResfriamentoAgua ? "Sim" : "Não") +
                "\n}";
    }
}

// Cliente (Main)
public class Builder {
    public static void main(String[] args) {
        System.out.println("--- Testando o Padrão Builder ---\n");

        // Construindo um PC Básico (apenas os campos obrigatórios)
        System.out.println("1. Montando PC de Escritório (Básico):");
        Computador pcEscritorio = new Computador.ComputadorBuilder("Intel Core i3", 8, 256)
                .build();
        System.out.println(pcEscritorio);

        System.out.println("\n------------------------------------------------\n");

        // Construindo um PC Gamer super equipado (usando encadeamento de métodos - Fluent Interface)
        System.out.println("2. Montando PC Gamer (Com opcionais):");
        Computador pcGamer = new Computador.ComputadorBuilder("AMD Ryzen 9", 32, 2000)
                .comPlacaDeVideo("RTX 4090 24GB")
                .comTecladoLuminoso(true)
                .comResfriamentoAgua(true)
                .build();
        System.out.println(pcGamer);
    }
}
