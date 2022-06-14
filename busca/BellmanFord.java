package busca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import estruturas.AlgoritmoDeBusca;
import estruturas.Aresta;
import estruturas.Grafo;
import estruturas.Vertice;

public class BellmanFord extends AlgoritmoDeBusca {
    private Map<Vertice, Caminho> menoresCaminhos;
    private List<Aresta> arestas;
    private boolean cicloNegativo = false;

    
    public BellmanFord(Grafo g, Integer v) {
        this.setGrafo(g);
        this.setVerticeOrigem(this.getGrafo().getVertice(v));
        this.setArestas(g.getArestas());
        this.menoresCaminhos = new HashMap<Vertice, Caminho>(this.getGrafo().getNumVertices());
    }

    public Map<Vertice, Caminho> getMenoresCaminhos() {
        return menoresCaminhos;
    }
   
    public boolean isCicloNegativo() {
        return cicloNegativo;
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

    // // Entrada: Vertice v
    // // Saída: Lista de arestas.
    // // Pré-condição: Nenhuma.
    // // Pós-condição: Distancias maximas atualizadas.
    // // Descrição: Define as chaves dos mapas como os vértices do grafo, e suas distâncias são inicialmente "infinitas", menos o vertice inicial.
    // public List<Aresta> getArestasVertice(Vertice v) {
    //     List<Aresta> as = new LinkedList<Aresta>();
    //     for (Aresta aresta : this.getArestas()) {
    //         if (aresta.getOrigem().equals(v))
    //             as.add(aresta);
    //     }
    //     return as;
    // }

    // Entrada: mapa de distancias máximas para os vértices.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Distancias maximas atualizadas.
    // Descrição: Define as chaves dos mapas como os vértices do grafo, e suas distâncias são inicialmente "infinitas", menos o vertice inicial.
    private void initMap(Map<Vertice, Integer> distVerticeMax) {
        // Colocando a distâcia infinita para cada vértice e predecessores nulos
        for (Vertice vertice : this.getGrafo().getVertices()) {
            distVerticeMax.put(vertice, Integer.MAX_VALUE);
            //predecessor.put(vertice, null);
        }
        distVerticeMax.put(this.getVerticeOrigem(), 0);
    }

    // Entrada: mapa de distancias máximas para os vértices.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Distancias maximas atualizadas.
    // Descrição: Faz o relaxamento para todas as arestas do grafo.
    private void buscaAux(Map<Vertice, Integer> distMax) {
        final int numVertices = this.getGrafo().getNumVertices();
        var vertices = this.getGrafo().getVertices();
        vertices.forEach((vertice) -> {
            menoresCaminhos.put(vertice, new Caminho(this.getVerticeOrigem()));
        });
        for (int i = 0; i < numVertices - 1; i++) {
            for (Aresta aresta : this.getArestas()) {
                relaxamento(distMax, aresta);
            }
        }
        for (int i = 0; i < numVertices - 1; i++) {
            for (Aresta aresta : this.getArestas()) {
                if(possuiCicloNegativo(distMax, aresta))
                    this.cicloNegativo = true;
            }
        }
    }

    // Entrada: distancias máximas e aresta a ser processada.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Distancias maximas atualizadas.
    // Descrição: Realiza o relaxamento para uma aresta.
    private void relaxamento(Map<Vertice, Integer> distMax, Aresta aresta) {
        Vertice u = aresta.getOrigem();
        Vertice v = aresta.getDestino();
        int distanciaU = distMax.get(u);
        int distanciaV = distMax.get(v);
        int peso = aresta.getPeso();
        if (distanciaU != Integer.MAX_VALUE && distanciaU + peso < distanciaV) {
            distMax.put(v, distanciaU + peso);
            menoresCaminhos.forEach((k,c) -> {
                if(!c.lastVertice().equals(k)){
                    c.add(v);
                }
            });
        }
    }

    // Entrada: distancias máximas e aresta a ser processada.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Distancias maximas atualizadas.
    // Descrição: Realiza o relaxamento para uma aresta.
    private boolean possuiCicloNegativo(Map<Vertice, Integer> distMax, Aresta aresta) {
        Vertice u = aresta.getOrigem();
        Vertice v = aresta.getDestino();
        int distanciaU = distMax.get(u);
        int distanciaV = distMax.get(v);
        int peso = aresta.getPeso();
        if (distanciaU != Integer.MAX_VALUE && distanciaU + peso < distanciaV) {
            return true;
        }
        return false;
    }

    



    // Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Caminhos mínimos criados.
    // Descrição: Executa o algoritmo de busca bellmanford.
    public void buscar() {
        final int size = this.getGrafo().getNumVertices();
        Map<Vertice, Integer> distVerticeMax = new HashMap<Vertice, Integer>(size); // mapa de distâncias para cada vértice
        this.initMap(distVerticeMax);
        this.buscaAux(distVerticeMax);
        if(!this.cicloNegativo){
            distVerticeMax.forEach((v, d) -> {
                System.out.println("destino " + v.toString() + " dist.: " + d);
            });
        }else{
            System.out.println("O grafo contem ciclo com peso negativo");
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    public String className() {
        return "BellmanFord";
    }
}
