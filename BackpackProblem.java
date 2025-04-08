public class BackpackProblem {

    // Função recursiva para resolver o problema da mochila usando backtracking (sem programação dinâmica)
    public static int mochila(Item[] itens, int capacidade, int i, int pesoAtual, int valorAtual) {
        // Caso base: quando todos os itens foram avaliados
        if (i == itens.length) {
            return valorAtual;  // Retorna o valor acumulado até o momento
        }

        // Caso 1: Não incluir o item atual
        int naoIncluir = mochila(itens, capacidade, i + 1, pesoAtual, valorAtual);

        // Caso 2: Incluir o item atual, se o peso permitir
        int incluir = 0;
        if (pesoAtual + itens[i].peso <= capacidade) {
            incluir = mochila(itens, capacidade, i + 1, pesoAtual + itens[i].peso, valorAtual + itens[i].valor);
        }

        // Retorna o melhor valor entre incluir ou não o item
        return Math.max(naoIncluir, incluir);
    }

    public static void main(String[] args) {
        // Criando um conjunto de itens
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

        int capacidade = 165;  // Capacidade da mochila
        
        // Chamando a função mochila (iniciando a recursão com o primeiro item)
        int valorMaximo = mochila(itens, capacidade, 0, 0, 0);
        
        // Exibindo o resultado
        System.out.println("Valor máximo que pode ser carregado na mochila: " + valorMaximo);
    }
}