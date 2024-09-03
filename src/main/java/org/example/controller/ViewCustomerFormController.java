package org.example.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Customer;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ViewCustomerFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> coId;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    void btnOnactionReload(ActionEvent event) {
        coId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        ObservableList<Customer> observableArrayList = FXCollections.observableArrayList();

        try {
            String SQL="select * from customer";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liteshop", "root", "1234");
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                Customer customer=new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address")
                );
                observableArrayList.add(customer);
            }
            tblCustomer.setItems(observableArrayList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void loadTable(){
        coId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        ObservableList<Customer> observableArrayList = FXCollections.observableArrayList();

        try {
            String SQL="select * from customer";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liteshop", "root", "1234");
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                Customer customer=new Customer(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address")
                );
                observableArrayList.add(customer);
            }
            tblCustomer.setItems(observableArrayList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }



}
