package estruturas;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Grafo{
    private int numVertices;
    private List<Aresta> arestas;
    private Map<Vertice, List<Vertice>> adjVertices;
    private boolean orientado = false;

    public boolean isOrientado() {
        return orientado;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public List<Vertice> getVertices(){
        var vertices = new LinkedList<Vertice>();
        this.adjVertices.keySet().forEach((v) -> {
            vertices.push(v);
        });
        vertices.sort((v,u) -> v.getValue() - u.getValue());
        return vertices;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public Grafo(boolean orientado){
        this.adjVertices = new HashMap<Vertice, List<Vertice>>();
        this.orientado = orientado;
        this.arestas = new LinkedList<Aresta>();
    }
    
    
    public Map<Vertice, List<Vertice>> getAdjVertices() {
        return adjVertices;
    }

    public List<Aresta> getArestasAdj(Vertice u) {
        List<Aresta> arts = new LinkedList<Aresta>();
        for (Aresta aresta : this.arestas) {
            if(aresta.getOrigem().equals(u))
                arts.add(aresta);
        }
        return arts;
    }

    public Aresta getAresta(Vertice u, Vertice v){
        for (Aresta aresta : arestas) {
            if(aresta.getOrigem().equals(u) && aresta.getDestino().equals(v))
                return aresta;
        }
        return null;
    }

    public void setAdjVertices(Map<Vertice, List<Vertice>> adjVertices) {
        this.adjVertices = adjVertices;
    }

    public void addVertice(Integer v, Integer u, int peso) {
        Vertice vAux = new Vertice(v);
        Vertice uAux = new Vertice(u);

        this.addAresta(peso, vAux, uAux);
        this.addVerticeNoMap(vAux);
        this.addVerticeNoMap(uAux); 
            
        this.addAdjToVerticeList(vAux, uAux);
        if(!orientado){
            this.addAdjToVerticeList(uAux, vAux);
            this.addAresta(peso, uAux, vAux);
        }
    }

    private void addAresta(int peso, Vertice vAux, Vertice uAux) {
        Aresta a = new Aresta(vAux, uAux, peso);
        this.arestas.add(a);
    }

    
    private void addAdjToVerticeList(Vertice u, Vertice v) {
        adjVertices
            .get(u)
                .add(v);
    }

    private void addVerticeNoMap(Vertice v) {
        adjVertices.putIfAbsent(v, new LinkedList<>());
    }

    public Vertice getVertice(Vertice v){
        for (Map.Entry<Vertice, List<Vertice>> entry : this.getAdjVertices().entrySet()) {
            Vertice u = entry.getKey();
            if(u.equals(v)){
                return u;
            }   
        }
        return null;
    }


    public Vertice getVertice(Integer v){
        for (Map.Entry<Vertice, List<Vertice>> entry : this.getAdjVertices().entrySet()) {
            Vertice u = entry.getKey();
            if(u.equals(new Vertice(v))){
                return u;
            }   
        }
        return null;
    }


    @Override
    public String toString() {
        String print = "";
        for (Map.Entry<Vertice, List<Vertice>> set : this.adjVertices.entrySet()) {
            print += set.getKey().toString();
            print += " -> ";
            for (Vertice v : set.getValue()) {
                print += v.toString();
                print += " ";
            }
            print += "\n";
        }
        return print;
    }

}