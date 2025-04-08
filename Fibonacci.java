public class Fibonacci {

    private static final int RECURSION_LIMIT = 9999;

    public static void main(String[] args) {
        int[] tamanhos = {4, 8, 16, 32};
        int[] tamanhosplus = {4, 8, 16, 32, 128, 1000, 10000};  
        String[][] results = new String[tamanhos.length + tamanhosplus.length * 2][6];
        int row = 0;

        // >>>>> FIBO REC (Recursivo) <<<<3
        for (int n : tamanhos) {
            String result;
            long start = System.nanoTime();

            fiboInstructions = 0;
            fiboIterations = 0;

            if (n > RECURSION_LIMIT) {
                result = "Estouro de Pilha";
                results[row][5] = "-";
            } else {
                int fibResult = fibo(n);
                result = String.valueOf(fibResult);
                long end = System.nanoTime();
                results[row][5] = String.format("%.2f", (end - start) / 1e6); 
            }

            results[row][0] = "Fibo (Recursivo)";
            results[row][1] = String.valueOf(n);  
            results[row][2] = result;  
            results[row][3] = String.valueOf(fiboIterations);  
            results[row][4] = String.valueOf(fiboInstructions); 
            row++;
        }

        // >>>>> FIBO ARRAY (Iterativo) <<<<3
        for (int n : tamanhosplus) {
            long start = System.nanoTime();

            fiboArrayInstructions = 0;
            fiboArrayIterations = 0;

            int result = fiboArray(n);
            long end = System.nanoTime();

            results[row][0] = "Fibo Array (Iterativo)";
            results[row][1] = String.valueOf(n);  
            results[row][2] = String.valueOf(result); 
            results[row][3] = String.valueOf(fiboArrayIterations); 
            results[row][4] = String.valueOf(fiboArrayInstructions);  
            results[row][5] = String.format("%.2f", (end - start) / 1e6); 
            row++;
        }

        // >>>>> FIBO MEMOIZED <<<<3
        for (int n : tamanhosplus) {
            String result;
            long start = System.nanoTime();

            memoizedFiboInstructions = 0;
            memoizedFiboInterations = 0;

            if (n > RECURSION_LIMIT) {
                result = "Estouro de Pilha";
                results[row][5] = "-";  
            } else {
                int fibResult = memoizedFibo(new int[n + 1], n);
                
                if (fibResult == -1) {
                    result = "Estouro de Pilha";  // se retornar -1, foi um estouro de pilha
                    results[row][5] = "-";
                } else {
                    result = String.valueOf(fibResult);
                    long end = System.nanoTime();
                    results[row][5] = String.format("%.2f", (end - start) / 1e6);
                }
            }

            results[row][0] = "Memoized Fibo";
            results[row][1] = String.valueOf(n); 
            results[row][2] = result;  
            results[row][3] = String.valueOf(memoizedFiboInterations); 
            results[row][4] = String.valueOf(memoizedFiboInstructions);  
            row++;
        }

        printTable(results);
    }

    // FIBO REC (Recursivo)
    static long fiboIterations = 0;
    static long fiboInstructions = 0;
    public static int fibo(int n) {
        fiboIterations++;
        fiboInstructions++;
        if (n <= 1) {
            fiboInstructions++;
            return n;
        } else {
            fiboInstructions++;
            int a = fibo(n - 1);

            fiboInstructions++;
            int b = fibo(n - 2);

            fiboInstructions++; // soma
            fiboInstructions++; // retorno
            return a + b;
        }
    }

    // FIBO ARRAY (Iterativo)
    static long fiboArrayIterations = 0;
    static long fiboArrayInstructions = 0;
    public static int fiboArray(int n) {
        fiboArrayInstructions++;
        int[] f = new int[n + 1];
        fiboArrayInstructions+=2; 
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            fiboArrayInstructions++;
            fiboArrayInstructions+=3; // acesso + soma + atribuições
            fiboArrayIterations++;
            f[i] = f[i - 1] + f[i - 2];
            fiboArrayInstructions++;
        }
        fiboArrayInstructions++;
        return f[n];
    }

    // FIBO MEMOIZED
    static long memoizedFiboInterations = 0;
    static long memoizedFiboInstructions = 0;
    public static int memoizedFibo(int[] f, int n) {
        if (n > RECURSION_LIMIT) {
            return -1;  
        }

        for (int i = 0; i <= n; i++) {
            f[i] = -1;
        }
        return lookupFibo(f, n);
    }

    public static int lookupFibo(int[] f, int n) {
        memoizedFiboInstructions++;
        if (f[n] >= 0) {
            memoizedFiboInstructions++;
            return f[n];
        }
        memoizedFiboInterations++;
        memoizedFiboInstructions++;

        if (n <= 1) {
            memoizedFiboInstructions++;
            f[n] = n;
        } else {
            memoizedFiboInstructions+=2; // duas chamadas recursivas
            int a = lookupFibo(f, n - 1);
            int b = lookupFibo(f, n - 2);

            memoizedFiboInstructions+=2;
            f[n] = a + b;
        }

        memoizedFiboInstructions++;
        return f[n];
    }

    // Função para imprimir a tabela
    public static void printTable(String[][] rows) {
        String[] headers = {"Algoritmos", "Tamanho", "Resultado", "Iterações", "Instruções", "Tempo (ms)"};
        int[] widths = {22, 12, 16, 16, 16, 12};
    
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
