package estruturas;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Grafo{
    private int numVertices;
    private List<Aresta> arestas;
    private Map<Vertice, List<Vertice>> adjVertices;
    private boolean orientado = false;
    
    public Grafo(boolean orientado){
        this.adjVertices = new HashMap<Vertice, List<Vertice>>();
        this.orientado = orientado;
        this.arestas = new LinkedList<Aresta>();
    }
    public boolean isOrientado() {
        return orientado;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    // Entrada: Nenhuma.
    // Saída: Lista de todos os vértices do grafo.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Procura todos os vértices do grafo e ordena eles também.   
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
    
    public Map<Vertice, List<Vertice>> getAdjVertices() {
        return adjVertices;
    }

    // Entrada: Um vértice.
    // Saída: Lista de vértices adjacentes.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Busca todas as arestas que fazem adjacêcia com um vértice.   
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

    // Entrada: origem, destino e peso de um vértice.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Instancias de vertices criadas e adicionas nas lista de adjacência.
    // Descrição: Instancia e adiciona objetos vértices no HashMap.   
    public void addVertice(Integer origem, Integer destino, int peso) {
        var auxOrigem = new Vertice(origem);
        var auxDestino = new Vertice(destino);

        this.addAresta(peso, auxOrigem, auxDestino);
        this.addVerticeNoMap(auxOrigem);
        this.addVerticeNoMap(auxDestino); 
            
        this.addAdjToVerticeList(auxOrigem, auxDestino);
        if(!orientado){
            this.addAdjToVerticeList(auxDestino, auxOrigem);
            this.addAresta(peso, auxDestino, auxOrigem);
        }
    }

    // Entrada: Peso, origem e destino.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Aresta adicionada na lista.
    // Descrição: Cria e adiciona uma aresta na lista de arestas.   
    private void addAresta(int peso, Vertice origem, Vertice destin) {
        var aresta = new Aresta(origem, destin, peso);
        this.arestas.add(aresta);
    }

    // Entrada: Vertice origem e destino.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Vertice destino adicionado.
    // Descrição: Adiciona o vértice destino na lista de vértices do vértice de origem.    
    private void addAdjToVerticeList(Vertice u, Vertice v) {
        adjVertices
            .get(u)
                .add(v);
    }

    // Entrada: Um vertice.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Vertice adicionado ao HashMap de vertices adjacentes.
    // Descrição: Adiciona um vértice ao HashMap caso ele não tenha sido adicionado.
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

    // Entrada: Arestas de referência, e aresta principal.
    // Saída: Lista de arestas vizinhas.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Percorre as arestas e define quais delas são arestas vizinhas da aresta principal. 
    public static List<Aresta> getArestasVizinhas(Collection<Aresta> arestas, Aresta aresta) {
        var arestasVizinhas = new LinkedList<Aresta>();
        arestas.forEach((a) -> {
            if(!a.equals(aresta)){
                if(a.getOrigem().equals(aresta.getOrigem()) || a.getDestino().equals(aresta.getDestino())){
                    arestasVizinhas.add(a);
                }else if(a.getOrigem().equals(aresta.getDestino()) || a.getDestino().equals(aresta.getOrigem())){
                    arestasVizinhas.add(a);
                }
            }
        });
        return arestasVizinhas;
    }

    // Entrada: Vertice a ser processado.
    // Saída: Contém o vértice.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Verifica se possui um determinado vértice no grafo.
    public boolean containsVertice(Integer v) {
        for (var u : this.getVertices()) {
            if(u.getValue() == v)
                return true;
        }
        return false;
    }
}