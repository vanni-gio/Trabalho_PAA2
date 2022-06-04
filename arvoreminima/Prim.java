package arvoreminima;

import java.util.HashMap;
import java.util.Map;
// import java.util.stream.Stream;
// import java.util.Map.Entry;
// import java.util.stream.Collectors;
// import java.util.LinkedHashMap;

import estruturas.AlgoritmoAGM;
import estruturas.Grafo;
import estruturas.Vertice;



public class Prim extends AlgoritmoAGM {
    private Vertice inicial;
    private int pesoMin;
	


    public Prim(Grafo grafo, Integer inicial) {
		super(grafo);
        this.inicial = new Vertice(inicial);
        this.pesoMin = 0;
		//TODO Auto-generated constructor stub
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
        var parent = new HashMap<Vertice, Vertice>(numV);
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
                        parent.put(u, minV);
                        pesos.put(u, aresta.getPeso());
                    }
                }
            }
        }
        printMS(parent, pesos);
    }

    public void printMS(HashMap<Vertice, Vertice> parent, HashMap<Vertice, Integer> pesos) {
        parent.remove(this.getInicial(), null);
        System.out.println("vertice inicial: " + this.getInicial());
        System.out.print("arestas: ");
        parent.forEach((u,v) -> {
            System.out.print("(" + v + ", " + u +") " );
            Integer peso = pesos.get(u);
            this.pesoMin += peso;
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
