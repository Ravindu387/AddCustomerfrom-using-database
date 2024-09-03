package org.example.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.model.Customer;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;

public class AddCustomeerFormController {

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnOnActionAdd(ActionEvent event) {
        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText()
        );

        try {
            String SQL="insert into customer values(?,?,?)";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liteshop", "root", "1234");
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, txtId.getText());
            pstmt.setString(2, txtName.getText());
            pstmt.setString(3, txtAddress.getText());

            boolean b = pstmt.executeUpdate() > 0;
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Customer Added!").show();
            }
            System.out.println("Data inserted successfully");


            System.out.println(customer);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clearTxt();
  }
    @FXML
    void btnOnActionBack(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/deleteCustomerForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  public void clearTxt(){
        txtAddress.setText("");
        txtName.setText("");
        txtId.setText("");
  }
}
