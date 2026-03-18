package app.persistence;

import app.entities.Bmi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BmiMapper {

    private final ConnectionPool connectionPool;

    public BmiMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void createBmi(Bmi bmi) throws SQLException {

        String sql = "INSERT INTO bmi (height, weight, bmi, name, verdict, created) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection =  connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);)
        {
            ps.setInt(1, bmi.getHeight());
            ps.setInt(2, bmi.getWeight());
            ps.setDouble(3, bmi.getBmi());
            ps.setString(4, bmi.getName());
            ps.setString(5, bmi.getVerdict());
            ps.setObject(6, bmi.getCreated());
            ps.executeUpdate();
        }
    }
}
