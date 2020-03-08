import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import java.util.Set;
import java.util.TreeSet;

public class core {

    public static void main(String[] args) {
        String a = 24 + "";
        String b = 23 + "";
        System.out.println(a.compareTo(b));

        Set<String> list = new TreeSet<>();
        list.add("World");
        list.add("Hello");
        System.out.println(list);
        Set<HtmlTag> tags = new TreeSet<>();
        tags.add(new HtmlTag("body"));
        tags.add(new HtmlTag("head"));
        tags.
    }

}

class HtmlTag implements Comparable {
    private String tag;

    public HtmlTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int compareTo(Object o) {
        return -1;
    }

    public int compareTo(HtmlTag o) {
        return this.tag.compareTo(o.tag);
    }

    @Override
    public String toString() {
        return "HtmlTag{" +
                "tag='" + tag + '\'' +
                '}';
    }
}

class Point implements Comparable<Point>{
    private int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if (x < o.x){

        }
    }
}
