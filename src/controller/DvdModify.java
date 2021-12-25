package controller;
import models.Dvd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DvdModify {
    //Lay du lieu tu db
    public static List<Dvd> findALl() throws SQLException {
        List<Dvd> dvdList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            //Lay tat ca sach
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "select * from dvd";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                Dvd dvd = new Dvd(resultSet.getInt("id"),resultSet.getString("title"),
                        resultSet.getString("author"),resultSet.getInt("public_year"),
                        resultSet.getString("publisher"),resultSet.getInt("import_price"),
                        resultSet.getInt("export_price"),resultSet.getBoolean("status"),
                        resultSet.getInt("size"),resultSet.getInt("duration"));
                dvdList.add(dvd);
            }

        } catch(SQLException ex) {
            Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
        return dvdList;
    }

    public static void insert(Dvd dvd) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca sach
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "insert into dvd(title, author, public_year, publisher, import_price, export_price, size, duration) values(?, ? ,?, ?, ? ,?, ?, ?)";

            statement = connection.prepareCall(sql);

            statement.setString(1, dvd.getTitle());
            statement.setString(2, dvd.getAuthor());
            statement.setInt(3, dvd.getPublicYear());
            statement.setString(4, dvd.getPublisher());
            statement.setInt(5, dvd.getImportPrice());
            statement.setInt(6, dvd.getExportPrice());
            statement.setInt(7, dvd.getSize());
            statement.setInt(8, dvd.getDuration());

        } catch(SQLException ex) {
            Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    public static void update(Dvd dvd) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca sach
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "update dvd set title=?, author=?, public_year=?, publisher=?, import_price=?, export_price=?, size=?, duration=? where id=?";

            statement = connection.prepareCall(sql);

            statement.setString(1, dvd.getTitle());
            statement.setString(2, dvd.getAuthor());
            statement.setInt(3, dvd.getPublicYear());
            statement.setString(4, dvd.getPublisher());
            statement.setInt(5, dvd.getImportPrice());
            statement.setInt(6, dvd.getExportPrice());
            statement.setInt(7, dvd.getSize());
            statement.setInt(8, dvd.getDuration());
            statement.setInt(9, dvd.getId());


        } catch(SQLException ex) {
            Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    public static void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca sach
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "delete from dvd  where id=?";

            statement = connection.prepareCall(sql);

            statement.setInt(1, id);


        } catch(SQLException ex) {
            Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdModify.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }
}
