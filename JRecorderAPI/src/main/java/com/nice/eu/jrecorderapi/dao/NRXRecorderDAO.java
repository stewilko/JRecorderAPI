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

package com.nice.eu.jrecorderapi.dao;

import com.nice.eu.jrecorderapi.commonobjectmodel.*;
import com.nice.eu.jrecorderapi.commonobjectmodel.events.CClientCallbackInterface;
import com.nice.eu.jrecorderapi.events.RecorderEventsHandlerBase;
import com.nice.eu.jrecorderapi.model.CallDetails;
import com.nice.eu.jrecorderapi.model.Channel;
import com.nice.eu.jrecorderapi.model.Enumerations;
import com.nice.eu.jrecorderapi.model.Enumerations.ReturnCode;
import com.nice.eu.jrecorderapi.model.ServerCapabilities;
import com4j.EventCookie;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A Data Access Object abstraction around the auto generated COM interfaces provided by COM4J.
 * Use this object to access NRX call and channel resources. To listen for recorder events
 * add handlers using the AddEventHandler and RemoveEventHandler functions. Users of this class 
 * should call LastError to determine the status of the last operation.
 * 
 * COM wrappers are disposed as soon as possible to clean up unmanaged code in a predicable manner.
 * 
 * @author swilkinson
 */
public class NRXRecorderDAO {
    
    
    final int DEFAULTAPIPORT = 8024;
    
    private CRecorderApiClientInterface mRecorderAPI;
    
    private NRXRecorderDAOEventProxy mEventProxy;
    
    private EventCookie mEventCookie;
    
    private NRXRecorderDAOTransforms mTransforms;
        
    /**
     * Connect to the Recorder API Service.
     * @param username The Username of the API account
     * @param password The Password of the API account
     * @param host The host name or IP address of the NRX server
     * @param port The port to connect to
     * @throws IOException
     */
    public void Connect(String username, String password, String host, int port) throws IOException {
               
        //Create transforms
        mTransforms = new NRXRecorderDAOTransforms();
        
        //Connect to API
        mRecorderAPI = ClassFactory.createCClient();
        mRecorderAPI.connect(username, password, host, port);
        
        CReturnCode result = mRecorderAPI.lastResult();
               
        if (result != CReturnCode.CReturnCode_OK) {
            mRecorderAPI = null;
            throw new IOException("Failed to connect to Recorder. Return code is " + result.toString());
        }
        
        //Wire up events. The proxy takes care of transformation and dispatch to subscribers.
        mEventProxy = new NRXRecorderDAOEventProxy();
        mEventCookie = mRecorderAPI.advise(CClientCallbackInterface.class, mEventProxy);
                
    }
    
    /**
     * Connect to the Recorder API Service using the default port number
     * @param username The Username of the API account
     * @param password The Password of the API account
     * @param host The host name or IP address of the NRX server
     * @throws IOException
     */
    public void Connect(String username, String password, String host) throws IOException {
        Connect(username, password, host, DEFAULTAPIPORT);
    }
    
    /**
     * Close the connection to the NRX database. All events will stop and calls to
     * data access functions will fail until Connect is called again
     */
    public void Close() {
        
        if (mEventProxy != null) {
            mEventProxy.Close();
        }
        //perhaps the proxy should own this
        if (mEventCookie != null) {
            mEventCookie.close();
        }
        
        if (mRecorderAPI != null) {
            mRecorderAPI.disconnect();
            mRecorderAPI = null;
        }
    }
    
    /**
     * Adds an event handler that recorder events will be routed into.
     * @param handler The event handler to add. It is safe to add event handlers
     * whilst the DAO is connected.
     */
    public void AddEventHandler(RecorderEventsHandlerBase handler) {
        mEventProxy.AddEventHandler(handler);
    }
    
    /**
     * Remove an event handler. Calling Close() will remove all event handlers.
     * @param handler The event handler to remove. It is safe to remove event handlers 
     * whilst the DAO is connected.
     */
    public void RemoveEventHandler(RecorderEventsHandlerBase handler) {
        mEventProxy.RemoveEventHandler(handler);
    }
       
    /**
     * Returns a ServerCapabilities model that represents the permissions of the current
     * user account. Returns null if not connected.
     * @return A populated ServerCapabilites model
     */
    public ServerCapabilities GetRecorderCapabilities() {
        if (mRecorderAPI != null) {
            CServerCapabiltiesInterface ccaps = mRecorderAPI.getServerCapabilities();
            try {
                ServerCapabilities caps = mTransforms.ToServerCapabilties(ccaps);
                return caps;
            } finally {
                if (ccaps != null)
                    ccaps.dispose();
            }
        }
        return null;
    }
    
