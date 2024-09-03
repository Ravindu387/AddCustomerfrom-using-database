package org.example.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.*;

public class DeleteCustomerFormController {

    public JFXTextArea txtArea;
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
                txtArea.setText(
                        "Customer ID : "+id+
                        "\n\nCustomer Name : "+name+
                        "\n\nCustomer Address : "+address
                );

                System.out.println(id+","+name+","+address);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void btnOnActionDelete(ActionEvent event) {
        try {
            String SQL="delete from customer where id='" + txtSearch.getText() + "'";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liteshop", "root", "1234");
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            boolean b = pstmt.executeUpdate() > 0;
            if(b){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted..!").show();
            }
            System.out.println("Data inserted successfully");




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clearTxt();
    }
    public void clearTxt(){
        txtArea.setText("");
    }

}
