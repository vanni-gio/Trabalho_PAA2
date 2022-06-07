import arquivo.HandleLeituraArquivo;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import arquivo.DadosArquivo;
import estruturas.Grafo;
import estruturas.Vertice;
import menu.Menu;

public class Main {

    // Entrada: Args(nao usado).
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Função principal que chama as funções de execução do programa, além de instanciar objetos principais.
    public static void main(String[] args) {
        HandleLeituraArquivo handleArq = null;
        DadosArquivo dados = null;
        Grafo grafo = null;
        Menu menu = new Menu(grafo);
        Scanner ler = new Scanner(System.in);
        Integer verticeInicial = null;
        while (true) {
            printOpcoes();
            String op = ler.nextLine();
            switch (op) {
                case "1":
                    limparTela();
                    System.out.print("Digite o caminho do arquivo: ");
                    String path = ler.nextLine();
                    handleArq = new HandleLeituraArquivo(path);
                    dados = handleArq.lerArquivo();
                    grafo = createGrafo(dados);
                    menu = new Menu(grafo);
                    System.out.println("Digite enter para continuar...");
                    ler.nextLine();
                    break;
                case "2":
                    limparTela();
                    System.out.print("Digite o vertice inicial: ");
                    verticeInicial = Integer.parseInt(ler.nextLine());
                    if (dados != null)
                        menu.executarBellmanFord(verticeInicial);
                    else
                        System.out.println("Arquivo nao foi aberto");
                    System.out.println("Digite enter para continuar...");
                    ler.nextLine();
                    break;
                case "3":
                    limparTela();
                    System.out.print("Digite o vertice inicial: ");
                    verticeInicial = Integer.parseInt(ler.nextLine());
                    if (dados != null)
                        menu.executarBFS(verticeInicial);
                    else
                        System.out.println("Arquivo nao foi aberto");
                    System.out.println("Digite enter para continuar...");
                    ler.nextLine();
                    break;
                case "4":
                    limparTela();
                    System.out.print("Digite o vertice inicial: ");
                    verticeInicial = Integer.parseInt(ler.nextLine());
                    if (dados != null)
                        menu.executarBFS(verticeInicial);
                    else
                        System.out.println("Arquivo nao foi aberto");
                    System.out.println("Digite enter para continuar...");
                    ler.nextLine();
                    break;
                case "5":
                    limparTela();
                    if (dados != null)
                        menu.executarKruskal();
                    else
                        System.out.println("Arquivo nao foi aberto");
                    System.out.println("Digite enter para continuar...");
                    ler.nextLine();
                    break;
                case "6":
                    limparTela();
                    System.out.print("Digite o vertice inicial: ");
                    verticeInicial = Integer.parseInt(ler.nextLine());
                    if (dados != null)
                        menu.executarPrim(verticeInicial);
                    else
                        System.out.println("Arquivo nao foi aberto");
                    System.out.println("Digite enter para continuar...");
                    ler.nextLine();
                    break;
                case "7":
                    limparTela();
                    System.out.print("Digite 1 para Kruskal e 2 para Prim: ");
                    String algoritmo = ler.nextLine();
                    if (dados != null)
                        menu.executarGerarImgGraphiz(algoritmo);
                    else
                        System.out.println("Arquivo nao foi aberto");
                    System.out.println("Digite enter para continuar...");
                    ler.nextLine();
                    break;
                case "0":
                    ler.close();
                    return;
                default:
                    break;
            }
        }
    }
    // Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Imprime na tela as opções do menu.
    private static void printOpcoes() {
        System.out.println("--------------------------------------");
        System.out.println("\t1 - Ler arquivo");
        System.out.println("\t2 - Executar BellmanFord");
        System.out.println("\t3 - Executar Busca Em Largura");
        System.out.println("\t4 - Executar Busca Em Profundidade");
        System.out.println("\t5 - Executar Kruskal");
        System.out.println("\t6 - Executar Prim");
        System.out.println("\t7 - Gerar Imagem");
        System.out.println("\t0 - Sair");
        System.out.println("--------------------------------------\n");
        System.out.print("\tDigite uma opção: ");
    }

    // Entrada: Dados lidos do arquivo.
    // Saída: Um grafo com as arestas e os vertices definidos no arquivo.
    // Pré-condição: Grafo criado.
    // Pós-condição: Nenhuma.
    // Descrição: Cria um grafo a partir dos dados retirados do arquivo.
    public static Grafo createGrafo(DadosArquivo dadosArq) {
        if(dadosArq == null)
            return null;
        Grafo g = new Grafo(dadosArq.getOrientado());
        g.setNumVertices(dadosArq.getNumVertices());
        for (Vector<Integer> vector : dadosArq.getVerticesArray())
            g.addVertice(vector.elementAt(0), vector.elementAt(1), vector.elementAt(2));
        ordenaGrafo(g);
        return g;
    }

    // Entrada: Grafo.
    // Saída: Nenhuma.
    // Pré-condição: Grafo criado.
    // Pós-condição: Vertices e arestas ordenadas.
    // Descrição: Ordena os vertices e as arestas de um grafo.
    private static void ordenaGrafo(Grafo g) {
        if(g == null)
            return;
        for (Map.Entry<Vertice, List<Vertice>> entry : g.getAdjVertices().entrySet()) {
            List<Vertice> list = entry.getValue();
            Collections.sort(list);
        }
        Collections.sort(g.getArestas());
    }

    // Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Estar no sistema operacional Windows.
    // Pós-condição: Tela de saída limpa.
    // Descrição: Executa o comando "cls" para limpar o buffer de saída do programa.
    private static void limparTela() {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(new String[] { "cmd.exe", "/c", "cls" });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
