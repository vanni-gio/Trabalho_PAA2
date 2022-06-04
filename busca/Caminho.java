package busca;
import java.util.LinkedList;
import java.util.List;

import estruturas.Vertice;

public class Caminho {
    private List<Vertice> path;

    public Caminho() {
        this.setPath(new LinkedList<>());
    }

    public Caminho(List<Vertice> list) {
        this.setPath(list);
    }

    public Caminho(Vertice inicial) {
        var list = new LinkedList<Vertice>();
        list.add(inicial);
        this.setPath(list);
    }

    public boolean hasVertice(Vertice v){
        return this.path.contains(v);
    }

    public List<Vertice> getPath() {
        return path;
    }

    public void setPath(List<Vertice> path) {
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
