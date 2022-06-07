package arquivo;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;


// Classe para encapsular os dados lidos do arquivo
public class DadosArquivo {
    private boolean orientado;
    private Integer numVertices;
    private List<Vector<Integer>> arestasList;

    public DadosArquivo() {
        this.arestasList = new LinkedList<Vector<Integer>>();
    }

    public boolean getOrientado() {
        return orientado;
    }

    public List<Vector<Integer>> getVerticesArray() {
        return arestasList;
    }

    public void setVerticesArray(List<Vector<Integer>> verticesList) {
        this.arestasList = verticesList;
    }

    public Integer getNumVertices() {
        return numVertices;
    }

    public void setNumVertices(Integer numVertices) {
        this.numVertices = numVertices;
    }

    public void setOrientado(boolean orientado) {
        this.orientado = orientado;
    }

    // Entrada: origem, destino e peso de uma aresta do arquivo.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Cria um vetor com as informações da aresta e adiciona na lista.    
    public void addAresta(Integer origem, Integer destino, Integer peso){
        Vector<Integer> vector = new Vector<Integer>(3);
        vector.add(origem);
        vector.add(destino);
        vector.add(peso);
        this.arestasList.add(vector);
    }

    // Entrada: Nenhuma.
    // Saída: Nenhuma.
    // Pré-condição: Nenhuma.
    // Pós-condição: Nenhuma.
    // Descrição: Imprime os dados do arquivo *debug*
    public void print(){
        System.out.println(this.orientado);
        System.out.println(this.numVertices);
        for (Vector<Integer> vector : arestasList) {
            System.out.print(vector.elementAt(0) + " ");
            System.out.print(vector.elementAt(1) + " ");
            System.out.print(vector.elementAt(2) + " \n");
        }
    }

}
