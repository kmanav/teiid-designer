/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.xslt.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.teiid.designer.core.xslt.Style;
import org.teiid.designer.core.xslt.StyleRegistry;

/**
 * StyleRegistry
 *
 * @since 8.0
 */
public class StyleRegistryImpl implements StyleRegistry {

    public final List styles;

    /**
     * Construct an instance of StyleRegistry.
     * 
     */
    public StyleRegistryImpl() {
        super();
        this.styles = new LinkedList();
    }

    /* (non-Javadoc)
     * @See org.teiid.designer.ddl.StyleRegistry#getStyle(java.lang.String)
     */
    @Override
	public Style getStyle(String name) {
        final Iterator iter = styles.iterator();
        while (iter.hasNext()) {
            final Style style = (Style)iter.next();
            if ( style.getName().equals(name) ) {
                return style;
            }
        }
        return null;
    }

    /* (non-Javadoc)
     * @See org.teiid.designer.ddl.StyleRegistry#getStyles()
     */
    @Override
	public Collection getStyles() {
        return styles;
    }

}
