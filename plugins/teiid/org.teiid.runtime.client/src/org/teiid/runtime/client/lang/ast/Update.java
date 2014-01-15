/* Generated By:JJTree: Do not edit this line. Update.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import org.teiid.runtime.client.lang.TeiidNodeFactory.ASTNodes;
import org.teiid.runtime.client.lang.parser.TeiidParser;

public class Update extends Command {

    private SetClauseList changeList;

    /** Identifies the group to be updated. */
    private GroupSymbol group;

    /** optional criteria defining which row get updated. */
    private Criteria criteria;

    public Update(int id) {
        super(id);
    }

    public Update(TeiidParser p, int id) {
        super(p, id);
    }

    /**
     * @return the changeList
     */
    public SetClauseList getChangeList() {
        return changeList;
    }

    /**
     * Add change to change list - a change is represented by a CompareCriteria
     * internally but can be added here as an element and an expression
     * @param id Element to be changed
     * @param value Expression, often a value, being set
     */
    public void addChange(ElementSymbol id, Expression value) {
        if (changeList == null)
            changeList = parser.createASTNode(ASTNodes.SET_CLAUSE_LIST);

        SetClause setClause = parser.createASTNode(ASTNodes.SET_CLAUSE);
        setClause.setSymbol(id);
        setClause.setValue(value);
        changeList.addClause(setClause);
    }

    /**
     * @param changeList the changeList to set
     */
    public void setChangeList(SetClauseList changeList) {
        this.changeList = changeList;
    }

    /**
     * Returns the group being updated
     * @return Group being updated
     */
    public GroupSymbol getGroup() {
        return group;
    }

    /**
     * Set the group being updated
     * @param group Group being updated
     */
    public void setGroup(GroupSymbol group) {
        this.group = group;
    }

    /**
     * Returns the criteria object for this command, may be null
     * @return Criteria, may be null
     */
    public Criteria getCriteria() {
        return this.criteria;
    }

    /**
     * Set the criteria for this Update command
     * @param criteria Criteria to be associated with this command
     */
    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.changeList == null) ? 0 : this.changeList.hashCode());
        result = prime * result + ((this.criteria == null) ? 0 : this.criteria.hashCode());
        result = prime * result + ((this.group == null) ? 0 : this.group.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        Update other = (Update)obj;
        if (this.changeList == null) {
            if (other.changeList != null) return false;
        } else if (!this.changeList.equals(other.changeList)) return false;
        if (this.criteria == null) {
            if (other.criteria != null) return false;
        } else if (!this.criteria.equals(other.criteria)) return false;
        if (this.group == null) {
            if (other.group != null) return false;
        } else if (!this.group.equals(other.group)) return false;
        return true;
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=ce325d62b951bcd63dec74a5c48397e7 (do not edit this line) */
