public class PercentualEleicao {

    private int totalEleitores;
    private int votosValidos;
    private int votosBrancos;
    private int votosNulos;

    public PercentualEleicao(int totalEleitores, int votosValidos, int votosBrancos, int votosNulos) {
        this.totalEleitores = totalEleitores;
        this.votosValidos = votosValidos;
        this.votosBrancos = votosBrancos;
        this.votosNulos = votosNulos;
    }

    public double getPercentualVotosValidos() {
        return (double) votosValidos / totalEleitores * 100;
    }

    public double getPercentualVotosBrancos() {
        return (double) votosBrancos / totalEleitores * 100;
    }

    public double getPercentualVotosNulos() {
        return (double) votosNulos / totalEleitores * 100;
    }

}
