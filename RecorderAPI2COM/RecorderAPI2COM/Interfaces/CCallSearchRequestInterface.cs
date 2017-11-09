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
using RecorderAPI2COM.Model;

namespace RecorderAPI2COM.Interfaces
{
    [Guid("F9054270-7133-43F3-A8F0-256BB1ECB205")]
    public interface CCallSearchRequestInterface
    {
        [DispId(1)]
        CCallDetailCollection Query { get; set; }

        [DispId(2)]
        int Timeout { get; set; }

        [DispId(3)]
        int PageSize { get; set; }

        [DispId(4)]
        int PageOffset { get; set; }

        [DispId(5)]
        CSortDirection SortDirection { get; set; }

        [DispId(6)]
        CCallDetailFieldSelection SortField { get; set; }

        [DispId(7)]
        string StartTime { get; set; }

        [DispId(8)]
        string EndTime { get; set; }

        [ComVisible(false)]
        DateTime StartTimeNative { get; }
        
        [ComVisible(false)]
        DateTime EndTimeNative { get; }

        [ComVisible(false)]
        CallDetail[] QueryNative { get; }

        [ComVisible(false)]
        CallSearchOption[] OptionsNative { get; }

    }
}
