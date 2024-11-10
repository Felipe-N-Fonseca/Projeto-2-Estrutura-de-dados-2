package SRC;

class AVL{

    // Metodo de rotação para direita
    private Node rotateRight(Node y){
        Node x = y.getLeft();
        Node t2 = x.getRight();

        x.setRight(y);
        y.setLeft(t2);

        y.updateHeights();
        x.updateHeights(); 

        return x;            
    }

    // Metodo de rotação para esquerda
    private Node rotateLeft(Node x){
        Node y = x.getRight();
        Node t2 = y.getLeft();

        y.setLeft(x);
        x.setRight(t2);

        y.updateHeights();
        x.updateHeights();

        return y;
    }


    // Rotação dupla direita
    private Node rotateRightLeft(Node z){
        z.setRight(rotateRight(z.getRight()));
        return rotateLeft(z);
    }

    // Rotação dupla esquerda
    private Node rotateLeftRight(Node z){
        z.setLeft(rotateLeft(z.getLeft()));
        return rotateRight(z);
    }

    // Método para obter a altura do nó
    private int height(Node node) {
        return node == null ? 0 : node.getHeight();
    }

    public void setHeight(int height){
        this.height = height;
    }

     // Método para atualizar a altura do nó
    private void updateHeight(Node node) {
        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
    }


    // Método para obter o fator de balanceamento do nó
    private int getBalance(Node node) {
        return node == null ? 0 : height(node.getLeft()) - height(node.getRight());
    }
}
