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

package com.nice.eu.jrecorderapi.model;

/**
 * ServerCapabilities model. This is used to report the available functions 
 * of the connected recorder. Useful as a keep alive.
 * @author swilkinson
 */
public class ServerCapabilities {
    
    private Enumerations.ArchivingSupportLevel ArchivingSupportLevel;
    private Enumerations.BulkRetrieveSupportLevel BulkRetrieveSupportLevel;
    private boolean CanFindVoxIdsForCdrIds;
    private boolean SupportsCtiFields;
    
    /**
     * @return the ArchivingSupportLevel
     */
    public Enumerations.ArchivingSupportLevel getArchivingSupportLevel() {
        return ArchivingSupportLevel;
    }

    /**
     * @param ArchivingSupportLevel the ArchivingSupportLevel to set
     */
    public void setArchivingSupportLevel(Enumerations.ArchivingSupportLevel ArchivingSupportLevel) {
        this.ArchivingSupportLevel = ArchivingSupportLevel;
    }

    /**
     * @return the BulkRetrieveSupportLevel
     */
    public Enumerations.BulkRetrieveSupportLevel getBulkRetrieveSupportLevel() {
        return BulkRetrieveSupportLevel;
    }

    /**
     * @param BulkRetrieveSupportLevel the BulkRetrieveSupportLevel to set
     */
    public void setBulkRetrieveSupportLevel(Enumerations.BulkRetrieveSupportLevel BulkRetrieveSupportLevel) {
        this.BulkRetrieveSupportLevel = BulkRetrieveSupportLevel;
    }

    /**
     * @return the CanFindVoxIdsForCdrIds
     */
    public boolean isCanFindVoxIdsForCdrIds() {
        return CanFindVoxIdsForCdrIds;
    }

    /**
     * @param CanFindVoxIdsForCdrIds the CanFindVoxIdsForCdrIds to set
     */
    public void setCanFindVoxIdsForCdrIds(boolean CanFindVoxIdsForCdrIds) {
        this.CanFindVoxIdsForCdrIds = CanFindVoxIdsForCdrIds;
    }

    /**
     * @return the SupportsCtiFields
     */
    public boolean isSupportsCtiFields() {
        return SupportsCtiFields;
    }

    /**
     * @param SupportsCtiFields the SupportsCtiFields to set
     */
    public void setSupportsCtiFields(boolean SupportsCtiFields) {
        this.SupportsCtiFields = SupportsCtiFields;
    }
    
}
