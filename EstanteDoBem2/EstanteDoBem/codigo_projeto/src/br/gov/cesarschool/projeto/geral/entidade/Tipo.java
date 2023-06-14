package br.gov.cesarschool.projeto.geral.entidade;

public enum Tipo {
    ALIMENTO(true, false, false),
    HIGIENE(false, true, false),
    MATERIAL(false, false, true);

    private boolean isAlimento;
    private boolean isHigiene;
    private boolean isMaterial;

    Tipo(boolean isAlimento, boolean isHigiene, boolean isMaterial) {
        this.isAlimento = isAlimento;
        this.isHigiene = isHigiene;
        this.isMaterial = isMaterial;
    }

    public boolean isAlimento() {
        return isAlimento;
    }

    public boolean isHigiene() {
        return isHigiene;
    }

    public boolean isMaterial() {
        return isMaterial;
    }
}

