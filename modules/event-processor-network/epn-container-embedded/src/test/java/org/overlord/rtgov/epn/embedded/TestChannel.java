/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008-11, Red Hat Middleware LLC, and others contributors as indicated
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
package org.overlord.rtgov.epn.embedded;

import java.io.Serializable;

import org.overlord.rtgov.epn.Channel;
import org.overlord.rtgov.epn.EventList;

public class TestChannel implements Channel {
    
    private java.util.List<Serializable> _events=new java.util.Vector<Serializable>();        

    public java.util.List<Serializable> getEvents() {
        return(_events);
    }

    public void send(EventList events) throws Exception {
        for (Serializable event : events) {
            _events.add(event);            
        }
    }

    public void send(EventList events, int retriesLeft) throws Exception {
        for (Serializable event : events) {
            _events.add(event);            
        }
    }

    public void close() throws Exception {
    }
    
}
