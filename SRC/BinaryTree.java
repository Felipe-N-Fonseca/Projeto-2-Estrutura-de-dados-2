package SRC;

class BinaryTree{
    private Node root; // Ponteiro do nó inicial.

    // Construtores, setters e getters.
    public BinaryTree(){setRoot(null);}
    public BinaryTree(Node node){setRoot(node);}
    public void setRoot(Node node){this.root = node;}
    public Node getRoot(){return this.root;}

    
    // Métodos:
    
    public boolean isEmpty(){return root == null;} // Verifica se a árvore está vazia
    
    public int getDegree(Node node){ // Encontra o grau da arvore.
        if(node.isLeaf())return 0;
        if(node.getLeft() == null) return calc.max(node.getDegree(), getDegree(node.getRight()));
        if(node.getRight() == null)return calc.max(node.getDegree(), getDegree(node.getLeft()));
        return calc.max(node.getDegree(), calc.max(getDegree(node.getLeft()), getDegree(node.getRight())));
    }

    public int getHeight(){return root.getHeight();} // Encontra a altura da árvore a partir do root.

    public void inOrderTraversal(Node node){ // Imprime os valores dos nós usando o metodo em ordem.
        if(node.getLeft() != null) inOrderTraversal(node.getLeft());
        System.out.println(node.getDe());
        if(node.getRight() != null) inOrderTraversal(node.getRight());
    }
    
    private int diffCompare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
    }

    public void remove(String data) {
        root = remove(root, data);
    }

    private Node remove(Node node, String data) {
        if (node == null) {
            throw new RuntimeException("Nó com chave " + data + " não existe na BST!");
        }

        // Comparar ignorando a diferença entre maiúsculas e minúsculas
        int diff = diffCompare(data, node.getNomeEsc());
                
        if (diff < 0) {
            node.setLeft(remove(node.getLeft(), data));
        } else if (diff > 0) {
            node.setRight(remove(node.getRight(), data));
        } else {
            node = removeNode(node);
        }
        
        return node;
    }

    private Node removeNode(Node node) {
        // Se o nó for uma folha, basta removê-lo
        if (node.isLeaf()) {
            return null;
        }
        
        // Se não tiver filho à esquerda, substitui pelo filho à direita
        if (!node.hasLeft()) {
            return node.getRight();
        } 
        
        // Se não tiver filho à direita, substitui pelo filho à esquerda
        else if (!node.hasRight()) {
            return node.getLeft();
        } else {
            // Se tiver ambos os filhos, encontra o predecessor
            Node predecessor = predecessor(node.getNomeEsc());
            node.setNomeEsc(predecessor.getNomeEsc());
            node.setLeft(remove(node.getLeft(), predecessor.getNomeEsc()));
        }
        
        return node;        
    }

    public Boolean insertNode(String de, String mun, int codEsc, String nomeEsc, String dsPais, byte numAlunos){
        if(isEmpty()){
            setRoot(new Node(de, null, null, null, mun, codEsc, nomeEsc, dsPais, numAlunos));
            return true;
        }
        if(insertNode(de, mun, codEsc, nomeEsc, dsPais, numAlunos, root)) return true;
        return false;
    }

    public Boolean insertNode(String de, String mun, int codEsc, String nomeEsc, String dsPais, byte numAlunos, Node no){
        int diff = diffCompare(nomeEsc, no.getNomeEsc());
        if(diff == 0){
            no.adicionaEstrangeiro(dsPais, numAlunos);
            return true;
        }
        if(no.isLeaf()){
            if (diff < 0) {
                no.setLeft(new Node(de, null, null, null, mun, codEsc, nomeEsc, dsPais, numAlunos));
                return true;
            } else if (diff > 0) {
                no.setRight(new Node(de, null, null, null, mun, codEsc, nomeEsc, dsPais, numAlunos));
                return true;
            }
        }
        if(!no.isLeaf()){
            if (diff < 0) {
                return insertNode(de, mun, codEsc, nomeEsc, dsPais, numAlunos, no.getLeft());
            } else if (diff > 0) {
                return insertNode(de, mun, codEsc, nomeEsc, dsPais, numAlunos, no.getRight());
            }
        }
        return false;
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
    private Node search(String data) {
        return search(root, data);
    }

    private Node search(Node node, String data) {
        if (node == null) {
            return null;
        }

        // Compara a chave ignorando a diferença de maiúsculas e minúsculas
        int diff = diffCompare(data, node.getNomeEsc());

        if (diff < 0) {
            return search(node.getRight(), data);
        } else if (diff > 0) {
            return search(node.getLeft(), data);
        } else {
            return node; // Encontrou o nó
        }
    }

    
}