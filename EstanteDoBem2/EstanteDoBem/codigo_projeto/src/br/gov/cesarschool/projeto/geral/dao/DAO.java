package br.gov.cesarschool.projeto.geral.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;

import br.gov.cesarschool.projeto.arrecadacao.Arrecadacao;
import br.gov.cesarschool.projeto.arrecadacao.Doacao;
import br.gov.cesarschool.projeto.cliente.Instituicao;
import br.gov.cesarschool.projeto.geral.entidade.Endereco;
import br.gov.cesarschool.projeto.geral.entidade.ODS;


public class DAO {
    private String diretorioBase;

    public DAO(String diretorioBase) {
        this.diretorioBase = diretorioBase;
    }

  


    
    public boolean incluirDoacao(Instituicao instituicao, Doacao doacao) {
        String nomeArquivo = diretorioBase + instituicao.getCNPJ() + "_doacoes.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
  
            writer.write("Doação:");
            writer.newLine();
            writer.write("Item: " + doacao.getProduto().getDescricao());
            writer.newLine();
            writer.write("Quantidade: " + doacao.getQuantidade());
            writer.newLine();
            writer.newLine();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void incluirArrecadacao(Instituicao instituicao, Arrecadacao arrecadacao) {
        String nomeArquivo = diretorioBase + instituicao.getCNPJ() + "_arrecadacao.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write("Tipo do produto: " + arrecadacao.getProduto().getDescricao());
            writer.newLine();
            writer.write("Quantidade: " + arrecadacao.getQuantidadeMeta());
            writer.newLine();
            writer.write("Id da arrecadacao: " + arrecadacao.getSelfId());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public boolean incluirInst(Instituicao instituicao) {
        String nomeArquivo = diretorioBase + instituicao.getCNPJ() + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            writer.write("Nome: " + instituicao.getNome());
            writer.newLine();
            writer.write("Email: " + instituicao.getEmail());
            writer.newLine();
            writer.write("Telefone: " + instituicao.getTelefone());
            writer.newLine();
            writer.write("ID: " + instituicao.getId());
            writer.newLine();
            writer.write("CNPJ: " + instituicao.getCNPJ());
            writer.newLine();
            writer.write("Descricao: " + instituicao.getDescricao());
            writer.newLine();
            writer.write("Instagram: " + instituicao.getInstagram());
            writer.newLine();
            writer.write("ODS: " + instituicao.getODS());
            writer.newLine();
            writer.write("Setor de atuacao: " + instituicao.getSetor());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String[] lerArrecadacao(Instituicao instituicao) {
            String nomeArquivo = diretorioBase + instituicao.getCNPJ() + "_arrecadacao" + ".txt";
            List<String> ArrecadacaosList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
                String line;


                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Tipo do produto:")) {
                        String categoria = line.substring(line.indexOf(":") + 1).trim();
                        ArrecadacaosList.add(categoria);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] ArrecadacaosArray = new String[ArrecadacaosList.size()];
            return ArrecadacaosList.toArray(ArrecadacaosArray);
        }

    public void atualizarArrecadacao(Instituicao instituicao, Doacao doacao) {
        String nomeArquivo = diretorioBase + instituicao.getCNPJ() + "_arrecadacao" + ".txt";
        File arquivo = new File(nomeArquivo);
        List<String> linhas = new ArrayList<>();    
        System.out.println(nomeArquivo);

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.equalsIgnoreCase("Tipo do produto: " + doacao.getProduto().getDescricao())) {
                    String proximaLinha = reader.readLine();


                    int indiceSeparador = proximaLinha.indexOf(":");
                    if (indiceSeparador != -1) {
                        String conteudo = proximaLinha.substring(indiceSeparador + 1).trim();


                        int quantidadeArrecadacao = Integer.parseInt(conteudo);


                        int novaQuantidade = quantidadeArrecadacao - doacao.getQuantidade();

                        if (novaQuantidade > 0) {

                            linhas.add(linha);
                            linhas.add("Quantidade: " + novaQuantidade);
                        }
                        continue;
                    }
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(arquivo)) {
            for (String linha : linhas) {
                writer.write(linha);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer[] lerQuantidades(Instituicao instituicao) {
        String nomeArquivo = diretorioBase + instituicao.getCNPJ()  + ".txt";
        List<Integer> quantidadesList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Quantidade:")) {
                    String quantidadeStr = line.substring(line.indexOf(":") + 1).trim();
                    int quantidade = Integer.parseInt(quantidadeStr);
                    quantidadesList.add(quantidade);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return quantidadesList.toArray(new Integer[quantidadesList.size()]);
    }

   public Instituicao buscarInstPorCNPJ(String cnpj) {
    String nomeArquivo = diretorioBase + cnpj + ".txt";
    File arquivo = new File(nomeArquivo);

    if (arquivo.exists()) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String nome = null;
            String email = null;
            String telefone = null;
            String instagram = null;
            Endereco endereco = null;
            ODS ods = null;
            String descricao = null;
            String setor = null;
            int id = 0;

            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome:")) {
                    nome = linha.substring(5).trim();
                } else if (linha.startsWith("Email:")) {
                    email = linha.substring(6).trim();
                } else if (linha.startsWith("Telefone:")) {
                    telefone = linha.substring(9).trim();
                } else if (linha.startsWith("Instagram:")) {
                    instagram = linha.substring(10).trim();
                } else if (linha.startsWith("Endereco:")) {
                } else if (linha.startsWith("ODS:")) {
                    
                } else if (linha.startsWith("Descricao:")) {
                    descricao = linha.substring(10).trim();
                } else if (linha.startsWith("Setor:")) {
                    setor = linha.substring(6).trim();
                }
            }


            Instituicao instituicao = new Instituicao(nome, cnpj, email, telefone, instagram, endereco, ods, descricao, setor, id);
            return instituicao;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    return null; 
}



}