package model;

public class Product {


    int id;
    String name;
    int price;
    int stock;
    int reserved_stock;

    public Product() {
    }
    public Product(int id, String name, int price, int stock, int reserved_stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.reserved_stock = reserved_stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getReserved_stock() {
        return reserved_stock;
    }

    public void setReserved_stock(int reserved_stock) {
        this.reserved_stock = reserved_stock;
    }
}
