package app.persistence;

import app.entities.Bmi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<Bmi> getAllBmiEntries() throws SQLException {

        List<Bmi> bmiList = new ArrayList<>();

        String sql = "SELECT weight, height, bmi, name, verdict, created FROM bmi";

        try (Connection connection =  connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);)
        {
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int weight = rs.getInt("weight");
                int height =  rs.getInt("height");
                double bmi = rs.getDouble("bmi");
                String name = rs.getString("name");
                String verdict = rs.getString("verdict");
                LocalDateTime created = rs.getTimestamp("created").toLocalDateTime();
                bmiList.add(new Bmi(height, weight, bmi, name, verdict, created));
            }
        }
        return bmiList;
    }
}
