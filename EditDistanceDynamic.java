public class EditDistanceDynamic {
    // Assumindo os Custos: Remoção=R, Inserção=I , Substituição=S e Match=M=0
    public static int ed(String SInicial, String TFinal) {
        int m = SInicial.length();
        int n = TFinal.length();
        int[][] matriz = new int[m + 1][n + 1]; // matriz[0][0] = 0
        // Inicializa a primeira linha e coluna da matriz
        for (int i = 1; i <= m; i++) {
            matriz[i][0] = matriz[i - 1][0] + 1; // soma uma I
        }
        // Inicializa a primeira coluna da matriz
        for (int j = 1; j <= n; j++) {
            matriz[0][j] = matriz[0][j - 1] + 1; // Soma uma R
        }
        // Preenche a matriz
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int custoExtra; // Custo da operação extra
                if (SInicial.charAt(i - 1) == TFinal.charAt(j - 1)) {
                    custoExtra = 0; // Se os caracteres forem iguais, custo é 0 (Match)
                } else {
                    custoExtra = 1; // Se forem diferentes, custo é 1 (Substituição)
                }
                matriz[i][j] = Math.min(matriz[i - 1][j] + 1, // Remoção
                        Math.min(matriz[i][j - 1] + 1, // Inserção
                                matriz[i - 1][j - 1] + custoExtra)); // Substituição
            }
        }
        return matriz[m][n];
    }
    public static void main(String[] args) {
        String SInicial = "Casablanca";
        String TFinal = "Portentoso";
        int resultado = ed(SInicial, TFinal);
        System.out.println("A distância de edição entre \"" + SInicial + "\" e \"" + TFinal + "\" é: " + resultado);
    }
}    