package de.dfki.vsm.runtime.symbol;

import de.dfki.vsm.editor.event.VariableChangedEvent;
import de.dfki.vsm.runtime.error.RunTimeException;
import de.dfki.vsm.runtime.value.ListValue;
import de.dfki.vsm.runtime.value.StructValue;
import de.dfki.vsm.runtime.value.AbstractValue;
import de.dfki.vsm.util.cpy.Copyable;
import de.dfki.vsm.util.evt.EventCaster;
import de.dfki.vsm.util.tpl.TPLTuple;

public class SymbolEntry implements Copyable {

    // The Symbol Of The Entry
    private final String mSymbol;
    // The Value of The Entry
    private AbstractValue mValue = null;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public SymbolEntry(
            final String symbol,
            final AbstractValue value) {
        mSymbol = symbol;
        mValue = value;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final AbstractValue getValue() {
        return mValue;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final AbstractValue write(final AbstractValue value) throws RunTimeException {
        // Check If The Type Is Valid
        if (mValue.getType() == value.getType()) {
            mValue = value;
            //
            //System.err.println("Writing variable " + mSymbol/*.getName()*/);
            EventCaster.getInstance().convey(new VariableChangedEvent(this,
                    new TPLTuple<String, String>(mSymbol/*.getName()*/, mValue.getFormattedSyntax())));
            //
            return mValue;
        } else {
            throw new RunTimeException(this,
                    value.getConcreteSyntax() + " has wrong type");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final AbstractValue write(final AbstractValue value, final int index) throws RunTimeException {
        try {
            if (mValue.getType() == AbstractValue.Type.LIST) {
                AbstractValue oldValue = ((ListValue) mValue).getValueList().get(index);
                if (oldValue.getType() == value.getType()) {
                    ((ListValue) mValue).getValueList().set(index, value);
                    //
                    EventCaster.getInstance().convey(new VariableChangedEvent(this,
                            new TPLTuple<String, String>(mSymbol/*.getName()*/, mValue.getFormattedSyntax())));
                    //
                    return mValue;
                } else {
                    throw new RunTimeException(this,
                            value.getAbstractSyntax() + " has wrong type");
                }
            } else {
                throw new RunTimeException(this,
                        mValue.getAbstractSyntax() + " is not a list");
            }
        } catch (ClassCastException e) {
            throw new RunTimeException(this,
                    mValue.getAbstractSyntax() + " is not a list");
        } catch (IndexOutOfBoundsException e) {
            throw new RunTimeException(this,
                    mValue.getAbstractSyntax() + " out of bounds");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final AbstractValue write(final AbstractValue value, final String member) throws RunTimeException {
        try {
            if (mValue.getType() == AbstractValue.Type.STRUCT) {
                if (((StructValue) mValue).getValueMap().containsKey(member)) {

                    AbstractValue oldValue = ((StructValue) mValue).getValueMap().get(member);
                    if (oldValue.getType() == value.getType()) {
                        ((StructValue) mValue).getValueMap().put(member, value);
                        //
                        EventCaster.getInstance().convey(new VariableChangedEvent(this,
                                new TPLTuple<String, String>(mSymbol/*.getName()*/, mValue.getFormattedSyntax())));
                        //
                        return mValue;
                    } else {
                        throw new RunTimeException(this,
                                value.getAbstractSyntax() + " has wrong type");
                    }
                } else {
                    throw new RunTimeException(this,
                            member + " does not exist in struct " + mValue.getAbstractSyntax());
                }
            } else {
                throw new RunTimeException(this,
                        mValue.getAbstractSyntax() + " is not a struct");
            }
        } catch (ClassCastException e) {
            throw new RunTimeException(this,
                    mValue.getAbstractSyntax() + " is not a struct");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final AbstractValue read() {
        return mValue;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final AbstractValue read(final int index) throws RunTimeException {
        try {
            if (mValue.getType() == AbstractValue.Type.LIST) {
                return ((ListValue) mValue).getValueList().get(index);
            } else {
                throw new RunTimeException(this,
                        mValue.getAbstractSyntax() + " is not a list");
            }
        } catch (ClassCastException e) {
            throw new RunTimeException(this,
                    mValue.getAbstractSyntax() + " is not a list");
        } catch (IndexOutOfBoundsException e) {
            throw new RunTimeException(this,
                    mValue.getAbstractSyntax() + " out of bounds");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public final AbstractValue read(final String member) throws RunTimeException {
        try {
            if (mValue.getType() == AbstractValue.Type.STRUCT) {
                AbstractValue result = ((StructValue) mValue).getValueMap().get(member);
                if (result != null) {
                    return result;
                } else {
                    throw new RunTimeException(this,
                            member + " does not exist in struct " + mValue.getAbstractSyntax());
                }
            } else {
                throw new RunTimeException(this,
                        mValue.getAbstractSyntax() + " is not a struct");
            }
        } catch (ClassCastException e) {
            throw new RunTimeException(this,
                    mValue.getAbstractSyntax() + " is not a struct");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public final SymbolEntry getCopy() {
        return new SymbolEntry(mSymbol, mValue.getCopy());
    }
}
