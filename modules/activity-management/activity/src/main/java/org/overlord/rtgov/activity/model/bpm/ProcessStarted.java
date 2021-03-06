/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008-12, Red Hat Middleware LLC, and others contributors as indicated
 * by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package org.overlord.rtgov.activity.model.bpm;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.persistence.Entity;

/**
 * This activity type represents a process started event.
 *
 */
@Entity
public class ProcessStarted extends BPMActivityType implements java.io.Externalizable {

    private static final int VERSION = 1;

    private String _processType=null;
    private String _version=null;

    /**
     * The default constructor.
     */
    public ProcessStarted() {
    }
    
    /**
     * The copy constructor.
     * 
     * @param ba The bpm activity to copy
     */
    public ProcessStarted(ProcessStarted ba) {
        super(ba);
        _processType = ba._processType;
        _version = ba._version;
    }
    
    /**
     * This method sets the process type.
     * 
     * @param processType The process type
     */
    public void setProcessType(String processType) {
        _processType = processType;
    }
    
    /**
     * This method gets the process type.
     * 
     * @return The process type
     */
    public String getProcessType() {
        return (_processType);
    }
    
    /**
     * This method sets the version.
     * 
     * @param version The version
     */
    public void setVersion(String version) {
        _version = version;
    }
    
    /**
     * This method gets the version.
     * 
     * @return The version
     */
    public String getVersion() {
        return (_version);
    }
    
    /**
     * {@inheritDoc}
     */
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        
        out.writeInt(VERSION);
        
        out.writeObject(_processType);
        out.writeObject(_version);
    }

    /**
     * {@inheritDoc}
     */
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        super.readExternal(in);
        
        in.readInt(); // Consume version, as not required for now
        
        _processType = (String)in.readObject();
        _version = (String)in.readObject();
    }
}
