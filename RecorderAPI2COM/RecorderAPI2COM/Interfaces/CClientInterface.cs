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

using RecorderAPI2COM.Model;
using System.Runtime.InteropServices;
using RecorderAPI2COM.Collections;

namespace RecorderAPI2COM.Interfaces
{
    [Guid("5D35D6C9-FF64-4E03-8A0C-F21B4441F208")]
    public interface CRecorderApiClientInterface
    {
        [DispId(1)]
        CReturnCode LastResult { get; set; }

        [DispId(2)]
        void Connect(string user, string password, string host, int port);

        [DispId(3)]
        void Disconnect();

        [DispId(4)]
        bool IsConnected { get; }

        [DispId(5)]
        void GetServerCapabilities(out CServerCapabilities serverCapabilties);

        [DispId(6)]
        void GetChannelInfo(ushort channelId, out CChannel info);

        [DispId(7)]
        void GetAllChannels(out CChannelCollection channels);

        [DispId(8)]
        void GetCallDetails(int cvsKey, out CCallDetail details);

        [DispId(9)]
        void SearchCalls(CCallSearchRequestInterface request, out CCallSearchResultsCollection results);

        [DispId(10)]
        void CallUpdate(int cvsKey, CCallDetail deets);
    }
}
