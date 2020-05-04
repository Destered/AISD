package Tree;

public class Node {
    final static int t = 2;
    Key[] keys = new Key[2 * t];
    Node[] child = new Node[2 * t + 1];
    Node parent = null;
    int countKey;
    int countChild;
    boolean leaf = false;

    public Node() {
    }
}
