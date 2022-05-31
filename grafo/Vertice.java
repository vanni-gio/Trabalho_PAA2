package grafo;
import java.util.Objects;

public class Vertice implements Comparable<Vertice>{
    private Integer value;
    private int hashCode;

    public Vertice(Integer value) {
        this.value = value;
        this.hashCode = Objects.hash(value);
    }

    public Integer getValue(){
        return this.value;
    }
   
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        Vertice other = (Vertice) obj;
        return this.getValue() == other.getValue();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.getValue().toString();
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
    public static int compare(Vertice x, Vertice y) {
        return (x.getValue() < y.getValue()) ? -1 : ((x.getValue() == y.getValue()) ? 0 : 1);
    }

    @Override
    public int compareTo(Vertice o) {
        return compare(this, o);
    }
}
