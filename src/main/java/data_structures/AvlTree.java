package data_structures;

import java.util.Random;

public class AvlTree {

    private Node root;

    public AvlTree(int startValue){
        root = new Node(startValue);
    }

    public void delete(int value){
        delete(value, root);
    }

    private void delete(int value, Node n){
        if(n.value < value){
            if(n.right == null){
                return;
            }
            else {
                if(n.right.value == value){
                    if(isList(n.right)){
                        n.right = null;
                    }
                    else {
                        Node change = closest((height(n.right.left) > height(n.right.right)? n.right.left : n.right.right), value);
                        int temp = change.value;
                        delete(change.value, n.right);
                        n.right.value = temp;
                        n.right.height = Math.max(height(n.right.left), height(n.right.right));
                    }
                }
                else {
                    delete(value, n);
                    n.right.height = Math.max(height(n.right.left), height(n.right.right));
                }
            }
        }
        else if(n.value > value){
            if(n.left == null){
                return;
            }
            else {
                if(n.left.value == value){
                    if(isList(n.left)){
                        n.left = null;
                    }
                    else {
                        Node change = closest((height(n.left.left) > height(n.left.right)? n.left.left : n.left.right), value);
                        int temp = change.value;
                        delete(change.value, n);
                        n.left.value = temp;
                        n.left.height = Math.max(height(n.left.left), height(n.left.right));
                    }
                }
                else {
                    delete(value, n.left);
                    n.left.height = Math.max(height(n.left.left), height(n.left.right));
                }
            }
        }
        else {
            System.out.println("panic");
        }
        balance(n);
    }

    public void add(int value){
        add(value, root);
    }

    private void balance(Node n){
        int right = height(n.right);
        int left = height(n.left);

        if(left - right == 2){
            if(height(n.left.right) > height(n.left.left)){
                bigRightRotation(n);
            }
            else {
                smallRightRotation(n);
            }
        }
        if(right - left == 2){
            if(height(n.right.left) > height(n.right.right)){
                bigLeftRotation(n);
            }
            else {
                smallLeftRotation(n);
            }
        }

        n.height = Math.max(height(n.right), height(n.left));
    }

    private void add(int value, Node n){
        if(n.value == value) return;
        if(n.value < value){
            if(n.right == null){
                n.right = new Node(value);
                if(n.height == 0){
                    n.height = 1;
                }
            }
            else {
                add(value, n.right);
                if(n.height == n.right.height){
                    n.height = n.right.height + 1;
                }
            }
        }
        else {
            if(n.left == null){
                n.left = new Node(value);
                if(n.height == 0){
                    n.height = 1;
                }
            }
            else {
                add(value, n.left);
                if(n.height == n.left.height){
                    n.height = n.left.height + 1;
                }
            }
        }
        balance(n);
    }

    private int height(Node n){
        if(n == null) return 0;
        else return n.height + 1;
    }

    private AvlTree(Node n){
        root = n;
    }

    private void smallRightRotation(Node n){
        Node a = n;
        Node b = a.left;
        Node l = b.left;
        Node c = b.right;
        Node r = a.right;
        int temp = a.value;
        a.value = b.value;
        b.value = temp;
        a.right = b;
        a.left = l;
        b.left = c;
        b.right = r;
        b.height = Math.max(height(b.left), height(b.right));
        a.height = Math.max(height(a.left), height(a.right));
    }

    private void smallLeftRotation(Node n){
        Node b = n;
        Node a = b.right;
        Node l = b.left;
        Node c = a.left;
        Node r = a.right;
        int temp = a.value;
        a.value = b.value;
        b.value = temp;
        b.left = a;
        a.left = l;
        a.right = c;
        b.right = r;
        a.height = Math.max(height(a.left), height(a.right));
        b.height = Math.max(height(b.left), height(b.right));
    }

    private void bigLeftRotation(Node node){
        Node a = node;
        Node b = a.right;
        Node c = b.left;
        Node l = a.left;
        Node m = c.left;
        Node n = c.right;
        Node r = b.right;
        int temp = a.value;
        a.value = c.value;
        c.value = temp;
        a.left = c;
        c.left = l;
        c.right = m;
        b.left = n;
        b.height = Math.max(height(b.left), height(b.right));
        c.height = Math.max(height(c.right), height(c.left));
        a.height = Math.max(height(a.right), height(a.left));
    }

    private void bigRightRotation(Node node){
        Node a = node;
        Node b = a.left;
        Node c = b.right;
        Node l = b.left;
        Node m = c.left;
        Node n = c.right;
        Node r = a.right;
        int temp = a.value;
        a.value = c.value;
        c.value = temp;
        a.right = c;
        c.right = r;
        c.left = n;
        b.right = m;
        b.height = Math.max(height(b.left), height(b.right));
        c.height = Math.max(height(c.right), height(c.left));
        a.height = Math.max(height(a.right), height(a.left));
    }

    private Node closest(Node n, int value){
        if(n.value < value){
            if(n.right == null) {
                return n;
            }
            else {
                return closest(n.right, value);
            }
        }
        else {
            if(n.left == null) {
                return n;
            }
            else {
                return closest(n.left, value);
            }
        }
    }

    private boolean isList(Node n){
        if(n == null) return false;
        return n.left == null && n.right == null;
    }

    private int nodeHeight(Node n){
        if(n == null) return -1;
        int r = Math.max(nodeHeight(n.left), nodeHeight(n.right)) + 1;
        if(n.height != r) System.out.println("Error: " + n.value + " " + n.height + " " + r);
        return r;
    }

    private static class Node {
        int value;
        int height = 0;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", height=" + height +
                    '}';
        }
    }

    private String toString(Node n){
        if(n == null) return "";
        String r = "";
        r += toString(n.left);
        r += n.value + ", ";
        r += toString(n.right);
        return r;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    public static void main(String[] args) {
        AvlTree tree = new AvlTree(10);
        tree.add(1);
        tree.add(8);
        tree.add(4);
        tree.add(12);
        tree.add(11);
        tree.add(7);
        tree.add(9);
        tree.add(5);
        tree.add(1);
        System.out.println(tree);
        tree.delete(11);
        System.out.println(tree);
    }
}
