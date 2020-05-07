import Tree.BTree;
import Tree.Key;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class core {

    private static void dataFromFile(int lastNum, BTree tree) throws IOException {
        for (int i = 1; i <= lastNum; i++) {

            FileReader fr = new FileReader("./src/Tree/InputData/DataPack_" + i); // знак '#' является разделителем
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null && !str.equals("")) {
                String[] buf = str.split("#");
                tree.insert(new Key(Integer.parseInt(buf[0]), buf[1]));
            }
            fr.close();
        }
    }

    public static void main(String[] args) throws Exception {
        /*
        Немного о контесте задачи, у нас существует необычная база данных особых объектов имеющих свои уникальные идентификаторы,
        сама БД находится на жёстком диске, чтение с которого занимает намного больше времени чем чтение из оперативной памяти(1 к 100000 по Т. Кормену),
        так что я заполняю BTree ключами (Идентификатор, Уникальный ключ). Уникальный ключ - набор символов используемы в БД для получении информации.
        (Search - проверяет наличие Идентификатора. getInfo() - выводит Уникальный ключ или же "Key 'X' does not exist!" если Х ключ не найден в дереве.
         */

        BTree tree = new BTree();
        dataFromFile( 100,tree); //считываем из набора данных
        double start = System.nanoTime();
        System.out.println(tree.search(7));
        System.out.println(((System.nanoTime() - start) / 1000000 + ";"));
        start = System.nanoTime();
        tree.insert(new Key(15001, ""));
        System.out.println(((System.nanoTime() - start) / 1000000 + ";"));
        start = System.nanoTime();
        tree.remove(15001);
        System.out.println(((System.nanoTime() - start) / 1000000 + ";"));
        System.out.println(tree.getInfo(1));

    }


}
