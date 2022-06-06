package arvoreminima;

import java.util.HashMap;
import java.util.Map;
// import java.util.stream.Stream;
// import java.util.Map.Entry;
// import java.util.stream.Collectors;
// import java.util.LinkedHashMap;

import estruturas.AlgoritmoAGM;
import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;



public class Prim extends AlgoritmoAGM {
    private Vertice inicial;
    private int pesoMin;
	private HashMap<Vertice, Aresta> primAGM; 


    public Prim(Grafo grafo, Integer inicial) {
		super(grafo);
        this.inicial = new Vertice(inicial);
        this.pesoMin = 0;
        this.primAGM = null;
		//TODO Auto-generated constructor stub
	}
    
    public HashMap<Vertice, Aresta> getPrimAGM() {
        return primAGM;
    }

    public void setPrimAGM(HashMap<Vertice, Aresta> primAGM) {
        this.primAGM = primAGM;
    }

    public Vertice getInicial() {
        return inicial;
    }

    public void setInicial(Vertice inicial) {
        this.inicial = inicial;
    }

    public Vertice minVertice(Map<Vertice, Integer> key, Map<Vertice, Boolean> mst) {
        var min = Integer.MAX_VALUE;
        Vertice minVertice = null;
        for (var v : this.getGrafo().getVertices()) {
            if(key.get(v) < min && !mst.get(v)){
                min = key.get(v);
                minVertice = v;
            }
        }

        return minVertice;
    }

    public void primMST(){
        final var numV = this.getGrafo().getNumVertices();
        var pesos = new HashMap<Vertice, Integer>(numV);
        var parent = new HashMap<Vertice, Aresta>(numV);
        var mst = new HashMap<Vertice, Boolean>(numV);
        var vertices = this.getGrafo().getVertices();
        for (var vertice :  vertices) {
            pesos.put(vertice, Integer.MAX_VALUE);
            mst.put(vertice, false);
        }
        pesos.put(this.inicial, 0);
        parent.put(this.inicial, null);

        for (int i = 0; i < numV - 1; i++) {
            var minV = minVertice(pesos, mst);
            mst.put(minV, true);
            var grafo = this.getGrafo();
            for (Vertice u : vertices) {
                var aresta = grafo.getAresta(minV, u);
                if(aresta != null){
                    if(!mst.get(u) && aresta.getPeso() < pesos.get(u)){
                        Aresta a = new Aresta(minV, u, aresta.getPeso());
                        parent.put(u, a);
                        pesos.put(u, aresta.getPeso());
                    }
                }
            }
        }
        this.setPrimAGM(parent);
        printMS(parent);
    }

    public void printMS(HashMap<Vertice, Aresta> parent) {
        parent.remove(this.getInicial(), null);
        System.out.println("vertice inicial: " + this.getInicial());
        System.out.print("arestas: ");
        parent.forEach((vertice, aresta) -> {
            System.out.print("(" + aresta.getOrigem() + ", " + aresta.getDestino() +") " );
            this.pesoMin += aresta.getPeso();
        });
        System.out.println("\npeso total: " + this.pesoMin);
    }
     
    // public static Map<Vertice, Vertice> ordenaMap(Map<Vertice, Vertice> map) {
    //     Map<Vertice, Vertice> sorted =
	// 		    map.entrySet().stream()
	// 		       .sorted(Map.Entry.comparingByValue())
    //                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    //     return sorted;
    // }
}
