package de.htw.toto.moco.server.user;

import de.htw.toto.moco.server.navigation.POI;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 17.09.13
 * Time: 17:29
 * To change this template use File | Settings | File Templates.
 */


@XmlRootElement(name = "UserList")
public class UserList {
    private List<String> users;

    public UserList() {
        users = new ArrayList<String>();
    }

    @XmlElements({@XmlElement(name = "String", type = String.class)})
    @XmlElementWrapper
    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}