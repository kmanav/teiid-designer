/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.komodo.sramp.shell.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.komodo.repository.AtomRepositoryManager;
import org.komodo.sramp.shell.util.ShellConstants;
import org.overlord.sramp.shell.AbstractShellCommand;
import org.s_ramp.xmlns._2010.s_ramp.BaseArtifactType;

/**
 * Adds a VDB to the repository.  
 */
public class AddVdbCommand extends AbstractShellCommand implements ShellConstants {

    /**
     * Constructor.
     */
    public AddVdbCommand() {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.overlord.sramp.shell.ShellCommand#execute()
     */
    @Override
    public void execute() throws Exception {
        String fileName = requiredArgument(0, "Path to VDB is required.");
        String vdbName = requiredArgument(1, "VDB Name is required.");
        
        InputStream vdbStream = null;
        
        try {
        	vdbStream = getResourceAsStream(fileName);
    	}catch(Exception e){
    		print(e.getMessage());
			return;
    	}
        
        Object obj = this.getContext().getVariable(KOMODO_CLIENT_QNAME);
        
        if (obj==null){
    		print("Please connect to a Komodo S-RAMP server using connectKomodo {URL} before executing this command.");
			return;
        }
        
        AtomRepositoryManager repo = (AtomRepositoryManager) obj;
        BaseArtifactType baseArtifact = repo.addVdb(vdbStream, vdbName);
        
        if (baseArtifact == null){
        	print("Add VDB failed.");
        }else{
        	print("Add VDB successful.");
        }
        
      }

    /**
     * {@inheritDoc}
     *
     * @see org.overlord.sramp.shell.ShellCommand#printHelp()
     */
    @Override
    public void printHelp() {
        print("The 'addVdb' command adds a VDB to the Komodo repository.");
        print("");
        print("Example usage:");
        print(">  komodo:addVdb {Path to VDB} {VDB Name}");
    }

    /**
     * {@inheritDoc}
     *
     * @see org.overlord.sramp.shell.ShellCommand#printUsage()
     */
    @Override
    public void printUsage() {
        print("komodo:addVdb {Path to VDB} {VDB Name}");
    }
    
    /**
     * Obtains the content of a file resource.
     * 
     * @param fileName the file name relative to the calling class (cannot be <code>null</code> or empty)
     * @return the input stream to the content; may be <code>null</code> if the resource does not exist
     * @throws FileNotFoundException 
     */
    protected InputStream getResourceAsStream(final String fileName) throws FileNotFoundException {
        return new FileInputStream(fileName);
    }
}
