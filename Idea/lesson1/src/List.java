
public class List {
    Element head;

    public List() {
        head = null;
    }

    public List(Element head) {
        this.head = head;
    }

    public void addElements(int data) {
        head = new Element(data, head);
    }


    public void reverse() {
        if (head != null) {
            Element previous = null;
            Element cur = head;
            Element next = cur.next;
            while (cur != null) {
                cur.next = previous;
                previous = cur;
                cur = next;
                if (cur != null) {
                    head = cur;
                    next = cur.next;
                }

            }
        }
    }

    public Pair reverse2(Element head) {
        if (head != null) {
            Pair p = reverse2(head.next);
            if (p.a != null) {
                p.a.next = head;
                head.next = null;
                p.a = p.a.next;
            } else{
                p.b = head;
                p.a = head;
                head.next = null;
            }
        }
    }

    public void listOut() {
        System.out.print("[");
        Element cur = head;
        while (cur != null) {
            System.out.print(" " + cur.data);
            cur = cur.next;
        }
        System.out.println(" ]");
    }
}