public class core {


    public static void main(String[] args) {
        System.out.println("    ---Проверка insert---");
        GraphicPic gp = new GraphicPic("FigureInfo.txt");
        System.out.println(" -Лист до добавления-");
        gp.show();
        gp.insert(new Figure("Round", "Green", 1, 2, 3, 0));
        gp.insert(new Figure("Square", "Green", 0, 2, 4, 0));
        System.out.println(" -После добавления-");
        gp.show();
        System.out.println();
        System.out.println("    ---Проверка getByColor(Green,Red)---");
        Figure.Color[] color = {Figure.Color.Red, Figure.Color.Green};
        GraphicPic gp2 = gp.getByColor(color);
        gp2.show();
        System.out.println();
        System.out.println("    ---Проверка hasSquareBiggerThanS(4)---");
        GraphicPic gp3 = gp.hasSquareBiggerThanS(4);
        gp3.show();
        System.out.println();
        System.out.println("---Проверка commonWith---");
        Figure square = new Figure("Square", "Black", 1, 4, 8, 1);
        GraphicPic gp4 = gp.commonWith(square);
        gp4.show();
        System.out.println();
        System.out.println("---Проверка delete(Round)---");
        gp.delete(Figure.Type.Round);
        gp.show();

    }
}
