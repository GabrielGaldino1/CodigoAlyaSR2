package br.gov.cesarschool.projeto.cliente;


import br.gov.cesarschool.projeto.geral.dao.DAO;
import br.gov.cesarschool.projeto.arrecadacao.Arrecadacao;
import br.gov.cesarschool.projeto.arrecadacao.Doacao;
import br.gov.cesarschool.projeto.geral.entidade.Usuario;
import br.gov.cesarschool.projeto.geral.util.ValidadorCNPJ;


public class InstituicaoMediator {
    private static InstituicaoMediator instance;
    private DAO arquivoDAO;

    private InstituicaoMediator() {
        arquivoDAO = new DAO("EstBem_");
    }

    public static InstituicaoMediator getInstance() {
        if (instance == null) {
            instance = new InstituicaoMediator();
        }
        return instance;
    }


    public boolean incluirInst(Instituicao instituicao) {
        if (validarInformacoesdaInst(instituicao)) {
            return arquivoDAO.incluirInst(instituicao);
        }
        return false;
    }

    private boolean validarInformacoesdaInst(Instituicao instituicao) {
        
    	validarInformacoesUsuario(instituicao);

        if (instituicao.getCNPJ() == null || instituicao.getCNPJ().isEmpty()) {
            System.out.println("CNPJ da instituicao branco ou nulo.");
            return false;
        }
        if (!ValidadorCNPJ.validarCNPJ(instituicao.getCNPJ())) {
            System.out.println("CNPJ da instituicao invalido.");
            return false;
        }

        if (instituicao.getDescricao() == null || instituicao.getDescricao().isEmpty()) {
            System.out.println("Descricao da instituicao invalida.");
            return false;
        }
        if (instituicao.getODS() == null || instituicao.getODS().getDescricao().isEmpty()) {
            System.out.println("ODS da instituicao invalida.");
            return false;
        }
        if(instituicao.getEndereco().getRua() == null || instituicao.getEndereco().getRua().isEmpty()){
            System.out.println("Rua invalida.");
        }
	    if (instituicao.getEndereco().getNumero() < 0) {
            System.out.println("Numero invalido.");
	        return false;
	    }
	    if (instituicao.getEndereco().getCidade() == null || instituicao.getEndereco().getRua().isEmpty()) {
	        System.out.println("Cidade invalida");
            return false;
	    }
	    if (instituicao.getEndereco().getEstado() == null || instituicao.getEndereco().getEstado().isEmpty()) {
	        System.out.println("Estado invalido.");
            return false;
	    }
	    if (instituicao.getEndereco().getRegiao() == null || instituicao.getEndereco().getRegiao().isEmpty()) {
	        System.out.println("Regiao invalida.");
            return false;
	    }
        return true;
    }
    private boolean validarInformacoesUsuario(Usuario usuario) {
    	  if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
              System.out.println("Nome inválido.");
              return false;
          }

          if (usuario.getEmail() == null || usuario.getEmail().isEmpty() ) {
              System.out.println("Email inválido.");
              return false;
          }

          if (usuario.getTelefone() == null || usuario.getTelefone().isEmpty() ) {
              System.out.println("Telefone inválido.");
              return false;
          }
          
          return true;
    }
    

   private boolean validarInformacoesDoacao(Doacao doacao, Instituicao instituicao) {
        if (doacao.getProduto().getDescricao() == null) {
            System.out.println("Item da doação inválido.");
            return false;
        }
        if (doacao.getQuantidade() <= 0) {
            System.out.println("Quantidade da doação inválida.");
            return false;
        }

        String[] arrecsArray = arquivoDAO.lerArrecadacao(instituicao);


        for (String categoria : arrecsArray) {
            if (doacao.getProduto().getDescricao().equalsIgnoreCase(categoria)) {
                return true;
            }
        }

        System.out.println("Item da doação não corresponde a nenhuma arrecadacao.");
        return false;
    }    
    
    
    public boolean inserirInst(Instituicao instituicao) {
        if (validarInformacoesdaInst(instituicao) != false) {
        	arquivoDAO.incluirInst(instituicao);
        	return true;
        }
        else {
        	System.out.println("Erro ao incluir instituicao");
        	return false;
        }
    }
    
    
    public boolean inserirDoacao(Doacao doacao, Instituicao instituicao) {
        if (validarInformacoesDoacao(doacao, instituicao)!= false) {
        	arquivoDAO.incluirDoacao(instituicao,doacao);
        	return true;
        }
        else {
        	System.out.println("Erro ao incluir Doacao");
        	return false;
        }
    }
    
    
    public void inserirArrecadacao(Instituicao instituicao, Arrecadacao arrecadacao) {
        arquivoDAO.incluirArrecadacao(instituicao, arrecadacao);
    }
    
  
}