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
using RecorderAPI2COM.Interfaces;
using System.Runtime.InteropServices;

namespace RecorderAPI2COM.Model
{
    /// <summary>
    /// Decorator class that add a COM interface to the Recorder API ServerCapabilties model.
    /// </summary>
    [Guid("636590A0-959D-4EF5-81D8-6E2D0A4EDF8E"),
    ClassInterface(ClassInterfaceType.None),
    ComDefaultInterface(typeof(CChannelInterface))]
    public class CChannel : CChannelInterface
    {

        private Channel mChannel;

        public CChannel()
        {
            mChannel = new Channel();
        }

        public CChannel(Channel chan)
        {
            mChannel = chan;
        }

        public byte RecID
        {
            get { return mChannel.RecID; }
            set { mChannel.RecID = value; }
        }

        public ushort ChannelId
        {
            get { return mChannel.ChannelId; }
            set { mChannel.ChannelId = value; }
        }

        public ushort ParrotId
        {
            get { return mChannel.ParrotId; }
            set { mChannel.ParrotId = value; }
        }

        public string Name
        {
            get { return mChannel.Name; }
            set { mChannel.Name = value; }
        }

        //TODO
        //public ChannelSeating Seating
        //{
        //    get { return mChannel.Seating; }
        //    set { mChannel.Seating = value; }
        //}

        public string Phone
        {
            get { return mChannel.Phone; }
            set { mChannel.Phone = value; }
        }

        public string IPAddr
        {
            get { return mChannel.IPAddr; }
            set { mChannel.IPAddr = value; }
        }

        //TODO
        //public int[] Mark
        //{
        //    get { return mChannel.Mark; }
        //    set { mChannel.Mark = value; }
        //}

        //TODO
        //public ParrotChannel Parrotchannel
        //{
        //    get { return mChannel.Parrotchannel; }
        //    set { mChannel.Parrotchannel = value; }
        //}



    }
}
