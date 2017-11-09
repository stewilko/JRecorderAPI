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
using System.Globalization;
using System.Runtime.InteropServices;
using CyberTech.RecorderApi;
using RecorderAPI2COM.Interfaces;

namespace RecorderAPI2COM.Model
{
    /// <summary>
    /// COM Decorator for the CustomData Recorder API type. Custom metadata fields (ie CVSCxx) are represented by this type.
    /// Since CustomData is immutable a new instance is created in the appropriate setters. CustomData also uses Object to store
    /// the data which doesn't play well with some clients (COM4J) so expose this as a culture invariant string.
    /// </summary>
    [Guid("9E55FE4D-FEEE-461B-948A-B9FADBDBF263"),
    ClassInterface(ClassInterfaceType.None),
    ComDefaultInterface(typeof(CCustomDataInterface))]
    public class CCustomData : CCustomDataInterface
    {

        private CustomData mCustomData;

        public CCustomData()
        {
            mCustomData = new CustomData("", "", CustomType.Boolean, 255);
        }

        public CCustomData(string name, string displayName, CCustomType type, byte size)
        {
            mCustomData = new CustomData(name, displayName, (CustomType)type, size);
        }

        public CCustomData(CustomData data)
        {
            mCustomData = data;
        }

        [ComVisible(false)]
        internal CustomData InternalCustomData
        {
            get { return mCustomData; }
        }

        public string Name
        {
            get { return mCustomData.Name; }
            set { mCustomData = new CustomData(value, mCustomData.Display, mCustomData.Type, mCustomData.Size); }
        }

        public string DisplayName
        {
            get { return mCustomData.Display; }
            set { mCustomData = new CustomData(mCustomData.Name, value, mCustomData.Type, mCustomData.Size); }
        }

        public CCustomType Type
        {
            get { return (CCustomType)mCustomData.Type; }
            set { mCustomData = new CustomData(mCustomData.Name, mCustomData.Display, (CustomType)value, mCustomData.Size); }
        }

        public byte Size
        {
            get { return mCustomData.Size; }
            set { mCustomData = new CustomData(mCustomData.Name, mCustomData.Display, mCustomData.Type, value); }
        }

        public string Value
        {
            get
            {
                switch (Type)
                {
                    case CCustomType.Text:
                        return (string)mCustomData.Value;
                    case CCustomType.Numeric:
                        int intVal = (int)mCustomData.Value;
                        return intVal.ToString(CultureInfo.InvariantCulture);
                    case CCustomType.Boolean:
                        bool boolVal = (bool) mCustomData.Value;
                        return boolVal.ToString(CultureInfo.InvariantCulture);
                    case CCustomType.DateTime:
                        DateTime dt = (DateTime)mCustomData.Value;
                        return dt.ToString(Constants.CUSTOMDATADATETIMEFORMAT);
                    default:
                        throw new ArgumentOutOfRangeException();
                }
            }
            set
            {
                switch (Type)
                {
                    case CCustomType.Text:
                        mCustomData.Value = value;
                        break;
                    case CCustomType.Boolean:
                        mCustomData.Value = Boolean.Parse(value);
                        break;
                    case CCustomType.Numeric:
                        mCustomData.Value = long.Parse(value);
                        break;
                    case CCustomType.DateTime:
                        mCustomData.Value = DateTime.ParseExact(value, Constants.CUSTOMDATADATETIMEFORMAT,
                            CultureInfo.InvariantCulture);
                        break;
                    default:
                        throw new ArgumentOutOfRangeException();
                }
            }
        }
    }
}
