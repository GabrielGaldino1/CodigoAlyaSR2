package br.gov.cesarschool.projeto.arrecadacao;


import br.gov.cesarschool.projeto.cliente.Instituicao;

import br.gov.cesarschool.projeto.geral.entidade.Produto;

public class Arrecadacao{
    
    private int quantidadeAtual;
    private int quantidadeMeta;
    private Instituicao instituicao;
    private Produto produto;
    private String selfId;



    public Arrecadacao(int quantidadeAtual, int quantidadeMeta, Instituicao instituicao, Produto produto, String selfId) {
        this.quantidadeAtual = quantidadeAtual;
        this.quantidadeMeta = quantidadeMeta;
        this.instituicao = instituicao;
        this.produto = produto;
        this.selfId = instituicao.getCNPJ();
    }
    
    
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }
    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }
    public int getQuantidadeMeta() {
        return quantidadeMeta;
    }
    public void setQuantidadeMeta(int quantidadeMeta) {
        this.quantidadeMeta = quantidadeMeta;
    }
    public Instituicao getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    public String getSelfId() {
        return selfId;
    }

    
}
