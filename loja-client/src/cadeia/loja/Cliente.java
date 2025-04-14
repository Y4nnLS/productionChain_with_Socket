package cadeia.loja;

import java.util.Random;

import cadeia.modelo.Veiculo;

public class Cliente extends Thread {
    private final Garagem garagem;
    private final String nome;
    private final Random random = new Random();

    public Cliente(String nome, Garagem garagem) {
        this.nome = nome;
        this.garagem = garagem;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Veiculo v = garagem.retirar();
                LogCliente.registrar(nome,
                        "Comprou veículo ID: " + v.getId() +
                                " | Tipo: " + v.getTipo().getDescricao() +
                                " | Cor: " + v.getCor().getDescricao() +
                                " | Estação: " + v.getIdEstacao() +
                                " | Funcionário: " + v.getIdFuncionario() +
                                " | " + garagem.loja.nome +
                                " | Posição na esteira da loja: " + v.getPosicaoEsteiraLoja());
                Thread.sleep(random.nextInt(2000)); // Simula tempo entre compras
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
