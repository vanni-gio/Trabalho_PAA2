package busca;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import estruturas.AlgoritmoDeBusca;
import estruturas.Grafo;
import estruturas.Vertice;

public class BuscaEmLargura extends AlgoritmoDeBusca{
    private final Caminho caminho = new Caminho();

    public BuscaEmLargura(Grafo g, Integer v){
        this.setGrafo(g);
        this.setVertices(this.getGrafo().getAdjVertices());
        this.setVerticeOrigem(this.getGrafo().getVertice(new Vertice(v)));
    }

    public Caminho getCaminho() {
        return caminho;
    }
    
    // Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Executa o algoritmo de busca em largura.
    @Override
    public void buscar() {
        Queue<Vertice> fila = new LinkedList<Vertice>(); // fila de vértices a serem processador
        LinkedList<Vertice> visitados = new LinkedList<Vertice>(); // lista de vértices visitados
        visitados.add(this.getVerticeOrigem());
        fila.add(this.getVerticeOrigem());

        while (!fila.isEmpty()) {
            var atual = fila.poll(); // retira a primeiro vertice da fila
            addVerticeCaminho(atual);
            List<Vertice> vizinhos = getMapGrafo().get(atual);
            for (Vertice u : vizinhos) {
                if(!visitados.contains(u)){
                    visitados.add(u);
                    fila.add(u);
                }
            }
        }
    }


    @Override
    public String className() {
        // TODO Auto-generated method stub
        return "Busca Em Largura";
    }
    
}
