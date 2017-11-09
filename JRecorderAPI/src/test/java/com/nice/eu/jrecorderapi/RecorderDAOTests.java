/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nice.eu.jrecorderapi;

import com.nice.eu.jrecorderapi.dao.NRXRecorderDAO;
import com.nice.eu.jrecorderapi.model.CallDetails;
import com.nice.eu.jrecorderapi.model.Channel;
import com.nice.eu.jrecorderapi.model.Enumerations;
import com.nice.eu.jrecorderapi.model.Enumerations.ReturnCode;
import com.nice.eu.jrecorderapi.model.ServerCapabilities;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author swilkinson
 */
public class RecorderDAOTests {
    
    @Test
    public void RecorderDAOConnectTest(){
        
        NRXRecorderDAO dao = null;
        
        try {
            
            InetAddress add = Inet4Address.getByName("192.168.45.125");
                       
            boolean reachable = add.isReachable(1000);
            
            dao = Connect();
        } catch (IOException ex) {
            Logger.getLogger(RecorderDAOTests.class.getName()).log(Level.SEVERE, null, ex);
            Assert.fail("Cannot conntect to logger");
        } finally {
            if (dao != null) {
                dao.Close();
            }
        }
    }
    
    @Test
    public void RecorderDAOServerCapsTest() throws IOException{
        
        NRXRecorderDAO dao = Connect();
        
        ServerCapabilities caps = dao.GetRecorderCapabilities();
        
        Enumerations.ArchivingSupportLevel ar = caps.getArchivingSupportLevel();
        Enumerations.BulkRetrieveSupportLevel br = caps.getBulkRetrieveSupportLevel();
        boolean ctifields = caps.isSupportsCtiFields();
        boolean vox = caps.isCanFindVoxIdsForCdrIds();
        
        dao.Close();
    }
    
    @Test
    public void RecorderDAOChanelInfoTest() throws IOException{
        
        NRXRecorderDAO dao = Connect();
        
        //Request for out of bounds channel
        Channel chan = dao.GetChannelInfo((short)0);
        ReturnCode lastError = dao.LastError();
        Assert.assertEquals(lastError, ReturnCode.ParameterError);
        
        //Request for valid channel
        chan = dao.GetChannelInfo((short)1);
        lastError = dao.LastError();     
        Assert.assertEquals(lastError, ReturnCode.Ok);
        
        
        short channelId = chan.getChannelId();
        String ipAddr = chan.getIpAddr();
        String name = chan.getName();
        short parrotId = chan.getParrotId();
        String phone = chan.getPhone();
        byte recID = chan.getRecId();
        
        dao.Close();
    }
    
    @Test
    public void RecorderDAOChannelInfosTest() throws IOException{
        
        NRXRecorderDAO dao = Connect();
        
        List<Channel> infos = dao.GetChannelInfos();
        
        for (Channel i : infos) {
            System.out.println(i);
            System.out.println(i.getChannelId() + " " + i.getName());
        }

        
        dao.Close();
        
    }
    
    @Test
    public void RecorderDAOCallSearchTest() throws IOException{
        NRXRecorderDAO dao = Connect();
        
        CallDetails deets = new CallDetails();
        ArrayList<CallDetails> query = new ArrayList<>();
        query.add(deets);
        
        OffsetDateTime start = OffsetDateTime.now().minusMinutes(5);
        OffsetDateTime end = OffsetDateTime.now();
        
        List<Integer> res = dao.SearchCalls(query, start, end, Enumerations.SortDirection.Ascending, Enumerations.SortField.Start);
        
        dao.Close();
        
    }
        
    private NRXRecorderDAO Connect() throws IOException {
        NRXRecorderDAO dao = new NRXRecorderDAO();
        dao.Connect("RecorderAPI", "RecorderAPI", "192.168.45.125");
        return dao;
    }
    
}
