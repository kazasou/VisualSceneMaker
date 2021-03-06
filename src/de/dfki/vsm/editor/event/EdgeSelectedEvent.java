package de.dfki.vsm.editor.event;

import de.dfki.vsm.model.sceneflow.Edge;
import de.dfki.vsm.util.evt.EventObject;

/**
 * @author Gregor Mehlmann
 */
public class EdgeSelectedEvent extends EventObject {

    private Edge mEdge;

    public EdgeSelectedEvent(Object source, Edge edge) {
        super(source);
        mEdge = edge;
    }

    public Edge getEdge() {
        return mEdge;
    }

    public String getEventDescription() {
        return "NodeSelectedEvent(" + mEdge.getSource() + " -> " + mEdge.getTarget() + ")";
    }
}
