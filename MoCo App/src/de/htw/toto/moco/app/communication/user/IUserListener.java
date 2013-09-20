package de.htw.toto.moco.app.communication.user;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 20.09.13
 * Time: 12:44
 * To change this template use File | Settings | File Templates.
 */
public interface IUserListener {
    public void resultUserList(List<String> users);

    public void resultAddFriend(Boolean result);

    public void error(Throwable e);
}
