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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.overlord.rtgov.activity.model.ActivityType;
import org.overlord.rtgov.activity.model.Context;

/**
 * This activity type represents a BPM actvity.
 *
 */
@Entity
public abstract class BPMActivityType extends ActivityType implements java.io.Externalizable {

    private static final int VERSION = 1;

    /**
     * The default constructor.
     */
    public BPMActivityType() {
    }
    
    /**
     * The copy constructor.
     * 
     * @param ba The bpm activity to copy
     */
    public BPMActivityType(BPMActivityType ba) {
        super(ba);
    }
    
    /**
     * This method gets the instance id.
     * 
     * @return The instance id
     */
    @Transient
    @JsonIgnore
    public String getInstanceId() {
        for (Context context : getContext()) {
            if (context.getType() == Context.Type.Endpoint) {
                return (context.getValue());
            }
        }
        
        return (null);
    }
    
    /**
     * This method sets the instance id. The information is
     * actually stored as a context entry for the Endpoint type.
     * 
     * @param instanceId The instance id
     */
    public void setInstanceId(String instanceId) {
        Context current=null;
        
        for (Context context : getContext()) {
            if (context.getType() == Context.Type.Endpoint) {
                current = context;
                break;
            }
        }
        
        if (current == null) {
            current = new Context();
            current.setType(Context.Type.Endpoint);
            getContext().add(current);
        }
        
        current.setValue(instanceId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        
        out.writeInt(VERSION);
        
    }

    /**
     * {@inheritDoc}
     */
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        super.readExternal(in);
        
        in.readInt(); // Consume version, as not required for now
        
    }
}
