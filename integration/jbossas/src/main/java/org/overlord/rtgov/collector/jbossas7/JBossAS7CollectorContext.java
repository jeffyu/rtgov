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
package org.overlord.rtgov.collector.jbossas7;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.transaction.TransactionManager;

import org.overlord.rtgov.activity.collector.CollectorContext;

/**
 * This class provides context information regarding the
 * JBossAS7 environment for the activity collector.
 *
 */
public class JBossAS7CollectorContext implements CollectorContext {

    private static final String TRANSACTION_MANAGER = "java:jboss/TransactionManager";

    private static final Logger LOG=Logger.getLogger(JBossAS7CollectorContext.class.getName());

    //@Resource(lookup=TRANSACTION_MANAGER)
    private TransactionManager _transactionManager=null;

    /**
     * This method initializes the collector context.
     */
    @PostConstruct
    public void init() {
        try {
            InitialContext ctx=new InitialContext();
            
            _transactionManager = (TransactionManager)ctx.lookup(TRANSACTION_MANAGER);
            
        } catch (Exception e) {
            LOG.log(Level.SEVERE, java.util.PropertyResourceBundle.getBundle(
                    "rtgov-jbossas.Messages").getString("RTGOV-JBOSSAS-1"), e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public String getHost() {
        return (System.getProperty("jboss.qualified.host.name","Unknown-Host"));
    }

    /**
     * {@inheritDoc}
     */
    public String getNode() {
        return (System.getProperty("jboss.node.name","Unknown-Node"));
    }

    /**
     * {@inheritDoc}
     */
    public TransactionManager getTransactionManager() {
        return (_transactionManager);
    }
}
