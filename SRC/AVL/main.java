import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main { 
    public static void main(String[] args) {
        AVL Arvore = new AVL();

        // Path filePath = Paths.get("../../DATA/Quantidade de alunos estrangeiros por nacionalidade_2° Semestre 2023.csv").toAbsolutePath().toString();
        // String arquivoCSV = Paths.get("../../DATA/Quantidade de alunos estrangeiros por nacionalidade_2° Semestre 2023.csv").toAbsolutePath().toString();
        String arquivoCSV = "../../DATA/Quantidade de alunos estrangeiros por nacionalidade_2° Semestre 2023.csv";
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
    
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            clearScreen();
            System.out.println("\nEscolha uma operação:");
            System.out.println("1. Mostrar escolas em ordem alfabética");
            System.out.println("2. Quantidade de alunos por país em uma escola");
            System.out.println("3. Quantidade de alunos por país no Brasil");
            System.out.println("4. ");
            System.out.println("5. Mostrar número de alunos estrangeiros em cada escola");
            System.out.println("6. Comparar Desempenho");
            System.out.println("7. Sair");
            System.out.print("Digite sua opção: ");
            opcao = scanner.nextInt();
            clearScreen();

            switch(opcao){
                case 1:
                    System.out.println("Imprimindo escolas em ordem alfabética:");
                    Arvore.ordemAlfabetica(Arvore.getRoot());
                    continue;
                case 2:
                    System.out.println("Em caso de duvidas a respeito do nome das escolas, escolha primeiro a opção 1. Mostrar escolas em ordem alfabética");
                    System.out.println("Digite o nome da escola que está bucando [Digite 'exit' caso não saiba o nome de uma escola]: ");
                    String escola = scanner.nextLine();
                    Arvore.qtdDeAlunosEmUmaEscola(escola);
                    continue;
                case 3:
                    
                    continue;
                case 4:
                    
                    continue;
                case 5:
                    
                    continue;

                case 6:
                    
                    continue;
                case 7:
                    System.out.println("Saindo...\n\n");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 7);
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}


