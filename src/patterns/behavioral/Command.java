package patterns.behavioral;

// 1. A Interface do Comando (O padrão em si)
interface ICommand {
    void execute();
}

// 2. O Recebedor (Receiver) - Quem realmente faz o trabalho pesado
class Light {
    public void turnOn() {
        System.out.println("A luz está LIGADA 💡");
    }
    public void turnOff() {
        System.out.println("A luz está DESLIGADA 🌑");
    }
}

// 3. Comandos Concretos - Encapsulam a chamada ao Recebedor
class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// 4. O Invocador (Invoker) - Quem aperta o botão. Ele não sabe o que o comando faz!
class RemoteControl {
    private ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void pressButton() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Nenhum comando configurado.");
        }
    }
}

// 5. Cliente (Main) - Configura tudo
public class Command {
    public static void main(String[] args) {
        System.out.println("--- Testando o Padrão Command ---\n");
        
        // 1. Criamos o recebedor
        Light livingRoomLight = new Light();

        // 2. Criamos os comandos passando o recebedor
        ICommand turnOn = new LightOnCommand(livingRoomLight);
        ICommand turnOff = new LightOffCommand(livingRoomLight);

        // 3. Criamos o invocador (Controle Remoto)
        RemoteControl remote = new RemoteControl();

        // 4. Testando: Configuramos o controle para ligar e apertamos o botão
        System.out.println("Configurando controle para LIGAR...");
        remote.setCommand(turnOn);
        remote.pressButton();

        // 5. Testando: Configuramos o controle para desligar e apertamos o botão
        System.out.println("\nConfigurando controle para DESLIGAR...");
        remote.setCommand(turnOff);
        remote.pressButton();
    }
}
