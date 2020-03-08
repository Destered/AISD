public class Element {
        int data;
        Element next;

        public Element() {

        }

        public Element(int data, Element next) {
            this.data = data;
            this.next = next;
        }

        public void addElement(int data, Element next){
            this.next = new Element(data,next);
        }


}
