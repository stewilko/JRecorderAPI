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
import com.nice.eu.jrecorderapi.model.CallDetails;
import com.nice.eu.jrecorderapi.model.CustomData;
import com.nice.eu.jrecorderapi.model.Enumerations;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example process that connects to the recorder and once a second searches for
 * calls that ended in the last minute with a default OddEvens tag (Unknown). If a 
 * call is found it is tagged with "Evens" if it was made on an even numbered channel
 * or tagged with "Odds" if it was made on an odd numbered channel.
 * @author swilkinson
 */
public class CallSearchAndTagging {
    
    //Recorder API connection parameters
    private static final String USERNAME = "RecorderAPI";
    private static final String PASSWORD = "RecorderAPI";
    private static final String HOST = "192.168.45.125";
    
    //The parameters of the custom call record data field to use. These can
    //be setup in the recorder Web UI.
    private static final String CVSCID = "CVSC16";
    private static final String CVSCDISPLAYNAME = "OddsEvens";
    private static final String DEFAULTODDEVENS = "Unknown";
    
    private static NRXRecorderDAO mRecorder;
    
    private static volatile boolean mIsRunning;
    
    public static void main(String[] args){
        
        String username = USERNAME;
        String password = PASSWORD;
        String host = HOST;
        
        //Simple arguments parsing for now.
        if (args.length < 3) {
            System.out.println("Not enough arguments specified. Using defaults.");
        } else {
            username = args[0];
            password = args[1];
            host = args[2];
        }
                
        System.out.println("Connecting to recorder API.");
        
        try {
            //create a recorder API
            mRecorder = new NRXRecorderDAO();
            mRecorder.Connect(username, password, host);
        } catch (IOException ex) {
            System.err.println("Failed to connect to recorder at " + HOST + ". Error code is " + mRecorder.LastError());
            return;
        }
        
        mIsRunning = true;
               
        System.out.println("Starting monitoring loop. Hit enter to exit.");
        
        //Start monitor loop
        Thread monitorThread = new Thread(() -> {
            MonitorLoop();
        });
        
        monitorThread.start();

        
        try {
            //Wait for exit
            System.in.read();
        } catch (IOException ex) {
            System.err.println("error reading console. Exiting.");
        }
        
        //Join threads
        mIsRunning = false;
        try {
            monitorThread.join(30000); //wait for long queries
        } catch (InterruptedException ex) {
            System.err.println("Error awaiting processing loop. Exiting.");
        }
        
        //Tidy up API
        System.out.println("Closing recorder connection.");
        mRecorder.Close();
        
        //Done
        
    }
    
    private static void MonitorLoop(){
        
        while (mIsRunning) {
            
            
            //1. Query Recorder API for calls within the last hour that do not have
            //an odd / even data mark
            List<Integer> resultCvs = SearchCalls();
            
            //2. Query the recorder for the call details for each call;
            for (Integer cvs : resultCvs) {
                UpdateCall(cvs);
            }
            
            //3. Sleep for a bit.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CallSearchAndTagging.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /*
    * Searchs for calls with an Unknown OddEvens marker in the last minute.
    */
    private static List<Integer> SearchCalls() {
            
        //Lookup calls with Unknown in custom field CSVC16 display name OddsEvents.
        //You can check this by using the Web UI -> System Installation -> Database Fields.
        CallDetails dets = GetOddEvenCallData(DEFAULTODDEVENS);

        //All calls within the last minute
        OffsetDateTime startTime = OffsetDateTime.now().minusMinutes(1);
        OffsetDateTime endTime = OffsetDateTime.now();
            
        List<CallDetails> query = new ArrayList<>();
        query.add(dets);
            
        System.out.print("Searching for calls from " + startTime + " to " + endTime + ". ");
        
        //Issue query
        List<Integer> resultCvs = mRecorder.SearchCalls(query, startTime, endTime, Enumerations.SortDirection.Ascending, Enumerations.SortField.Start);
        
        System.out.println("Found " + resultCvs.size());
        
        return resultCvs;
    }
    
    /*
    * Tags a call with the given cvsKey with an Evens or Odds marker.
    */
    private static void UpdateCall(int cvsKey) {
        
        //Fetch call details
        CallDetails call = mRecorder.GetCallDetail(cvsKey);
        
        String oddEvens = (0x1 & call.getChannel()) == 0 ? "Evens" : "Odds";
        
        System.out.print("Updating call on channel " + call.getChannel() + " from " + call.getCallingParty() + " to " + call.getCalledParty() + ". Call is " + oddEvens + ". ");
        
        //Create an updated call details object with the field we want to change
        call = GetOddEvenCallData(oddEvens);
        
        //Push the update to the API
        mRecorder.CallUpdate(cvsKey, call);
        
        System.out.println("Result : " + mRecorder.LastError());
    }
    
    /*
    * Returns a call details object suitable for searching for the OddEvens custom data field.
    */
    private static CallDetails GetOddEvenCallData(String oddEvenValue){
        
        CallDetails callDetails = new CallDetails();               
        CustomData customData = new CustomData()
                .setCustomType(Enumerations.CustomTypes.Text)
                .setName(CVSCID)                        
                .setDisplayName(CVSCDISPLAYNAME)
                .setSize((byte)16)
                .setValue(oddEvenValue);            
        
        callDetails.getCustomData().add(customData);
        
        return callDetails;
        
    }
    
}
