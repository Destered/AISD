import org.junit.Assert;
import org.junit.Test;

public class GraphicPicTest {
    private GraphicPic expected = new GraphicPic("FigureInfo.txt");
    private Figure f1 = new Figure("Round", "Blue", 3, 2, 2, 0);
    private Figure f2 = new Figure("Square", "White", 2, 6, 5, 3);
    private Figure f3 = new Figure("Square", "Black", 0, 1, 1, 0);
    private Figure f4 = new Figure("Line", "Green", 2, 2, 9, 5);
    private Figure f5 = new Figure("Round", "White", 8, 1, 1, 0);
    private Figure f6 = new Figure("Square", "Red", 0, 2, 4, 0);
    private Figure f7 = new Figure("Square", "Green", 0, 2, 4, 0);

    @Test
    public void show() {
        GraphicPic actuals = new GraphicPic();
        actuals.insert(f1);
        actuals.insert(f2);
        actuals.insert(f3);
        actuals.insert(f4);
        actuals.insert(f5);
        actuals.insert(f6);
        Assert.assertEquals(expected, actuals);


    }

    @Test
    public void insert() {
        expected.insert(f7);
        GraphicPic actual = new GraphicPic();
        actual.insert(f1);
        actual.insert(f2);
        actual.insert(f3);
        actual.insert(f4);
        actual.insert(f5);
        actual.insert(f7);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete() {
        expected.delete(Figure.Type.Square);
        GraphicPic actual = new GraphicPic();
        actual.insert(f1);
        actual.insert(f4);
        actual.insert(f5);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void commonWith() {
        GraphicPic buf = new GraphicPic("FigureInfo.txt");
        GraphicPic expected = buf.commonWith(new Figure("Square", "Black", 1, 4, 8, 1));
        GraphicPic actual = new GraphicPic();
        actual.insert(f1);
        actual.insert(f2);
        actual.insert(f3);
        actual.insert(f4);
        actual.insert(f7);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void hasSquareBiggerThanS() {
        expected = expected.hasSquareBiggerThanS(4);
        GraphicPic actual = new GraphicPic();
        actual.insert(f1);
        actual.insert(f2);
        actual.insert(f6);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getByColor() {
        Figure.Color[] color = new Figure.Color[]{Figure.Color.White, Figure.Color.Black};
        expected = expected.getByColor(color);
        GraphicPic actual = new GraphicPic();
        actual.insert(f2);
        actual.insert(f3);
        actual.insert(f5);
        Assert.assertEquals(expected, actual);

    }
}