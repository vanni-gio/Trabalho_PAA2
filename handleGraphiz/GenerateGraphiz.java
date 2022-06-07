package handleGraphiz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;


import estruturas.Aresta;
import estruturas.Grafo;


public class GenerateGraphiz {
    private final String path = "grafosGraphiz/grafo.gv";
    private Collection<Aresta> arestasMin;
    private String tipoGrafo;
    private Cor[] cores;
    private HashMap<Aresta, Cor> coresAresta;
    private String dotCode;


    // Descrição: Enum que representa algumas cores do Graphviz. 
    public enum Cor{
        GREEN("green"),
        BLACK("black"),
        RED("red"),
        BLUE("blue"),
        YELLOW("yellow"),
        NULL("");
        private String valor;
        Cor(String valor){
            this.valor = valor;
        }
        
        public String getValor() {
            return valor;
        }

        public boolean iguais(Cor other) {
            return this.getValor().endsWith(other.getValor());
        }
    }
    public GenerateGraphiz(Collection<Aresta> arestas, Grafo g) {
        this.arestasMin = arestas;
        this.tipoGrafo = g.isOrientado() ? "orientado" : "";
        this.cores = Cor.values();
        this.coresAresta = new HashMap<Aresta, Cor>();
        arestas.forEach((aresta) -> 
            coresAresta.put(aresta, Cor.NULL)
        );
    }

    public String getTipoGrafo() {
        return tipoGrafo;
    }

    public void setTipoGrafo(String tipoGrafo) {
        this.tipoGrafo = tipoGrafo;
    }

    public Collection<Aresta> getArestas() {
        return arestasMin;
    }
    
    public void setAresta(Collection<Aresta> aresta) {
        this.arestasMin = aresta;
    }

    public Cor[] getCores() {
        return cores;
    }

    public void setCores(Cor[] cores) {
        this.cores = cores;
    }

    public HashMap<Aresta, Cor> getCoresAresta() {
        return coresAresta;
    }

    public void setCoresAresta(HashMap<Aresta, Cor> coresAresta) {
        this.coresAresta = coresAresta;
    }


    // Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Cores das arestas definidas.
    // Descrição: Define as cores das arestas, sendo que todas as cores vizinhas de uma aresta são diferentes. 
    public void definirCoresArestas() {
        this.arestasMin.forEach((aresta) -> {
            var arestasVizinhas = Grafo.getArestasVizinhas(arestasMin, aresta);
            int indexCor = 0;
            Cor corAtual = this.getCores()[indexCor];
            for (Aresta arestaVizinha : arestasVizinhas) {
                var corVizinha = this.getCoresAresta().get(arestaVizinha);
                if(corVizinha.iguais(corAtual) && corVizinha.iguais(Cor.NULL)){
                    indexCor++;
                    corAtual = this.getCores()[indexCor];
                }
            }
            this.getCoresAresta().put(aresta, corAtual);
        });
    }

    // Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Imagem criada.
    // Descrição: Executa no cmd o comando para gerar uma imagem a partir do código Dot gerado. 
    public void createImgGrafo(){
        Runtime rt = Runtime.getRuntime();
        var rootDir = System.getProperty("user.dir") + "\\grafosGraphiz";
        System.out.println(rootDir);
        try {
            rt.exec(new String[]{"cmd.exe", "/c", "cd " + rootDir + " && dot.exe -Tpng grafo.gv -o grafo.png"});
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Entrada: Dados do arquivo.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Arquivo criado e com os dados escritos.
    // Descrição: Cria um arquivo .gv e escreve os dados nele.
    public void createFile(String data) {
        try {
            File myObj = new File(path);
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(path);
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    // Entrada: Nenhuma.
    // Saída: Código dot.
    // Pré-condição: Nenhuma.
    // Pós-condição: Código dot gerado.
    // Descrição: Monta os código dot a ser escrito no arquivo.
    public String gerarDotCode(){
        boolean orientado = this.getTipoGrafo().equals("") ? false : true;
        var arrow = orientado ? "->" : "--";
        var tipo = orientado ? "digraph" : "graph";
        var aresta = "& @ ! [label=\"#\", color=&]\n";  // A -- B [label=".63",color=antiquewhite]
        this.dotCode = "# G{ \nlayout=neato\n\tnode[shape=\"circle\"]\n".replace("#", tipo);
        this.getCoresAresta().forEach((a,cor) -> {
        this.dotCode += aresta
            .replaceFirst("&", Integer.toString(a.getOrigem().getValue()))
            .replaceFirst("@", arrow)
            .replaceFirst("!", Integer.toString(a.getDestino().getValue()))
            .replaceFirst("#", Integer.toString(a.getPeso()))
            .replaceFirst("&", cor.getValor());
        });
        this.dotCode += "}";
        return dotCode;
    }
    
    // Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Executa os métodos para gerar o código Dot e executar o mesmo no cmd.
    public void run() {
        this.definirCoresArestas();
        var dotCode = this.gerarDotCode();
        this.createFile(dotCode);
        this.createImgGrafo();
    }
}
