package service.impl;

import controller.request.dvd.SearchDvdRequest;
import models.Dvd;
import service.DvdService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DvdServiceImpl implements DvdService {

    @Override
    public List<Dvd> findAll() {
        List<Dvd> dvdList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "select * from dvd";
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                Dvd dvd = new Dvd(resultSet.getInt("id"),resultSet.getString("title"),
                        resultSet.getString("author"),resultSet.getInt("publicYear"),
                        resultSet.getString("publisher"),resultSet.getInt("importPrice"),
                        resultSet.getInt("exportPrice"),resultSet.getBoolean("status"),
                        resultSet.getInt("size"),resultSet.getInt("duration"));
                dvdList.add(dvd);
            }

        } catch(SQLException ex) {
            Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
        return dvdList;
    }

    @Override
    public void insertDvd(Dvd dvd) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "insert into dvd(title, author, publicYear, publisher, importPrice, exportPrice, size, duration) values(?, ? ,?, ?, ? ,?, ?, ?)";

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
            Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    @Override
    public void updateDvd(Dvd dvd) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "update dvd set title=?, author=?, publicYear=?, publisher=?, importPrice=?, exportPrice=?, size=?, duration=? where id=?";

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
            Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    @Override
    public void deleteDvd(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            //Query
            String sql = "delete from dvd  where id=?";

            statement = connection.prepareCall(sql);

            statement.setInt(1, id);


        } catch(SQLException ex) {
            Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(statement != null) {
                try{
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DvdServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
    }

    @Override
    public List<Dvd> searchDvd(SearchDvdRequest searchDvdRequest) {
        List<Dvd> dvdList = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        try {
            //Lay tat ca dia
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookmanager_db", "root", "");

            String title = searchDvdRequest.getTitle();
            String author = searchDvdRequest.getAuthor();
            Integer publicYear = searchDvdRequest.getPublicYear();

            //Query
            String sql = "select * from book where title=" + title + "or" +"author=" + author + "or" + "publicYear=" + publicYear;
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Dvd dvd = new Dvd(resultSet.getInt("id"), resultSet.getString("title"),
                        resultSet.getString("author"), resultSet.getInt("publicYear"),
                        resultSet.getString("publisher"), resultSet.getInt("importPrice"),
                        resultSet.getInt("exportPrice"), resultSet.getBoolean("status"),
                        resultSet.getInt("size"),resultSet.getInt("duration"));
                dvdList.add(dvd);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BookServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
        return dvdList;
    }
}
