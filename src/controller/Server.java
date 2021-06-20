/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Beverage1;
import Model.DAOConnection;
import Model.DAOforBeverage1;
import Model.DAOforOrders;
import Model.Orders1;
import static controller.controller.codeBeverage;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manfr
 */
public class Server implements DAOConnection {

    private List<Beverage1> list = new ArrayList<Beverage1>();
    
    
    
    public Server(){
    new Thread(() -> {try {
        openServer();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
}).start();
    
    }
    
    @Override
    public void openServer() throws IOException, ClassNotFoundException, Exception {
     
        
        Socket socket = null;

        ServerSocket ss = new ServerSocket(222);
        socket = ss.accept();

        System.out.println("Server: Connected with client");
        InputStream input = socket.getInputStream();
        ObjectInputStream object = new ObjectInputStream(input);
        Orders1 order = (Orders1) object.readObject();
          
        setList(order.getListDrinks());

        order.setListDrinks(null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoProgra3Parte1PU");
        DAOforOrders jpa = new DAOforOrders(emf);
     
        jpa.create(order);

        for (int i = 0; i < list.size(); i++) {
            codeBeverage = codeBeverage + 1;

            DAOforBeverage1 jpaB = new DAOforBeverage1(emf);

            Beverage1 beverage = new Beverage1(codeBeverage, list.get(i).getDescription());
            beverage.setOrderName(order);

            jpaB.create(beverage);

        }
        System.out.println("Server: Order added and ending connection");
        ss.close();
        socket.close();

    }

    public void setList(List<Beverage1> list) {
        this.list = list;
    }

}
