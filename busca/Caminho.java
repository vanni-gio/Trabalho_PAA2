package busca;
import java.util.LinkedList;

import grafo.Vertice;

public class Caminho {
    private LinkedList<Vertice> path;

    public Caminho() {
        this.setPath(new LinkedList<>());
    }

    public boolean hasVertice(Vertice v){
        return this.path.contains(v);
    }

    public LinkedList<Vertice> getPath() {
        return path;
    }

    public void setPath(LinkedList<Vertice> path) {
        this.path = path;
    }

    public void add(Vertice v) {
        this.path.add(v);
    }

    @Override
    public String toString() {
        String path = "";
        for (int i = 0; i < this.path.size(); i++) {
            if(i < this.path.size()-1)
                path += this.path.get(i) + " -> ";
            else
                path += this.path.get(i);
        }

        return path;
    }
}
