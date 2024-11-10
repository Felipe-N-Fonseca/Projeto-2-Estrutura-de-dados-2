package SRC;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main{
    public static void main(String[] args){
        BinaryTree Arvore = new BinaryTree();

        String arquivoCSV = "../DATA/Quantidade de alunos estrangeiros por nacionalidade_2° Semestre 2023.csv";
        String separador = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha = br.readLine(); // Lê o cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);

                // Extraindo as colunas necessárias
                String de = dados[1];
                String mun = dados[3];
                int codEsc = Integer.parseInt(dados[5]);
                String nomeEsc = dados[6];
                String dsPais = dados[7];
                byte numAlunos = (byte) Integer.parseInt(dados[8]); // Usamos o Byte para economizar memória

                Arvore.insertNode(de, mun, codEsc, nomeEsc, dsPais, numAlunos);

            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }

    }
}


