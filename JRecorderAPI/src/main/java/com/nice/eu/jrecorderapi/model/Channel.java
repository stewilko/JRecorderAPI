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
 * Channel model. This represents a recording channel.
 * @author swilkinson
 */
public class Channel {
    
    private byte RecId;
    private short ChannelId;
    private short ParrotId;
    private String Name;
    private String Phone;
    private String IpAddr;

    /**
     * @return the RecId
     */
    public byte getRecId() {
        return RecId;
    }

    /**
     * @param RecId the RecId to set
     */
    public void setRecId(byte RecId) {
        this.RecId = RecId;
    }

    /**
     * @return the ChannelId
     */
    public short getChannelId() {
        return ChannelId;
    }

    /**
     * @param ChannelId the ChannelId to set
     */
    public void setChannelId(short ChannelId) {
        this.ChannelId = ChannelId;
    }

    /**
     * @return the ParrotId
     */
    public short getParrotId() {
        return ParrotId;
    }

    /**
     * @param ParrotId the ParrotId to set
     */
    public void setParrotId(short ParrotId) {
        this.ParrotId = ParrotId;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * @param Phone the Phone to set
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    /**
     * @return the IpAddr
     */
    public String getIpAddr() {
        return IpAddr;
    }

    /**
     * @param IpAddr the IpAddr to set
     */
    public void setIpAddr(String IpAddr) {
        this.IpAddr = IpAddr;
    }
    
}
