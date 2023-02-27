package repository;

import config.SqliteConnection;
import model.Activity;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ActivityRepository {

    private static final String table = "activity_log";

    public Activity insert(Activity activity) {

        ResultSet resultSet = null;
        String sql = new StringBuilder("INSERT INTO ")
                .append(table)
                .append(" (date,cashier_identifier,movement,detail,comment,cashier_name)")
                .append(" VALUES(?,?,?,?,?,?)")
                .toString();

        try (Connection conn = SqliteConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, activity.getDate());
            preparedStatement.setString(2, activity.getCashier_identifier());
            preparedStatement.setString(3, activity.getMovement());
            preparedStatement.setInt(4, activity.getDetail());
            preparedStatement.setString(5, activity.getComment());
            preparedStatement.setString(6, activity.getCashier_name());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                activity.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return activity;
    }

    public List<Activity> selectAllByCashier(String cashierIdentifier) {
        String sql = new StringBuilder("SELECT date,id,cashier_identifier,movement,detail,comment,cashier_name FROM ")
                .append(table)
                .append(" WHERE cashier_identifier = ?")
                .toString();
        List<Activity> result = new ArrayList<>();
        ResultSet resultSet = null;

        try (Connection conn = SqliteConnection.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, cashierIdentifier);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(
                        new Activity(
                                resultSet.getString("date"),
                                resultSet.getInt("id"),
                                resultSet.getString("movement"),
                                resultSet.getInt("detail"),
                                resultSet.getString("comment"),
                                resultSet.getString("cashier_name"),
                                resultSet.getString("cashier_identifier"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
