public class BackpackDynamic {
    public static void main(String[] args) {
        int N = 10;
        int C = 165;
        Item[] itens = new Item[N + 1];
        itens[1] = new Item(23, 92);
        itens[2] = new Item(31, 57);
        itens[3] = new Item(29, 49);
        itens[4] = new Item(44, 68);
        itens[5] = new Item(53, 60);
        itens[6] = new Item(38, 43);
        itens[7] = new Item(63, 67);
        itens[8] = new Item(85, 84);
        itens[9] = new Item(89, 87);
        itens[10] = new Item(82, 72);

        long startTime = System.nanoTime();
        int maximovalor = backPackPD(N, C, itens);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        
        String[][] rows = new String[1][5];
        rows[0][0] = "BackPackDynamic";
        rows[0][1] = String.valueOf(maximovalor);
        rows[0][2] = String.valueOf(bpDynamicIterations);
        rows[0][3] = String.valueOf(bpDynamicInstructions);
        rows[0][4] = String.format("%.6f", elapsedTime / 1e6);

        printTable(rows);
    }

    public static long bpDynamicIterations = 0;
    public static long bpDynamicInstructions = 0;

    public static int backPackPD(int n, int c, Item[] itens) {
        int[][] maxTab = new int[n + 1][c + 1];
        for (int i = 1; i <= n; i++) {
            bpDynamicInstructions++;
            bpDynamicIterations++;
            for (int j = 1; j <= c; j++) {
                bpDynamicInstructions++;
                bpDynamicIterations++;
                if (itens[i].getPeso() <= j) {
                    bpDynamicInstructions++;
                    maxTab[i][j] = Math.max(maxTab[i - 1][j], itens[i].getValor() + maxTab[i - 1][j - itens[i].getPeso()]);
                    bpDynamicInstructions += 3;
                } else {
                    bpDynamicInstructions++;
                    maxTab[i][j] = maxTab[i - 1][j];
                    bpDynamicInstructions++;
                }
            }
        }
        bpDynamicInstructions++;
        return maxTab[n][c];
    }

    public static void printTable(String[][] rows) {
        String[] headers = {"Algoritmo", "Resultado", "Iterações", "Instruções", "Tempo (ms)"};
        int[] widths = {15, 11, 11, 13, 12};
        
        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%-" + widths[i] + "s", headers[i]);
            if (i < headers.length - 1) System.out.print(" | ");
        }
        System.out.println();
    
        for (int width : widths) {
            System.out.print("-".repeat(width));
            System.out.print("-+-");
        }
        System.out.println();
    
        String lastCategory = "";
        for (int r = 0; r < rows.length; r++) {
            String[] row = rows[r];
            String currentCategory = row[0];
    
            if (!lastCategory.equals("") && !currentCategory.equals(lastCategory)) {
                for (int width : widths) {
                    System.out.print("-".repeat(width));
                    System.out.print("-+-");
                }
                System.out.println();
            }
    
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%-" + widths[i] + "s", row[i]);
                if (i < row.length - 1) System.out.print(" | ");
            }
            System.out.println();
    
            lastCategory = currentCategory;
        }
    }
}
