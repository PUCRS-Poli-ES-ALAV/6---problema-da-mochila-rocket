public class BackpackDynamic {
    public static void main(String[] args) {
        int N = 4;
        int C = 5;
        Item[] itens = new Item[N + 1];
        itens[1] = new Item(5, 2);
        itens[2] = new Item(2, 4);
        itens[3] = new Item(2, 2);
        itens[4] = new Item(1, 3);

       int maximovalor = backPackPD(N, C, itens);
       System.out.println(maximovalor);
    }

    public static int backPackPD(int n, int c, Item[] itens){
        int[][] maxTab = new int[n + 1][c + 1];
        for(int i=1; i <= n; i++){
            for(int j=1; j <= c; j++){
                if(itens[i].getPeso() <= j){
                    maxTab[i][j] = Math.max(maxTab[i-1][j], itens[i].getValor() + maxTab[i-1][j-itens[i].getPeso()]);
                }
                else{
                    maxTab[i][j] = maxTab[i-1][j];
                }
            }
        }
        return maxTab[n][c];
    }
}