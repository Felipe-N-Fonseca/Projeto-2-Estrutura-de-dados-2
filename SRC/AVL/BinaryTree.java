/*
 *   Grupo de desenvolvedores do projeto:
 * 
 * - Erick Nakabayashi Dedvitis - 10403532
 * - Felipe do Nascimento Fonseca - 10409389
 * - Matheus Hidalgo do Nascimento Fest Ferreira - 10390172
 * 
 */

import java.util.HashMap;
import java.util.Map;

public class BinaryTree{
    private Node root; // Ponteiro do nó inicial.
    private Map<String, Integer> totalPorPais = new HashMap<>();
    private int DesInsert = 0;
    private int DesSearch = 0;
    private int DesRemove = 0;

    // Construtores, setters e getters.
    public BinaryTree(){setRoot(null);}
    public BinaryTree(Node node){setRoot(node);}
    public void setRoot(Node node){this.root = node;}
    public void setTotalPorPais(Map<String, Integer> totalPorPais){this.totalPorPais = totalPorPais;}
    public Node getRoot(){return this.root;}
    public Map<String, Integer> getTotalPorPais(){return this.totalPorPais;}
    public void incrementaDesInsert(){this.DesInsert++;}
    public void incrementaDesSearch(){this.DesSearch++;}
    public void incrementaDesRemove(){this.DesRemove++;}
    public int getDesInsert(){return this.DesInsert;}
    public int getDesSearch(){return this.DesSearch;}
    public int getDesRemove(){return this.DesRemove;}

    
    // Métodos:
    
    public boolean isEmpty(){return root == null;} // Verifica se a árvore está vazia
    
    public int getDegree(Node node){ // Encontra o grau da arvore.
        if(node.isLeaf())return 0;
        if(node.getLeft() == null && node.getRight() != null) return calc.max(node.getDegree(), getDegree(node.getRight()));
        if(node.getRight() == null)return calc.max(node.getDegree(), getDegree(node.getLeft()));
        return calc.max(node.getDegree(), calc.max(getDegree(node.getLeft()), getDegree(node.getRight())));
    }

    public int getHeight(){return root.getHeight();} // Encontra a altura da árvore a partir do root.
    
    private int diffCompare(String s1, String s2){
        return s1.compareToIgnoreCase(s2);
    }

    public Node remove(String data) {
        Node no = search(data);  // Localiza o nó a ser removido
        if (no == null) {
            incrementaDesRemove();
            throw new RuntimeException("Nó com chave " + data + " não existe na BST!");
        }
        return removeNode(no);
    }
    
    private Node removeNode(Node no) {
        if (no.isLeaf()) {  // Caso 1: Nó é uma folha
            incrementaDesRemove();
            replaceParentReference(no, null);
            return null;  // Nenhum predecessor relevante
        } else if (no.getLeft() == null) {  // Caso 2: Só tem filho à direita
            incrementaDesRemove();
            replaceParentReference(no, no.getRight());
            return null;  // Nenhum predecessor relevante
        } else if (no.getRight() == null) {  // Caso 3: Só tem filho à esquerda
            incrementaDesRemove();
            replaceParentReference(no, no.getLeft());
            return null;  // Nenhum predecessor relevante
        } else {  // Caso 4: Tem dois filhos
            incrementaDesRemove();
            Node predecessor = findMax(no.getLeft());  // Encontra o predecessor
            no.setNomeEsc(predecessor.getNomeEsc());  // Copia o valor do predecessor para o nó
            removeNode(predecessor);  // Remove o predecessor
            return predecessor;  // Retorna o predecessor
        }
    }
    
