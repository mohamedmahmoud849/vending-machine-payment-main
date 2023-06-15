package com.vodafone.payment.repository;

import com.vodafone.payment.model.CreditCard;

import java.sql.*;

public class CardPaymentRepo {
    private final String DB_URL = "jdbc:mysql://localhost:3306/card_db";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "P@ssw0rd";
    public CreditCard getCardInformation(String cardNumber)  {

       /* try {*/
       /*     Class.forName("com.mysql.jdbc.Driver");*/
       /* } catch (ClassNotFoundException e) {*/
       /*     throw new RuntimeException(e);*/
       /* }*/

        // Prepare the SQL statement
        String sql = "SELECT cardNumber, balance FROM CardInformation WHERE cardNumber = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/card_db", "root", "P@ssw0rd");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the cardNumber parameter
            stmt.setString(1, cardNumber);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Check if a record was found
            if (rs.next()) {
                // Card information exists
                String retrievedCardNumber = rs.getString("cardNumber");
                int balance = rs.getInt("balance");

                // Create and return a CreditCard object
                return new CreditCard(retrievedCardNumber, balance);
            } else {
                // Card information not found
                return null;
            }

        } catch (SQLException e) {
            // Handle any errors that occurred during the execution
            e.printStackTrace();
        }

        return null;
    }
    public void modifyCardBalance(String cardNumber, int newBalance) {
        // Prepare the SQL statement
        String sql = "UPDATE CardInformation SET balance = ? WHERE cardNumber = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters
            stmt.setInt(1, newBalance);
            stmt.setString(2, cardNumber);

            // Execute the update
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                // No rows were affected, card information may not exist
                System.out.println("Card information not found.");
            } else {
                System.out.println("Card balance updated successfully.");
            }

        } catch (SQLException e) {
            // Handle any errors that occurred during the execution
            e.printStackTrace();
        }
    }

}
