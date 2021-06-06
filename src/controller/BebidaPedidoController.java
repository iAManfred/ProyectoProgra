/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Model.Beverage;
import Model.CondimentDecorator;
import Model.Milk;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author manfr
 */
public class BebidaPedidoController implements Initializable {

    @FXML
    private Button btnElim;
    @FXML
    private MenuButton btnBebida;
    @FXML
    private MenuItem btnb1;
    @FXML
    private MenuItem btnb2;
    @FXML
    private MenuItem btnb3;
    @FXML
    private MenuItem btnb4;
    @FXML
    private Button btnAnadir;
    @FXML
    private TextField txtCant;
    @FXML
    private Button btnCancelarPedido;
    @FXML
    private Button btnVerPedidos;
    @FXML
    private Button btnRealizarPedido;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label labelBeverage;
    @FXML
    private Label lebelExtras;
    @FXML
    private Label labelQuantity;
    @FXML
    private ToggleButton btnMilk;
    @FXML
    private ToggleButton btnMocha;
    @FXML
    private ToggleButton btnSoy;
    @FXML
    private ToggleButton btnWhip;
    @FXML
    private ToggleButton btnCandy;
    @FXML
    private TextArea txtArea;

    ToggleButton[] vectorBotones = new ToggleButton[5];

    public static controller con = new controller();

    @FXML
    private TextField modi;

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vectorBotones[0] = this.btnMilk;
        vectorBotones[1] = this.btnMocha;
        vectorBotones[2] = this.btnSoy;
        vectorBotones[3] = this.btnWhip;
        vectorBotones[4] = this.btnCandy;
    }

    @FXML
    private void eliminar(ActionEvent event) {
        int borrar = Integer.parseInt(this.modi.getText());

        con.getList().remove(borrar);
        this.txtArea.setText(con.revisaOrden());

        if (con.getList().isEmpty()) {
            this.btnRealizarPedido.setDisable(true);
        }
    }

    @FXML
    private void bebida1(ActionEvent event) {
        this.btnBebida.setText("House Blend");
    }

    @FXML
    private void bebida2(ActionEvent event) {
        this.btnBebida.setText("Dark Roast");
    }

    @FXML
    private void bebida3(ActionEvent event) {
        this.btnBebida.setText("Espresso");
    }

    @FXML
    private void bebida4(ActionEvent event) {
        this.btnBebida.setText("Decaf");
    }

    @FXML
    private void anadir(ActionEvent event) {

        int veces = Integer.parseInt(this.txtCant.getText());

        if (!"Select a beverage".equals(this.btnBebida.getText())) {

            for (int i = 0; i < veces; i++) {

                con.setKeep(con.CrearBebida(this.btnBebida.getText()));

                for (ToggleButton vectorBotone : vectorBotones) {
                    if (vectorBotone.isSelected()) {
                        con.CrearDecorador(vectorBotone.getText(), con.getKeep());
                    }
                }

                con.getList().add(con.getKeep());

                this.txtArea.setText(con.revisaOrden());
            }

        } else {
            con.Alert();
        }

        this.btnRealizarPedido.setDisable(false);
    }

    @FXML
    private void CancelarPedido(ActionEvent event) {
        con.getList().clear();
        this.txtArea.setText(null);
        this.btnRealizarPedido.setDisable(true);
    }

    @FXML
    private void verPedidos(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/View/Pedidos.fxml"));

        Scene scene = new Scene(root);
        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void realizarPedido(ActionEvent event) {
        con.CrearOrden();
        this.txtArea.setText(null);
        this.btnRealizarPedido.setDisable(true);
        con.CrearLista();
    }

}