    /**
     * Returns a Channel model that represents a recording channel.
     * @param chanId The channel number to fetch. Returns null if not
     * connected.
     * @return A populated Channel model
     */
    public Channel GetChannelInfo(short chanId) {
        if (mRecorderAPI != null) {
            CChannelInterface cchan = mRecorderAPI.getChannelInfo(chanId);
            try {
                if (mRecorderAPI.lastResult() == CReturnCode.CReturnCode_OK) {
                    Channel chan = mTransforms.ToChannel(cchan);
                    return chan;
                }
            } finally {
                if (cchan != null)
                    cchan.dispose();
            }
        }
        return null;
    }
	
    /**
     * Returns a list of all channels in the recording platform. Returns null if
     * not connected.
     * @return A list of Channel models.
     */
    public List<Channel> GetChannelInfos(){
	if (mRecorderAPI != null) {
            CChannelCollectionInterface col = mRecorderAPI.getAllChannels();
			
            List<Channel> channels = new ArrayList<>();
			
            for (int i = 0; i < col.length(); i++) {
                channels.add(mTransforms.ToChannel(col.getByIndex(i)));
                col.getByIndex(i).dispose();
            }
            
            col.dispose();
			
            return channels;
        }
        return null;
    }
    
    /**
     * Returns a CallDetails model that represents a call in the recording database. 
     * Custom datafiles are also extracted. 
     * @param cvsKey The CVS key of the call record to extract.
     * @return A populated CallDetails model. 
     */
    public CallDetails GetCallDetail(int cvsKey){
        if (mRecorderAPI != null) {
            CCallDetailInterface cdets = mRecorderAPI.getCallDetails(cvsKey);
            try {
                CallDetails dets = mTransforms.ToCallDetails(cdets);
                return dets;
            } finally {
                if (cdets != null)
                    cdets.dispose();
            }
        }
        
        return null;
    }
    
    /**
     * SearchCalls is used to search the recording database for call that match query. Query is a list of CallDetails
     * that are OR'd together if two or more have the same field populated, and AND'd together for unique fields. CustomData 
     * records are also used for matching.
     * @param query The query to search for. Refer to the RecorderAPI for a full description of how this list is logically handled.
     * @param start The start time window to search for calls
     * @param end The end time of the window to search for calls
     * @param sortDir Sort direction used when sorting calls
     * @param sortField The field to use when sorting calls.
     * @return A list of call Ids that match the query. These can be used in calls to GetCallDetail.
     */
    public List<Integer> SearchCalls(List<CallDetails> query, OffsetDateTime start, OffsetDateTime end, Enumerations.SortDirection sortDir, Enumerations.SortField sortField) {
        
        List<Integer> results = new ArrayList<>();
        
        if (mRecorderAPI != null) {
            
            //Create request object
            CCallSearchRequestInterface req = ClassFactory.createCCallSearchRequest();
            
            try {
                
                //Roll in query objects
                for (CallDetails deets : query) {
                    CCallDetailCollectionInterface cquery = req.query();
                    cquery.add(mTransforms.ToCallDetails(deets));
                }

                //Add options
                req.sortDirection(mTransforms.ToSortDirection(sortDir));
                req.sortField(mTransforms.ToFieldSelection(sortField));
                
                //Add start and end              
                
                String startTime = mTransforms.ToDateTimeString(start);
                String endTime = mTransforms.ToDateTimeString(end);
                req.startTime(startTime);
                req.endTime(endTime);

                //TODO: Paging and Timeout
                //Execute the query
                CCallSearchResultsCollectionInterface callResults = mRecorderAPI.searchCalls(req);
                
                //Format results
                for (int i = 0; i < callResults.length(); i++) {
                    results.add(callResults.getByIndex(i));
                }
                
                           
            } finally {
                
                //Aggresivly tidy up COM handles, this will happen when the finalizer runs, 
                //but be nice and free these explicity.
                if (req != null) {
                    for (int i = 0; i < req.query().length(); i++) {
                        req.query().getByIndex(i).dispose();
                    }
                    req.dispose();
                }
                
            }

        }
        
        return results;
    }
    
    /**
     * CallUpdate is used to update the call data for a call record in the database. Attributes 
     * in the newCallDetails model are updated. Default values are ignored. Be aware that not all
     * fields can be updated. Check LastError to ensure the update committed correctly.
     * @param cvsKey The call key of the call to update
     * @param newCallDetails The new call details.
     */
    public void CallUpdate(int cvsKey, CallDetails newCallDetails) {
        
        if (mRecorderAPI != null) {
            mRecorderAPI.callUpdate(cvsKey, mTransforms.ToCallDetails(newCallDetails));
        }
        
    }
    
    /**
     * Returns the response code of the last operation. Check this to find details 
     * of the last operation.
     * @return The return code.
     */
    public ReturnCode LastError(){
        if (mRecorderAPI != null) {
            return mTransforms.ToReturnCode(mRecorderAPI.lastResult());
        }
        
        return ReturnCode.NotLoggedIn;
    }
    
    

    
    

}
