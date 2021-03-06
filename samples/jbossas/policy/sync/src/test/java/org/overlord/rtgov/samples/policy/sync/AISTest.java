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
package org.overlord.rtgov.samples.policy.sync;

import static org.junit.Assert.*;

import org.infinispan.manager.CacheContainer;
import org.junit.Test;
import org.overlord.rtgov.activity.model.soa.RequestReceived;
import org.overlord.rtgov.activity.util.ActivityValidatorUtil;
import org.overlord.rtgov.activity.validator.ActivityValidator;
import org.overlord.rtgov.common.infinispan.InfinispanManager;

public class AISTest {

    @Test
    public void testAcceptSpacedOutRequests() {
        ActivityValidator av=null;
        
        try {
            java.io.InputStream is=ClassLoader.getSystemResourceAsStream("av.json");
            
            byte[] b=new byte[is.available()];
            is.read(b);
            
            is.close();
            
            java.util.List<ActivityValidator> avs=ActivityValidatorUtil.deserializeActivityValidatorList(b);
            
            if (avs.size() != 1) {
            	fail("Expecting 1 activity validator, but got: "+avs.size());
            }
            
            av = avs.get(0);
            
            av.init();
            
        } catch (Exception e) {
            fail("Failed to load activity validator: "+e);
        }
        
        // Obtain Principals cache
        java.util.Map<Object,Object> cache=null;
        
        try {
            CacheContainer cc=InfinispanManager.getCacheContainer(null);
            
            cache = cc.getCache("Principals");
            
            cache.clear();
            
        } catch (Exception e) {
            fail("Failed to get default cache container: "+e);
        }
        
        RequestReceived rq1=new RequestReceived();
        rq1.setOperation("submitOrder");
        rq1.setServiceType("{urn:switchyard-quickstart-demo:orders:0.1.0}OrderService");
        rq1.setContent("<Order><customer>Fred</customer><total>100</total></Order>");
        rq1.getProperties().put("customer", "Fred");
        
        try {
        	av.validate(rq1);
            
            synchronized (this) {
                wait(3000);
            }
        } catch (Exception e) {
            fail("Failed to validate 1st event: "+e);
        }
        
        try {
        	av.validate(rq1);
        } catch (Exception e) {       	
            fail("Failed to validate 2nd event: "+e);
        }
    }
    
    @Test
    public void testRejectRushedSecondRequest() {
        ActivityValidator av=null;
        
        try {
            java.io.InputStream is=ClassLoader.getSystemResourceAsStream("av.json");
            
            byte[] b=new byte[is.available()];
            is.read(b);
            
            is.close();
            
            java.util.List<ActivityValidator> avs=ActivityValidatorUtil.deserializeActivityValidatorList(b);
            
            if (avs.size() != 1) {
            	fail("Expecting 1 activity validator, but got: "+avs.size());
            }
            
            av = avs.get(0);
            
            av.init();
            
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to load activity validator: "+e);
        }
        
        // Obtain Principals cache
        java.util.Map<Object,Object> cache=null;
        
        try {
            CacheContainer cc=InfinispanManager.getCacheContainer(null);
            
            cache = cc.getCache("Principals");
            
            cache.clear();
            
        } catch (Exception e) {
            fail("Failed to get default cache container: "+e);
        }
        
        RequestReceived rq1=new RequestReceived();
        rq1.setOperation("submitOrder");
        rq1.setServiceType("{urn:switchyard-quickstart-demo:orders:0.1.0}OrderService");
        rq1.setContent("<Order><customer>Fred</customer><total>100</total></Order>");
        rq1.getProperties().put("customer", "Fred");
        
        try {
        	av.validate(rq1);
            
            synchronized (this) {
                wait(500);
            }
        } catch (Exception e) {
            fail("Failed to validate event: "+e);
        }
        
        try {
        	av.validate(rq1);
        	
        	fail("Should have failed");
        } catch (Exception e) {
        }
    }
}
