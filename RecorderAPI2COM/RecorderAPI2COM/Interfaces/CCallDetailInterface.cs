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


using RecorderAPI2COM.Collections;
using RecorderAPI2COM.Model;
using System.Runtime.InteropServices;

namespace RecorderAPI2COM.Interfaces
{
    [Guid("AA1F4A7E-041D-4157-A173-34B0318323D7")]
    public interface CCallDetailInterface
    {
        [DispId(1)]
        int CvsKey { get; set; }

        [DispId(2)]
        int IDUser { get; set; }

        [DispId(3)]
        string UserHandle { get; set; }

        [DispId(4)]
        short Channel { get; set; }

        [DispId(5)]
        string ChanName { get; set; }

        [DispId(6)]
        CDirection Direction { get; set; }

        [DispId(7)]
        string CLI { get; set; }

        [DispId(8)]
        string Phone { get; set; }

        [DispId(9)]
        string Start { get; }

        [DispId(10)]
        string End { get; }

        [DispId(11)]
        int CallDuration { get; }

        [DispId(12)]
        sbyte Status { get; set; }

        [DispId(13)]
        string CallType { get; set; }

        [DispId(14)]
        short Compression { get; set; }

        [DispId(15)]
        short Mark { get; set; }

        [DispId(16)]
        string CallingParty { get; set; }

        [DispId(17)]
        string CalledParty { get; set; }

        [DispId(18)]
        int InitialRetention { get; set; }

        //TODO
        //[DispId(19)]
        //List<string> Remarks { get; set; } 

        [DispId(20)]
        CCustomDataCollection CustomData { get; }

        [DispId(21)]
        void AddCustomData(CCustomData data);


    }
}
