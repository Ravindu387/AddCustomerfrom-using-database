package org.example.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.example.model.Customer;

import java.sql.*;

public class UpdateCustomerFormController {

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    void btnOnActionSearch(ActionEvent event) {
        try {
            String SQL="select * from customer where id='" + txtSearch.getText() + "'";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liteshop", "root", "1234");
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                txtId.setText(id);
                txtName.setText(name);
                txtAddress.setText(address);

                System.out.println(id+","+name+","+address);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        try {
            String SQL="UPDATE customer SET id = ?,name=?,address=? WHERE id ='" + txtSearch.getText() +"' ";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liteshop", "root", "1234");
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setString(1, txtId.getText());
            pstmt.setString(2, txtName.getText());
            pstmt.setString(3, txtAddress.getText());

            boolean b = pstmt.executeUpdate() > 0;
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Customer update suceessfully!").show();
            }
            System.out.println("Data inserted successfully");



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clearTxt();
    }
    public void clearTxt(){
        txtAddress.setText("");
        txtName.setText("");
        txtId.setText("");
    }

}
