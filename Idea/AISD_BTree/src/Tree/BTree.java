package Tree;

public class BTree {
    private final static int t = 2;
    private Node root = null;

    public BTree() {
    }

    private void insertToNode(Key key, Node node) {
        node.keys[node.countKey] = key;
        node.countKey++;
        sort(node);
    }

    private void sort(Node node) {
        Key buf;
        for (int i = 0; i < 2 * t - 1; i++) {
            for (int j = i + 1; j <= 2 * t - 1; j++) {
                if ((node.keys[i] != null) && (node.keys[j] != null)) {
                    if ((node.keys[i].key) > (node.keys[j].key)) {
                        buf = node.keys[i];
                        node.keys[i] = node.keys[j];
                        node.keys[j] = buf;
                    } else break;
                }
            }
        }
    }

    private void split(Node node) {
        if (node.countKey < (2 * t - 1)) return;

        Node child1 = new Node();
        int j;
        for (j = 0; j <= t - 2; j++) child1.keys[j] = node.keys[j];
        for (j = t - 1; j < 2 * t - 1; j++) child1.keys[j] = null;
        child1.countKey = t - 1;
        if (node.countChild != 0) {
            for (int i = 0; i <= t - 1; i++) {
                child1.child[i] = node.child[i];
                child1.child[i].parent = child1;
            }
            for (int i = t; i <= (2 * t); i++) child1.child[i] = null;
            child1.leaf = false;
            child1.countChild = t - 1;
        } else {
            child1.leaf = true;
            child1.countChild = 0;
            for (int i = 0; i <= 2 * t; i++) child1.child[i] = null;
        }

        Node child2 = new Node();
        for (j = 0; j <= t - 1; j++) child2.keys[j] = node.keys[j + t];
        for (j = t; j <= 2 * t - 1; j++) child2.keys[j] = null;
        child2.countKey = t;
        if (node.countChild != 0) {
            for (int i = 0; i <= t; i++) {
                child2.child[i] = node.child[i + t];
                child2.child[i].parent = child2;
            }
            for (int i = t + 1; i <= 2 * t; i++) child2.child[i] = null;
            child2.leaf = false;
            child2.countChild = t;
        } else {
            child2.leaf = true;
            child2.countChild = 0;
            for (int i = 0; i <= (2 * t); i++) child2.child[i] = null;
        }

        if (node.parent == null) {
            node.keys[0] = node.keys[t - 1];
            for (int i = 1; i <= 2 * t - 1; i++) node.keys[i] = null;
            node.child[0] = child1;
            node.child[1] = child2;
            for (int i = 2; i <= 2 * t; i++) node.child[i] = null;
            node.parent = null;
            node.leaf = false;
            node.countKey = 1;
            node.countChild = 2;
            child1.parent = node;
            child2.parent = node;
        } else {
            insertToNode(node.keys[t - 1], node.parent);
            for (int i = 0; i <= 2 * t; i++) {
                if (node.parent.child[i] == node) node.parent.child[i] = null;
            }
            for (int i = 0; i <= 2 * t; i++) {
                if (node.parent.child[i] == null) {
                    for (j = 2 * t; j > i + 1; j--) node.parent.child[j] = node.parent.child[j - 1];
                    node.parent.child[i + 1] = child2;
                    node.parent.child[i] = child1;
                    break;
                }
            }
            child1.parent = node.parent;
            child2.parent = node.parent;
            node.parent.leaf = false;
        }
    }

    void deleteNode(Node node) {
        if (node.parent != null) {
            node.parent.countChild--;
            if (node.parent.child[0] == node) {
                node.parent.child[0] = node.parent.child[1];
            } else if (node.parent.child[1] == node) {
                node.parent.child[1] = null;
            }
        }
    }

    private boolean searchKey(int key, Node node) {
        if (node != null) {
            if (!node.leaf) {
                int i;
                for (i = 0; i <= 2 * t - 1; i++) {
                    if (node.keys[i] != null) {
                        if (key == node.keys[i].key) return true;
                        if (key < node.keys[i].key) {
                            return searchKey(key, node.child[i]);
                        }
                    } else break;
                }
                return searchKey(key, node.child[i]);
            } else {
                for (int i = 0; i <= 2 * t - 1; i++) if (node.keys[i] != null && key == node.keys[i].key) return true;
                return false;
            }
        } else return false;
    }

    private void remove(Key key, Node node) {
        Node ptr = node;
        int position = 0;
        int i;
        for (i = 0; i <= node.countKey - 1; i++) {
            if (key == node.keys[i]) {
                position = i;
                break;
            }
        }
        if (ptr.parent != null) {
            for (i = 0; i <= ptr.parent.countKey; i++) {
                if (ptr.child[i] == ptr) {
                    break;
                }
            }
        }
        ptr = ptr.child[position + 1];
        Key newkey;
        while (!ptr.leaf) ptr = ptr.child[0];
        if (ptr.countKey > (t - 1)) {
            newkey = ptr.keys[0];
            removeFromNode(newkey.key, ptr);
            node.keys[position] = newkey;
        } else {
            ptr = node;
            ptr = ptr.child[position];
            while (!ptr.leaf) ptr = ptr.child[ptr.countKey];
            newkey = ptr.keys[ptr.countKey - 1];
            node.keys[position] = newkey;
            if (ptr.countKey > (t - 1)) removeFromNode(newkey.key, ptr);
            else {

                removeLeaf(newkey.key, ptr);
            }
        }
    }

