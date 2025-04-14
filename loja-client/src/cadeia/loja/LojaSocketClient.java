package cadeia.loja;

import cadeia.modelo.Veiculo;
import java.io.ObjectInputStream;
import java.net.Socket;

public class LojaSocketClient {
    public static void main(String[] args) throws Exception {
        String nomeLoja = (args.length > 0) ? args[0] : "LojaRemota";
        int idLoja = (args.length > 1) ? Integer.parseInt(args[1]) : 0;

        Loja loja = new Loja(nomeLoja, 40);
        loja.start();

        // Iniciar clientes
        for (int i = 0; i < 20; i++) {
            Cliente cliente = new Cliente("Cliente" + i + "-" + nomeLoja, loja.garagem);
            cliente.start();
        }

        Socket socket = new Socket("localhost", 5000);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        while (true) {
            Veiculo v = (Veiculo) in.readObject();
            loja.receberVeiculo(v);
            System.out.println("[" + nomeLoja + "] Veículo recebido: " + v.getId());
        }
    }
}
