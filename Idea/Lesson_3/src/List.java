public class List {
    Elements head;

    public List() {
        head = null;
    }

    public void add(int data) {
        head = new Elements(data, head);
    }

    public void addlast(int data) {
        Elements cur = head;
        if (head != null) {
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Elements(data, null);
        } else head = new Elements(data,null);
    }

    public void listOut() {
        System.out.print("[");
        Elements cur = head;
        while (cur != null) {
            System.out.print(" " + cur.data);
            cur = cur.next;
        }
        System.out.println(" ]");
    }

    public void removeDuplicate(){
        Elements cur = head;
        while(cur != null){
            while(cur.next != null && cur.data == cur.next.data) {
                    cur.next = cur.next.next;
            }
            cur = cur.next;
        }
    }

    public void setCycle(int k,int data){
        int l = 0;
        boolean set = false;
        Elements cur = head;
        Elements last = head;
        while(last.next != null){
            last = last.next;
        }
        while(cur != null && !set){
            if(l == k){
                last.next = new Elements(data,cur);
                set = true;
            }
            cur = cur.next;
            l++;
        }
    }

    public void findCycle(){

    }
}