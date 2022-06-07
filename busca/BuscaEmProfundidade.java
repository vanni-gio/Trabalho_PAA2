package busca;
import java.util.LinkedList;
import java.util.Stack;

import estruturas.AlgoritmoDeBusca;
import estruturas.Grafo;
import estruturas.Vertice;

/**
 * BuscaEmLargura
 */
public class BuscaEmProfundidade extends AlgoritmoDeBusca{
    private final Caminho caminho = new Caminho();

    public BuscaEmProfundidade(Grafo g, Integer v){
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
    // Descrição: Executa o algoritmo de busca em profundidade. 
    public void buscar() {
        Vertice atual = this.getVerticeOrigem();
        LinkedList<Vertice> visitados = new LinkedList<Vertice>();
        Stack<Vertice> pilha = new Stack<Vertice>();
        Stack<Vertice> pais = new Stack<Vertice>();

        pilha.add(atual);
        visitados.add(atual);
        
        boolean addVerticeFila = false;
        while (!pilha.isEmpty()) {
            pais.push(atual);
            atual = pilha.pop();
            // nao adicionar o pai novamente no caminho
            if(!this.getCaminho().hasVertice(atual))
                addVerticeCaminho(atual);
            for (Vertice vizinho : getMapGrafo().get(atual)) {
                if (!visitados.contains(vizinho)) {    
                    visitados.add(vizinho);
                    pilha.push(vizinho);
                    addVerticeFila = true;
                    break;
                }
            }
            // caso o vertice nao possua vizinhos para visitar e ainda nao foi visitado todos os vertices
            if(!addVerticeFila && visitados.size() < this.getGrafo().getNumVertices()){
                Vertice pai = pais.pop();
                pilha.push(pai);
            }
            addVerticeFila = false;
        }
    }


    @Override
    public String className() {
        // TODO Auto-generated method stub
        return "Busca Em Profundidade";
    }
    
}