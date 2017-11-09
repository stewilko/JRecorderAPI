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

namespace RecorderAPI2COM.Collections
{

//Disable warning about hidden members, this is needed to expose the base functionality to COM. See CComCollection.
#pragma warning disable 0108

    [Guid("24DA9B4F-132E-4ACE-8A2E-E085D6C8EF05"),
    ClassInterface(ClassInterfaceType.None),
    ComDefaultInterface(typeof(CCallSearchResultsCollectionInterface))]
    public class CCallSearchResultsCollection : CComCollection<int>, CCallSearchResultsCollectionInterface
    {
        public int this[int i]
        {
            get { return base[i]; }

            set { base[i] = value; }
        }

        public void Add(int item)
        {
            base.Add(item);
        }

        public int GetByIndex(int index)
        {
            return base[index];
        }

        public void SetByIndex(int index, int value)
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
