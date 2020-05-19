package data_structures;

public class BinaryTree<T> {
    private Node<T> root;
    private int count = 2;


    public BinaryTree(T startValue){
        root = new Node<>(startValue);
    }

    public void add(T value){
        Node<T> current = root;
        int countCopy = count;
        while(countCopy >= 2) {
            if (countCopy < 4) {
                if (countCopy % 2 == 0) {
                    current.setLeft(new Node<T>(value));
                } else {
                    current.setRight(new Node<T>(value));
                }
            }
            else {
                if (countCopy % 2 == 0) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }

            }
            countCopy /= 2;
        }
        count++;
    }

    public String DFS(){
        String r = "";
        r += root.getValue().toString() + ", ";
        r += DFS(root.left);
        r += DFS(root.right);
        return r;
    }

    private String DFS(Node<T> n){
        if(n == null) return "";
        String r = "";
        r += n.getValue().toString() + ", ";
        r += DFS(n.left);
        r += DFS(n.right);
        return r;
    }

    public String BFS(){
        Queue<Node<T>> queue = new Queue<>();
        String r = "";
        queue.add(root);
        Node<T> current;
        while(queue.peek() != null){
            current = queue.pop();
            r += current.getValue().toString() + ", ";
            if(current.getLeft() != null){
                queue.add(current.getLeft());
            }
            if(current.getRight() != null){
                queue.add(current.getRight());
            }
        }
        return r;
    }

    private static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>(1);
        for(int i = 2; i < 33; i++){
            tree.add(i);
        }
        System.out.println(tree.DFS());
        System.out.println(tree.BFS());
    }
}
