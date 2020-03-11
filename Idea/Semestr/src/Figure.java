import java.text.DecimalFormat;

public class Figure {
    enum Type{Square,Round,Line}
    enum Color{White,Black,Red,Blue,Green}
    Type type;
    int x1;
    int x2;
    int y1;
    int y2;
    Color color;

     Figure(String type, String color, int x1, int x2, int y1, int y2) {
        this.type = Type.valueOf(type);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = Color.valueOf(color);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "Figure{" +
                "type=" + type +
                ", x1=" + x1 +
                ", x2=" + x2 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                ", color=" + color +
                ", square=" + df.format(getSquare()) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return x1 == figure.x1 &&
                x2 == figure.x2 &&
                y1 == figure.y1 &&
                y2 == figure.y2 &&
                type == figure.type;
    }

     double getSquare(){
        if(type == Type.Square){
            double side = x2-x1;
            double base = y1-y2;
            return side*base;

        } else if (type == Type.Round){
            return Math.PI*x2*x2;

        } else{
            return 0;
        }
    }

}
