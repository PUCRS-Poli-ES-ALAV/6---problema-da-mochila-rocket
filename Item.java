public class Item {
    public int peso;
    public int valor;

    public Item(int peso, int valor){
        this.peso = peso;
        this.valor = valor;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("Peso: %d, Valor: %d", peso, valor);
    }
}
