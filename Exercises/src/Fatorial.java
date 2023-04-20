public class Fatorial {
    public static long calcularFatorial(int num) {
        if (num == 0) {
            return 1;
        } else {
            return num * calcularFatorial(num - 1);
        }
    }
}
