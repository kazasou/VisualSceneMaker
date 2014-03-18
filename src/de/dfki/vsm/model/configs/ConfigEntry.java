package de.dfki.vsm.model.configs;

import de.dfki.vsm.util.ios.IndentWriter;
import de.dfki.vsm.model.ModelObject;
import de.dfki.vsm.util.xml.XMLParseError;
import de.dfki.vsm.util.xml.XMLWriteError;
import org.w3c.dom.Element;

/**
 * @author Gregor Mehlmann
 */
public final class ConfigEntry implements ModelObject, Comparable {

    // The Key Of The Feature
    private String mKey;
    // The Value Of The Feature
    private String mVal;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public ConfigEntry() {
        // Initialize The Members
        mKey = null;
        mVal = null;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public ConfigEntry(
            final String key,
            final String val) {
        // Initialize The Members
        mKey = key;
        mVal = val;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final String getKey() {
        return mKey;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final String getVal() {
        return mVal;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final void writeXML(final IndentWriter stream) throws XMLWriteError {
        stream.print("<Entry key=\"" + mKey + "\" val=\"" + mVal + "\"/>").flush();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final void parseXML(final Element element) throws XMLParseError {
        mKey = element.getAttribute("key");
        mVal = element.getAttribute("val");
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final String toString() {
        return "<Entry key=\"" + mKey + "\" val=\"" + mVal + "\"/>";
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final ConfigEntry getCopy() {
        return new ConfigEntry(mKey, mVal);
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public int compareTo(final Object obj) {
        if (obj instanceof ConfigEntry) {
            return mKey.compareTo(((ConfigEntry) obj).mKey);
        } else {
            return 0;
        }
    }
}