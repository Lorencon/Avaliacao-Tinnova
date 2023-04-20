import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Eleição

        PercentualEleicao eleicao = new PercentualEleicao(1000, 800, 150, 50);
        double percentualValidos = eleicao.getPercentualVotosValidos();
        double percentualBrancos = eleicao.getPercentualVotosBrancos();
        double percentualNulos = eleicao.getPercentualVotosNulos();

        System.out.printf("Percentual de votos válidos: %.2f%%\n", +percentualValidos);
        System.out.printf("Percentual de votos em branco: %.2f%%\n", +percentualBrancos);
        System.out.printf("Percentual de votos nulos: %.2f%%\n", +percentualNulos);

        // Bubble Sort

        int[] vetor = {5, 3, 2, 4, 7, 1, 0, 6};

        System.out.print("\n\nVetor incial: ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }

        BubbleSort.bubbleSort(vetor);
        System.out.print("\nVetor ordenado: ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }

        //Fatorial

        Scanner sc = new Scanner(System.in);
        int num = 0;
        boolean entradaNumeroValido = false;
        while (!entradaNumeroValido) {
            try {
                System.out.print("\n\nInforme o número que deseja saber seu fatorial: ");
                num = sc.nextInt();
                entradaNumeroValido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
                sc.nextLine();
            }
        }

        long fatorial = Fatorial.calcularFatorial(num);
        System.out.printf("O fatorial de %d! é = %d\n", num, fatorial);

        System.out.println("Alguns exemplos de fatoriais:");
        for (int i = 0; i <= 5; i++) {
            System.out.printf("%d! = %d\n", i, Fatorial.calcularFatorial(i));
        }

        //Soma dos Múltiplos

        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\nDigite um número para encontrar soma de todos os múltiplos de 3 ou 5 menores que ele: ");
        int x = 0;
        try {
            x = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite um número inteiro.");
            System.exit(1);
        }
        List<Integer> multiplos = SomaMultiplos.listaMultiplos(x);
        System.out.println("Os múltiplos de 3 ou 5 menores que " + x + " são: " + multiplos);
        int resultado = SomaMultiplos.somaMultiplos(x);
        System.out.println("A soma de todos os números múltiplos de 3 ou 5 menores que " + x + " é: " + resultado);
    }
}