/*
MIT License

Copyright (c) 2017 NICE Ltd

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/

package com.nice.eu.jrecorderapi.samples;

import com.nice.eu.jrecorderapi.dao.NRXRecorderDAO;
import com.nice.eu.jrecorderapi.events.RecorderEventsHandlerBase;
import com.nice.eu.jrecorderapi.model.CallDetails;
import com.nice.eu.jrecorderapi.model.Channel;
import com.nice.eu.jrecorderapi.model.CustomData;
import java.io.IOException;
import java.util.List;

public class ChannelStateMonitor {
    
    
    private static int[] mCvsCache;
    
    public static void main(String[] args) throws IOException{
        
        System.out.println("Connecting to server");
        
        final NRXRecorderDAO dao;
                
        try {
            dao = new NRXRecorderDAO();
            dao.Connect("RecorderAPI", "RecorderAPI", "192.168.45.125");
        } catch (Exception e) {
            System.err.println("Failed to connect to recorder :" + e.getLocalizedMessage());
            return;
        }
        
        
        
        //Setup caches
        List<Channel> infos = dao.GetChannelInfos();
        mCvsCache = new int[infos.size()];
        for (int i = 0; i < infos.size(); i++){
            mCvsCache[i] = -1;
        }
        
        System.out.println("Found " + mCvsCache.length + " channels.");
        
        System.out.println("Listening for channel events. Hit enter to quit.");
        
        //Wire up some subscribers
        dao.AddEventHandler(new RecorderEventsHandlerBase() {
            @Override
            public void OnChannelStateChange(short channel, String state) {
                System.out.println("Channel " + channel + " is now " + state);
                
                if ("Stop".equals(state)) {
                    //Call ended, remove active call from the list
                    //and print it's details (if available, we may have missed a start call before logging in
                    if( channel > 0 && channel < mCvsCache.length) {
                        if (mCvsCache[channel] >= 0) {
                            
                            //TODO. It seems if you immediately request call details after the Stop event
                            //the API sometimes misses the database update. Allow a few MS for the call details
                            //to hit the database. Symptoms are calls without end times.
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ex) {
                                
                            }
                            
                            CallDetails details = dao.GetCallDetail(mCvsCache[channel]);
                            
                            System.out.println("Detected call end on channel " + channel + " : ");
                            PrintCall(details);
                        }
                    }
                }
            }
            
            @Override
            public void OnCallStart(short channel, int cvsKey) {              
                if( channel > 0 && channel < mCvsCache.length) {
                    mCvsCache[channel] = cvsKey;
                    System.out.println("Detected call start on channel " + channel + " with CVSKEY " + cvsKey);
                } else {
                    System.err.println("Detect out of bounds call start on channel " + channel + " with CVSKEY " + cvsKey);
                }
            }
        });       
        
        System.in.read();
        
        //Be nice to the server and tidy up our connection
        dao.Close();
    }
       
    private static void PrintCall(CallDetails details) {
        System.out.println("\t From : " + details.getStart() + " to : " + details.getEnd());
        System.out.println("\t Phone number : " + details.getPhone() + " Calling Party : " + details.getCallingParty() + " Called Party : " + details.getCalledParty());
      
        
        //Print custom fields
        for (CustomData data : details.getCustomData()) {
            System.out.println("\t Custom field " + data.getDisplayName() + " is " + data.getValue());
        }
        
           
    }
    
    
    
    
}
