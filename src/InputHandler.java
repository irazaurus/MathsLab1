import java.util.Scanner;

/**
 * Класс для обработки пользовательского ввода
 */
public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private int number = 0;

    /**
     * Метод, спрашивающий способ оснащения
     * @return -1 - левые точки оснащения, 0 - средние точки оснащения, 1 - правые точки оснащения
     */
    public int askPosition(){
        System.out.println("""
                Пожалуйста, укажите способ оснащения.
                -1 - левые точки оснащения,
                0 - средние точки оснащения,
                1 - правые точки оснащения.""");
        returnMoreThan();
        try {
            number = scanner.nextInt();
            if (number != 1 && number != 0 && number != -1) throw new Exception("Неверный формат записи.");
        } catch (Exception e) {
            System.out.print("Неверный формат записи. Пожалуйста, введите -1, 0 или 1.\n");
            askPosition();
        }
        return number;
    }

    /**
     * Метод, спрашивающий количество точек разбиения
     * @return количество точек разбиения
     */
    public int askNumber(){
        System.out.println("Пожалуйста, укажите количество точек разбиения.");
        returnMoreThan();
        try {
            number = scanner.nextInt();
            if (number <= 0) throw new Exception();
        } catch (Exception e) {
            System.out.print("Неверный формат записи. Пожалуйста, введите натуральное число.\n");
            askNumber();
        }
        return number;
    }

    //Метод для вывода знака >
    private void returnMoreThan(){
        System.out.print("> ");
    }
}
