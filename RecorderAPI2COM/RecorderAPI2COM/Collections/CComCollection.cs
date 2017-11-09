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

namespace RecorderAPI2COM.Collections
{
    
    /// <summary>
    /// A base type that encapsulates a List of COM enabled objects. Some COM clients have difficulty (ie COM4J) in marshalling
    /// SAFEARRAYs of CoClasses so any array type parameter decend from this type. Since templated types arn't visible through COM
    /// marshalling dervived must still explicity expose an interface without the template, so this class only goes some way in preventing
    /// reimplementing collections for each type. 
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public class CComCollection<T>
    {
        List<T> mItems;

        public CComCollection()
        {
            mItems = new List<T>();
        }

        public T this[int i]
        {
            get
            {
                return mItems[i];
            }

            set
            {
                mItems[i] = value;
            }
        }

        public void Add(T item)
        {
            mItems.Add(item);
        }

        public T GetByIndex(int index)
        {
            return mItems[index];
        }

        public void SetByIndex(int index, T value)
        {
            mItems[index] = value;
        }

        public int Length
        {
            get { return mItems.Count; }
        }

        public void Clear()
        {
            mItems.Clear();
        }
    }
}
