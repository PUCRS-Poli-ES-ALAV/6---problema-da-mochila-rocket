public class EditDistance {
    public static void main(String[] args) {
        String s1 = "Casablanca";
        String s2 = "Portentoso";

        System.out.println(ed(s1, s2, s1.length(), s2.length()));
    }

    public static int ed(String SInicial, String TFinal, int i, int j) {
        if (i == 0 ) { // se um dos strings estiver vazio, a distância é o comprimento do outro string
            return j;
        } else if (j == 0) {
            return i;
        } else if (i == 0 && j == 0) { // caso as duas strings estiver vazias, a distância é 0
            return 0;
        
        } else if (SInicial.charAt(i-1) == TFinal.charAt(j-1)) { // se os últimos caracteres forem iguais, não precisamos fazer nada
            return ed(SInicial, TFinal, i - 1, j - 1);
        } else { 
            int replace = ed(SInicial, TFinal, i - 1, j - 1); // substituir o último caractere de SInicial pelo último caractere de TFinal
            int insert = ed(SInicial, TFinal, i, j - 1); // inserir o último caractere de TFinal em SInicial
            int delete = ed(SInicial, TFinal, i - 1, j); // deletar o último caractere de SInicial
            return Math.min(replace, Math.min(insert, delete)) + 1;
        }
    }

    

}