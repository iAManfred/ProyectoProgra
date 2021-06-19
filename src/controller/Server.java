/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Beverage1;
import Model.Beverage1JpaController;
import Model.Orders1;
import Model.Orders1JpaController1;
import static controller.controller.codeBeverage;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author manfr
 */
public class Server {
    private List<Beverage1> list = new ArrayList<Beverage1>();
    
    public void openServer() throws IOException, ClassNotFoundException, Exception{
        Socket socket = null;
         
          
          
                ServerSocket ss = new ServerSocket(222);
            socket = ss.accept();
            
            System.out.println("Connected");
            InputStream input = socket.getInputStream();
            ObjectInputStream object = new ObjectInputStream(input);
             Orders1 order= (Orders1)object.readObject();
             
           setList(order.getListDrinks());
             
             order.setListDrinks(null);
    EntityManagerFactory emf= Persistence.createEntityManagerFactory("ProyectoProgra3Parte1PU");
       Orders1JpaController1 jpa= new  Orders1JpaController1(emf);   
       PrintStream ps= new PrintStream(socket.getOutputStream());
       ps.println("Added");
        jpa.create(order);
    
    
        
         for(int i=0;i<list.size();i++){
           codeBeverage=codeBeverage+1;
           
            Beverage1JpaController jpaB= new  Beverage1JpaController(emf);
            
            Beverage1 beverage= new Beverage1(codeBeverage,list.get(i).getDescription());
            beverage.setOrderName(order);
    

         jpaB.create(beverage);
        
     }
        
         ss.close();
             socket.close();
    
    }

    public void setList(List<Beverage1> list) {
        this.list = list;
    }



}