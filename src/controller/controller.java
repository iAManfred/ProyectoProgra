/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Alert2;
import Model.Beverage;
import Model.Candy;
import Model.DarkRoast;
import Model.Decaf;
import Model.Espresso;
import Model.HouseBlend;
import Model.Milk;
import Model.Mocha;
import Model.Orders;
import Model.Soy;
import Model.Whip;
import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manfr
 */
public class controller {

    private Beverage beverage1;
    private Beverage keep;
    
    private List<Orders> listOrders = new ArrayList<Orders>();    
    private List<Beverage> list = new ArrayList<Beverage>();
    
    public void setList(List<Beverage> list) {
        this.list = list;
    }

    public void CrearLista() {
        List<Beverage> newList = new ArrayList<Beverage>();
        setList(newList);
    }
    
    public String revisarOdenesFiltro(String l) {
        String f = "";
        
        for (int i = 0; i < listOrders.size(); i++) {
            f = f + list.get(i).getDescription() + " " + ",Order: " + i + "\n";
            
        }
        return f;
    }
    
    public String revisarOrdenes() {
        String f = "";
        for (int i = 0; i < listOrders.size(); i++) {
            f = f + listOrders.get(i).orders() + " " + ",Order: " + i + "\n";
            
        }
        return f;
    }
    
    public String revisaOrden() {
        String f = "";
        for (int i = 0; i < list.size(); i++) {
            f = f + list.get(i).getDescription() + " " + ",Order detail: " + i + "\n";
            
        }
        
        return f;
    }
    
    public List<Beverage> getList() {
        return list;
    }
    
    public List<Orders> getListOrders() {
        return listOrders;
    }
    
    public Beverage getKeep() {
        return keep;
    }
    
    public void setKeep(Beverage keep) {
        this.keep = keep;
    }

    public Beverage getBeverage1() {
        return beverage1;
    }
    
    public Beverage CrearBebida(String beverage) {
        
        switch (beverage) {
            case "House Blend": {
                beverage1 = new HouseBlend();
                
                break;
            }
            case "Dark Roast": {
                beverage1 = new DarkRoast();
                
                break;
            }
            case "Espresso": {
                beverage1 = new Espresso();
                
                break;
            }
            case "Decaf": {
                beverage1 = new Decaf();
                
                break;
            }
        }
        
        return beverage1;
    }
    
    public void Alert() {
        Alert2 a = new Alert2();
        Alert2.showAlert("Error", "Falta la bebida base", Alert.AlertType.ERROR);
    }
    
    public Beverage CrearDecorador(String nom, Beverage b) {
        
        switch (nom) {
            case "Milk": {
                beverage1 = new Milk(b);
                
                break;
            }
            case "Mocha": {
                beverage1 = new Mocha(b);
                
                break;
            }
            case "Soy": {
                beverage1 = new Soy(b);
                
                break;
            }
            case "Whip": {
                beverage1 = new Whip(b);
                
                break;
            }
            case "Candy": {
                beverage1 = new Candy(b);
                
                break;
            }
        }
        
        setKeep(beverage1);
        
        return beverage1;
    }
    
    public Orders CrearOrden() {
        Orders order = new Orders();
        order.setListDrinks(list);
        listOrders.add(order);
        
        return order;
    }
    
    public String FiltraOrdenes(String f) {
        String l = "";
        switch (f) {            
            
            case "All": {
                
                for (int i = 0; i < listOrders.size(); i++) {
                    
                    l = l + listOrders.get(i).orders() + " " + ",Order: " + i + "\n";
                    System.out.println(listOrders.get(i).getCondition());
                }
                
                break;
            }
            
            case "Complete": {
                
                for (int i = 0; i < listOrders.size(); i++) {
                    if (listOrders.get(i).getCondition().equals("Complete")) {                        
                        
                        l = l + listOrders.get(i).orders() + " " + ",Order: " + i + "\n";
                        
                    }
                }
                
                break;
            }
            case "Pending": {
                
                for (int i = 0; i < listOrders.size(); i++) {
                    if (listOrders.get(i).getCondition().equals("Pending")) {                        
                        
                        l = l + listOrders.get(i).orders() + " " + ",Order: " + i + "\n";
                    }
                }
                
                break;
            }
            case "In Process": {
                for (int i = 0; i < listOrders.size(); i++) {
                    if (listOrders.get(i).getCondition().equals("In Process")) {                        
                        l = l + listOrders.get(i).orders() + " " + ",Order: " + i + "\n";
                    }
                }
                
                break;
            }
            
        }
        
        return l;
    }
    
    public String FiltrarOrden(int num) {
        String l = "Not Found";
        l = listOrders.get(num).orders();
        
        return l;
    }
    
    public void ActualizarEstado(int num, String estado) {
        for (Orders listOrder : listOrders) {
            if (listOrders.get(num) != null) {
                listOrders.get(num).setCondition(estado);
                
            }
        }
        
    }
    
}
