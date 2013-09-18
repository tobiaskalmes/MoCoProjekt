package de.htw.toto.moco.server.database;

import de.htw.toto.moco.server.logging.LoggerNames;
import de.htw.toto.moco.server.logging.RootLogger;
import de.htw.toto.moco.server.messaging.ChatMessage;
import de.htw.toto.moco.server.messaging.ChatMessageList;
import de.htw.toto.moco.server.navigation.POI;
import de.htw.toto.moco.server.navigation.POIList;
import de.htw.toto.moco.server.user.UserList;

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

//TODO , , , , deleteFriend, checkFriend
public class DBBackend {
    private static String dbHost = "localhost";
    private static String dbPort = "3306";
    private static String dbName = "mocodb";
    private static String dbUser = "root";
    private static String dbPass = "Admin123#";
    private static DBBackend instance;
    private Connection con = null;
    private RootLogger logger;

    private DBBackend() {
        //severe kacke dampft
        //debug
        //info
        //finest zu detailliert
        logger = RootLogger.getInstance(LoggerNames.DB_LOGGER);
        checkConnection();
    }

    public static DBBackend getInstance() {
        if (instance == null) {
            instance = new DBBackend();
        }
        return instance;

    }

    private void checkConnection() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //load driver

                con = DriverManager.getConnection(
                        "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?" + "user=" + dbUser + "&" + "password=" + dbPass);
            }
            catch (ClassNotFoundException e) {
                logger.log("driver not found", Level.SEVERE, e);
            }
            catch (SQLException e) {
                logger.log("can't connect", Level.WARNING, e);
            }
        }
    }

    public void closeConnection() {
        try {
            con.close();
        }
        catch (SQLException e) {
            logger.log("failed to close connection", Level.WARNING, e);
        }
    }

    private void closePreparedStatement(PreparedStatement pst) {
        try {
            pst.close();
        }
        catch (SQLException e) {
            logger.log("failed to close Statement", Level.WARNING, e);
        }
    }

    /*--------------------------------------------------------------*
     * user management                                              *
     *--------------------------------------------------------------*/

    public Boolean verifyUserPassword(String userName, String passwordToVerify) {
        String sql = "SELECT passwordhash FROM userlist WHERE username = ?;";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, userName);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return false;
                //user does not exist!
            }
            if (rs.getString("passwordhash").equals(passwordToVerify)) {
                return true;
                //user exist pw ok
            } else {
                return false;
                //user exist pw ng
            }
        }
        catch (SQLException e) {
            logger.log("verfiyUserPassword sql problems", Level.WARNING, e);
        }
        finally {
            closePreparedStatement(pst);

        }

        return false;
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
        }
        catch (SQLException e) {
            logger.log("addUser sql problems", Level.WARNING, e);
        }
        finally {
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
        }
        catch (SQLException e) {
            logger.log("deleteUser sql problems", Level.WARNING, e);
        }
        finally {

            closePreparedStatement(pst);
        }
    }

    public int getIdUserByName(String userName) {
        String sql = "SELECT idUser FROM userlist WHERE username = ?;";
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

        }
        catch (SQLException e) {
            logger.log("getIdUserByName sql problems", Level.WARNING, e);
        }
        finally {
            closePreparedStatement(pst);

        }

        return 0;
        //sth bad happened!
    }

    public String getUsernameById(int idUser) {
        String sql = "SELECT username FROM userlist WHERE idUser = ?;";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idUser);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {

                logger.log("requested userid for username not found!", Level.INFO);
                return "";
            }
            return rs.getString("username");

        }
        catch (SQLException e) {
            logger.log("getUsernameById sql problems", Level.WARNING, e);
        }
        finally {
            closePreparedStatement(pst);

        }

        return "";
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
            if (poi.isActive()) {
                pst.setInt(4, 1);
            } else {
                pst.setInt(4, 0);
            }
            pst.setString(5, poi.getName());
            pst.execute();
        }
        catch (SQLException e) {
            logger.log("addPoi sql problems", Level.WARNING, e);
        }
        finally {

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
                if (rs.getInt("active") == 1) {
                    poi = new POI(rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("name"), true,
                                  rs.getInt("type"), rs.getInt("idPoi"));
                } else {
                    poi = new POI(rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("name"), false,
                                  rs.getInt("type"), rs.getInt("idPoi"));
                }

            }
        }
        catch (SQLException e) {
            logger.log("getPoiById sql problems", Level.WARNING, e);
        }
        finally {

            closePreparedStatement(pst);
        }
        return poi;

    }

    public POIList getAllPOIs() {
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
                if (rs.getInt("active") == 1) {
                    poi = new POI(rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("name"), true,
                                  rs.getInt("type"), rs.getInt("idPoi"));
                } else {
                    poi = new POI(rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("name"), false,
                                  rs.getInt("type"), rs.getInt("idPoi"));
                }
                poiList.add(poi);

            }
        }
        catch (SQLException e) {
            logger.log("getAllPoi sql problems", Level.WARNING, e);
        }
        finally {

            closePreparedStatement(pst);
        }
        POIList pl = new POIList();
        pl.setPois(poiList);

        return pl;
    }
    /*--------------------------------------------------------------*
     * friend management                                            *
     *--------------------------------------------------------------*/

    public void addFriend(String usernameA, String usernameB) {
        String sql = "INSERT INTO friendlist (idUserA,idUserB) VALUES (?,?);"; //TODO on duplicate key?
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, getIdUserByName(usernameA));
            pst.setInt(2, getIdUserByName(usernameB));

            pst.execute();
        }
        catch (SQLException e) {
            logger.log("addFriend sql problems", Level.WARNING, e);
        }
        finally {

            closePreparedStatement(pst);
        }

    }

    public UserList getFriendlist(String username) {
        ArrayList<String> friendlist = new ArrayList<String>();

        String sql = "SELECT idUserB FROM friendlist WHERE idUserA = ?;";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, getIdUserByName(username));
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //keine freunde
            }
            rs.beforeFirst();
            while (rs.next()) {

                friendlist.add(getUsernameById(rs.getInt("idUserB")));

            }
        }
        catch (SQLException e) {
            logger.log("addFriend sql problems", Level.WARNING, e);
        }
        finally {

            closePreparedStatement(pst);
        }
        UserList userList = new UserList();
        userList.setUsers(friendlist);
        return userList;
    }
    /*--------------------------------------------------------------*
     * message management                                           *
     *--------------------------------------------------------------*/

    public ChatMessageList getAllChatMessagesForTwo(String usernameA, String usernameB) {
        ArrayList<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
        String sql = "SELECT chatmessage.idChatmessage, chatmessage.content, chatmessage.sendTime, uls.username AS sender, uld.username AS destination FROM chatmessage INNER JOIN userlist uls ON chatmessage.idsender = uls.idUser INNER JOIN userlist uld ON chatmessage.iddestination = uld.idUser WHERE (uls.username = ? AND uld.username = ?) OR (uls.username = ? AND uld.username = ?) ORDER BY sendTime DESC LIMIT 0,50;";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, usernameA);
            pst.setString(2, usernameB);
            pst.setString(3, usernameB);
            pst.setString(4, usernameA);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //keine chatmessages
            }
            rs.beforeFirst();
            while (rs.next()) {
                chatMessageList.add(
                        new ChatMessage(rs.getString("sender"), rs.getString("destination"), rs.getString("content"),
                                        rs.getInt("idChatmessage"), rs.getDate("sendTime").getTime()));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        finally {

            closePreparedStatement(pst);
        }
        ChatMessageList cml = new ChatMessageList();
        cml.setMessages(chatMessageList);
        return cml;
    }

    public ChatMessageList getAllChatMessages(String username) {
        ArrayList<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
        String sql = "SELECT chatmessage.idChatmessage, chatmessage.content, chatmessage.sendTime, uls.username AS sender, uld.username AS destination FROM chatmessage INNER JOIN userlist uls ON chatmessage.idsender = uls.idUser INNER JOIN userlist uld ON chatmessage.iddestination = uld.idUser WHERE uls.username = ? OR uld.username = ? ORDER BY sendTime DESC LIMIT 0,50;";
        checkConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, username);
            pst.execute();
            ResultSet rs = pst.getResultSet();
            if (!rs.first()) {
                return null;
                //keine chatmessages
            }
            rs.beforeFirst();
            while (rs.next()) {
                chatMessageList.add(
                        new ChatMessage(rs.getString("sender"), rs.getString("destination"), rs.getString("content"),
                                        rs.getInt("idChatmessage"), rs.getDate("sendTime").getTime()));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        finally {

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
            pst.setDate(4, new java.sql.Date(cm.getSendTime()));
            pst.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        finally {

            closePreparedStatement(pst);
        }

    }

}
