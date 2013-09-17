package de.htw.toto.moco.server.database;

import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.messaging.ChatMessageList;
import de.htw.toto.moco.server.navigation.POI;
import de.htw.toto.moco.server.logging.LoggerNames;
import de.htw.toto.moco.server.logging.RootLogger;
import de.htw.toto.moco.server.navigation.POIList;

import java.nio.channels.Channels;
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

    public static DBBackend getInstance() {
        if (instance == null) {
            instance = new DBBackend();
        }
        return instance;

    }

    private DBBackend() {
        //severe kacke dampft
        //debug
        //info
        //finest zu detailliert
        logger = RootLogger.getInstance(LoggerNames.DB_LOGGER);
        checkConnection();
    }

    private void checkConnection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //load driver

                con = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?" + "user=" + dbUser + "&" + "password=" + dbPass);
            } catch (ClassNotFoundException e) {
                logger.log("driver not found", Level.SEVERE, e);
            } catch (SQLException e) {
                logger.log("can't connect", Level.WARNING, e);
            }
        }
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            logger.log("failed to close connection", Level.WARNING, e);
        }
    }

    private void closePreparedStatement(PreparedStatement pst) {
        try {
            pst.close();
        } catch (SQLException e) {
            logger.log("failed to close Statement", Level.WARNING, e);
        }
    }

    /*--------------------------------------------------------------*
     * user management                                              *
     *--------------------------------------------------------------*/

    public Boolean verifyUserPassword(String userName, String passwordToVerify) {
        String sql = "SELECT passwordhash FROM userlist WHERE username IS ?;";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //user does not exist!
            }
            if (rs.getString("password").equals(passwordToVerify)) {
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

    public void addUser(String username, String password) {
        String sql = "INSERT INTO userlist (username,passwordhash) VALUES (?,?);"; //on duplicate key?
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            closePreparedStatement(pst);
        }
    }

    public void deleteUser(String username) {
        String sql = "DELETE * FROM userlist WHERE username = ?;";
        checkConnection();
        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }
    }
    public int getIdUserByName(String userName) {
        String sql = "SELECT idUser FROM userlist WHERE username IS ?;";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {

                logger.log("requested userid for username not found!", Level.INFO);
                return 0;
            }
            return rs.getInt("idUser");

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            closePreparedStatement(pst);

        }

        return 0;
        //sth bad happened!
    }

     /*-------------------------------------------------------------*
     * poi management                                               *
     *--------------------------------------------------------------*/

    public void addPoi(POI poi) {
        String sql = "INSERT INTO poi (latitude,longitude,type,active,poiName ) VALUES (?,?,?,?,?);"; //on duplicate key?
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setDouble(1, poi.getLatitude());
            pst.setDouble(2, poi.getLongitude());
            pst.setInt(3, poi.getType());
            if (poi.isActive())
                pst.setInt(4, 1);
            else
                pst.setInt(4, 0);
            pst.setString(5, poi.getName());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }
    }

    public POI getPoiById(int idPoi) {
        POI poi = null;
        String sql = "SELECT * FROM poi WHERE idPoi = ?;";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //keine poi
            }
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("active") == 1)
                    poi = new POI(rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("name"), true, rs.getInt("type"), rs.getInt("idPoi"));
                else
                    poi = new POI(rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("name"), false, rs.getInt("type"), rs.getInt("idPoi"));

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }
        return poi;

    }


    public POIList getAllPoi() {
        ArrayList<POI> poiList = new ArrayList<POI>();
        String sql = "SELECT * FROM poi";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //keine poi
            }
            rs.beforeFirst();
            while (rs.next()) {
                POI poi;
                if (rs.getInt("active") == 1)
                    poi = new POI(rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("name"), true, rs.getInt("type"), rs.getInt("idPoi"));
                else
                    poi = new POI(rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("name"), false, rs.getInt("type"), rs.getInt("idPoi"));
                poiList.add(poi);

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }
        POIList pl = new POIList();
        pl.setPois(poiList);

        return pl;
    }
    /*--------------------------------------------------------------*
     * friend management                                            *
     *--------------------------------------------------------------*/

    public void addFriend(int idUserA, int idUserB) {
        String sql = "INSERT INTO friendlist (idUserA,idUserB) VALUES (?,?);"; //on duplicate key?
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idUserA);
            pst.setInt(2, idUserB);

            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }

    }

    public ArrayList<Integer> getFriendlist(int idUser) {
        ArrayList<Integer> friendlist = new ArrayList<Integer>();

        String sql = "SELECT idUserB FROM friendlist WHERE idUserA = " + idUser + ";";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //keine freunde
            }
            rs.beforeFirst();
            while (rs.next()) {

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


    public ChatMessageList getAllChatMessage() {
        ArrayList<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
        String sql = "SELECT chatmessage.idChatmessage, chatmessage.content, chatmessage.sendTime, ul1.username AS sender, ul2.username AS destination FROM chatmessage INNER JOIN userlist ul1 ON chatmessage.idsender = ul1.idUser INNER JOIN userlist ul2 ON chatmessage.iddestination = ul2.idUser ORDER BY sendTime DESC LIMIT 0,50;";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //keine chatmessages
            }
            rs.beforeFirst();
            while (rs.next()) {
                chatMessageList.add(new ChatMessage(rs.getString("sender"), rs.getString("destination"),rs.getString("content"),rs.getInt("idChatmessage"), rs.getDate("sendTime")));

            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }
        ChatMessageList cml = new ChatMessageList();
        cml.setMessages(chatMessageList);
        return cml;
    }

    public void addChatMessage(ChatMessage cm) {
        ArrayList<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
        String sql = "INSERT INTO chatmessage (content,idsender,iddestination,sendTime) VALUES (?,?,?,?);";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, cm.getContent());
            pst.setInt(2, getIdUserByName(cm.getSender()));
            pst.setInt(3, getIdUserByName(cm.getReceiver()));
            pst.setDate(4, new java.sql.Date(cm.getSendTime().getTime()));
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            closePreparedStatement(pst);
        }

    }

}
