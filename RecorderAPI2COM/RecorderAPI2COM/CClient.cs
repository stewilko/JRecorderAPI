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

using System;
using System.Runtime.InteropServices;
using CyberTech.RecorderApi;
using RecorderAPI2COM.Collections;
using RecorderAPI2COM.Interfaces;
using RecorderAPI2COM.Model;

namespace RecorderAPI2COM
{
    /// <summary>
    /// Decorator class that add a COM interface to the Recorder API Client remoting proxy.
    /// Date times are marshalled as ISO strings. unsigned types (to allow for Java binding) are 
    /// marshalled as the 'next size up' signed type. So unsigned byte becomes signed short. Collections
    /// are marshalled as CComCollections to work around some clients not supporting SAFEARRAYs (COM4J).
    /// In general there is a COM exposed decorator for the major class (channel, call, etc) in the Recorder API
    /// </summary>
    [Guid("FD2B8CEB-9A4D-4E22-BB7C-AB5D8F17AD2C"),
    ClassInterface(ClassInterfaceType.None),
    ComDefaultInterface(typeof(CRecorderApiClientInterface)),
    ComSourceInterfaces(typeof(CClientCallbackInterface)),      //For IDispatch of events
    ComVisible(true)]
    public class CClient : CRecorderApiClientInterface
    {

        /// <summary>
        /// The decorated type.
        /// </summary>
        private Client mClient;

        public CReturnCode LastResult { get; set; }

        public CClient()
        {
            mClient = new Client();
            mClient.StartStop += HandleClientOnStartStop;
            mClient.CallKey += HandleCallKey;
        }

        #region Connection and Disconnection functions
        public void Connect(string user, string password, string host, int port)
        {
            LastResult = (CReturnCode)mClient.Connect(user, password, host, (ushort)port);
        }

        public void Disconnect()
        {
            LastResult = (CReturnCode)mClient.Disconnect();
        }

        public bool IsConnected
        {
            get
            {
                if (mClient != null)
                {
                    return mClient.IsConnected;
                }
                else
                {
                    return false;
                }
            }
        }

        public void Close()
        {
            if (mClient != null)
            {
                mClient.Close();
            }
        }


        #endregion

        #region Connection Administration functions
        public void GetServerCapabilities(out CServerCapabilities serverCapabilties)
        {
            ServerCapabilities servercaps;

            LastResult = (CReturnCode)mClient.GetServerCapabilities(out servercaps);

            serverCapabilties = new CServerCapabilities(servercaps);
        }

        //public void Status(SubSystem subSystem, out StatusCode code)
        //{
        //    LastResult = (CReturnCode)mClient.Status(subSystem, out code);
        //}

        #endregion

        #region Recording Channel functions

        public void GetAllChannels(out CChannelCollection channels)
        {
            Channel[] mchans;

            LastResult = (CReturnCode)mClient.GetAllChannels(out mchans);

            channels = new CChannelCollection();
            for (int i = 0; i < mchans.Length; i++)
            {
                channels.Add(new CChannel(mchans[i]));
            }

        }


        public void GetChannelInfo(ushort channelId, out CChannel info)
        {
            Channel chan;

            LastResult = (CReturnCode)mClient.GetChannelInfo(channelId, out chan);

            info = new CChannel(chan);
        }


        #endregion

        #region Call Data functions

        public void GetCallDetails(int cvsKey, out CCallDetail details)
        {
            CallDetail localCallDetail;
            LastResult = (CReturnCode)mClient.GetCallDetails(cvsKey, out localCallDetail);
            details = new CCallDetail(localCallDetail);
        }

        #endregion

        #region Call Search Functions

        public void SearchCalls(CCallSearchRequestInterface request, out CCallSearchResultsCollection results)
        {

            int[] internalResults = new int[5000];

            //Build RecorderAPI request, the CCallSearchRequest does the adaption for us
            LastResult =  (CReturnCode)mClient.CallSearch(request.StartTimeNative, request.EndTimeNative,
                request.QueryNative, out internalResults, request.OptionsNative);

            results = new CCallSearchResultsCollection();
            for (int i = 0; i < internalResults.Length; i++)
            {
                results.Add(internalResults[i]);
            }

        }

        #endregion

        #region Call Update Functions

        public void CallUpdate(int cvsKey, CCallDetail deets)
        {
            LastResult = (CReturnCode)mClient.CallUpdate(cvsKey, deets.InternalCallDetail);
        }

        #endregion

        #region Event Handling

        [ComVisible(false)]
        public delegate void OnStartStopDelegate(short channel, string state);

        public event OnStartStopDelegate OnStartStop;

        private void HandleClientOnStartStop(Client sender, ushort channel, ChannelState state)
        {
            if (OnStartStop != null)
            {
                try
                {
                    OnStartStop((short)channel, state.ToString());
                }
                catch (Exception e)
                {
                    Console.WriteLine("C# failed to invoke StartStop callback.");
                    Console.WriteLine(e.ToString());
                }
            }
        }

        [ComVisible(false)]
        public delegate void OnCallKeyDelegate(short channel, int cvsKey);

        public event OnCallKeyDelegate OnCallKey;
        private void HandleCallKey(Client sender, ushort channelId, int cvsKey)
        {
            if (OnCallKey != null)
            {
                try
                {
                    OnCallKey((short)channelId, cvsKey);
                }
                catch (Exception e)
                {
                    Console.WriteLine("C# failed to invoke OnCallKey callback.");
                    Console.WriteLine(e.ToString());
                }
            }
        }
        #endregion
    }
}
