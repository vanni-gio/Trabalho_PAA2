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
    
    public Menu(Grafo g){
        this.grafo = g;    
    }

    public void executarKruskal() {
        this.kruskal = new Kruskal(grafo);
        this.kruskal.KruskalMST();
        this.kruskal.printKruskal();
    }

    public void executarPrim(Integer inicial) {
        this.prim = new Prim(grafo, inicial);
        this.prim.primMST();
        this.prim.printPrimAGM();
    }

    public void executarBellmanFord(Integer inicial) {
        System.out.println(grafo);
        this.bellford = new BellmanFord(grafo, inicial);
        handleAlgoritmoBusca(this.bellford);
    }

    public void executarBFS(Integer inicial) {
        this.BFS = new BuscaEmLargura(grafo, inicial);
        handleAlgoritmoBusca(this.BFS);
    }
    
    public void executarDFS(Integer inicial) {
        this.DFS = new BuscaEmProfundidade(grafo, inicial);
        handleAlgoritmoBusca(this.DFS);
    }

    public void executarGerarImgGraphiz(String op) {
        if(op.equals("1"))//kruskal
            this.graphiz = new GenerateGraphiz(this.kruskal.getResult(), this.grafo);
        else if(op.equals("2")){//prim
            this.graphiz = new GenerateGraphiz(this.prim.getResult(), this.grafo);
        }else{
            System.out.println("op invalido");
            return;
        }
        this.graphiz.run();
    }
    
    private static void handleAlgoritmoBusca(AlgoritmoDeBusca algoritmo) {
        algoritmo.buscar();
        if(algoritmo.className() != "BellmanFord")
            System.out.println("Caminho " + algoritmo.className() + " : " + algoritmo.getCaminho());
        else
            System.out.println("Caminho " + algoritmo.className() + " : " + algoritmo.getCaminho());
    }
    

}
