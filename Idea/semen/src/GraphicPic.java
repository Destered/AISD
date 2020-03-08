import java.io.File;
import java.util.Scanner;


public class GraphicPic {
    Listek list = new Listek();

    public GraphicPic() {
    }

    public GraphicPic(String filename) {
        try {
            File file = new File("src/" + filename);
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] info = sc.nextLine().split(";");
                Figure f1 = new Figure(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]));
                list.add(f1);
            }
        } catch (Exception ex) {
            System.out.println("File not found!");
            System.exit(0);
        }
    }

    @Override
    public String toString() {
        return list.toString();

    }

    public void show() {
        System.out.println(toString());
    }


    public int getIndex(Figure f) {
        Figure f1;
        for (int i = 0; i < list.size(); i++) {
            f1 = list.mas[i];
            if (f1.equals(f)) {
                return i;
            }
        }
        return -1;

    }

    public void insert(Figure f) {
        int haveEquals = getIndex(f);
        if (haveEquals != -1) {
            list.setIndex(haveEquals, f);
        } else list.add(f);
    }

    public void delete(Figure.Type type) {
        list.remove(type);
    }

    public int size() {
        return list.size();
    }


    public GraphicPic commonWith(Figure r) {
        Figure check;
        if (size() != 0) {
            GraphicPic answer = new GraphicPic();
            for (int i = 0; i < list.size(); i++) {
                check = list.mas[i];
                if (check.type == Figure.Type.Square) {
                    if (!(check.y1 < r.y2 || check.y2 > r.y1 || check.x2 < r.x1 || check.x1 > r.x2)) {
                        answer.insert(check);
                    }
                } else if (check.type == Figure.Type.Round) {
                    if ((check.x1 > r.x1 - check.x2 && check.x1 < r.x2 + check.x2 && r.y1 > check.y1 && check.y1 > r.y2)) {
                        answer.insert(check);
                    } else if (check.y1 > r.y2 - check.x2 && check.y1 < r.y1 + check.x2 && r.x1 > check.x1 && check.x1 > r.x2) {
                        answer.insert(check);
                    }

                } else {
                    if (hasPointIntersection(r.x1, r.y1, r.x2, r.y1, check.x1, check.y1, check.x2, check.y2)) {
                        answer.insert(check);
                    } else if (hasPointIntersection(r.x1, r.y1, r.x1, r.y2, check.x1, check.y1, check.x2, check.y2)) {
                        answer.insert(check);
                    } else if (hasPointIntersection(r.x2, r.y2, r.x2, r.y1, check.x1, check.y1, check.x2, check.y2)) {
                        answer.insert(check);
                    } else if (hasPointIntersection(r.x2, r.y2, r.x1, r.y2, check.x1, check.y1, check.x2, check.y2)) {
                        answer.insert(check);
                    }


                }
            }


            return answer;
        } else {
            System.out.println("Пустой лист!");
            return new GraphicPic();
        }
    }


    public GraphicPic hasSquareBiggerThanS(double s) {
        GraphicPic answer = new GraphicPic();
        if (size() != 0) {
            for (int i = 0; i < size(); i++) {
                if (list.mas[i].getSquare() > s) {
                    answer.insert(list.mas[i]);
                }
            }
            return answer;

        } else return new GraphicPic();
    }

    public boolean hasPointIntersection(int x11, int y11, int x12, int y12, int x21, int y21, int x22, int y22) {
        int x;
        int y;
        int a1 = y11 - y12;
        int a2 = y21 - y22;
        int b1 = x12 - x11;
        int b2 = x22 - x21;
        int det = a1 * b2 - a2 * b1;
        if (det != 0) {
            x = ((x11 * y12 - x12 * y11) * (x22 - x21) - (x21 * y22 - x22 * y21) * (x12 - x11)) / det;
            y = ((y21 - y22) * x - (x21 * y22 - x22 * y21)) / det;

            if (((x11 <= x) && (x12 >= x) && (x21 <= x) && (x22 >= x)) || ((y11 <= y) && (y12 >= y) && (y21 <= y) && (y22 >= y)))
                return true;
        }
        return false;
    }


    public GraphicPic getByColor(Figure.Color[] colors) {
        if (size() != 0) {
            GraphicPic answer = new GraphicPic();
            for (int i = 0; i < list.mas.length; i++) {
                for (Figure.Color color : colors) {
                    if (list.mas[i].color == color) {
                        answer.insert(list.mas[i]);
                    }
                }
            }

            return answer;
        }
        System.out.println("Пустой список");
        return this;
    }
}

