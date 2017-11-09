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
using System.Collections.Generic;
using System.Globalization;
using System.Runtime.InteropServices;
using CyberTech.RecorderApi;
using RecorderAPI2COM.Collections;
using RecorderAPI2COM.Interfaces;


namespace RecorderAPI2COM.Model
{
    /// <summary>
    /// Composite type that wraps the Recorder API call search function.
    /// </summary>
    [Guid("062405F4-3E39-49B8-8E45-A72C6B88BDDE"),
     ClassInterface(ClassInterfaceType.None),
     ComDefaultInterface(typeof(CCallSearchRequestInterface))]
    public class CCallSearchRequest : CCallSearchRequestInterface
    {

        private CommandTimeoutCallSearchOption mTimeOutOption;
        private PagingCallSearchOption mPagingOption;
        private SortingCallSearchOption mSortingOption;


        public CCallSearchRequest()
        {
            Query = new CCallDetailCollection();
            mTimeOutOption = new CommandTimeoutCallSearchOption(15000);
            mPagingOption = new PagingCallSearchOption(0, 10000);
            mSortingOption = new SortingCallSearchOption(CallDetailFieldSelection.Phone, CyberTech.RecorderApi.SortDirection.Ascending);
        }

        public CCallDetailCollection Query { get; set; }

        public int Timeout
        {
            get { return (int)mTimeOutOption.Timeout; }
            set { mTimeOutOption = new CommandTimeoutCallSearchOption((uint)value); }
        }

        public int PageSize
        {
            get { return mPagingOption.Get; }
            set { mPagingOption = new PagingCallSearchOption((ulong)value, (int)mPagingOption.Skip); }
        }

        public int PageOffset
        {
            get { return (int)mPagingOption.Skip; }
            set { mPagingOption = new PagingCallSearchOption((ulong)mPagingOption.Get, value); }
        }

        public CSortDirection SortDirection
        {
            get { return (CSortDirection)mSortingOption.Direction; }
            set { mSortingOption = new SortingCallSearchOption(mSortingOption.OnField, (SortDirection)value); }
        }

        public CCallDetailFieldSelection SortField
        {
            get { return (CCallDetailFieldSelection)mSortingOption.OnField; }
            set { mSortingOption = new SortingCallSearchOption((CallDetailFieldSelection)value, mSortingOption.Direction); }
        }

        public string StartTime { get; set; }
        public string EndTime { get; set; }

        #region Adaption
        [ComVisible(false)]
        public DateTime StartTimeNative
        {
            get
            {
                return DateTime.ParseExact(StartTime, Constants.DATETIMEFORMAT, CultureInfo.InvariantCulture);
            }
        }

        [ComVisible(false)]
        public DateTime EndTimeNative
        {
            get
            {
                return DateTime.ParseExact(EndTime, Constants.DATETIMEFORMAT, CultureInfo.InvariantCulture);
            }
        }

        [ComVisible(false)]
        public CallDetail[] QueryNative
        {
            get
            {
                List<CallDetail> deets = new List<CallDetail>();
                for (int i = 0; i < Query.Length; i++)
                {
                    deets.Add(Query.GetByIndex(i).InternalCallDetail);
                }
                return deets.ToArray();
            }
        }

        [ComVisible(false)]
        public CallSearchOption[] OptionsNative
        {
            get
            {
                return new CallSearchOption[] { mTimeOutOption, mSortingOption, mPagingOption };
            }
        }

        #endregion

    }
}