    private void removeFromNode(int key, Node node) {
        for (int i = 0; i < node.countKey; i++) {
            if (node.keys[i].key == key) {
                for (int j = i; j < node.countKey; j++) {
                    node.keys[j] = node.keys[j + 1];
                    node.child[j] = node.child[j + 1];
                }
                node.keys[node.countKey - 1] = null;
                node.child[node.countKey - 1] = node.child[node.countKey];
                node.child[node.countKey] = null;
                break;
            }
        }
        node.countKey--;
    }

    private void removeLeaf(int key, Node node) {
        if ((node == root) && (node.countKey == 1)) {
            removeFromNode(key, node);
            root.child[0] = null;
            root = null;
            return;
        }
        if (node == root) {
            removeFromNode(key, node);
            return;
        }
        if (node.countKey > (t - 1)) {
            removeFromNode(key, node);
            return;
        }
        Key k1;
        Key k2;
        int positionSon = 0;
        int i;
        for (i = 0; i <= node.countKey - 1; i++) {
            if (key == node.keys[i].key) {
                break;
            }
        }
        Node parent = node.parent;
        for (int j = 0; j <= parent.countKey; j++) {
            if (parent.child[j] == node) {
                positionSon = j;
                break;
            }
        }

        if (positionSon == 0) {
            if (parent.child[positionSon + 1].countKey > (t - 1)) {
                k1 = parent.child[positionSon + 1].keys[0];
                k2 = parent.keys[positionSon];
                insertToNode(k2, node);
                removeFromNode(key, node);
                parent.keys[positionSon] = k1;
                removeFromNode(k1.key, parent.child[positionSon + 1]);
            } else {
                removeFromNode(key, node);
                if (node.countKey <= (t - 2)) reBuildTree(node);
            }
        } else {

            if (positionSon == parent.countKey) {

                if (parent.child[positionSon - 1].countKey > (t - 1)) {
                    Node temp = parent.child[positionSon - 1];
                    k1 = temp.keys[temp.countKey - 1];
                    k2 = parent.keys[positionSon - 1];
                    insertToNode(k2, node);
                    removeFromNode(key, node);
                    parent.keys[positionSon - 1] = k1;
                    removeFromNode(k1.key, temp);
                } else {
                    removeFromNode(key, node);
                    if (node.countKey <= (t - 2)) reBuildTree(node);
                }
            } else {

                if (parent.child[positionSon + 1].countKey > (t - 1)) {
                    k1 = parent.child[positionSon + 1].keys[0];
                    k2 = parent.keys[positionSon];
                    insertToNode(k2, node);
                    removeFromNode(key, node);
                    parent.keys[positionSon] = k1;
                    removeFromNode(k1.key, parent.child[positionSon + 1]);
                } else {

                    if (parent.child[positionSon - 1].countKey > (t - 1)) {
                        Node temp = parent.child[positionSon - 1];
                        k1 = temp.keys[temp.countKey - 1];
                        k2 = parent.keys[positionSon - 1];
                        insertToNode(k2, node);
                        removeFromNode(key, node);
                        parent.keys[positionSon - 1] = k1;
                        removeFromNode(k1.key, temp);
                    } else {
                        removeFromNode(key, node);
                        if (node.countKey <= (t - 2)) reBuildTree(node);
                    }
                }
            }
        }
    }

    private void leftConn(Node node, Node node2) {
        if (node == null) return;
        for (int i = 0; i <= (node2.countKey - 1); i++) {
            node.keys[node.countKey] = node2.keys[i];
            node.child[node.countKey] = node2.child[i];
            node.countKey++;
        }
        node.child[node.countKey] = node2.child[node2.countKey];
        for (int j = 0; j <= node.countKey; j++) {
            if (node.child[j] == null) break;
            node.child[j].parent = node;
        }
    }

    void rightConn(Node node, Node node2) {
        if (node == null) return;
        for (int i = 0; i <= (node2.countKey - 1); i++) {
            node.keys[node.countKey] = node2.keys[i];
            node.child[node.countKey + 1] = node2.child[i + 1];
            node.countKey++;
        }
        for (int j = 0; j <= node.countKey; j++) {
            if (node.child[j] == null) break;
            node.child[j].parent = node;
        }
    }

