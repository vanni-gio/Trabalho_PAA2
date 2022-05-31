package busca;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import grafo.Aresta;
import grafo.Grafo;
import grafo.Vertice;

public class BellmanFord extends AlgoritmoDeBusca{
    private Map<Integer, Caminho> menoresCaminhos;  
    private List<Aresta> arestas;

    
    public BellmanFord(Grafo g, Integer v) {
        this.setGrafo(g);
        this.setVerticeOrigem(this.getGrafo().getVertice(v));
        this.setArestas(g.getArestas());
        this.menoresCaminhos = new HashMap<Integer, Caminho>(this.getGrafo().getNumVertices());
    }

    public Map<Integer, Caminho> getMenoresCaminhos() {
        return menoresCaminhos;
    }

    public void setMenoresCaminhos(Map<Integer, Caminho> menoresCaminhos) {
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
        List<Vertice> set = new LinkedList<Vertice>();
        set.add(this.getVerticeOrigem());
        for (int i = 0; i < this.getGrafo().getNumVertices() - 1; i++) {
            for (Aresta aresta : this.getArestas()) {
                Vertice u = aresta.getOrigem();
                Vertice v = aresta.getDestino();
                int distanciaU = distMax.get(u);
                int distanciaV = distMax.get(v);
                int peso = aresta.getPeso();
                if(distMax.get(u) != Integer.MAX_VALUE && distanciaU + peso < distanciaV){
                    set.add(v);
                    distMax.put(v, distanciaU + peso);
                    predecessor.put(v, u);
                }
            }
            set.add(new Vertice(-1));
        }
        System.out.println(set);
    }

    public void executar(){
        final int size = this.getGrafo().getNumVertices();
        Map<Vertice, Integer> distVerticeMax = new HashMap<Vertice,Integer>(size);
		Map<Vertice, Vertice> predecessor = new HashMap<Vertice,Vertice>(size);
        this.initMaps(distVerticeMax, predecessor);
        this.relaxamento(distVerticeMax, predecessor);
        System.out.println(distVerticeMax);
        System.out.println(predecessor);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
