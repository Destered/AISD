
import java.io.File;
import java.util.Scanner;


public class GraphicPic {
    private Listek list = new Listek();

    GraphicPic() {
    }

    GraphicPic(String filename) {
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

    void show() {
        System.out.println(toString());
    }


    private int getIndex(Figure f) {
        Figure f1;
        int i = 0;
        Element cur = list.head;
        while (cur != null) {
            f1 = cur.data;
            if (f1.equals(f)) {
                return i;
            }
            cur = cur.next;
            i++;
        }
        return -1;

    }

    void insert(Figure f) {
        int haveEquals = getIndex(f);
        if (haveEquals != -1) {
            list.setIndex(haveEquals, f);
        } else list.add(f);
    }

    void delete(Figure.Type type) {
        list.remove(type);
    }

    private int size() {
        return list.size();
    }


    GraphicPic commonWith(Figure r) {
        Element cur = list.head;
        Figure check;
        if (size() != 0) {
            GraphicPic answer = new GraphicPic();
            while (cur != null) {
                check = cur.data;
                if (check.type == Figure.Type.Square) {
                    if (!(check.y1 < r.y2 || check.y2 > r.y1 || check.x2 < r.x1 || check.x1 > r.x2)) {
                        answer.insert(check);
                    }
                } else if (check.type == Figure.Type.Round) {
                    if ((check.x1 > r.x1 - check.x2 && check.x1 < r.x2 + check.x2 && r.y1 > check.y1 && check.y1 > r.y2)) {
                        answer.insert(check);
                    } else if (check.y1 > r.y2 - check.x2 && check.y1 < +r.y1 + check.x2 && r.x1 > check.x1 && check.x1 > r.x2) {
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
                cur = cur.next;
            }


            return answer;
        } else {
            System.out.println("Пустой лист!");
            return new GraphicPic();
        }
    }


    GraphicPic hasSquareBiggerThanS(double s) {
        Element cur = list.head;
        GraphicPic answer = new GraphicPic();
        if (size() != 0) {
            while (cur != null) {
                if (cur.data.getSquare() > s) {
                    if (answer.list.head != null) {
                        answer.insert(cur.data);
                    } else {
                        answer.list.head = new Element(cur.data, null);
                    }
                }
                cur = cur.next;
            }
            return answer;
        } else return new GraphicPic();
    }

    private boolean hasPointIntersection(int x11, int y11, int x12, int y12, int x21, int y21, int x22, int y22) {
        int x;
        int y;
        int a1 = y11 - y12;
        int a2 = y21 - y22;
        int b1 = x12 - x11;
        int b2 = x22 - x21;
        int det = a1 * b2 - a2 * b1;
        if (det != 0) {
            x = -1 * ((x11 * y12 - x12 * y11) * (x22 - x21) - (x21 * y22 - x22 * y21) * (x12 - x11)) / det;
            y = ((y21 - y22) * x - (x21 * y22 - x22 * y21)) / det;

            return (((x11 <= x) && (x12 >= x) && (x21 <= x) && (x22 >= x)) || ((y11 <= y) && (y12 >= y) && (y21 <= y) && (y22 >= y)));

        }
        return false;
    }


    GraphicPic getByColor(Figure.Color[] colors) {
        Element cur = list.head;
        if (size() != 0) {
            GraphicPic answer = new GraphicPic();
            while (cur != null) {
                for (Figure.Color color : colors) {
                    if (cur.data.color == color) {
                        answer.insert(cur.data);
                    }
                }
                cur = cur.next;
            }

            return answer;
        }
        System.out.println("Пустой список");
        return this;
    }

    @Override
    public boolean equals(Object o) {
        boolean equals = true;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphicPic that = (GraphicPic) o;
        Element cur = list.head;
        Element cur2 = that.list.head;
        while (cur != null && cur2 != null && equals) {
            if (cur.data.equals(cur2.data)) {
                cur = cur.next;
                cur2 = cur2.next;
            } else equals = false;
        }
        if(cur == null && cur2 == null) {
        } else equals = false;
        return equals;
    }

}

