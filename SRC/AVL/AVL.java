/*
 *   Grupo de desenvolvedores do projeto:
 * 
 * - Erick Nakabayashi Dedvitis - 10403532
 * - Felipe do Nascimento Fonseca - 10409389
 * - Matheus Hidalgo do Nascimento Fest Ferreira - 10390172
 * 
 */

public class AVL extends BinaryTree{
    // Construtores
    public AVL(){super();}
    public AVL(Node root){super(root);}

    // Métodos:

    @Override
    public Node insertNode(String de, String mun, int codEsc, String nomeEsc, String dsPais, byte numAlunos) {
        return balance(super.insertNode(de, mun, codEsc, nomeEsc, dsPais, numAlunos));
    }
	
	@Override
	public Node remove(String data) {
		return balance(super.remove(data));
	}

    private Node balance(Node no){
        if(no == null)return null;
        
        int noFB = no.getFB();
        if(noFB < -1){
            if(no.getLeft().getFB() <= 0)no = RR(no);
            else no = LR(no);
        }else if (noFB > 1){
            if(no.getRight().getFB() >= 0)no = LL(no);
            else no = RL(no);
        }
        
        return no;
    }

    // Metodo de rotação para direita
    private Node RR(Node y){
        Node x = y.getLeft();
        y.setLeft(x.getRight());
        if(x.getRight() != null)x.getRight().setParent(y);
        x.setParent(y.getParent());
        x.setRight(y);
        y.setParent(x);
        y = x;
        y.updateFB();
        y.getRight().updateFB();
        return y;            
    }
        
    // Metodo de rotação para esquerda
    private Node LL(Node y){
        Node x = y.getRight();
        y.setRight(x.getLeft());
        if(x.getLeft() != null)x.getLeft().setParent(y);
        x.setParent(y.getParent());
        x.setLeft(y);
        y.setParent(x);
        y = x;
        y.updateFB();
        y.getLeft().updateFB();
        return y;        
    }

    private Node LR(Node no){
        no.setLeft(LL(no.getLeft()));
        return RR(no);
    }

    private Node RL(Node no){
        no.setRight(RR(no.getRight()));
        return LL(no);
    }
    
}




