package de.dfki.vsm.model.sceneflow.command.expression.condition.logical;

import de.dfki.vsm.model.sceneflow.command.expression.Expression;
import de.dfki.vsm.util.ios.IndentWriter;
import de.dfki.vsm.util.xml.XMLParseAction;
import de.dfki.vsm.util.xml.XMLParseError;
import de.dfki.vsm.util.xml.XMLWriteError;
import org.w3c.dom.Element;

/**
 *
 * @author Gregor Mehlmann
 */
public class PrologCond extends LogicalCond {

    Expression mUsrCmd;

    public PrologCond() {
    }

    public PrologCond(Expression cmd) {
        mUsrCmd = cmd;
    }

    public Expression getUsrCmd() {
        return mUsrCmd;
    }

    public LogicalType getLogicalType() {
        return LogicalType.PROLOG;
    }

    public String getAbstractSyntax() {
        return "PrologCondition(" + mUsrCmd.getAbstractSyntax() + ")";
    }

    public String getConcreteSyntax() {
        return "@" + mUsrCmd.getConcreteSyntax() + "";
    }

    public String getFormattedSyntax() {
        return "@" + mUsrCmd.getFormattedSyntax() + "";
    }

    public PrologCond getCopy() {
        return new PrologCond(mUsrCmd.getCopy());
    }

    public void writeXML(IndentWriter out) throws XMLWriteError {
        out.println("<PrologCondition>");
        mUsrCmd.writeXML(out);
        out.println("</PrologCondition>");
    }

    public void parseXML(Element element) throws XMLParseError {
        XMLParseAction.processChildNodes(element, new XMLParseAction() {

            public void run(Element element) throws XMLParseError {
                mUsrCmd = Expression.parse(element);
            }
        });
    }

}
