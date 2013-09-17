package de.htw.toto.moco.app.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 17.09.13
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */
public class ChecksumHandler {
    private static final char[] HEX_CHARS         = "0123456789abcdef".toCharArray();
    private static final int    CHAR_LENGTH_LIMIT = 262144;
    private static ChecksumHandler instance;
    private        MessageDigest   checksum;

    /**
     * private constructor
     *
     * @param type checksum type
     */
    private ChecksumHandler(Type type) {
        try {
            this.checksum = MessageDigest.getInstance(type.getType());
        }
        catch (NoSuchAlgorithmException e) {

        }
    }

    /**
     * returns the instance of the checksum handler with the specific type
     *
     * @param type the type of the checksum
     * @return instance of the checksum handler
     */
    public static ChecksumHandler getInstance(Type type) {
        if (instance == null) {
            instance = new ChecksumHandler(type);
        }
        return instance;
    }

    /**
     * returns the checksum from a string
     *
     * @param s string for which the checksum is to be built
     * @return the checksum as byte array
     */
    private byte[] getChecksumFromStringAsByteArray(String s) throws UnsupportedEncodingException {
        return this.checksum.digest(s.getBytes("UTF-8"));
    }

    /**
     * returns the checksum from a string builder
     *
     * @param sb string builder for which the checksum is to be built
     * @return the checksum as a string
     */
    public String getChecksumFromStringBuilder(StringBuilder sb)
            throws UnsupportedEncodingException {
        this.checksum.reset();
        if ((sb.length() > CHAR_LENGTH_LIMIT) && (sb.length() >= 20)) {
            byte[] buffer;
            int pos;
            for (pos = 0; (pos + CHAR_LENGTH_LIMIT) < sb.length(); pos += CHAR_LENGTH_LIMIT) {
                buffer = sb.substring(pos, pos + CHAR_LENGTH_LIMIT).getBytes("UTF-8");
                this.checksum.update(buffer);
                System.gc();
            }
            buffer = sb.substring(pos, sb.length()).getBytes("UTF-8");
            this.checksum.update(buffer, 0, sb.length() - pos);
            System.gc();
            return this.asHex(this.checksum.digest());
        }
        return this.getChecksumFromString(sb.toString());
    }

    /**
     * returns the checksum from a string
     *
     * @param s string for which the checksum is to be built
     * @return checksum
     */
    public String getChecksumFromString(String s) throws UnsupportedEncodingException {
        return this.asHex(this.getChecksumFromStringAsByteArray(s));
    }

    /**
     * converts the byte array to a string
     *
     * @param buf byte array
     * @return string representation of the byte array
     */
    public String asHex(byte[] buf) {
        char[] chars = new char[2 * buf.length];
        for (int i = 0; i < buf.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[(2 * i) + 1] = HEX_CHARS[buf[i] & 0x0F];
        }
        return new String(chars);
    }

    /**
     * adds the string to the checksum
     *
     * @param s string to be added
     */
    public void update(String s) {
        try {
            this.checksum.update(s.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException e) {

        }
    }

    /**
     * resets the checksum handler
     */
    public void reset() {
        this.checksum.reset();
    }

    /**
     * returns the checksum for the collected data
     *
     * @return the checksum for the collected data
     */
    public String digest() {
        return this.asHex(this.checksum.digest());
    }

    /**
     * Liefert den Hash dem gegebenen String (Achtung! Nicht verwenden, während man einen größeren
     * Hash baut, da der Hash durch diese Funktionen resettet wird!)
     *
     * @param s gegebener String
     * @return Hash zu dem String
     */
    public String instantDigest(String s) {
        this.reset();
        this.update(s);
        return this.digest();
    }

    /**
     * contains the supported checksum types
     */
    public enum Type {
        SHA1("SHA1"),
        MD5("MD5");
        private final String type;

        Type(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }
}
