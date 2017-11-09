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


using System.Runtime.InteropServices;

namespace RecorderAPI2COM.Interfaces
{
    [Guid("286103E0-438F-4506-ACE2-E0C5FA89F059")]
    public interface CChannelInterface
    {
        [DispId(1)]
        byte RecID { get; set; }

        [DispId(2)]
        ushort ChannelId { get; set; }

        [DispId(3)]
        ushort ParrotId { get; set; }

        [DispId(4)]
        string Name { get; set; }

        //[DispId(5)]
        //public ChannelSeating Seating
        //{
        //    get { return mChannel.Seating; }
        //    set { mChannel.Seating = value; }
        //}

        [DispId(6)]
        string Phone { get; set; }

        [DispId(7)]
        string IPAddr { get; set; }

        //[DispId(8)]
        //int[] Mark { get; set; }

    }
}
