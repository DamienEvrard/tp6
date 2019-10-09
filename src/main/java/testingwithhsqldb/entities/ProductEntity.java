/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingwithhsqldb.entities;

/**
 *
 * @author damie
 */
public class ProductEntity {
    private int ID;
    private String Name;
    private double Price;
    
    public ProductEntity(int id, String name, double price){
        this.ID=id;
        this.Name=name;
        this.Price=price;
    }
    
    public int getId(){
        return this.ID;
    }
    
    public String getName(){
        return this.Name;
    }
    
    public double getPrice(){
        return this.Price;
    }
}
