package arvoreminima;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import estruturas.AlgoritmoAGM;
import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;


public class Kruskal extends AlgoritmoAGM{
	private int pesoMin;
	private Collection<Aresta> result;
	
	// Classe para encapsular o pai de um vértice assim como seu rank.
	class SubSet
    {
		Vertice pai;
		int rank;
		
		public SubSet(Vertice pai){
			this.pai = pai;
			this.rank = 0;
		}
    };
	
	public Collection<Aresta> getResult() {
		return result;
	}

	public void setResult(List<Aresta> result) {
		this.result = result;
	}

	public Kruskal(Grafo grafo) {
		super(grafo);
		this.pesoMin = 0;
	}

	// Entrada: Subset contendo rank e pai de um vértice, e vértice a ser procurado.
    // Saída: Vértice pai achado.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Procura um vértice como pai do em cada subconjunto.
	public Vertice find(Map<Vertice, SubSet> subset, Vertice v){
        if (subset.get(v).pai.equals(v))
			return v;
        
		return find(subset, subset.get(v).pai);
    }

	// Entrada: Mapa de pais, rank dos vertices e vértices a serem unidos.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Alteração nos pais e ranks dos mapas.
    // Descrição: Une as componentes rankeadas por "u" e "v" em uma só tornando "v" o pai de "u" ou vice versa.
	public void union(Map<Vertice, SubSet> pai, Map<Vertice, Integer> rank, Vertice u, Vertice v){
		var uRoot = this.find(pai, u);
        var vRoot = this.find(pai, v);

		if(rank.get(uRoot) < rank.get(vRoot)){
			pai.put(uRoot, new SubSet(vRoot));
		}else if(rank.get(uRoot) > rank.get(vRoot)){
			pai.put(vRoot, new SubSet(uRoot));
		}else{
			pai.put(vRoot, new SubSet(uRoot));
			rank.put(vRoot, rank.get(vRoot) + 1);
		}
 
	}


	// Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Realiza o algoritmo de Kruskal.
	public void KruskalMST() {
		List<Aresta> result = new LinkedList<Aresta>();
		int i = 0, e = 0;
		var pai = new HashMap<Vertice, SubSet>();
		var rank = new HashMap<Vertice, Integer>();

		for (var vertice : this.getGrafo().getVertices()) {
			pai.put(vertice, new SubSet(vertice));
			rank.put(vertice, 0);
		}
		var arestas = this.getGrafo().getArestas();
		arestas.sort((a, b) -> a.getPeso() - b.getPeso()); // ordena pelos pesos
		while (e < this.getGrafo().getNumVertices() - 1) {
			var aresta = arestas.get(i++);

			var x = this.find(pai, aresta.getOrigem());
			var y = this.find(pai, aresta.getDestino());

			if(!x.equals(y)){
				e++;
				result.add(aresta);
				this.union(pai, rank, x, y);
			}
		}
		this.setResult(result);
		// int custoMinimo = 0;
		// for (Aresta aresta : result) {
		// 	custoMinimo += aresta.getPeso();
		// 	System.out.println(aresta.getOrigem().toString() + " " + aresta.getDestino().toString());
		// }
		// System.out.println("Custo Minimo: " + custoMinimo);
	}


	// Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Imprime na tela o resultado do algoritmo de Kruskal.
	public void printKruskal() {
        System.out.print("arestas: ");
        this.getResult().forEach((aresta) -> {
            System.out.print("(" + aresta.getOrigem() + ", " + aresta.getDestino() +") " );
            this.pesoMin += aresta.getPeso();
        });
        System.out.println("\npeso total: " + this.pesoMin);
    }
		
}
