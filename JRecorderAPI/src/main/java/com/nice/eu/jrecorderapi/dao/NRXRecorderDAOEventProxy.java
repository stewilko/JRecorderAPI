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

import com.nice.eu.jrecorderapi.commonobjectmodel.events.CClientCallbackInterface;
import com.nice.eu.jrecorderapi.events.RecorderEventsHandlerBase;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that routes events from the COM dispatch interface into Java event handlers.
 * Java clients should use instances of RecorderEventHandlerBase registered through the 
 * DAO to handle incoming call events.
 * @author swilkinson
 */
public class NRXRecorderDAOEventProxy extends CClientCallbackInterface{
    
    private final List<RecorderEventsHandlerBase> mEventHandlers = new ArrayList<>();
     
    public void AddEventHandler(RecorderEventsHandlerBase handler) {
        synchronized(mEventHandlers) {
            mEventHandlers.add(handler);
        }
    }
    
    public void RemoveEventHandler(RecorderEventsHandlerBase handler) {
        synchronized(mEventHandlers) {
            mEventHandlers.remove(handler);
        }
    }
    
    
    @Override
    public void onStartStop(short channel, String state){    
        synchronized(mEventHandlers) {
            mEventHandlers.forEach((e) -> {
                e.OnChannelStateChange(channel, state);
            });
        }      
    }
    
    @Override
    public void onCallKey(short channel, int cvsKey) {
        synchronized(mEventHandlers) {
            mEventHandlers.forEach((e) -> {
                e.OnCallStart(channel, cvsKey);
            });
        }    
    }
    
    public void Close() {
        synchronized(mEventHandlers) {
            mEventHandlers.clear();
        }
    }
    
    
}
