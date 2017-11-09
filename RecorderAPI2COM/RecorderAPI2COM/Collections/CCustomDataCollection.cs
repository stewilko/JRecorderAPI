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
using RecorderAPI2COM.Model;

namespace RecorderAPI2COM.Collections
{

    //Disable warning about hidden members, this is needed to expose the base functionality to COM. See CComCollection.
#pragma warning disable 0108

    [Guid("EFEB072A-A4F1-4DDD-935A-3BACD3B0528D"),
    ClassInterface(ClassInterfaceType.None),
    ComDefaultInterface(typeof(CCustomDataCollectionInterface))]
    public class CCustomDataCollection : CComCollection<CCustomData>, CCustomDataCollectionInterface
    {
        public CCustomData this[int i]
        {
            get { return base[i]; }

            set { base[i] = value; }
        }

        public void Add(CCustomData item)
        {
            base.Add(item);
        }

        public CCustomData GetByIndex(int index)
        {
            return base[index];
        }

        public void SetByIndex(int index, CCustomData value)
        {
            base[index] = value;
        }

        public int Length { get { return base.Length; } }

        public void Clear()
        {
            base.Clear();
        }
    }

#pragma warning restore 0108
}
