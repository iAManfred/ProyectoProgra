/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Alert2;
import Model.Beverage1;
import Model.Candy;
import Model.DAOConnection;
import Model.DarkRoast;
import Model.Decaf;
import Model.Espresso;
import Model.HouseBlend;
import Model.Milk;
import Model.Mocha;
import Model.Orders1;
import Model.Soy;
import Model.Whip;
import javafx.scene.control.Alert;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author manfr
 */
public class controller {

    private Beverage1 beverage1;
    private Beverage1 keep;
    private int code = 0;
    private Orders1 orders;

    public Orders1 getOrders() {
        return orders;
    }

    public void setOrders(Orders1 orders) {
        this.orders = orders;
    }

    private List<Orders1> listOrders = new ArrayList<Orders1>();
    private List<Beverage1> list = new ArrayList<Beverage1>();

    public static int codeBeverage = 0;

    public void setList(List<Beverage1> list) {
        this.list = list;
    }

    public void CrearLista() {
        List<Beverage1> newList = new ArrayList<Beverage1>();
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
            //   f = f + listOrders.get(i).orders() + " " + ",Order: " + i + "\n";

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

    public List<Beverage1> getList() {
        return list;
    }

    public List<Orders1> getListOrders() {
        return listOrders;
    }

    public Beverage1 getKeep() {
        return keep;
    }

    public void setKeep(Beverage1 keep) {
        this.keep = keep;
    }

    public Beverage1 getBeverage1() {
        return beverage1;
    }

    public Beverage1 CrearBebida(String beverage) {

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

    public Beverage1 CrearDecorador(String nom, Beverage1 b) {

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

    public void CrearOrden() throws Exception {

        code = code + 1;
        String name = Integer.toString(code);

        DAOConnection server1 = new Server();
        Client2 client = new Client2();

      
        Orders1 order = new Orders1(name, "Pending");
        order.setListDrinks(list);

        
      new Thread(() -> {
             try {
                 client.Client2(order);
             } catch (Exception ex) {
                 Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
             } }).start();
      
      
         
        listOrders.add(order);

    }

    public String FiltraOrdenes(String f) {
        String l = "";
        switch (f) {

            case "All": {

                for (int i = 0; i < listOrders.size(); i++) {

                    l = l + listOrders.get(i).orders() + " " + ",Order: " + i + "\n";
                    System.out.println(listOrders.get(i).getConditioon());
                }

                break;
            }

            case "Complete": {

                for (int i = 0; i < listOrders.size(); i++) {
                    if (listOrders.get(i).getConditioon().equals("Complete")) {

                        l = l + listOrders.get(i).orders() + " " + ",Order: " + i + "\n";

                    }
                }

                break;
            }
            case "Pending": {

                for (int i = 0; i < listOrders.size(); i++) {
                    if (listOrders.get(i).getConditioon().equals("Pending")) {

                        l = l + listOrders.get(i).orders() + " " + ",Order: " + i + "\n";
                    }
                }

                break;
            }
            case "In Process": {
                for (int i = 0; i < listOrders.size(); i++) {
                    if (listOrders.get(i).getConditioon().equals("In Process")) {
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
        for (Orders1 listOrder : listOrders) {
            if (listOrders.get(num) != null) {
                listOrders.get(num).setConditioon(estado);

            }
        }

    }

}
