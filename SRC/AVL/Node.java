package AVL;
import java.util.HashMap;
import java.util.Map;

public class Node{
    private int codEsc, FB;
    private String de, mun, nomeEsc;
    private Node parent, left, right;
    private Map<String, Byte> Estrangeiro;

    // Construtores
    public Node(){
        this(null, null, null, null, null, 0, null, null, (byte) 0);
    }

    public Node(String de, Node parent, Node left, Node right, String mun, int codEsc, String nomeEsc, String dsPais, byte numAlunos){
        setDe(de);
        setParent(parent);
        setLeft(left);
        setRight(right);
        setMun(mun);
        setCodEsc(codEsc);
        setNomeEsc(nomeEsc);
        setEstrangeiro(new HashMap<>());
        if(dsPais != null){
            adicionaEstrangeiro(dsPais, numAlunos);
        }
        setFB(0);

    }

    // Setters e Getters
    public void setDe(String de){this.de = de;}
    public void setParent(Node parent){this.parent = parent;}
    public void setLeft(Node left){this.left = left;}
    public void setRight(Node right){this.right = right;}
    public void setCodEsc(int codEsc){this.codEsc = codEsc;}
    public void setMun(String mun){this.mun = mun;}
    public void setNomeEsc(String nomeEsc){this.nomeEsc = nomeEsc;}
    public void setEstrangeiro(Map<String, Byte> estrangeiro){this.Estrangeiro = estrangeiro;}
    public void setFB(int FB){this.FB = FB;}

    public String getDe(){return this.de;}
    public Node getParent(){return this.parent;}
    public Node getLeft(){return this.left;}
    public Node getRight(){return this.right;}
    public int getCodEsc(){return codEsc;}
    public String getMun(){return mun;}
    public String getNomeEsc(){return nomeEsc;}
    public Map<String, Byte> getEstrangeiro(){return Estrangeiro;}
    public int getFB(){return FB;}


    // Métodos
    public boolean isRoot(){return getParent() == null;} // Verifica se é o nó raiz
    public boolean isLeaf(){return (getLeft() == null && getRight() == null);} // verifica se é o nó folha
    public boolean hasLeft(){return getLeft() != null;} // Verifica se tem filho a esquerda
    public boolean hasRight(){return getRight() != null;} // Verifica se tem filho a direita

    public int getDegree(){ // busca o grau do nó
        if(hasLeft() && hasRight()) return 2;
        if(hasLeft() || hasRight()) return 1;
        return 0;
    }
    
    public int getLevel(){ // busca o nivel do nó
        if(isRoot())return 0;
        return getParent().getLevel()+1;
    }

    public int getHeight(){ // encontra a altura do nó
        if(isLeaf())return 0;
        if(getLeft() == null) return getRight().getHeight()+1;
        if(getRight() == null)return getLeft().getHeight()+1;
        return calc.max(getLeft().getHeight(), getRight().getHeight())+1;
    }

    public boolean adicionaEstrangeiro(String dsPais, byte numAlunos){
        if(Estrangeiro.put(dsPais, numAlunos) == null) return true;
        return false;
    }

    public void updateFB(){
		int leftHeight = hasLeft() ? left.getHeight() : -1;
		int rightHeight = hasRight() ? right.getHeight() : -1;
		setFB(rightHeight - leftHeight);
	}
}

