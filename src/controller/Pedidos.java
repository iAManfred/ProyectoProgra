/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.BebidaPedidoController.con;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author manfr
 */
public class Pedidos implements Initializable {

    @FXML
    private TextField txtFiltroCodigo;
    @FXML
    private MenuButton filtroEstado;
    @FXML
    private MenuItem filtroListo;
    @FXML
    private MenuItem filtroEspera;
    @FXML
    private MenuItem filtroProceso;
    @FXML
    private MenuItem filtroTodos;
    @FXML
    private MenuButton cambiarEstado;
    @FXML
    private MenuItem cambiarEstadoListo;
    @FXML
    private MenuItem cambiarEstadoEspera;
    @FXML
    private MenuItem cambiarEstadoProceso;
    @FXML
    private TextArea txtDesc;
    @FXML
    private Label labelFilter;
    @FXML
    private Label labelFilterStatus;
    @FXML
    private Label OrderInfo;
    @FXML
    private TextArea txtGeneralOrders;
    @FXML
    private Button btnSearch;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     this.txtGeneralOrders.setText(con.revisarOrdenes());
    
    
    }    



    @FXML
    private void filtrarListo(ActionEvent event) {
    this.txtGeneralOrders.setText(con.FiltraOrdenes(this.filtroListo.getText())); 
    }

    @FXML
    private void filtrarEspera(ActionEvent event) {
    this.txtGeneralOrders.setText(con.FiltraOrdenes(this.filtroEspera.getText()));
    }

    @FXML
    private void filtrarProceso(ActionEvent event) {
    this.txtGeneralOrders.setText(con.FiltraOrdenes(this.filtroProceso.getText()));
    }

    @FXML
    private void filtrarTodo(ActionEvent event) {
    this.txtGeneralOrders.setText(con.FiltraOrdenes(this.filtroTodos.getText()));
    }

    @FXML
    private void cambiarEstadoListo(ActionEvent event) {
    int filtro=Integer.parseInt(this.txtFiltroCodigo.getText());
 
        con.getListOrders().get(filtro).setConditioon(this.cambiarEstadoListo.getText());
    
    
    
    }

    @FXML
    private void cambiarEstadoEspera(ActionEvent event) {
   int filtro=Integer.parseInt(this.txtFiltroCodigo.getText());
    
        con.getListOrders().get(filtro).setConditioon(this.cambiarEstadoEspera.getText());
    } 

    @FXML
    private void cambiarEstadoProceso(ActionEvent event) {
    int filtro=Integer.parseInt(this.txtFiltroCodigo.getText());
    
        con.getListOrders().get(filtro).setConditioon(this.cambiarEstadoProceso.getText());
    }



    @FXML
    private void filtrarNombre(ActionEvent event) {
     int filtro=Integer.parseInt(this.txtFiltroCodigo.getText());
        
     this.txtGeneralOrders.setText(con.FiltrarOrden(filtro));
    this.txtDesc.setText(con.getListOrders().get(filtro).revisaOrden());
        
    }
    
}
