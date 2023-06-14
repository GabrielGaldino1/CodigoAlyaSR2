package br.gov.cesarschool.projeto.geral.entidade;

public class Produto {
    private String descricao;
    private Tipo especificacao;
    
    public Produto(String descricao, Tipo especificacao) {
        this.descricao = descricao;
        this.especificacao = especificacao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getEspecificacao() {
        if (especificacao.isAlimento()) {
            return "Alimento";
        } else if (especificacao.isHigiene()) {
            return "Higiene";
        } else if (especificacao.isMaterial()) {
            return "Material";
        } else {
            return "Tipo";
        }
    }
    
    public void setEspecificacao(Tipo especificacao) {
        this.especificacao = especificacao;
    }
}
