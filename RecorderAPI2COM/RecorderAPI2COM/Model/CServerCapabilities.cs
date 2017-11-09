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
    [Guid("08B5FA2D-7DCE-4B86-881E-5FBD8159ED03"),
    ClassInterface(ClassInterfaceType.None)]
    public class CServerCapabilities : CServerCapabiltiesInterface
    {
        ServerCapabilities mCaps;

        public CServerCapabilities()
        {
            mCaps = new ServerCapabilities();
        }

        public CServerCapabilities(ServerCapabilities caps)
        {
            mCaps = caps;
        }

        public void Copy(ServerCapabilities caps)
        {
            mCaps.ArchivingSupportLevel = caps.ArchivingSupportLevel;
            mCaps.BulkRetrieveSupportLevel = caps.BulkRetrieveSupportLevel;
            mCaps.CanFindVoxIdsForCdrIds = caps.CanFindVoxIdsForCdrIds;
            mCaps.HasChannels = caps.HasChannels;
            mCaps.SupportsCtiFields = caps.SupportsCtiFields;
        }

        public bool CanFindVoxIdsForCdrIds
        {
            get { return mCaps.CanFindVoxIdsForCdrIds; }
            set { mCaps.CanFindVoxIdsForCdrIds = value; }
        }

        public bool HasChannels
        {
            get { return mCaps.HasChannels; }
            set { mCaps.HasChannels = value; }
        }

        public bool SupportsCtiFields
        {
            get { return mCaps.SupportsCtiFields; }
            set { mCaps.SupportsCtiFields = value; }
        }

        public CArchivingSupportLevel ArchivingSupportLevel {
            get { return (CArchivingSupportLevel)mCaps.ArchivingSupportLevel; }
            set { mCaps.ArchivingSupportLevel = (ArchivingSupportLevel)value; }
        }

        public CBulkRetrieveSupportLevel BulkRetrieveSupportLevel
        {
            get { return (CBulkRetrieveSupportLevel)mCaps.BulkRetrieveSupportLevel; }
            set { mCaps.BulkRetrieveSupportLevel = (BulkRetrieveSupportLevel)value; }
        }

    }
}
