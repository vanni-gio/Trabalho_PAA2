package busca;
import java.util.List;
import java.util.Map;

import grafo.Grafo;
import grafo.Vertice;

public abstract class AlgoritmoDeBusca {
    private Grafo grafo;
    private Map<Vertice, List<Vertice>> vertices;
    private Vertice verticeOrigem;
    

    public void addVerticeCaminho(Vertice v) {
        this.getCaminho().add(v);
    }

    public Caminho getCaminho() {
        return new Caminho();
    }


    public Vertice getVerticeOrigem() {
        return verticeOrigem;
    }

    public void setVerticeOrigem(Vertice verticeTarget) {
        this.verticeOrigem = verticeTarget;
    }

    public Map<Vertice, List<Vertice>> getMapGrafo() {
        return vertices;
    }

    public void setVertices(Map<Vertice, List<Vertice>> vertices) {
        this.vertices = vertices;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public void buscar(){
        System.out.println("Not implemented");
    }
    

    public String className() {
        return "AlgoritmoDeBusca";
    }
    
}
