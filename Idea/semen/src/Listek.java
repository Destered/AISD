public class Listek  {
    Figure[] mas = new Figure[0];
    int i = -1;

    public Listek(Figure[] mas) {
        this.mas = mas;
    }


    public Listek() {
    }

    public int size() {
        return mas.length;
    }
    public boolean isEmpty() {
        return mas.length == 0;
    }

    public boolean contains(Figure o){
        for (Object check : mas) {
            if ((check + "").equals((o + ""))) {
                return true;
            }
        }
        return false;
    }


    public Figure getCur() {
        return mas[i];
    }


    public boolean add(Figure o) {
        Figure[] masNew = new Figure[mas.length + 1];
        int i = 0;
        for (Figure obj : mas) {
            masNew[i] = obj;
            i++;
        }
        masNew[masNew.length - 1] = o;
        mas = masNew;
        return true;
    }

    public void setIndex(int index, Figure figure){
        mas[index] = figure;
    }


    public boolean remove(Figure o) {
        boolean alreadyRemove = false;
        if (this.contains(o)) {
            int j = 0;
            Figure[] masNew = new Figure[mas.length - 1];
            for (int i = 0; i < mas.length; i++) {
                if (mas[i] == o & !alreadyRemove) {
                    alreadyRemove = true;
                    continue;
                }
                if (j < mas.length - 1) {
                    masNew[j] = mas[i];
                    j++;
                }
            }
            mas = masNew;
            return true;
        }
        return false;
    }

    public int countType(Figure.Type type) {
        int count = 0;
        for (Figure figure:mas) {
            if (figure.type == type) {
                count++;
            }
        }
        return count;
    }

    public boolean remove(Figure.Type type ) {
        int j = 0;
            Figure[] masNew = new Figure[mas.length - countType(type)];
            for (int i = 0; i < mas.length; i++) {
                if (mas[i].type == type ) {
                    continue;
                }
                if (j < mas.length - 1) {
                    masNew[j] = mas[i];
                    j++;
                }
            }
            mas = masNew;
            return true;
        }



    @Override
    public String toString() {
        String out = "";
        for (Object c : mas) {
            System.out.println(c.toString());
        }
        return out;
    }
}
