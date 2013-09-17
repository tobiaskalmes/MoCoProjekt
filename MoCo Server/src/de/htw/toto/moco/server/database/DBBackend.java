package de.htw.toto.moco.server.database;

import de.htw.toto.moco.server.logging.LoggerNames;
import de.htw.toto.moco.server.logging.RootLogger;
import de.htw.toto.moco.server.navigation.Poi;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 15.09.13
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */

//TODO addChatmessage, getChatmessage, addFriend, getFriend, deleteFriend, checkFriend
public class DBBackend {
    private Connection con = null;
    private static String dbHost = "localhost";
    private static String dbPort = "3306";
    private static String dbName = "mocodb";
    private static String dbUser = "moco";
    private static String dbPass = "MoCo1234";

    private RootLogger logger;
    private static DBBackend instance;
    public static DBBackend getInstance(){
        if (instance == null){
            instance = new DBBackend();
        }
         return instance;

    }

    private DBBackend(){
        //severe kacke dampft
        //debug
        //info
        //finest zu detailliert
        logger = RootLogger.getInstance(LoggerNames.DB_LOGGER);
        checkConnection();
    }

    private void checkConnection(){
         if (con==null){
             try {
                 Class.forName("com.mysql.jdbc.Driver"); //load driver

                 con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?" + "user=" + dbUser + "&" + "password=" + dbPass);
             } catch (ClassNotFoundException e) {
                 logger.log("driver not found", Level.SEVERE,e);
             } catch (SQLException e) {
                 logger.log("can't connect", Level.WARNING, e);
             }
         }
    }

    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
           logger.log("failed to close connection",Level.WARNING,e);
        }
    }
    private void closePreparedStatement(PreparedStatement pst){
        try {
            pst.close();
        } catch (SQLException e) {
           logger.log("failed to close Statement",Level.WARNING,e);
        }
    }

    /*--------------------------------------------------------------*
     * user management                                              *
     *--------------------------------------------------------------*/

    public Boolean verifyUserPassword(String userName, String passwordToVerify){
        String sql = "SELECT passwordhash FROM userlist WHERE username IS ?;";
        checkConnection();
        PreparedStatement pst= null;
        try {
            pst  = con.prepareStatement(sql);
            pst.setString(1, userName);
            pst.execute();
            ResultSet rs= pst.getResultSet();
            if (!rs.first()) {
                return null;
                //user does not exist!
            }
            if (rs.getString("password").equals(passwordToVerify)){
                return true;
                //user exist pw ok
            } else {
                return false;
                //user exist pw ng
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            closePreparedStatement(pst);

        }

        return null;
        //sth bad happened!
    }

    public void addUser(String username,String password){
        String sql = "INSERT INTO userlist (username,passwordhash) VALUES (?,?);"; //on duplicate key?
        checkConnection();
        PreparedStatement pst= null;
        try {
            pst  = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            closePreparedStatement(pst);
        }
    }

    public void deleteUser(String username){
        String sql = "DELETE * FROM userlist WHERE username = ?;";
        checkConnection();
        PreparedStatement pst= null;

        try {
            pst  = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }
    }

     /*-------------------------------------------------------------*
     * poi management                                               *
     *--------------------------------------------------------------*/

     public void addPoi(Poi poi) {
         String sql = "INSERT INTO poi (latitude,longitude,type,active,poiName ) VALUES (?,?,?,?,?);"; //on duplicate key?
         checkConnection();
         PreparedStatement pst= null;
         try {
             pst  = con.prepareStatement(sql);
             pst.setDouble(1, poi.getLatitude());
             pst.setDouble(2, poi.getLongitude());
             pst.setInt(3,poi.getType());
             if(poi.isActive())
                pst.setInt(4,1);
             else
                pst.setInt(4,0);
             pst.setString(5,poi.getName());
             pst.execute();
         } catch (SQLException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         } finally {

             closePreparedStatement(pst);
         }
     }
    public Poi getPoiById(int idPoi){
        Poi poi = null;
        String sql = "SELECT * FROM poi WHERE idPoi = ?;";
        checkConnection();
        PreparedStatement pst= null;
        try {
            pst  = con.prepareStatement(sql);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //keine poi
            }
            rs.beforeFirst();
            while(rs.next()){
                if(rs.getInt("active")==1)
                    poi= new Poi(rs.getDouble("latitude"),rs.getDouble("longitude"),rs.getString("name"),true, rs.getInt("type"),rs.getInt("idPoi"));
                else
                    poi= new Poi(rs.getDouble("latitude"),rs.getDouble("longitude"),rs.getString("name"),false, rs.getInt("type"),rs.getInt("idPoi"));

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }
        return poi;

    }


    public ArrayList<Poi> getAllPoi(){
        ArrayList<Poi> poiList =new ArrayList<Poi>();
        String sql = "SELECT * FROM poi";
        checkConnection();
        PreparedStatement pst= null;
        try {
            pst  = con.prepareStatement(sql);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //keine poi
            }
            rs.beforeFirst();
            while(rs.next()){
                Poi poi;
                if(rs.getInt("active")==1)
                    poi= new Poi(rs.getDouble("latitude"),rs.getDouble("longitude"),rs.getString("name"),true, rs.getInt("type"),rs.getInt("idPoi"));
                   else
                    poi= new Poi(rs.getDouble("latitude"),rs.getDouble("longitude"),rs.getString("name"),false, rs.getInt("type"),rs.getInt("idPoi"));
                poiList.add(poi);

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }
        return poiList;
    }
    /*--------------------------------------------------------------*
     * friend management                                            *
     *--------------------------------------------------------------*/

    public void addFriend(int idUserA, int idUserB ){
        String sql = "INSERT INTO friendlist (idUserA,idUserB) VALUES (?,?);"; //on duplicate key?
        checkConnection();
        PreparedStatement pst= null;
        try {
            pst  = con.prepareStatement(sql);
            pst.setInt(1, idUserA);
            pst.setInt(2, idUserB);

            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }

    }

    public ArrayList<Integer> getFriendlist(int idUser){
        ArrayList<Integer> friendlist = new ArrayList<Integer>();

        String sql = "SELECT idUserB FROM friendlist WHERE idUserA = "+idUser+";";
        checkConnection();
        PreparedStatement pst= null;
        try {
            pst  = con.prepareStatement(sql);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //keine freunde
            }
            rs.beforeFirst();
            while(rs.next()){

                friendlist.add(rs.getInt("idUserA"));

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }
        return friendlist;
    }
    /*--------------------------------------------------------------*
     * message management                                           *
     *--------------------------------------------------------------*/
}
