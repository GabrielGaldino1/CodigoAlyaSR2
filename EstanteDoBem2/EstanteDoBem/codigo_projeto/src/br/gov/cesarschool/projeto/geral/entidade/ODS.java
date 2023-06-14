package br.gov.cesarschool.projeto.geral.entidade;

public enum ODS {
    ERRADICACAO_DA_POBREZA("Erradicacao da pobreza"),
    FOME_ZERO_E_AGRICULTURA_SUSTENTAVEL("Fome zero e agricultura sustentavel"),
    SAUDE_E_BEM_ESTAR("Saude e bem-estar"),
    EDUCACAO_DE_QUALIDADE("Educacao de qualidade"),
    IGUALDADE_DE_GENERO("Igualdade de genero"),
    AGUA_POTAVEL_E_SANEAMENTO("Agua potavel e saneamento"),
    ENERGIA_ACESSIVEL_E_LIMPA("Energia acessivel e limpa"),
    TRABALHO_DECENTE_E_CRESCIMENTO_ECONOMICO("Trabalho decente e crescimento economico"),
    INDUSTRIA_INOVACAO_E_INFRAESTRUTURA("Industria, inovacao e infraestrutura"),
    REDUCAO_DAS_DESIGUALDADES("Reducao das desigualdades"),
    CIDADES_E_COMUNIDADES_SUSTENTAVEIS("Cidades e comunidades sustentaveis"),
    CONSUMO_E_PRODUCAO_RESPONSAVEIS("Consumo e producao responsaveis"),
    ACAO_CONTRA_A_MUDANCA_GLOBAL_DO_CLIMA("Acao contra a mudanca global do clima"),
    VIDA_SUBMARINA("Vida submarina"),
    VIDA_TERRESTRE("Vida terrestre"),
    PAZ_JUSTICA_E_INSTITUICOES_EFICAZES("Paz, justica e instituicoes eficazes"),
    PARCERIAS_E_MEIOS_DE_IMPLEMENTACAO("Parcerias e meios de implementacao");

    private final String descricao;

    ODS(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ODS valueOfDescricao(String descricao) {
        for (ODS ods : ODS.values()) {
            if (ods.getDescricao().equalsIgnoreCase(descricao)) {
                return ods;
            }
        }
        throw new IllegalArgumentException("ODS invalido: " + descricao);
    }
}
