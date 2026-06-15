class InventoryItem {

    private int id;
    private String name;
    private String category;
    private String location;
    private int stockTotal;
    private int stockAvailable;
    private int stockOnLoan;

   
    public InventoryItem(int id, String name, String category, String location, int stockTotal, int stockAvailable, int stockOnLoan) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
        this.stockTotal = stockTotal;
        this.stockAvailable = stockAvailable;
        this.stockOnLoan = stockOnLoan;
    }
 
    public int getId() { return id; }
    public String getname() { return name; }
    public String getcategory() { return category; }
    public String getlocation() { return location; }
    public int getstockTotal() { return stockTotal; }
    public int getstockAvailable() { return stockAvailable; }
    public int getstockOnLoan() { return stockOnLoan; }

    public void addStock(int key, int quantity) {
        if (quantity > 0) {
            this.stockTotal += quantity;
            this.stockAvailable += quantity;
        }
    }

  
  
    public boolean receive(int key, int quantity) {
        if (quantity <= 0) return false;
        
  
        this.stockAvailable += quantity;
        this.stockOnLoan -= quantity;
        
  
        if (this.stockOnLoan < 0) {
            this.stockOnLoan = 0;
        }
        
        return true;            
    }

    
    
    public boolean lend(int key, int quantity) {
        if (quantity <= 0) return false;
        
    
        if (this.stockAvailable >= quantity) { 
            this.stockAvailable -= quantity;
            this.stockOnLoan += quantity;
            return true;              
        }
        
        return false;                
    }
}