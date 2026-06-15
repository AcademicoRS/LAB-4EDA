import edu.princeton.cs.algs4.BST;

public class BSTInventoryIndex implements InventoryIndex {
    private BST<Integer, InventoryItem> st;

    public BSTInventoryIndex() {
        this.st = new BST<>();
    }

    public void put(Integer key, InventoryItem value) {
        st.put(key, value);
    }

    public InventoryItem get(Integer key) {
        return st.get(key);
    }

    public void delete(Integer key) {
        st.delete(key);
    }

    public boolean contains(Integer key) {
        return st.contains(key);
    }

    public Iterable<Integer> keys() {
        return st.keys();
    }

    public int size() {
        return st.size();
    }

    public int height() {

        return st.height(); 
    }
}

// Creo que hay q meterle @override, tengo entendido que se tienen que sobreescribir pero no estoy segurin papu.
//AH Y ESTO SOLO ESTA DEFINIENDO AL BST, YA QUE SOLO ESTA A LA ESPERA DE NUEVAS INSTRUCCIONES. POR ESO NO SE VE COMO LOS DEMAS MI NEGRITO BELLO PECHOCHO 
//Lo entendimos todo (necesitamos un 6 porfavor :cccc)
//este es nuestro mayor esfuerzo