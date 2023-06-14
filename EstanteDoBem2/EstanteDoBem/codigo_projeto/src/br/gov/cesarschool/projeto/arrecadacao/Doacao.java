package br.gov.cesarschool.projeto.arrecadacao;
import br.gov.cesarschool.projeto.geral.entidade.Produto;


public class Doacao {
    public Doacao(Produto produto, int quantidade) {
        this.quantidade = quantidade;
        this.produto = produto;
    }
    public int quantidade;
    public Produto produto;
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
