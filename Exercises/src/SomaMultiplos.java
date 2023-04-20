import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SomaMultiplos {
    public static List<Integer> listaMultiplos(int x) {
        List<Integer> multiplos = new ArrayList<>();
        for (int i = 1; i < x; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                multiplos.add(i);
            }
        }
        return multiplos;
    }

    public static int somaMultiplos(int x) {
        return IntStream.range(1, x)
                .filter(i -> {
                    try {
                        return i % 3 == 0 || i % 5 == 0;
                    } catch (ArithmeticException e) {
                        return false;
                    }
                })
                .sum();
    }
}
