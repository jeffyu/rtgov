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
package org.overlord.rtgov.analytics.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.overlord.rtgov.activity.model.ActivityTypeId;
import org.overlord.rtgov.analytics.service.InvocationDefinition;
import org.overlord.rtgov.analytics.service.MEPDefinition;

public class MEPDefinitionTest {

    private static final String OPERATION_1 = "op1";
    private static final String INTERFACE_1 = "intf1";
    private static final String OPERATION_2 = "op2";
    private static final String INTERFACE_2 = "intf2";

    @Test
    public void testMergeNoReqResp() {
        
        MEPDefinition mep1=new MEPDefinition() {};
        
        InvocationDefinition id1=new InvocationDefinition();
        id1.setInterface(INTERFACE_1);
        id1.setOperation(OPERATION_1);
        
        mep1.getInvocations().add(id1);
        
        MEPDefinition mep2=new MEPDefinition() {};
        
        InvocationDefinition id2=new InvocationDefinition();
        id2.setInterface(INTERFACE_1);
        id2.setOperation(OPERATION_1);
        
        mep2.getInvocations().add(id2);
        
        InvocationDefinition id3=new InvocationDefinition();
        id3.setInterface(INTERFACE_2);
        id3.setOperation(OPERATION_2);
        
        mep2.getInvocations().add(id3);
        
        mep1.merge(mep2);  
        
        if (mep1.getInvocations().size() != 2) {
            fail("Expecting 2 invocations: "+mep1.getInvocations().size());
        }
        
        if (mep1.getInvocation(INTERFACE_1, OPERATION_1, null) == null) {
            fail("Failed to get st1/op1");
        }
        
        if (mep1.getInvocation(INTERFACE_2, OPERATION_2, null) == null) {
            fail("Failed to get st2/op2");
        }
    }
    
    @Test
    public void testMergeClearReqRespId() {
        
        MEPDefinition mep1=new MEPDefinition() {};
        mep1.setRequestId(new ActivityTypeId());
        mep1.setResponseId(new ActivityTypeId());
        
        MEPDefinition mep2=new MEPDefinition() {};
        
        mep1.merge(mep2);  
        
        if (mep1.getRequestId() != null) {
            fail("Request id should be null");
        }
        
        if (mep1.getResponseId() != null) {
            fail("Response id should be null");
        }
    }
}
