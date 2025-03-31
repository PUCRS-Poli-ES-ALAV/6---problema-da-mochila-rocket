public class Recursion {
    public static void main(String[] args) {
        System.out.println("fibo(4): " + fibo(4)); 
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
    }

    /* >>>> Fibonacci <<<3
    FIBO-REC (n)
        se n ≤ 1
        então devolva n
        senão a ← FIBO-REC (n − 1)
                b ← FIBO-REC (n − 2)
                devolva a + b  */
    public static int fibo(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibo(n - 1) + fibo(n - 2);
        }
    }

    /* FIBO (n)
        f [0] ← 0 
        f [1] ← 1
        para i ← 2 até n faça
            f[i] ← f[i-1]+f[i-2]
        devolva f [n] */
    public static int fiboArray(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
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
    public static int memoizedFibo(int[] f, int n) {
        for (int i = 0; i <= n; i++) {
            f[i] = -1;
        }
        return lookupFibo(f, n);
    }

    public static int lookupFibo(int[] f, int n) {
        if (f[n] >= 0) {
            return f[n];
        }
        if (n <= 1) {
            f[n] = n;
        } else {
            f[n] = lookupFibo(f, n - 1) + lookupFibo(f, n - 2);
        }
        return f[n];
    }
}