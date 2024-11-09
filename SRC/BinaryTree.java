package SRC;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Math;

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
        if(node.getleft() == null) return calc.max(node.getDegree(), getDegree(node.getRight()));
        if(node.getRight() == null)return calc.max(node.getDegree(), getDegree(node.getleft()));
        return calc.max(node.getDegree(), calc.max(getDegree(node.getleft()), getDegree(node.getRight())));
    }

    public int getHeight(){return root.getHeight();} // Encontra a altura da árvore a partir do root.

    public void inOrderTraversal(Node node){ // Imprime os valores dos nós usando o metodo em ordem.
        if(node.getleft() != null) preOrderTraversal(node.getleft());
        System.out.println(node.getData());
        if(node.getRight() != null) preOrderTraversal(node.getRight());
    }

    public void preOrderTraversal(Node node){ // Imprime os valores dos nós usando o metodo pre ordem.
        System.out.println(node.getData());
        if(node.getleft() != null) preOrderTraversal(node.getleft());
        if(node.getRight() != null) preOrderTraversal(node.getRight());
    }

    public void postOrderTraversal(Node node){ // Imprime os valores dos nós usando o metodo pos ordem.
        if(node.getleft() != null) preOrderTraversal(node.getleft());
        if(node.getRight() != null) preOrderTraversal(node.getRight());
        System.out.println(node.getData());

    }

    public void levelOrderTraversal(){ // Imprime os valores dos nós usando o metodo de niveis.
        Queue<Node> Fila = new LinkedList<>();
        Fila.add(getRoot());

        double Altura = Math.pow(2, getHeight()) - 1; // Calculo do tamanho máximo da lista baseada no número de nós.

        for(int i = 0; i < Altura; i++){ // Percorre a fila enquanto tiver elementos
            if(Fila.isEmpty())break;
            Node NoAtual = Fila.poll();
            System.out.println(NoAtual.getData());
            if(NoAtual.hasLeft()) Fila.add(NoAtual.getleft());
            if(NoAtual.hasRight()) Fila.add(NoAtual.getRight());
        }
    }

    public boolean removeNode(Node no){ // remove um nó da arvore
        if(no.isRoot()) return true;
        if(no.getDegree() == 2){ // caso tenha os dois filhos
            
        }else if(no.getDegree() == 1){ // caso tenha um filho
            if(no.hasLeft()){
                if(no.getParent().getleft() == no)no.getParent().setLeft(no.getleft());
                else no.getParent().setRight(no.getleft());
                return true;
            }else if(no.hasRight()){
                if(no.getParent().getleft() == no)no.getParent().setLeft(no.getRight());
                else no.getParent().setRight(no.getRight());
                return true;
            }
        }else{ // caso não tenha filho
            if(no.getParent().getleft() == no)no.getParent().setLeft(null);
            else no.getParent().setRight(null);
            return true;
        }
        return false;
    }
}