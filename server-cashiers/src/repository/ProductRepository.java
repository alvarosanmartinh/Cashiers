package repository;

import config.SqliteConnection;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductRepository {

    private static final String table = "product";

    public Product insert(Product product) {

        ResultSet resultSet = null;
        String sql = new StringBuilder("INSERT INTO ")
                .append(table)
                .append(" (name,price,stock,reserved_stock)")
                .append(" VALUES(?,?,?,?)")
                .toString();

        try (Connection conn = SqliteConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setInt(4, product.getReserved_stock());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    public Product update(Product product) {

        String sql = new StringBuilder("UPDATE ")
                .append(table)
                .append(" SET ")
                .append(" name = ?,price = ?,stock = ?,reserved_stock = ?")
                .append(" WHERE id = ?")
                .toString();

        try (Connection conn = SqliteConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setInt(4, product.getReserved_stock());
            preparedStatement.setInt(5, product.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 1){
            } else if (affectedRows > 1){
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product;
    }

    public void delete(Product product) {


        String sql = new StringBuilder("DELETE FROM ")
                .append(table)
                .append(" WHERE id = ?")
                .toString();

        try (Connection conn = SqliteConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, product.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<Product> selectAll() {
        String sql = new StringBuilder("SELECT id,name,price,stock,reserved_stock FROM ")
                .append(table)
                .toString();
        List<Product> result = new ArrayList<>();

        try (Connection conn = SqliteConnection.connect();
             Statement statement  = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                result.add(
                        new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("price"),
                            resultSet.getInt("stock"),
                            resultSet.getInt("reserved_stock"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
