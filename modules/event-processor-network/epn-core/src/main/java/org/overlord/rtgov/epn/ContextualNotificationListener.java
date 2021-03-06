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
package org.overlord.rtgov.epn;

/**
 * This class represents an abstract listener interested in information
 * about an Event Processor Network notification subject.
 *
 */
public abstract class ContextualNotificationListener implements NotificationListener {

    /**
     * This method notifies the listener when a situation occurs
     * on the identified network/version/node concerning a list
     * of events.
     * 
     * @param subject The subject
     * @param events The events that have been processed
     */
    public final void notify(String subject, EventList events) {
        // Reset to make sure context class loader related classes are used
        events.reset();
        
        // Resolve the events in the list, in the context of the supplied
        // classloader
        events.resolve(getContextClassLoader());
        
        // Get the client to handle the events
        handleEvents(subject, events);
    }
    
    /**
     * This method returns the context classloader to use when
     * resolving the events.
     * 
     * @return The context classloader
     */
    public abstract ClassLoader getContextClassLoader();
   
    /**
     * This method notifies the listener when a situation occurs
     * on the subject for the identified network/version/node
     * concerning a list of events.
     * 
     * @param subject The subject
     * @param events The events that have been processed
     */
    public abstract void handleEvents(String subject, EventList events);
    
}
