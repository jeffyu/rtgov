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
package org.overlord.rtgov.tests.platforms.jbossas.customevent.epn;

import java.io.Serializable;

import org.overlord.rtgov.activity.model.soa.RequestReceived;
import org.overlord.rtgov.tests.platforms.jbossas.customevent.data.CustomActivityEvent;

/**
 * This class provides a test implementation of the EventProcessor
 * interface, used to receive custom events.
 *
 */
public class HandleCustomActivityEventProcessor extends org.overlord.rtgov.ep.EventProcessor {

    /**
     * {@inheritDoc}
     */
    @Override
    public Serializable process(String source, Serializable event,
            int retriesLeft) throws Exception {
        Serializable ret=null;
        
        if (event instanceof CustomActivityEvent) {
            System.out.println(">>> CUSTOM ACTIVITY ASSOCIATED WITH: "
                    +((CustomActivityEvent)event).getActivityType());
            
            if (((CustomActivityEvent)event).getActivityType() instanceof RequestReceived) {
            	RequestReceived rr=(RequestReceived)((CustomActivityEvent)event).getActivityType();
                
                if (rr.getProperties().containsKey("item")
                		&& rr.getProperties().get("item").equals("JAM")) {
                    ret = event;
                }
            }
        } else {
            System.out.println(">>> NOT CUSTOM ACTIVITY EVENT: "+event);
        }
        
        return (ret);
    }

}
