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
package org.overlord.rtgov.activity.collector;

import org.overlord.rtgov.activity.model.ActivityUnit;

/**
 * This interface represents an activity event collector.
 *
 */
public interface ActivityUnitLogger {

    /**
     * This method initializes the activity logger.
     */
    public void init();

    /**
     * This method records the supplied activity event.
     * 
     * @param act The activity event
     */
    public void log(ActivityUnit act);
    
    /**
     * This method closes the activity logger.
     */
    public void close();

}
