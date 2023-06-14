package br.gov.cesarschool.projeto.cliente;

import br.gov.cesarschool.projeto.geral.entidade.Usuario;
import br.gov.cesarschool.projeto.arrecadacao.Arrecadacao;
import br.gov.cesarschool.projeto.geral.entidade.Endereco;

import br.gov.cesarschool.projeto.geral.entidade.ODS;
public class Instituicao extends Usuario{
    public String cnpj;
    public String instagram;
    public Endereco endereco;
    public ODS ods;
    public String descricao;
    public Arrecadacao arrecadacao;
    public String setor;
    public int id;


    public Instituicao(String nome, String cnpj, String email, String telefone, String instagram, Endereco endereco, ODS ods, String descricao, String setor, int id) {
        super(nome, email, telefone);
        this.cnpj = cnpj;
        this.instagram = instagram;
        this.endereco = endereco;
        this.ods = ods;
        this.descricao = descricao;
        this.setor = setor;
        this.id = id;

    }


    public String getCNPJ() {
        return cnpj;
    }

    public void setCNPJ(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public String getNomeCompleto() {
        return nome;
    }
    public ODS getODS(){
        return ods;
    }
    public void setODS(ODS ods){
        this.ods = ods;
    }
     public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Arrecadacao getArrecadacao() {
        return arrecadacao;
    }

    public void setArrecadacao(Arrecadacao arrecadacao) {
        this.arrecadacao = arrecadacao;
    }
    
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
    public int getId() {
        return id;
    }


}