    private void reBuildTree(Node node) {
        if ((node == root) && (node.countKey == 0)) {
            if (root.child[0] != null) {
                root.child[0].parent = null;
                root = root.child[0];
            } else {
                root = null;
            }
            return;
        }
        Node ptr = node;
        int positionSon = 0;
        Node parent = ptr.parent;
        for (int j = 0; j <= parent.countKey; j++) {
            if (parent.child[j] == ptr) {
                positionSon = j;
                break;
            }
        }

        if (positionSon == 0) {
            insertToNode(parent.keys[positionSon], ptr);
            leftConn(ptr, parent.child[positionSon + 1]);
            parent.child[positionSon + 1] = ptr;
            parent.child[positionSon] = null;
            removeFromNode(parent.keys[positionSon].key, parent);
            if (ptr.countKey == 2 * t) {
                while (ptr.countKey == 2 * t) {
                    if (ptr == root) {
                        split(ptr);
                        break;
                    } else {
                        split(ptr);
                        ptr = ptr.parent;
                    }
                }
            } else if (parent.countKey <= (t - 2)) reBuildTree(parent);
        } else {

            if (positionSon == parent.countKey) {
                insertToNode(parent.keys[positionSon - 1], parent.child[positionSon - 1]);
                leftConn(parent.child[positionSon - 1], ptr);
                parent.child[positionSon] = parent.child[positionSon - 1];
                parent.child[positionSon - 1] = null;
                removeFromNode(parent.keys[positionSon - 1].key, parent);
                Node temp = parent.child[positionSon];
                if (ptr.countKey == 2 * t) {
                    while (temp.countKey == 2 * t) {
                        if (temp == root) {
                            split(temp);
                            break;
                        } else {
                            split(temp);
                            temp = temp.parent;
                        }
                    }
                } else if (parent.countKey <= (t - 2)) reBuildTree(parent);
            } else {
                insertToNode(parent.keys[positionSon], ptr);
                leftConn(ptr, parent.child[positionSon + 1]);
                parent.child[positionSon + 1] = ptr;
                parent.child[positionSon] = null;
                removeFromNode(parent.keys[positionSon].key, parent);
                if (ptr.countKey == 2 * t) {
                    while (ptr.countKey == 2 * t) {
                        if (ptr == root) {
                            split(ptr);
                            break;
                        } else {
                            split(ptr);
                            ptr = ptr.parent;
                        }
                    }
                } else if (parent.countKey <= (t - 2)) reBuildTree(parent);
            }
        }
    }

    public void insert(Key key) {
        if (root == null) {
            Node newRoot = new Node();
            newRoot.keys[0] = key;
            for (int j = 1; j <= (2 * t - 1); j++) newRoot.keys[j] = null;
            for (int i = 0; i <= (2 * t); i++) newRoot.child[i] = null;
            newRoot.countKey = 1;
            newRoot.countChild = 0;
            newRoot.leaf = true;
            newRoot.parent = null;
            root = newRoot;
        } else {
            Node ptr = root;
            while (!ptr.leaf) {
                for (int i = 0; i <= 2 * t - 1; i++) {
                    if (ptr.keys[i] != null) {
                        if (key.key < ptr.keys[i].key) {
                            ptr = ptr.child[i];
                            break;
                        }
                        if ((ptr.keys[i + 1] == null) && (key.key >= ptr.keys[i].key)) {
                            ptr = ptr.child[i + 1];
                            break;
                        }
                    } else break;

                }
            }
            insertToNode(key, ptr);
            while (ptr.countKey == 2 * t) {
                if (ptr == root) {
                    split(ptr);
                    break;
                } else {
                    split(ptr);
                    ptr = ptr.parent;
                }
            }
        }
    }

    public boolean search(int key) {
        return searchKey(key, root);
    }

    public void remove(int key) {
        Node ptr = this.root;
        int i;
        if (!searchKey(key, ptr)) {
            return;
        } else {
            for (i = 0; i <= ptr.countKey - 1; i++) {
                if (ptr.keys[i] != null) {
                    if (key == ptr.keys[i].key) {
                        break;
                    } else {
                        if ((key < ptr.keys[i].key)) {
                            ptr = ptr.child[i];
                            i = -1;
                        } else {
                            if (i == (ptr.countKey - 1)) {
                                ptr = ptr.child[i + 1];
                                i = -1;
                            }
                        }
                    }
                } else break;
            }
        }
        if (ptr.leaf) {
            if (ptr.countKey > (t - 1)) removeFromNode(key, ptr);
            else removeLeaf(key, ptr);
        } else remove(new Key(key), ptr);
    }

    public String getInfo(int key) {
        Key buf = getInfo(key, root);
        if (buf == null) return "Key '" + key + "' does not exist!";
        else return buf.toString();
    }

    private Key getInfo(int key, Node node) {
        if (node != null) {
            if (!node.leaf) {
                int i;
                for (i = 0; i <= 2 * t - 1; i++) {
                    if (node.keys[i] != null) {
                        if (key == node.keys[i].key) return node.keys[i];
                        if (key < node.keys[i].key) {
                            return getInfo(key, node.child[i]);
                        }
                    } else break;
                }
                return getInfo(key, node.child[i]);
            } else {
                for (int i = 0; i <= 2 * t - 1; i++)
                    if (node.keys[i] != null && key == node.keys[i].key) return node.keys[i];
                return null;
            }
        } else return null;
    }

}
