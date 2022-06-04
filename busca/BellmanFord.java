package busca;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import estruturas.AlgoritmoDeBusca;
import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;

public class BellmanFord extends AlgoritmoDeBusca{
    private Map<Vertice, Caminho> menoresCaminhos;  
    private List<Aresta> arestas;

    public BellmanFord(Grafo g, Integer v) {
        this.setGrafo(g);
        this.setVerticeOrigem(this.getGrafo().getVertice(v));
        this.setArestas(g.getArestas());
        this.menoresCaminhos = new HashMap<Vertice, Caminho>(this.getGrafo().getNumVertices());
    }

    public Map<Vertice, Caminho> getMenoresCaminhos() {
        return menoresCaminhos;
    }

    public void setMenoresCaminhos(Map<Vertice, Caminho> menoresCaminhos) {
        this.menoresCaminhos = menoresCaminhos;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public List<Aresta> getArestasVertice(Vertice v){
        List<Aresta> as = new LinkedList<Aresta>();
        for (Aresta aresta : this.getArestas()) {
            if(aresta.getOrigem().equals(v))
                as.add(aresta);
        }
        return as;
    }   

    private void initMaps(Map<Vertice, Integer> distVerticeMax,  Map<Vertice, Vertice> predecessor) {
		//Colocando a distâcia infinita para cada vértice e predecessores nulos
        for (Vertice vertice : this.getGrafo().getVertices()) {
            distVerticeMax.put(vertice, Integer.MAX_VALUE);
            predecessor.put(vertice, null);
        }
        distVerticeMax.put(this.getVerticeOrigem(), 0);
    }

    private void relaxamento(Map<Vertice, Integer> distMax, Map<Vertice, Vertice> predecessor) {
        final int numVertices = this.getGrafo().getNumVertices();
        var vertices = this.getGrafo().getVertices();
        vertices.forEach((vertice) -> {
            menoresCaminhos.put(vertice, null);
        });

        Caminho c = new Caminho();
        for (int i = 0; i < numVertices - 1; i++) {
            for (Aresta aresta : this.getArestas()) {
                Vertice u = aresta.getOrigem();
                Vertice v = aresta.getDestino();
                int distanciaU = distMax.get(u);
                int distanciaV = distMax.get(v);
                int peso = aresta.getPeso();
                if(distMax.get(u) != Integer.MAX_VALUE && distanciaU + peso < distanciaV){
                    distMax.put(v, distanciaU + peso);
                    if(!c.getPath().contains(u))
                        c.add(u);
                    if(!c.getPath().contains(v))
                        c.add(v);
                }
            }
        }
        System.out.println(c);
        createMenoresCaminhos(c);
        menoresCaminhos.forEach((key, value) -> {
            System.out.println(value);
        });
    }

    private void createMenoresCaminhos(Caminho c) {       
        menoresCaminhos.forEach((key, value) -> {
            Caminho aux = new Caminho(c.getPath().subList(0, c.getPath().indexOf(key)+1));
            menoresCaminhos.put(key, aux);
        });
    }

    public void executar(){
        final int size = this.getGrafo().getNumVertices();
        Map<Vertice, Integer> distVerticeMax = new HashMap<Vertice,Integer>(size);
		Map<Vertice, Vertice> predecessor = new HashMap<Vertice,Vertice>(size);
        this.initMaps(distVerticeMax, predecessor);
        this.relaxamento(distVerticeMax, predecessor);
        System.out.println(distVerticeMax);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    public static List<Vertice> mergeList(List<Vertice> a, List<Vertice> b) {
        for (Vertice vertice : b) {
            if(!a.contains(vertice))
                a.add(vertice);
        }
        return a;
    }   
}
