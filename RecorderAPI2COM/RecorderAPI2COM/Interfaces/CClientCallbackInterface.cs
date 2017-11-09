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

using CyberTech.RecorderApi;
using System;
using System.Runtime.InteropServices;

namespace RecorderAPI2COM.Interfaces
{
    [Guid("2017A4CE-AB94-48EF-9315-30FD346A3F60"),
        InterfaceType(ComInterfaceType.InterfaceIsIDispatch)]
    public interface CClientCallbackInterface : IClientCallback
    {
        //void ServerClosingCallBack(object sender, EventArgs arguments);

        //void StatusChangeCallBack(object sender, StatusChangeEventArgs arguments);

        [DispId(3)]
        void OnStartStop(short channel, String state);

        [DispId(4)]
        void OnCallKey(short channel, int cvsKey);

        //void AudioFetchedCallBack(object sender, AudioFetchedEventArgs arguments);

        //void CallSearchResultsCallBack(object sender, CallSearchResultsEventArgs arguments);
    }
}
