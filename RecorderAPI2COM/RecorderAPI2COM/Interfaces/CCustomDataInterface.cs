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

namespace RecorderAPI2COM.Interfaces
{
    [Guid("6D8AD2CB-54D3-4316-920E-B9C6E6A5E7F6")]
    public interface CCustomDataInterface
    {
        [DispId(1)]
        string Name { get; set; }

        [DispId(2)]
        string DisplayName { get; set; }

        [DispId(3)]
        CCustomType Type { get; set; }

        [DispId(4)]
        byte Size { get; set; }

        [DispId(5)]
        string Value { get; set; }
    }
}
