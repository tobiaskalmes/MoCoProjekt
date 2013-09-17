package de.htw.toto.moco.server.database;

/**
 * Created with IntelliJ IDEA.
 * User: TG
 * Date: 14.09.13
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 */

public abstract class DBRequest {
 private IDBRequestReceiver receiver;

    public IDBRequestReceiver getReceiver() {
        return receiver;
    }
}
