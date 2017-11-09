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
 * CustomData model. This represents a custom data entry associated with a call. These
 * map to CVSCxx fields in the recorder database.
 * @author swilkinson
 */
public class CustomData {
    
    private String Name;
    private String DisplayName;
    private Enumerations.CustomTypes CustomType;
    private byte Size;
    private String Value;

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     * @return 
     */
    public CustomData setName(String Name) {
        this.Name = Name;
        return this;
    }

    /**
     * @return the DisplayName
     */
    public String getDisplayName() {
        return DisplayName;
    }

    /**
     * @param DisplayName the DisplayName to set
     * @return 
     */
    public CustomData setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
        return this;
    }

    /**
     * @return the CustomType
     */
    public Enumerations.CustomTypes getCustomType() {
        return CustomType;
    }

    /**
     * @param CustomType the CustomType to set
     * @return 
     */
    public CustomData setCustomType(Enumerations.CustomTypes CustomType) {
        this.CustomType = CustomType;
        return this;
    }

    /**
     * @return the Size
     */
    public byte getSize() {
        return Size;
    }

    /**
     * @param Size the Size to set
     * @return 
     */
    public CustomData setSize(byte Size) {
        this.Size = Size;
        return this;
    }

    /**
     * @return the Value
     */
    public String getValue() {
        return Value;
    }

    /**
     * @param Value the Value to set
     * @return 
     */
    public CustomData setValue(String Value) {
        this.Value = Value;
        return this;
    }
    
}
