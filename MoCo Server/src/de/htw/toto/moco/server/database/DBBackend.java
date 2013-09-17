package de.htw.toto.moco.server.database;

import de.htw.toto.moco.server.navigation.POI;
import de.htw.toto.moco.server.logging.LoggerNames;
import de.htw.toto.moco.server.logging.RootLogger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 15.09.13
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */

//TODO addChatmessage, getChatmessage, addFriend, getFriend, deleteFriend, checkFriend
public class DBBackend {
    private static Connection con = null;
    private static String dbHost = "localhost";
    private static String dbPort = "3306";
    private static String dbName = "mocodb";
    private static String dbUser = "moco";
    private static String dbPass = "MoCo1234";

    private DBBackend(){
        try {
            Class.forName("com.mysql.jdbc.Driver"); //load driver

            //connect
            con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?" + "user=" + dbUser + "&" + "password=" + dbPass);
        } catch (ClassNotFoundException e) {
            System.out.println("driver not found");
        } catch (SQLException e) {
            System.out.println("can't connect");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    private static Connection getInstance(){
        if(con == null)
            new DBBackend();
        return con;
    }

    private static void closeConnection(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("failed to close connection");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    private static void closePreparedStatement(PreparedStatement pst){
        try {
            pst.close();
        } catch (SQLException e) {
            System.out.println("failed to close Statement");
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /*--------------------------------------------------------------*
     * user management                                              *
     *--------------------------------------------------------------*/

    public Boolean verifyUserPassword(String userName, String passwordToVerify){
        String sql = "SELECT password FROM mocodb.user WHERE username IS " + userName;
        Connection con = getInstance();
        PreparedStatement pst= null;
        try {
            pst  = con.prepareStatement(sql);
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
            closeConnection(con);
            closePreparedStatement(pst);
        }

        return null;
        //sth bad happened!
    }

    public void addUser(String username,String password){
        String sql = "INSERT INTO user (username,password) VALUES (?,?);"; //on duplicate key?
        Connection con = getInstance();
        PreparedStatement pst= null;
        try {
            pst  = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            closeConnection(con);
            closePreparedStatement(pst);
        }
    }

    public void deleteUser(String username){
        String sql = "DROP FROM user WHERE username = \""+ username+"\"" ;
        Connection con = getInstance();
        PreparedStatement pst= null;
        try {
            pst  = con.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            closeConnection(con);
            closePreparedStatement(pst);
        }
    }

     /*-------------------------------------------------------------*
     * poi management                                               *
     *--------------------------------------------------------------*/

     public void addPoi(POI poi) {
         String sql = "INSERT INTO poi (latitude,longitude,type,active,name ) VALUES (?,?,?,?,?);"; //on duplicate key?
         Connection con = getInstance();
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
             closeConnection(con);
             closePreparedStatement(pst);
         }
     }
    public ArrayList<POI> getAllPoi(){
        ArrayList<POI> poiList =new ArrayList<POI>();
        String sql = "SELECT * FROM poi";
        Connection con = getInstance();
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
                POI poi;
                if(rs.getInt("active")==1)
                    poi= new POI(rs.getDouble("latitude"),rs.getDouble("longitude"),rs.getString("name"),true, 
                                rs.getInt("type"),rs.getInt("idPoi"));
                   else
                    poi= new POI(rs.getDouble("latitude"),rs.getDouble("longitude"),rs.getString("name"),false, 
                                rs.getInt("type"),rs.getInt("idPoi"));
                poiList.add(poi);

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            closeConnection(con);
            closePreparedStatement(pst);
        }
        return poiList;
    }
    /*--------------------------------------------------------------*
     * friend management                                            *
     *--------------------------------------------------------------*/

    public void addFriend(int idUserA, int idUserB ){
        String sql = "INSERT INTO friendlist (idUserA,idUserB) VALUES (?,?);"; //on duplicate key?
        Connection con = getInstance();
        PreparedStatement pst= null;
        try {
            pst  = con.prepareStatement(sql);
            pst.setInt(1, idUserA);
            pst.setInt(2, idUserB);

            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            closeConnection(con);
            closePreparedStatement(pst);
        }

    }

    public ArrayList<Integer> getFriendlist(int idUser){
        ArrayList<Integer> friendlist = new ArrayList<Integer>();

        String sql = "SELECT idUserB FROM friendlist WHERE idUserA = "+idUser+";";
        Connection con = getInstance();
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
            closeConnection(con);
            closePreparedStatement(pst);
        }
        return friendlist;
    }
}
