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
    
    @Override
    public void buscar() {
        Queue<Vertice> fila = new LinkedList<Vertice>();
        LinkedList<Vertice> visistados = new LinkedList<Vertice>();
        Vertice v = this.getVerticeOrigem();
        visistados.add(v);
        fila.add(v);

        while (!fila.isEmpty()) {
            v = fila.poll();
            addVerticeCaminho(v);
            List<Vertice> vizinhos = getMapGrafo().get(v);
            for (Vertice u : vizinhos) {
                if(!visistados.contains(u)){
                    visistados.add(u);
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
