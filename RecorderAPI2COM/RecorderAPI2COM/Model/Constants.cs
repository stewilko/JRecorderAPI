﻿/*
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

namespace RecorderAPI2COM.Model
{
    /// <summary>
    /// Useful internal constants
    /// </summary>
    [ComVisible(false)]
    public class Constants
    {
        /// <summary>
        /// Timestamp format used for call start and end times
        /// </summary>
        public static readonly string DATETIMEFORMAT = "yyyy-MM-ddTHH:mm:ss.fffzzz";

        /// <summary>
        /// Timestamp format use for custom DateTime data fields. NRX only supports 1 sec resolution here, in MySQL format
        /// </summary>
        public static readonly string CUSTOMDATADATETIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
    }
}
