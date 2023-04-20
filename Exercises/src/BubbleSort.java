public class BubbleSort {
    public static void bubbleSort(int[] vetor) {
        int n = vetor.length;

        for (int i = 0; i < n - 1; i++) { //controla o número de vezes para realizar a ordenação
            for (int j = 0; j < n - i - 1; j++) {  //percorre o vetor da posição e comparando com o par para trocar a
                // posição se necessário
                if (vetor[j] > vetor[j + 1]) { //verifica se o elemento j é maior que o elemento na posição j+1
                    //troca os elementos de posição
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
    }
}
