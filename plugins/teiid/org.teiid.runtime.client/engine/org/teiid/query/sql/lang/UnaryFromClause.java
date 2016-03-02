/* Generated By:JJTree: Do not edit this line. UnaryFromClause.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.query.sql.lang;

import java.util.Collection;
import org.teiid.designer.query.sql.lang.IUnaryFromClause;
import org.teiid.query.parser.LanguageVisitor;
import org.teiid.query.parser.TeiidParser;
import org.teiid.query.sql.symbol.GroupSymbol;

/**
 *
 */
public class UnaryFromClause extends FromClause implements IUnaryFromClause<GroupSymbol, LanguageVisitor> {

    private GroupSymbol groupSymbol;
    
    private Command expandedCommand;

    /**
     * @param p
     * @param id
     */
    public UnaryFromClause(TeiidParser p, int id) {
        super(p, id);
    }

    /**
     * @return the groupSymbol
     */
    @Override
    public GroupSymbol getGroup() {
        return groupSymbol;
    }

    /**
     * @param groupSymbol the groupSymbol to set
     */
    @Override
    public void setGroup(GroupSymbol groupSymbol) {
        this.groupSymbol = groupSymbol;
    }

    /**
     * @return the expandedCommand
     */
    public Command getExpandedCommand() {
        return expandedCommand;
    }

    /**
     * @param expandedCommand the expandedCommand to set
     */
    public void setExpandedCommand(Command expandedCommand) {
        this.expandedCommand = expandedCommand;
    }

    @Override
    public void collectGroups(Collection<GroupSymbol> groups) {
        groups.add(getGroup());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.expandedCommand == null) ? 0 : this.expandedCommand.hashCode());
        result = prime * result + ((this.groupSymbol == null) ? 0 : this.groupSymbol.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        UnaryFromClause other = (UnaryFromClause)obj;
        if (this.expandedCommand == null) {
            if (other.expandedCommand != null) return false;
        } else if (!this.expandedCommand.equals(other.expandedCommand)) return false;
        if (this.groupSymbol == null) {
            if (other.groupSymbol != null) return false;
        } else if (!this.groupSymbol.equals(other.groupSymbol)) return false;
        return true;
    }

    /** Accept the visitor. **/
    @Override
    public void acceptVisitor(LanguageVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public UnaryFromClause clone() {
        UnaryFromClause clone = new UnaryFromClause(this.parser, this.id);

        if(getGroup() != null)
            clone.setGroup(getGroup().clone());
        if(getExpandedCommand() != null)
            clone.setExpandedCommand(getExpandedCommand().clone());
        clone.setOptional(isOptional());
        clone.setMakeInd(isMakeInd());
        clone.setMakeInd(getMakeInd());
        clone.setNoUnnest(isNoUnnest());
        clone.setMakeDep(isMakeDep());
        clone.setMakeNotDep(isMakeNotDep());
        clone.setPreserve(isPreserve());

        return clone;
    }

}
/* JavaCC - OriginalChecksum=363ebf15eecd5ebdaa922627c2a7905d (do not edit this line) */
