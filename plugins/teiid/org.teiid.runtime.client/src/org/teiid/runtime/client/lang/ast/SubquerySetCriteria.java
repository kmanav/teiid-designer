/* Generated By:JJTree: Do not edit this line. SubquerySetCriteria.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=TeiidNodeFactory,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.runtime.client.lang.ast;

import org.teiid.runtime.client.lang.SubqueryHint;
import org.teiid.runtime.client.lang.parser.TeiidParser;

public class SubquerySetCriteria extends AbstractSetCriteria {

    private QueryCommand command;

    private SubqueryHint subqueryHint = new SubqueryHint();

    public SubquerySetCriteria(int id) {
        super(id);
    }

    public SubquerySetCriteria(TeiidParser p, int id) {
        super(p, id);
    }

    /**
     * Set the subquery command (either a SELECT or a procedure execution).
     * @param command Command to execute to get the values for the criteria
     */
    public void setCommand(QueryCommand command) {
        this.command = command;
    }

    public SubqueryHint getSubqueryHint() {
        return subqueryHint;
    }
    
    public void setSubqueryHint(SubqueryHint subqueryHint) {
        this.subqueryHint = subqueryHint;
    }

    /**
     * Get the subquery command used to produce the values for this SetCriteria.
     * @return Command Command to execute
     */
    public QueryCommand getCommand() {
        return this.command;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.command == null) ? 0 : this.command.hashCode());
        result = prime * result + ((this.subqueryHint == null) ? 0 : this.subqueryHint.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SubquerySetCriteria other = (SubquerySetCriteria)obj;
        if (this.command == null) {
            if (other.command != null) return false;
        } else if (!this.command.equals(other.command)) return false;
        if (this.subqueryHint == null) {
            if (other.subqueryHint != null) return false;
        } else if (!this.subqueryHint.equals(other.subqueryHint)) return false;
        return true;
    }

    /** Accept the visitor. **/
    public void jjtAccept(Teiid8ParserVisitor visitor, Object data) {
        visitor.visit(this, data);
    }
}
/* JavaCC - OriginalChecksum=089107c07c615436629d5cb75f0cd12d (do not edit this line) */
