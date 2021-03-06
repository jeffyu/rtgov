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
package org.overlord.rtgov.tests.epn;

import org.overlord.rtgov.epn.Network;
import org.overlord.rtgov.epn.Node;
import org.overlord.rtgov.epn.Subscription;
import org.overlord.rtgov.epn.util.NetworkUtil;

/**
 * This class is responsible for loading the test network and registering it with the
 * Event Processor Network Manager.
 *
 */
public class NetworkLoader {
    
    /** The root. **/
    public static final String ROOT = "Root";
    /** The child A. **/
    public static final String CHILD_A = "ChildA";
    /** The child B. **/
    public static final String CHILD_B = "ChildB";
    /** The subject. **/
    public static final String TEST_SUBJECT = "TestSubject";
    /** The network. **/
    public static final String TEST_NETWORK = "TestNetwork";
    /** The file. **/
    public static final String NETWORK_FILE="/networks/TestNetwork.json";
    
    /**
     * The main method.
     * 
     * @param args The list of args
     */
    public static void main(String[] args) {
        NetworkLoader loader=new NetworkLoader();
        
        Network net=loader.createNetwork();
        
        try {
            byte[] b=NetworkUtil.serialize(net);
            
            java.net.URL url=NetworkLoader.class.getResource(NETWORK_FILE);
            
            java.io.FileOutputStream fos=new java.io.FileOutputStream(url.getFile());
            
            fos.write(b);
            fos.flush();
            
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method loads the network.
     * 
     * @return The network
     */
    public Network loadNetwork() {
        Network ret=null;
        
        try {
            java.io.InputStream is=NetworkLoader.class.getResourceAsStream(NETWORK_FILE);
            
            byte[] b=new byte[is.available()];
            is.read(b);
            
            is.close();
            
            ret = NetworkUtil.deserialize(b);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (ret);
    }

    /**
     * This method creates the network.
     * 
     * @return The new network
     */
    public Network createNetwork() {
        Network ret=new Network();
        ret.setName(TEST_NETWORK);
        ret.setVersion(""+System.currentTimeMillis());
        
        ret.subjects().add(TEST_SUBJECT);
        
        Root rootep=new Root();
        Child childAep=new Child();
        Child childBep=new Child();
        
        ChildPredicate childApred=new ChildPredicate();
        childApred.setMin(0);
        childApred.setMax(9);
        
        ChildPredicate childBpred=new ChildPredicate();
        childBpred.setMin(10);
        childBpred.setMax(19);
        
        Node root=new Node();
        root.setName(ROOT);
        root.setEventProcessor(rootep);
        ret.getNodes().add(root);
        
        Node childA=new Node();
        childA.setName(CHILD_A);
        childA.setPredicate(childApred);
        childA.setEventProcessor(childAep);
        childA.getSourceNodes().add(ROOT);
        ret.getNodes().add(childA);

        Node childB=new Node();
        childB.setName(CHILD_B);
        childB.setPredicate(childBpred);
        childB.setEventProcessor(childBep);
        childB.getSourceNodes().add(ROOT);
        ret.getNodes().add(childB);
        
        Subscription sub=new Subscription();
        sub.setNodeName(ROOT);
        sub.setSubject(TEST_SUBJECT);
        ret.getSubscriptions().add(sub);
        
        return (ret);
    }
}
