package de.htw.toto.moco.server.logging;

/**
 * Beinhaltet die einzelnen Logger
 *
 * @author Tobias Kalmes
 */
public enum LoggerNames {
    MAIN_LOGGER("mainLogger"),
    DB_LOGGER("databaseLogger");
    private String name;

    LoggerNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
