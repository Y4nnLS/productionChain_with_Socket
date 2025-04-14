package cadeia.loja;

import cadeia.modelo.Veiculo;

public class Loja extends Thread {
    public final String nome;
    private final EsteiraLoja esteira;
    private final LogLoja log;
    public Garagem garagem;

    public Loja(String nome, int capacidadeEsteira) {
        this.nome = nome;
        this.esteira = new EsteiraLoja(capacidadeEsteira);
        this.log = new LogLoja(nome);
        this.garagem = new Garagem(this);
    }

    // Simula recebimento de veículo da fábrica
    public void receberVeiculo(Veiculo veiculo) throws InterruptedException {
        esteira.adicionarVeiculo(veiculo);
        int posicao = esteira.getPosicao(veiculo);
        System.out.println("posição: ");
        veiculo.setLoja(veiculo.getIdLoja(), posicao);
        garagem.adicionar(veiculo);

        log.registrarEntrada(veiculo, nome, posicao);

    }

    // Simula cliente retirando veículo
    public Veiculo venderVeiculo() throws InterruptedException {
        return esteira.retirarVeiculo();
    }

    @Override
    public void run() {
        // Thread da loja em modo espera/consumo passivo
        while (true) {
            try {
                Thread.sleep(1000); // Pode ser ajustado conforme a lógica de recebimento
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public boolean temVeiculos() {
        return !esteira.estaVazia();
    }

    public String getNome() {
        return nome;
    }
}
