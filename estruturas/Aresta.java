package estruturas;

public class Aresta implements Comparable<Aresta>{
    private Vertice origem;
    private Vertice destino;
    private int peso;

    
    public Aresta(Vertice origem, Vertice destino, int peso) {
        this.setOrigem(origem);
        this.setDestino(destino);
        this.setPeso(peso);
    }


    public Vertice getOrigem() {
        return origem;
    }



    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }



    public Vertice getDestino() {
        return destino;
    }



    public void setDestino(Vertice destino) {
        this.destino = destino;
    }


    public int getPeso() {
        return peso;
    }


    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getOrigem() + " -> " + this.getDestino() + " : " + this.getPeso();
    }


    @Override
    public int compareTo(Aresta o) {
        // TODO Auto-generated method stub
        return this.getOrigem().getValue() - o.getOrigem().getValue();
    }

    @Override
    public boolean equals(Object obj) {
        Aresta other = (Aresta) obj;
        // TODO Auto-generated method stub
        return (this.getDestino().equals(other.getDestino()) && this.getOrigem().equals(other.getOrigem())) || (this.getOrigem().equals(other.getDestino()) && this.getDestino().equals(other.getOrigem()));
    }
}
