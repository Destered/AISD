import java.util.Scanner;

public class core {
    public static void main(String[] args) {
        String answer = "";     // финальная переменная проверки
        Scanner sc = new Scanner(System.in); // сканнер
        String[][] mas = new String[2][2]; // массив строк 2х2
        for (int i = 0; i < mas.length; i++) { // цикл по i (в массиве по столбцам)
            boolean alphabet = true; // переменная проверки что элемент массива (по j) удовлетворяет условию

            for (int j = 0; j < mas.length; j++) { // цикл по j элементам(по строкам массива)
                mas[i][j] = sc.nextLine(); // считываем строку для [i][j] элемента массива
                String[] check = mas[i][j].split(" ");  // каждый элемент массива check являет собой слово (строку разделили по пробелам)
                for (String check2 : check) {   // проверяем каждое слово
                    char that; // переменная текущего символа
                    char previous = 'a';  // переменная предыдущего символа ( по умолчанию 'a' чтоб первая проверка всегда норм была
                    for (int k = 0; k < check2.length(); k++) {  // цикл по длине слова
                        that = check2.charAt(k); // that = символ c индексом k
                        if (!((int) that >= (int) previous && (that >= (int) 'a' && that <= (int) 'z'))) {  // проверка что значение в кодировке текущего символа НЕ больше или равно значению предыдущего и проверка что символ НЕ является буквой (идём от обратного)
                            alphabet = false;  // если провекрка прошла, то переменной проверке выставляем false
                        }
                        previous = check2.charAt(k); // переменная предыдущего символа принимает значение текущего (так же конец итерации)
                    }
                }
                if (!alphabet ) { // проверка после цикла переменной проверки
                    answer += "0"; // добавляем 0 если условие не выполнено
                } else {
                    answer += "1"; // 1 если выполнено
                }                  // тем самым строка например 1100 показывает что первая строка массива выполняет условия а вторая нет( а нам нужно чтоб в каждом столбце была хотя бы 1 строка)
            }

        }
        if((answer.charAt(0) == '1' || answer.charAt(3) == '1') && (answer.charAt(2) == '1' || answer.charAt(1) == '1')){ // финальная проверка что в строке проверки 0 или 2 символ равен 1 И то что 1 или 3 символ равен 1
            System.out.println("True");                                                                                   // то есть мы как раз проверяем чтоб хотя бы одна позиция отвечающие за первый столбец и хотя бы одна позиция второго столбца соблюдала условие
        } else System.out.println("False");
    }
}


