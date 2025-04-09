import java.util.*;

public class BackpackRecursive {
    public static long recursiveIterations = 0;
    public static long recursiveInstructions = 0;

    // Função recursiva para resolver o problema da mochila
    public static int mochila(Item[] itens, int capacidade, int i, int pesoAtual, int valorAtual, List<Item> itensUsados, List<Item> melhorLista) {
        recursiveInstructions++;  // Conta chamada da função
        if (i == itens.length) {
            recursiveInstructions++;  // Conta comparação
            if (valorAtual > valorMochila(melhorLista)) {
                recursiveInstructions += melhorLista.size() + 1;  // Conta clear e addAll
                melhorLista.clear();
                melhorLista.addAll(new ArrayList<>(itensUsados));
            }
            return valorAtual;
        }

        // Caso 1: Não incluir o item atual
        recursiveIterations++;  // Conta iteração
        mochila(itens, capacidade, i + 1, pesoAtual, valorAtual, new ArrayList<>(itensUsados), melhorLista);

        // Caso 2: Incluir o item atual, se o peso permitir
        recursiveInstructions++;  // Conta comparação
        if (pesoAtual + itens[i].getPeso() <= capacidade) {
            recursiveInstructions += 3;  // Conta adição e remoção na lista
            itensUsados.add(itens[i]);
            mochila(itens, capacidade, i + 1, pesoAtual + itens[i].getPeso(), valorAtual + itens[i].getValor(), itensUsados, melhorLista);
            itensUsados.remove(itensUsados.size() - 1);
        }

        return valorMochila(melhorLista);
    }

    // Função auxiliar para calcular o valor total de uma lista de itens
    private static int valorMochila(List<Item> itens) {
        int total = 0;
        for (Item item : itens) {
            total += item.getValor();  // Conta adição
        }
        return total;
    }

    public static void main(String[] args) {
        // Definição dos itens disponíveis para colocar na mochila
        Item[] itens = {
            new Item(23, 92),
            new Item(31, 57),
            new Item(29, 49),
            new Item(44, 68),
            new Item(53, 60),
            new Item(38, 43),
            new Item(63, 67),
            new Item(85, 84),
            new Item(89, 87),
            new Item(82, 72)
        };
    
        // Lista para armazenar os itens usados na melhor solução
        List<Item> itensUsados = new ArrayList<>();
        List<Item> melhorLista = new ArrayList<>();
        int capacidade = 165;  // Capacidade máxima da mochila
    
        // Medição do tempo de execução da função de mochila
        long startTime = System.nanoTime();
        mochila(itens, capacidade, 0, 0, 0, itensUsados, melhorLista);
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
    
        // Preparação da tabela de resultados do algoritmo
        String[][] rows = new String[1][5];
        rows[0][0] = "BackpackRecursive";
        rows[0][1] = String.valueOf(valorMochila(melhorLista));
        rows[0][2] = String.valueOf(recursiveIterations);
        rows[0][3] = String.valueOf(recursiveInstructions);
        rows[0][4] = String.format("%.6f", elapsedTime / 1e6);
    
        // Imprime a tabela de resultados do algoritmo
        printTable(rows);

        System.out.println("\n");
    
        // Imprime a tabela com os itens usados na melhor solução
        printItemsTable(melhorLista);
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
    
        for (int r = 0; r < rows.length; r++) {
            String[] row = rows[r];
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%-" + widths[i] + "s", row[i]);
                if (i < row.length - 1) System.out.print(" | ");
            }
            System.out.println();
        }
    }
    
    public static void printItemsTable(List<Item> items) {
        String[] headers = {"Índice", "Peso", "Valor"};
        int[] widths = {10, 10, 10};
    
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
    
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.printf("%-" + widths[0] + "d", i + 1);
            System.out.print(" | ");
            System.out.printf("%-" + widths[1] + "d", item.getPeso());
            System.out.print(" | ");
            System.out.printf("%-" + widths[2] + "d", item.getValor());
            System.out.println();
        }
    }
}    