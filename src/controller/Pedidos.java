/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Orders1;
import static controller.BebidaPedidoController.con;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    private Button btnSearch;
    @FXML
    private AnchorPane anchor;
    @FXML
    private TableView<Orders1> tableOrders;

    @FXML
    private TableColumn<Orders1, String> columCode;
    @FXML
    private TableColumn<Orders1, String> columCondition;

    private ObservableList<Orders1> filter = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    private ObservableList<Orders1> items;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // this.txtGeneralOrders.setText(con.revisarOrdenes());

        items = FXCollections.observableArrayList(con.getListOrders());
        tableOrders.setItems(items);
        columCondition.setCellValueFactory(new PropertyValueFactory<Orders1, String>("conditioon"));
        columCode.setCellValueFactory(new PropertyValueFactory<Orders1, String>("name"));

    }

    @FXML
    private void filtrarListo(ActionEvent event) {
        
        filtrarEstado(this.filtroListo.getText());
        items = FXCollections.observableArrayList(con.getListOrders());
        this.tableOrders.setItems(filter);
    }

    @FXML
    private void filtrarEspera(ActionEvent event) {
       
        filtrarEstado(this.filtroEspera.getText());
        items = FXCollections.observableArrayList(con.getListOrders());
        this.tableOrders.setItems(filter);

    }

    @FXML
    private void filtrarProceso(ActionEvent event) {
        
        filtrarEstado(this.filtroProceso.getText());
        items = FXCollections.observableArrayList(con.getListOrders());
        this.tableOrders.setItems(filter);

    }

    @FXML
    private void filtrarTodo(ActionEvent event) {
        items = FXCollections.observableArrayList(con.getListOrders());
        this.tableOrders.setItems(items);
    }

    @FXML
    private void cambiarEstadoListo(ActionEvent event) {
        changeList(this.cambiarEstadoListo.getText());
        changeEstate(this.cambiarEstadoListo.getText());
        items = FXCollections.observableArrayList(con.getListOrders());
        this.tableOrders.setItems(items);
    }

    @FXML
    private void cambiarEstadoEspera(ActionEvent event) {
        
        changeList(this.cambiarEstadoEspera.getText());

        changeEstate(this.cambiarEstadoEspera.getText());
        items = FXCollections.observableArrayList(con.getListOrders());
        this.tableOrders.setItems(items);
    }

    @FXML
    private void cambiarEstadoProceso(ActionEvent event) {
   
        changeList(this.cambiarEstadoProceso.getText());
        changeEstate(this.cambiarEstadoProceso.getText());
        items = FXCollections.observableArrayList(con.getListOrders());
        this.tableOrders.setItems(items);

    }

    @FXML
    private void filtrarNombre(ActionEvent event) {
        items = FXCollections.observableArrayList(con.getListOrders());

        filtrarNumero(this.txtFiltroCodigo.getText());
        this.tableOrders.setItems(filter);
    }

    private void filtrarEstado(String estado) {
        filter.clear();
        for (Orders1 p : this.items) {
            if (p.getConditioon().toLowerCase().contains(estado.toLowerCase())) {
                filter.add(p);
            }
        }

    }

    private void filtrarNumero(String estado) {
        filter.clear();
        for (Orders1 p : this.items) {
            if (p.getName().toLowerCase().contains(estado.toLowerCase())) {
                filter.add(p);
            }
        }

    }

    @FXML
    private void Select(MouseEvent event) {
        con.setOrders(this.tableOrders.getSelectionModel().getSelectedItem());
        this.txtDesc.setText(con.getOrders().revisaOrden());
    }

    private void changeEstate(String n) {

        for (Orders1 p : this.items) {
            if (p == con.getOrders()) {
                p.setConditioon(n);
            }
        }
    }

    public void changeList(String n) {

        for (Orders1 p : con.getListOrders()) {
            if (p == con.getOrders()) {
                p.setConditioon(n);
            }

        }

    }
}
