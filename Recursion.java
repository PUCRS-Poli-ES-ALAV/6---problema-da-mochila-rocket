public class Recursion {
    
    public static void main(String[] args) {
        /*System.out.println("fibo(4): " + fibo(4)); 
        System.out.println("fibo(8): " + fibo(8)); 
        System.out.println("fibo(16): " + fibo(16)); 
        System.out.println("fibo(32): " + fibo(32)); 

        System.out.println("fiboArray(4): " + fiboArray(4));
        System.out.println("fiboArray(8): " + fiboArray(8));
        System.out.println("fiboArray(16): " + fiboArray(16));
        System.out.println("fiboArray(32): " + fiboArray(32));

        System.out.println("memoizedFibo(4): " + memoizedFibo(new int[5], 4));
        System.out.println("memoizedFibo(8): " + memoizedFibo(new int[9], 8));
        System.out.println("memoizedFibo(16): " + memoizedFibo(new int[17], 16));
        System.out.println("memoizedFibo(32): " + memoizedFibo(new int[33], 32));

        // Estouro de representação
        System.out.println("fiboArray(128): " + fiboArray(128));
        System.out.println("fiboArray(1000): " + fiboArray(1000));
        System.out.println("fiboArray(10000): " + fiboArray(10000));

        System.out.println("memoizedFibo(128): " + memoizedFibo(new int[129], 128));
        System.out.println("memoizedFibo(1000): " + memoizedFibo(new int[1001], 1000));

        // Estouro de pilha
        // System.out.println("memoizedFibo(10000): " + memoizedFibo(new int[10001], 10000)); Morreu >.<
    */
        int[] tamanhos = {4, 8, 16, 32};
        int[] tamanhosplus = {4,8,16,32,128,1000,10000};
        String[][] results = new String[tamanhos.length + tamanhosplus.length * 2][5];
        int row = 0;

        // >>>>> FIBO REC (Recursivo) <<<<
        for (int n : tamanhos) {
            int result = fibo(n);
            results[row][0] = "Fibo (Recursivo)";
            results[row][1] = String.valueOf(result);
            results[row][2] = String.valueOf(n);
            results[row][3] = String.valueOf(fiboInstructions);
            results[row][4] = String.valueOf(fiboIterations);
            row++;
        }

        // >>>>> FIBO ARRAY (Iterativo) <<<<
        for (int n : tamanhosplus) {
            int result = fiboArray(n);
            results[row][0] = "Fibo Array (iterativo)";
            results[row][1] = String.valueOf(result);
            results[row][2] = String.valueOf(n);
            results[row][3] = String.valueOf(fiboArrayInstructions);
            results[row][4] = String.valueOf(fiboArrayIterations);
            row++;
        }

        // >>>>> FIBO MEMOIZED <<<<
        for (int n : tamanhosplus) {
            int result = memoizedFibo(new int[n + 1], n);
            results[row][0] = "Fibo Memoized";
            results[row][1] = String.valueOf(result);
            results[row][2] = String.valueOf(n);
            results[row][3] = String.valueOf(memoizedFiboInstructions);
            results[row][4] = String.valueOf(memoizedFiboInterations);
            row++;
        }
        printTable(results);
    }

    /* >>>> Fibonacci <<<3
    FIBO-REC (n)
        se n ≤ 1
        então devolva n
        senão a ← FIBO-REC (n − 1)
                b ← FIBO-REC (n − 2)
                devolva a + b  */
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

            fiboInstructions++; //soma
            fiboInstructions++; //retorno
            return a + b;
        }
    }

    /* FIBO (n)
        f [0] ← 0 
        f [1] ← 1
        para i ← 2 até n faça
            f[i] ← f[i-1]+f[i-2]
        devolva f [n] */
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
            fiboArrayInstructions+=3; //acesso + soma + atribuições
            fiboArrayIterations++;
            f[i] = f[i - 1] + f[i - 2];
            fiboArrayInstructions++;
        }
        fiboArrayInstructions++;
        return f[n];
    }

    /* MEMOIZED-FIBO (f, n)
        para i ← 0 até n faça
            f [i] ← −1
        devolva LOOKUP-FIBO (f, n)

    LOOKUP-FIBO (f, n)
        se f [n] ≥ 0
            então devolva f [n]
        se n ≤ 1
        então f [n] ← n
        senão f [n] ← LOOKUP-FIBO(f, n − 1) + LOOKUP-FIBO(f, n − 2)
        devolva f [n] */
    static long memoizedFiboInterations = 0;
    static long memoizedFiboInstructions = 0;
    public static int memoizedFibo(int[] f, int n) {
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
            memoizedFiboInstructions+=2; //duas chamadas recursivas
            int a = lookupFibo(f, n - 1);
            int b = lookupFibo(f, n - 2);

            memoizedFiboInstructions+=2;
            f[n] = a + b;
        }

        memoizedFiboInstructions++;
        return f[n];
    }

    public static void printTable(String[][] rows) {
        String[] headers = {"Algoritmos", "Resultado", "Tamanho", "Instruções", "Iterações"};
    int[] widths = {22, 13, 11, 21, 12};
    
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