    // Método auxiliar para substituir a referência do nó pai para o novo nó (ou null)
    private void replaceParentReference(Node node, Node newNode) {
        Node parent = node.getParent();
        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(newNode);
            } else if (parent.getRight() == node) {
                parent.setRight(newNode);
            }
        }
        if (newNode != null) {
            newNode.setParent(parent);  // Atualiza o pai do novo nó
        }
    }
    

    public Node insertNode(String de, String mun, int codEsc, String nomeEsc, String dsPais, byte numAlunos){
        updateTotalPorPais(dsPais, numAlunos);
        if(isEmpty()){
            incrementaDesInsert();
            setRoot(new Node(de, null, null, null, mun, codEsc, nomeEsc, dsPais, numAlunos));
            return getRoot();
        }
        return insertNode(de, mun, codEsc, nomeEsc, dsPais, numAlunos, root);
    }

    public Node insertNode(String de, String mun, int codEsc, String nomeEsc, String dsPais, byte numAlunos, Node no){
        int diff = diffCompare(nomeEsc, no.getNomeEsc());
        if(diff == 0){
            incrementaDesInsert();
            no.adicionaEstrangeiro(dsPais, numAlunos);
            return no;
        }
        if(no.isLeaf()){
            if (diff < 0) {
                incrementaDesInsert();
                no.setLeft(new Node(de, no, null, null, mun, codEsc, nomeEsc, dsPais, numAlunos));
                return no;
            } else if (diff > 0) {
                incrementaDesInsert();
                no.setRight(new Node(de, no, null, null, mun, codEsc, nomeEsc, dsPais, numAlunos));
                return no;
            }
        }
        if(!no.isLeaf()){
            if (diff < 0) {
                incrementaDesInsert();
                if(no.hasLeft())return insertNode(de, mun, codEsc, nomeEsc, dsPais, numAlunos, no.getLeft());
                no.setLeft(new Node(de, no, null, null, mun, codEsc, nomeEsc, dsPais, numAlunos));
                return no;
            } else if (diff > 0) {
                incrementaDesInsert();
                if(no.hasRight())return insertNode(de, mun, codEsc, nomeEsc, dsPais, numAlunos, no.getRight());
                no.setRight(new Node(de, no, null, null, mun, codEsc, nomeEsc, dsPais, numAlunos));
                return no;
            }
        }
        return null;
    }

    public Node findMax() {
		return findMax(root);
	}
	
	private Node findMax(Node node) {
		if (node == null) {
			return null;
		}

		while (node.hasRight()) {
			node = node.getRight();
		}
		return node;
	}
	
    
    public Node findPredecessor(String data) {
        return predecessor(data);
    }

    private Node predecessor(String data) {
        // Faz a busca do nó baseado no nome ignorando o caso
        Node node = search(data);
        return predecessor(node);
    }

    private Node predecessor(Node node) {
        if (node == null) {
            return null;
        }

        // Se o nó tem um filho à esquerda, o predecessor é o maior valor da subárvore à esquerda
        if (node.hasLeft()) {
            return findMax(node.getLeft());
        } else {
            Node current = node;
            Node parent = node.getParent();

            // Caso o nó não tenha filho à esquerda, subimos pela árvore até encontrar um nó
            // que seja filho direito de seu pai (o que significa que o pai é o predecessor)
            while (parent != null && current == parent.getLeft()) {
                current = parent;
                parent = current.getParent();
            }

            return parent;
        }
    }

    // Método de busca que também ignora o caso das letras
    public Node search(String data) {
        return search(root, data);
    }

    public Node search(Node node, String data) {
        if (node == null) {
            incrementaDesSearch();
            return null;
        }

        // Compara a chave ignorando a diferença de maiúsculas e minúsculas
        int diff = diffCompare(data, node.getNomeEsc());

        if (diff > 0) {
            incrementaDesSearch();
            return search(node.getRight(), data);
        } else if (diff < 0) {
            incrementaDesSearch();
            return search(node.getLeft(), data);
        } else {
            incrementaDesSearch();
            return node; // Encontrou o nó
        }
    }

    public void updateTotalPorPais(String a, byte b){
        if (getTotalPorPais().containsKey(a))this.totalPorPais.put(a, this.totalPorPais.get(a) + b);
        else this.totalPorPais.put(a, 0 + b);
    }
    
    public void ordemAlfabetica(Node node){ // Imprime os valores dos nós usando o metodo em ordem.
        if(node.getLeft() != null) ordemAlfabetica(node.getLeft());
        System.out.println(" - " + node.getNomeEsc());
        if(node.getRight() != null) ordemAlfabetica(node.getRight());
    }

    public void qtdDeAlunosEmUmaEscola(String escola){ // implementar
        if(escola == "exit")return;
        Node x = search(escola);
        if(x == null){System.out.println("\n\nNome inválido, tente novamente.");return;}
        System.out.println("Número de alunos estrangeiros na escola " + escola + ": " + x.getSomaEstrangeiros());
    }

    
    public void qtdDeAlunosEmCadaEscola(Node node){ // Imprime os valores dos nós usando o metodo em ordem.
        if(node.getLeft() != null) qtdDeAlunosEmCadaEscola(node.getLeft());
        qtdDeAlunosEmUmaEscola(node.getNomeEsc());
        if(node.getRight() != null) qtdDeAlunosEmCadaEscola(node.getRight());
    }

    public void getQtdDeAlunosPorPais(){
        for (String a : this.totalPorPais.keySet()){
            System.out.println(" - " + a + ": " + this.totalPorPais.get(a));
        }
    }

