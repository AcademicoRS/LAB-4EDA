


class InventoryItem{

private int id;
private String name;
private String category;
private String location;
private int stockTotal;
private int stockAvailable;
private int stockOnLoan;


public InventoryItem (int id ,String name ,String category ,String location ,int stockTotal ,int stockAvailable ,int stockOnLoan)
{

    this.id = id;
    this.name = name;
    this.category = category;
    this.location = location;
    this.stockTotal = stockTotal;
    this.stockAvailable = stockAvailable;
    this.stockOnLoan = stockOnLoan;

}

public int getId() {return id;}
public String getname(){return name;}
public String getcategory(){return category;}
public String getlocation(){return location;}
public int getstockTotal(){return stockTotal;}
public int getstockAvaiable(){return stockAvailable;}
public int getstockOnLoan(){return stockOnLoan;}


//AHI ESTAN TUS CAGAS DE GETTERS RICK TE VOY A PEGAR

public void addStock (int itemId , int quantity ){

}


public boolean lend (int itemId , int quantity ){

    return true;
}

public boolean receive (int itemId , int quantity ){

    return true; //ojito piojito esto es para que no tire error de compilacion. Lo tenemos que ajustar mas ratito
}



public static void main(String[] args) {

}
}