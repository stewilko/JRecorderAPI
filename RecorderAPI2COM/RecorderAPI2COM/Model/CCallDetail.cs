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

using System.Collections.Generic;
using System.Globalization;
using System.Runtime.InteropServices;
using CyberTech.RecorderApi;
using RecorderAPI2COM.Collections;
using RecorderAPI2COM.Interfaces;

namespace RecorderAPI2COM.Model
{
    /// <summary>
    /// Decorator for the Recorder API CallDetail class that a COM exposed interface.
    /// </summary>
    [Guid("37A8BDF4-A0F1-4A89-8C00-27495D7AAA79"),
    ClassInterface(ClassInterfaceType.None),
    ComDefaultInterface(typeof(CCallDetailInterface))]
    public class CCallDetail : CCallDetailInterface
    {
        
        /// <summary>
        /// The decorated type.
        /// </summary>
        private CallDetail mCallDetail;

        public CCallDetail()
        {
            mCallDetail = new CallDetail();

            //CallDetail dosn't create these itself, naughty.
            mCallDetail.Custom = new List<CustomData>();
            mCallDetail.Remarks = new List<Remark>();
        }

        public CCallDetail(CallDetail dets)
        {
            mCallDetail = dets;

            if (mCallDetail.Custom == null)
            {
                mCallDetail.Custom = new List<CustomData>();
            }

            if (mCallDetail.Remarks == null)
            {
                mCallDetail.Remarks = new List<Remark>();
            }
        }

        [ComVisible(false)]
        internal CallDetail InternalCallDetail
        {
            get { return mCallDetail; }
        }

        public int CvsKey
        {
            get { return mCallDetail.CvsKey; }
            set { mCallDetail.CvsKey = value; }
        }

        public int IDUser
        {
            get { return mCallDetail.IDUser; }
            set { mCallDetail.IDUser = value; }
        }

        public string UserHandle
        {
            get { return mCallDetail.UserHandle; }
            set { mCallDetail.UserHandle = value; }
        }

        public short Channel
        {
            get { return (short)mCallDetail.Channel; }
            set { mCallDetail.Channel = (ushort)value; }
        }

        public string ChanName
        {
            get { return mCallDetail.ChanName; }
            set { mCallDetail.ChanName = value; }
        }

        public CDirection Direction
        {
            get { return (CDirection)mCallDetail.Dir; } 
            set { mCallDetail.Dir = (Direction) value; }
        }

        public string CLI
        {
            get { return mCallDetail.CLI; }
            set { mCallDetail.CLI = value; }
        }

        public string Phone
        {
            get { return mCallDetail.Phone; }
            set { mCallDetail.Phone = value; }
        }

        public string Start
        {
            get { return mCallDetail.Start.ToString(Constants.DATETIMEFORMAT, CultureInfo.InvariantCulture); }
        }

        public string End
        {
            get { return mCallDetail.End.ToString(Constants.DATETIMEFORMAT, CultureInfo.InvariantCulture); }
        }

        public int CallDuration
        {
            get { return (int)mCallDetail.CallDuration.TotalMilliseconds; }
        }

        public sbyte Status
        {
            get { return mCallDetail.Status; }
            set { mCallDetail.Status = value; }
        }

        public string CallType
        {
            get { return mCallDetail.CallType; }
            set { mCallDetail.CallType = value; }
        }

        public short Compression
        {
            get { return mCallDetail.Compression; }
            set { mCallDetail.Compression = value; }
        }

        public short Mark
        {
            get { return mCallDetail.Mark; }
            set { mCallDetail.Mark = (byte)(value & 0x00ff); }
        }

        public string CallingParty
        {
            get { return mCallDetail.CallingParty; }
            set { mCallDetail.CalledParty = value; }
        }

        public string CalledParty
        {
            get { return mCallDetail.CalledParty; }
            set { mCallDetail.CalledParty = value; }
        }

        public int InitialRetention
        {
            get { return mCallDetail.InitialRetention; }
            set { mCallDetail.InitialRetention = value; }
        }

        public CCustomDataCollection CustomData
        {
            get
            {
                CCustomDataCollection col = new CCustomDataCollection();
                mCallDetail.Custom.ForEach(c =>
                    col.Add(new CCustomData(c))
                );
                return col;
            }
        }

        public void AddCustomData(CCustomData data)
        {
            mCallDetail.Custom.Add(data.InternalCustomData);
        }
    }
}
