package br.gov.cesarschool.projeto.main;

import br.gov.cesarschool.projeto.arrecadacao.Doacao;
import br.gov.cesarschool.projeto.cliente.Instituicao;
import br.gov.cesarschool.projeto.geral.dao.DAO;
import br.gov.cesarschool.projeto.geral.entidade.Endereco;
import br.gov.cesarschool.projeto.geral.entidade.ODS;
import br.gov.cesarschool.projeto.geral.entidade.Produto;
import br.gov.cesarschool.projeto.geral.entidade.Tipo;
import br.gov.cesarschool.projeto.arrecadacao.Arrecadacao;
import br.gov.cesarschool.projeto.cliente.InstituicaoMediator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InstituicaoMediator mediator = InstituicaoMediator.getInstance();

        Instituicao instituicao = null;

        DAO dao = new DAO("EstBem_");

        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1 - Cadastrar Instituicao");
            System.out.println("2 - Registrar Arrecadacao");
            System.out.println("3 - Fazer Doacoes");//é necessário ter uma arrecadação previamente cadastrada para poder doar com sucesso.
            System.out.println("0 - Sair");
            System.out.print("Selecione uma operacao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    instituicao = cadastrarInstituicao(scanner, mediator);
                    break;
                case 2:
                    if (instituicao != null) {
                        registrarArrecadacao(scanner, mediator, instituicao);
                    } else {
                        System.out.println("Não há instituições cadastradas");
                    }
                    break;
                case 3:
                    System.out.print("Insira o CNPJ da ONG que irá receber a doação: ");
                    String cnpjDoacao = scanner.nextLine();
                    instituicao = dao.buscarInstPorCNPJ(cnpjDoacao);
                    if (instituicao != null) {
                        realizarDoacao(scanner, mediator, instituicao);
                        
                    } else {
                        System.out.println("Insira uma instituicao válida!");
                    }
                    break;
                case 0:
                    System.out.println("Encerrando execução.");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    private static Instituicao cadastrarInstituicao(Scanner scanner, InstituicaoMediator mediator) {
        System.out.println("Registro de Instituicao");

        System.out.print("Nome da Instituicao: ");
        String nome = scanner.nextLine();

        System.out.print("E-mail da Instituicao: ");
        String email = scanner.nextLine();

        System.out.print("Número de telefone da Instituicao: ");
        String numeroTelefone = scanner.nextLine();

        System.out.print("ID da Instituicao: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("CNPJ da Instituicao: ");
        String cnpj = scanner.nextLine();

        System.out.print("Descrição da Instituicao: ");
        String descricao = scanner.nextLine();

        System.out.print("ODS da Instituicao: ");
        String odsDescricao = scanner.nextLine();
        ODS ods = ODS.valueOfDescricao(odsDescricao);

        System.out.print("Setor de atuacao: ");
        String setor = scanner.nextLine();

        System.out.print("Instagram da Instituicao: ");
        String instagram = scanner.nextLine();

        System.out.println("Endereco: ");
        System.out.print("Rua: ");
        String rua = scanner.nextLine();

        System.out.print("Número: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        System.out.print("Complemento: ");
        String complemento = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Estado: ");
        String estado = scanner.nextLine();

        System.out.print("Regiao: ");
        String regiao = scanner.nextLine();

        Endereco endereco = new Endereco(rua, numero, complemento, cidade, estado, regiao);

        Instituicao instituicao = new Instituicao(nome, cnpj, email, numeroTelefone, instagram, endereco, ods, descricao, setor, id);
        mediator.inserirInst(instituicao);

        System.out.println("Instituicao registrada com sucesso!");
        return instituicao;
    }

    private static void registrarArrecadacao(Scanner scanner, InstituicaoMediator mediator, Instituicao instituicao) {
        System.out.println("Registro de Arrecadacao da instituicao");
        System.out.print("Nome do item a ser pedido: ");
        String nomeItem = scanner.nextLine();

        System.out.println("Tipo do produto da arrecadacao: ");
        System.out.println("1 - Alimento");
        System.out.println("2 - Higiene");
        System.out.println("3 - Material");
        System.out.print("Escolha o tipo: ");
        int tipoEscolhido = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        System.out.print("Quantidade do item da arrecadacao: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Id da arrecadacao: ");
        String selfId = scanner.nextLine();

        Tipo tipo;
        switch (tipoEscolhido) {
            case 1:
                tipo = Tipo.ALIMENTO;
                break;
            case 2:
                tipo = Tipo.HIGIENE;
                break;
            case 3:
                tipo = Tipo.MATERIAL;
                break;
            default:
                System.out.println("Tipo inválido. Definindo como Material por padrão.");
                tipo = Tipo.MATERIAL;
                break;
        }

        Produto produto = new Produto(nomeItem, tipo);
        Arrecadacao necessidade = new Arrecadacao(0, quantidade, instituicao, produto, selfId);
        instituicao.setArrecadacao(necessidade);

        mediator.inserirArrecadacao(instituicao, necessidade);

        System.out.println("Arrecadacao registrada com sucesso!");
    }

    private static void realizarDoacao(Scanner scanner, InstituicaoMediator mediator, Instituicao instituicao) {
        DAO dao = new DAO("EstBem_");
        System.out.println("Fazer Doações");
        System.out.print("Nome do produto: ");
        String nomeItem = scanner.nextLine();

        Produto produto = new Produto(nomeItem, null);
        System.out.print("Quantidade do item de doação: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Doacao doacao = new Doacao(produto, quantidade);
        dao.atualizarArrecadacao(instituicao,doacao);
        mediator.inserirDoacao(doacao, instituicao);
        
    }
}