public void getEscolasMaiorConcentracaoPorPais(String pais) {
    // Chama o método auxiliar e obtém a escola com maior concentração
    Node escolaComMaiorConcentracao = escolaComMaiorConcentracaoPorPais(root, pais);
    
    // Se a escola foi encontrada, imprime o nome da escola e a concentração
    if (escolaComMaiorConcentracao != null) {
        int maiorConcentracao = escolaComMaiorConcentracao.getEstrangeiro().get(pais);
        System.out.println("Escola com maior concentração de alunos de " + pais + ": " 
            + escolaComMaiorConcentracao.getNomeEsc() + "\nConcentração de alunos: " + maiorConcentracao);
    } else {
        System.out.println("Nenhuma escola encontrada para o país: " + pais);
    }
}

// Método auxiliar que retorna a escola com maior concentração
private Node escolaComMaiorConcentracaoPorPais(Node node, String pais) {
    if (node == null) {
        return null;  // Retorna null se o nó for vazio
    }

    // Inicializa a escola com maior concentração e a maior quantidade de alunos
    Node escolaComMaiorConcentracao = null;
    int maiorConcentracao = -1;

    // Verifica se a escola tem alunos estrangeiros desse país
    if (node.getEstrangeiro().containsKey(pais)) {
        int quantidadeAlunosPais = node.getEstrangeiro().get(pais);

        // Se a quantidade de alunos dessa nacionalidade for maior do que a atual maior concentração
        if (quantidadeAlunosPais > maiorConcentracao) {
            maiorConcentracao = quantidadeAlunosPais;
            escolaComMaiorConcentracao = node;
        }
    }

    // Recursivamente percorre a árvore para procurar em ambos os filhos
    Node escolaEsquerda = escolaComMaiorConcentracaoPorPais(node.getLeft(), pais);
    Node escolaDireita = escolaComMaiorConcentracaoPorPais(node.getRight(), pais);

    // Compara as escolas encontradas nos filhos e retorna a escola com maior concentração
    if (escolaEsquerda != null && escolaEsquerda.getEstrangeiro().get(pais) > maiorConcentracao) {
        escolaComMaiorConcentracao = escolaEsquerda;
    }
    if (escolaDireita != null && escolaDireita.getEstrangeiro().get(pais) > maiorConcentracao) {
        escolaComMaiorConcentracao = escolaDireita;
    }

    return escolaComMaiorConcentracao;  // Retorna o nó da escola com maior concentração
}

    
    public void distribuiPorDistrito() {
        Map<String, Map<String, Integer>> distribucaoPorDistrito = new HashMap<>();

        distribuiPorDistritoRecursivo(root, distribucaoPorDistrito);

        // Exibindo a distribuição por distrito
        for (String municipio : distribucaoPorDistrito.keySet()) {
            System.out.println("Distrito: " + municipio);
            Map<String, Integer> nacionalidades = distribucaoPorDistrito.get(municipio);
            for (String nacionalidade : nacionalidades.keySet()) {
                System.out.println("  Nacionalidade: " + nacionalidade + " - " + nacionalidades.get(nacionalidade) + " alunos");
            }
            System.out.println();
        }
    }

    private void distribuiPorDistritoRecursivo(Node node, Map<String, Map<String, Integer>> distribucaoPorDistrito) {
        if (node == null) {
            return;
        }

        // Pegando o município da escola
        String municipio = node.getMun();

        // Para cada nacionalidade na escola, atualize o total de alunos por nacionalidade
        for (Map.Entry<String, Byte> entry : node.getEstrangeiro().entrySet()) {
            String nacionalidade = entry.getKey();
            byte numAlunos = entry.getValue();

            distribucaoPorDistrito
                .computeIfAbsent(municipio, k -> new HashMap<>())
                .merge(nacionalidade, (int) numAlunos, Integer::sum);
        }

        // Recursivamente processa os filhos
        distribuiPorDistritoRecursivo(node.getLeft(), distribucaoPorDistrito);
        distribuiPorDistritoRecursivo(node.getRight(), distribucaoPorDistrito);
    }



}