public class Listek {
    Element head = null;

    Listek() {
    }

    int size() {
        Element cur = head;
        int k = 0;
        while (cur != null) {
            cur = cur.next;
            k++;
        }
        return k;
    }


    void add(Figure o) {
        Element cur = head;
        if (head != null) {
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Element(o, null);
        } else head = new Element(o, null);
    }

    void setIndex(int index, Figure figure) {
        int i = 0;
        Element cur = head;
        while (cur != null && i != index) {
            cur = cur.next;
            i++;
        }
        if (cur != null) {
            cur.data = figure;
        } else System.out.println("Индекс не существует");
    }



    void remove(Figure.Type type) {
        Element newHead = null;
        Element newCur = null;
        Element cur = head;
        while (cur != null) {
            if (cur.data.type != type) {
                if (newHead != null) {
                    newCur.add(cur);
                    newCur = newCur.next;
                } else {
                    newHead = new Element(cur.data, null);
                    newCur = newHead;
                }
            }
            cur = cur.next;
        }
        head = newHead;
    }


    @Override
    public String toString() {
        Element cur = head;
        StringBuilder out = new StringBuilder();
        while (cur != null) {
            out.append(cur.data.toString()).append("\n");
            cur = cur.next;
        }
        return out.toString();
    }
}

class Element {
    Figure data;
    Element next;

    Element(Figure data, Element next) {
        this.data = data;
        this.next = next;
    }


    void add(Element o) {
        next = new Element(o.data, null);
    }
}