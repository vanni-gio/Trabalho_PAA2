package estruturas;


// Classe abstrata para encapsular métodos e atributos utilizados por todos os algoritmos de árvore geradora mínima.
public abstract class AlgoritmoAGM {
    private Grafo grafo;
    private Aresta aresta;
    
	public Aresta getAresta() {
        return aresta;
    }

    public void setAresta(Aresta aresta) {
        this.aresta = aresta;
    }

    public AlgoritmoAGM(Grafo grafo) {
        this.grafo = grafo;
    }

    public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}
}