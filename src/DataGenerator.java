import edu.princeton.cs.algs4.StdRandom;
import java.util.ArrayList;
import java.util.HashSet;

public class DataGenerator {

    private static final String[] CATEGORIES = {
        "Sensor", "Motor", "Microcontrolador", "Cable", 
        "Bateria", "Herramienta", "Modulo", "Kit"
    };
    
    private static final String[] LOCATIONS = {
        "Estante_A", "Estante_B", "Caja_1", "Caja_2", 
        "Laboratorio", "Bodega"
    };

    public static InventoryItem generateItem(int id) {
        String nombre = "Componente " + id;

        String categoria = CATEGORIES[StdRandom.uniformInt(CATEGORIES.length)]; 
        String ubi = LOCATIONS[StdRandom.uniformInt(LOCATIONS.length)];
        
        int stockTotal = StdRandom.uniformInt(1, 21); 
        int stockDisponible = stockTotal;
        int stockActualPrestado = 0;

        return new InventoryItem(id, nombre, categoria, ubi, stockTotal, stockDisponible, stockActualPrestado);
    }

public static ArrayList<InventoryOperation> generateOperations(int m, int keyUniverse, long seed) { 
        
        StdRandom.setSeed(seed);
        
        ArrayList<InventoryOperation> operations = new ArrayList<>(m);
        
        HashSet<Integer> condfio = new HashSet<>(); // Creo que habia una mejor forma de hacer esto sin HashSet, creo que no los dijo el profe, no recuerdo bien asi que ricky confio en ti que lo tienes apuntado en algun lugar.
        
        for (int i = 0; i < m; i++) {
            
            int key = StdRandom.uniformInt(1, keyUniverse + 1); 
            
            double p = StdRandom.uniformDouble(); 
            
            OperationType type;
            int cantidad = 0;
            InventoryItem item = null;
            
            if (p < 0.35) { 
                type = OperationType.PURCHASE;
                cantidad = StdRandom.uniformInt(1, 6);
                
                if (!condfio.contains(key)) {
                    item = generateItem(key);
                    condfio.add(key); 
                } else {
                    item = null;
                }
                
            } else if (p < 0.65) { 
                type = OperationType.QUERY;
                cantidad = 0;
                
            } else if (p < 0.80) { 
                type = OperationType.LEND;
                cantidad = StdRandom.uniformInt(1, 6); 
                
            } else if (p < 0.90) { 

                type = OperationType.RECEIVE;
                cantidad = StdRandom.uniformInt(1, 6); 
                
            } else { 
                type = OperationType.DISPOSE;
                cantidad = 0; 
                
                condfio.remove(key); 
            }
            
            operations.add(new InventoryOperation(type, key, cantidad, item));
        }
        
        return operations;
    }
}