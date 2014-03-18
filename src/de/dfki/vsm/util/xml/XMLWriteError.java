package de.dfki.vsm.util.xml;

/**
 * @author Gregor Mehlmann
 */
public class XMLWriteError extends Exception {

    private final XMLWriteable mObj;
    private final String mMsg;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public XMLWriteError(final XMLWriteable obj, final String msg) {
        mObj = obj;
        mMsg = msg;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final String getMsg() {
        return mMsg;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final Object getObj() {
        return mObj;
    }
}