package teste_lode;

public class PacienteModel {
    private static int contador = 1;
    private int codigo;
    private String nome;
    private String dataNascimento;
    private String endereco;
    private String observacoes;

    public PacienteModel(String nome, String dataNascimento, String endereco, String observacoes) {
        this.codigo = contador++;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.observacoes = observacoes;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Nome: " + nome + ", Data de Nascimento: " + dataNascimento +
                ", Endereço: " + endereco + ", Observações: " + observacoes;
    }
}
