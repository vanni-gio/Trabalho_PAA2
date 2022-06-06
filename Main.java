import arquivo.HandleLeituraArquivo;
import arvoreminima.Kruskal;
import arvoreminima.Prim;
import busca.BellmanFord;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import arquivo.DadosArquivo;
import busca.BuscaEmLargura;
import busca.BuscaEmProfundidade;
import estruturas.AlgoritmoDeBusca;
import estruturas.Grafo;
import estruturas.Vertice;
import handleGraphiz.GenerateGraphiz;


public class Main {
    public static Grafo createGrafo(DadosArquivo dadosArq) {
        Grafo g = new Grafo(dadosArq.getOrientado());
        g.setNumVertices(dadosArq.getNumVertices());
        for (Vector<Integer> vector : dadosArq.getVerticesArray())
            g.addVertice(vector.elementAt(0), vector.elementAt(1), vector.elementAt(2));
        ordenaGrafo(g);
        return g;
    }

    private static void ordenaGrafo(Grafo g) {
        for (Map.Entry<Vertice, List<Vertice>> entry : g.getAdjVertices().entrySet()) {
            List<Vertice> list = entry.getValue();
            Collections.sort(list);
        }
    }

    public static void main(String[] args) {
        HandleLeituraArquivo handleArq = new HandleLeituraArquivo("C:\\Users\\Giovanni Pereira\\Desktop\\Novo(a) Documento de Texto.txt");
        DadosArquivo dados = handleArq.lerArquivo();
        Grafo g = createGrafo(dados);
        BuscaEmLargura BFS = new BuscaEmLargura(g, 3);
        BuscaEmProfundidade DFS = new BuscaEmProfundidade(g, 3);
        BellmanFord bellford = new BellmanFord(g, 3);
        Kruskal kruskal = new Kruskal(g);
        Prim prim = new Prim(g, 2);
        // handleAlgoritmoBusca(DFS);
        // handleAlgoritmoBusca(BFS);
        // bellford.executar();
        // kruskal.KruskalMST();
        prim.primMST();
        var arestasAGM = prim.getPrimAGM().values();
        var graphiz = new GenerateGraphiz(arestasAGM, g);
    }

    private static void handleAlgoritmoBusca(AlgoritmoDeBusca algoritmo) {
        algoritmo.buscar();
        System.out.println("Caminho " + algoritmo.className() + " : " + algoritmo.getCaminho());

    }
}
