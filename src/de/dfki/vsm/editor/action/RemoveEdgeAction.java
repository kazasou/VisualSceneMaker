package de.dfki.vsm.editor.action;

import de.dfki.vsm.editor.WorkSpace;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * @author Gregor Mehlmann
 */
public class RemoveEdgeAction extends EdgeAction {

    public RemoveEdgeAction(WorkSpace workSpace, de.dfki.vsm.editor.Edge edge) {
        mWorkSpace = workSpace;
        mGUIEdge = edge;
        mDataEdge = edge.getDataEdge();
        mSourceGUINode = edge.getSourceNode();
        mTargetGUINode = edge.getTargetNode();

        // store the dockpoints
        mSourceGUINodeDockPoint = mSourceGUINode.getEdgeDockPoint(edge);
        mTargetGUINodeDockPoint = (mSourceGUINode.equals(mTargetGUINode)) ? mSourceGUINode.getSelfPointingEdgeDockPoint(edge) : mTargetGUINode.getEdgeDockPoint(edge);

        mGUIEdgeType = edge.getType();
        mSceneFlowPane = mWorkSpace.getSceneFlowEditor();
        mUndoManager = mSceneFlowPane.getUndoManager();
    }

    public void run() {
        delete();
        // Update Redo/Undo state
        mUndoManager.addEdit(new Edit());
        UndoAction.getInstance().refreshUndoState();
        RedoAction.getInstance().refreshRedoState();
    }

    private class Edit extends AbstractUndoableEdit {

        @Override
        public void undo() throws CannotUndoException {
            create();
        }

        @Override
        public void redo() throws CannotRedoException {
            delete();
        }

        @Override
        public boolean canUndo() {
            return true;
        }

        @Override
        public boolean canRedo() {
            return true;
        }

        @Override
        public String getUndoPresentationName() {
            return "Undo Deletion Of Edge";
        }

        @Override
        public String getRedoPresentationName() {
            return "Redo Deletion Of Edge";
        }
    }
}
