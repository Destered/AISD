package Tree;


public class Key {
    int key;
    private String data;

    public Key(int key, String data) {
        this.key = key;
        this.data = data;
    }

    public Key(int key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key1 = (Key) o;
        return key == key1.key;
    }

    @Override
    public String toString() {
        return data;
    }
}
