import arquivo.HandleLeituraArquivo;
import busca.AlgoritmoDeBusca;
import busca.BellmanFord;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import arquivo.DadosArquivo;
import busca.BuscaEmLargura;
import busca.BuscaEmProfundidade;
import grafo.Grafo;
import grafo.Vertice;


public class Main {
    public static Grafo createGrafo(DadosArquivo dadosArq) {
        Grafo g = new Grafo(dadosArq.getOrientado());
        g.setNumVertices(dadosArq.getNumVertices());
        for (Vector<Integer> vector : dadosArq.getVerticesArray())
            g.addVertice(vector.elementAt(0), vector.elementAt(1), vector.elementAt(2));
        ordenaVerticesAdj(g);
        return g;
    }

    private static void ordenaVerticesAdj(Grafo g) {
        for (Map.Entry<Vertice, List<Vertice>> entry : g.getAdjVertices().entrySet()) {
            List<Vertice> list = entry.getValue();
            System.out.println(list);
            Collections.sort(list);
            System.out.println(list);
        }
    }

    public static void main(String[] args) {
        HandleLeituraArquivo handleArq = new HandleLeituraArquivo("C:\\Users\\Giovanni Pereira\\Desktop\\Novo(a) Documento de Texto.txt");
        DadosArquivo dados = handleArq.lerArquivo();
        Grafo g = createGrafo(dados);
        System.out.println(g);
        BuscaEmLargura BFS = new BuscaEmLargura(g, 3);
        BuscaEmProfundidade DFS = new BuscaEmProfundidade(g, 3);
        BellmanFord bellford = new BellmanFord(g, 1);
        handleAlgoritmoBusca(DFS);
        handleAlgoritmoBusca(BFS);
        bellford.executar();
    }

    private static void handleAlgoritmoBusca(AlgoritmoDeBusca algoritmo) {
        algoritmo.buscar();
        System.out.println("Caminho " + algoritmo.className() + ": " + algoritmo.getCaminho());

    }
}
