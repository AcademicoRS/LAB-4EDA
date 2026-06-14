import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StopwatchCPU;
import java.util.ArrayList;

public class Experimento {

    //kenji carrito yo soy una mochila
    public static String AlienX(int instancia, String estructuraNombre, InventoryIndex index, ArrayList<InventoryOperation> tipoOperación, int m) {

        //Se supone que deboes dejar todo en 0 ricky?
        //see pq se supone que es el nventario inicial, entonces no tienes datos. tontito unu
        //podriamos dejarlo asi o con muchos int, como prefieras mi king unu
        int purchase_total = 0, query_total = 0, lend_total = 0, receive_total = 0, dispose_total = 0;
        int query_yupi = 0, query_caracolitos = 0;
        int lend_yupi = 0, lend_caracolitos = 0;
        int receive_yupi = 0, receive_caracolitos = 0;

        StopwatchCPU timer = new StopwatchCPU();

        for (InventoryOperation op : tipoOperación) {
            OperationType type = op.getType();
            int key = op.getKey();
            int quantity = op.getQuantity();

            if (type == OperationType.PURCHASE) {
                purchase_total++;
                InventoryItem itemToPut = op.getItem(); 
                
                if (itemToPut != null) {
                    index.put(key, itemToPut);
                } else {

                    InventoryItem existingItem = index.get(key);
                    if (existingItem != null) {
                        existingItem.addStock(key, quantity);
                        index.put(key, existingItem); 
                    }
                }

            } else if (type == OperationType.QUERY) {
                query_total++;
                InventoryItem item = index.get(key);
                if (item != null) query_yupi++;
                else query_caracolitos++;

            } else if (type == OperationType.LEND) {
                lend_total++;
                InventoryItem item = index.get(key);
                if (item != null && item.lend(key, quantity)) {
                    lend_yupi++;
                } else {
                    lend_caracolitos++;
                }

            } else if (type == OperationType.RECEIVE) {
                receive_total++;
                InventoryItem item = index.get(key);
                if (item != null && item.receive(key, quantity)) {
                    receive_yupi++;
                } else {
                    receive_caracolitos++;
                }

            } else if (type == OperationType.DISPOSE) {
                dispose_total++;
                index.delete(key);
            }
        }

        double elapsed_seconds = timer.elapsedTime();

        int final_size = index.size();
        int final_height = index.height();

        
        return instancia + "," + estructuraNombre + "," + m + "," + 
               purchase_total + "," + query_total + "," + lend_total + "," + receive_total + "," + dispose_total + "," +
               query_yupi + "," + query_caracolitos + "," + lend_yupi + "," + lend_caracolitos + "," + 
               receive_yupi + "," + receive_caracolitos + "," + final_size + "," + final_height + "," + elapsed_seconds;
    }

public static void main(String[] args) {
    int[] Tamaños = { 18, 19};

    System.out.println("Iniciando experimento...");

    for (int t : Tamaños) {
        int m = (int) Math.pow(2, t);
        
        // Creamos la instancia de Out de Princeton apuntando a la raíz
        Out cuchurrumin = new Out("inventory_experiment_" + m + ".csv");
        
        // Configuramos para que Excel entienda el separador inmediatamente
        cuchurrumin.println("sep=,"); 
        
        cuchurrumin.println("instancia;estructura,m,purchase_total,query_total,lend_total,receive_total,dispose_total," +
                            "query_successful,query_failed,lend_successful,lend_failed,receive_successful,receive_failed," +
                            "final_size,final_height,elapsed_seconds");

        System.out.println("Procesando tamaño m = " + m + "...");

        for (int i = 1; i <= 30; i++) {
            long seed = m + i; 
            int keyUniverse = 4 * m;

            ArrayList<InventoryOperation> tipoOperación = DataGenerator.generateOperations(m, keyUniverse, seed);
            BSTInventoryIndex bst = new BSTInventoryIndex();
            String resultBST = AlienX(i, "BST", bst, tipoOperación, m);
            cuchurrumin.println(resultBST);

           
            tipoOperación = DataGenerator.generateOperations(m, keyUniverse, seed); 
            
            RedBlackBSTInventoryIndex rbBST = new RedBlackBSTInventoryIndex();
            String resultRbBST = AlienX(i, "RedBlackBST", rbBST, tipoOperación, m);
            cuchurrumin.println(resultRbBST);
            
        }
        
       
        cuchurrumin.close();
    }
    
}
}

//YUPI = BUENO QUE SI LO TENGO | CARACOLITOS = MALO QUE NO LO TENGO