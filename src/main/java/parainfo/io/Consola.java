package parainfo.io;

import java.util.Locale;
import java.util.Scanner;

public class Consola {

    private Scanner scanner;

    public Consola() {
        scanner = new Scanner(System.in, "ISO-8859-1"); // áéióúñ
        scanner = scanner.useLocale(Locale.US); // 123.456
    }

    public void print(String msg) {
        System.out.print(msg);
    }

    public void print(Object msg) {
        System.out.print(msg);
    }

    public void println(String msg) {
        System.out.println(msg);
    }

    public void println(Object msg) {
        System.out.println(msg);
    }

    public void print(String plantilla, Object... args) {
        System.out.printf(Locale.US, plantilla, args);
    }

    public void println(String plantilla, Object... args) {
        System.out.printf(Locale.US, plantilla + "\n", args);
    }

    public void println() {
        System.out.println();
    }

    public void repite(String s, Integer veces) {

        for (int i = 0; i < veces; i++) {
            System.out.print(s);
        }

    }

    public String getString(String msg) {
        print(msg);
        String result = scanner.nextLine();
        return result;
    }

    public Integer getInteger(String msg) {
        String temp = getString(msg);

        Integer result = null;
        try {
            result = Integer.valueOf(temp);

        } catch (NumberFormatException e) {
        }

        return result;
    }

    public Double getDouble(String msg) {
        String temp = getString(msg);

        Double result = null;
        try {
            result = Double.valueOf(temp);

        } catch (NumberFormatException e) {
        }

        return result;
    }
}
