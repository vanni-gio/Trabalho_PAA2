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

	class SubSet
    {
		Vertice pai;
		int rank;

		public SubSet(){

		}

		public SubSet(Vertice pai){
			this.pai = pai;
			this.rank = 0;
		}
    };

	public Vertice find(Map<Vertice, SubSet> subset, Vertice v){
        if (subset.get(v).pai.equals(v))
			return v;
        
		return find(subset, subset.get(v).pai);
    }

	public void union(Map<Vertice, SubSet> pai, Map<Vertice, Integer> rank, Vertice u, Vertice v){
		var xroot = this.find(pai, u);
        var yroot = this.find(pai, v);

		if(rank.get(xroot) < rank.get(yroot)){
			pai.put(xroot, new SubSet(yroot));
		}else if(rank.get(xroot) > rank.get(yroot)){
			pai.put(yroot, new SubSet(xroot));
		}else{
			pai.put(yroot, new SubSet(xroot));
			rank.put(yroot, rank.get(yroot) + 1);
		}
 
	}

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
		arestas.sort((a, b) -> a.getPeso() - b.getPeso());
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

	public void printKruskal() {
        System.out.print("arestas: ");
        this.getResult().forEach((aresta) -> {
            System.out.print("(" + aresta.getOrigem() + ", " + aresta.getDestino() +") " );
            this.pesoMin += aresta.getPeso();
        });
        System.out.println("\npeso total: " + this.pesoMin);
    }
		
}
