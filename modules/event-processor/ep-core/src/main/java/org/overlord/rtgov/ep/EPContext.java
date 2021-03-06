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
package org.overlord.rtgov.ep;

import org.overlord.rtgov.ep.service.EPService;

/**
 * This interface provides services to the EventProcessor
 * implementations that process the events.
 *
 */
public interface EPContext {

    /**
     * This method is used to pass a result, obtained
     * from processing an event, back to the environment
     * managing the event processing.
     * 
     * @param result The result
     */
    public void handle(Object result);

    /**
     * This method logs information.
     * 
     * @param info The information
     */
    public void logInfo(String info);

    /**
     * This method logs an error.
     * 
     * @param error The error
     */
    public void logError(String error);

    /**
     * This method logs debug information.
     * 
     * @param debug The debug information
     */
    public void logDebug(String debug);

    /**
     * This method returns the named service if available.
     * 
     * @param name The service name
     * @return The service, or null if not found
     */
    public EPService getService(String name);
    
}
