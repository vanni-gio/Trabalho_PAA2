package menu;

import arvoreminima.Kruskal;
import arvoreminima.Prim;
import busca.BellmanFord;
import busca.BuscaEmLargura;
import busca.BuscaEmProfundidade;
import estruturas.AlgoritmoDeBusca;
import estruturas.Grafo;
import handleGraphiz.GenerateGraphiz;

public class Menu {
    private BuscaEmProfundidade DFS;
    private BuscaEmLargura BFS;
    private BellmanFord bellford;
    private Kruskal kruskal;
    private Prim prim;
    private GenerateGraphiz graphiz;
    private Grafo grafo;

    public Menu(Grafo g) {
        this.grafo = g;
    }

    // Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Grafo instanciado.
    // Pós-condição: Nenhuma.
    // Descrição: Chama as funções que executam o algoritmo Kruskal.
    public void executarKruskal() {
        if (grafo == null){
            System.out.println("Erro");
            return;
        }
        this.kruskal = new Kruskal(grafo);
        this.kruskal.KruskalMST();
        this.kruskal.printKruskal();
    }

    // Entrada: Vertice inicial.
    // Saída: Nenhuma.
    // Pré-condição: Vertice inicial válido, grafo instanciado.
    // Pós-condição: Nenhuma.
    // Descrição: Chama as funções que executam o algoritmo Prim.
    public void executarPrim(Integer inicial) {
        if (!this.grafo.containsVertice(inicial) || grafo == null){
            System.out.println("Erro");
            return;
        }
        this.prim = new Prim(grafo, inicial);
        this.prim.primMST();
        this.prim.printPrimAGM();
    }

    // Entrada: Vertice inicial.
    // Saída: Nenhuma.
    // Pré-condição: Vertice inicial válido, grafo instanciado.
    // Pós-condição: Nenhuma.
    // Descrição: Chama as funções que executam o algoritmo BellmanFord.
    public void executarBellmanFord(Integer inicial) {
        if (!this.grafo.containsVertice(inicial) || grafo == null){
            System.out.println("Erro");
             return;
        }
        this.bellford = new BellmanFord(grafo, inicial);
        handleAlgoritmoBusca(this.bellford);
    }

    // Entrada: Vertice inicial.
    // Saída: Nenhuma.
    // Pré-condição: Vertice inicial válido, grafo instanciado.
    // Pós-condição: Nenhuma.
    // Descrição: Chama as funções que executam o algoritmo BFS.
    public void executarBFS(Integer inicial) {
        if (!this.grafo.containsVertice(inicial) || grafo == null){
            System.out.println("Erro");
            return;
        }
        this.BFS = new BuscaEmLargura(grafo, inicial);
        handleAlgoritmoBusca(this.BFS);
    }

    // Entrada: Vertice inicial.
    // Saída: Nenhuma.
    // Pré-condição: Vertice inicial válido, grafo instanciado.
    // Pós-condição: Nenhuma.
    // Descrição: Chama as funções que executam o algoritmo DFS.
    public void executarDFS(Integer inicial) {
        if (!this.grafo.containsVertice(inicial) || grafo == null){
            System.out.println("Erro");
            return;
        }
        this.DFS = new BuscaEmProfundidade(grafo, inicial);
        handleAlgoritmoBusca(this.DFS);
    }

    // Entrada: Grafo.
    // Saída: Nenhuma.
    // Pré-condição: Grafo criado.
    // Pós-condição: Vertices e arestas ordenadas.
    // Descrição: Ordena os vertices e as arestas de um grafo.
    public void executarGerarImgGraphiz(String op) {
        if (op.equals("1"))// kruskal
            if(kruskal != null)
                this.graphiz = new GenerateGraphiz(this.kruskal.getResult(), this.grafo, "Kruskal");
            else
                System.out.println("Kruskal nao foi executado");
        else if (op.equals("2")) {// prim
            if(this.prim != null)
                this.graphiz = new GenerateGraphiz(this.prim.getResult(), this.grafo, "Prim");
            else
                System.out.println("Prim nao foi executado");
        } else if(op.equals("3")){
            this.graphiz = new GenerateGraphiz(this.grafo.getArestas(), this.grafo, "Grafo");
        } else {
            System.out.println("Opção invalida");
            return;
        }
        this.graphiz.run();
    }

    // Entrada: Algoritmo de busca.
    // Saída: Nenhuma.
    // Pré-condição: Algoritmo de busca instanciado.
    // Pós-condição: Nenhuma.
    // Descrição: Imprime na tela o caminho gerado pelos algoritmos BellmanFord, DFS
    // e BFS.
    private static void handleAlgoritmoBusca(AlgoritmoDeBusca algoritmo) {
        if (algoritmo == null){
            System.out.println("Algoritmo nao carregado");
            return;
        }
            
        algoritmo.buscar();
        if (algoritmo.className() != "BellmanFord")
            System.out.println("Caminho " + algoritmo.className() + " : " + algoritmo.getCaminho());
        else{
            var bellmanford = (BellmanFord) algoritmo;
            if(!bellmanford.isCicloNegativo()){
                System.out.println("Caminho " + algoritmo.className() + " : ");
                bellmanford.getMenoresCaminhos().forEach((key, value) -> {
                    System.out.println(key + " : " + value);
                });
            }
        }
            
    }

}
