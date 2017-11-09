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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Call model. This represents a call in the recorder database.
 * @author swilkinson
 */
public class CallDetails {
    private int CvsKey;
    private int IdUser;
    private String UserHandle;
    private short Channel;
    private String ChannelName;
    private Enumerations.Direction Direction;
    private String Cli;
    private String Phone;
    private LocalDateTime Start;
    private LocalDateTime End;
    private int CallDuration;
    private byte CallStatus;
    private String CallType;
    private short Compression;
    private short Mark; //ubyte in NRX
    private String CallingParty;
    private String CalledParty;
    private int InitialRetention;
    private List<CustomData> CustomData;

    public CallDetails() {
        
        //Setup defaults so they match NRX search queries.
        Direction = Enumerations.Direction.Unknown;
        CustomData = new ArrayList<>();
        Channel = 0;
        Compression = 0;
        CvsKey = 0;
        IdUser = 0;
        Mark = 255;
        InitialRetention = Integer.MIN_VALUE;
        CallStatus = -128;
        
    }
    
    /**
     * @return the CvsKey
     */
    public int getCvsKey() {
        return CvsKey;
    }

    /**
     * @param CvsKey the CvsKey to set
     */
    public void setCvsKey(int CvsKey) {
        this.CvsKey = CvsKey;
    }

    /**
     * @return the IdUser
     */
    public int getIdUser() {
        return IdUser;
    }

    /**
     * @param IdUser the IdUser to set
     */
    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    /**
     * @return the UserHandle
     */
    public String getUserHandle() {
        return UserHandle;
    }

    /**
     * @param UserHandle the UserHandle to set
     */
    public void setUserHandle(String UserHandle) {
        this.UserHandle = UserHandle;
    }

    /**
     * @return the Channel
     */
    public short getChannel() {
        return Channel;
    }

    /**
     * @param Channel the Channel to set
     */
    public void setChannel(short Channel) {
        this.Channel = Channel;
    }

    /**
     * @return the ChannelName
     */
    public String getChannelName() {
        return ChannelName;
    }

    /**
     * @param ChannelName the ChannelName to set
     */
    public void setChannelName(String ChannelName) {
        this.ChannelName = ChannelName;
    }

    /**
     * @return the Direction
     */
    public Enumerations.Direction getDirection() {
        return Direction;
    }

    /**
     * @param Direction the Direction to set
     */
    public void setDirection(Enumerations.Direction Direction) {
        this.Direction = Direction;
    }

    /**
     * @return the Cli
     */
    public String getCli() {
        return Cli;
    }

    /**
     * @param Cli the Cli to set
     */
    public void setCli(String Cli) {
        this.Cli = Cli;
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
     * @return the Start
     */
    public LocalDateTime getStart() {
        return Start;
    }

    /**
     * @param Start the Start to set
     */
    public void setStart(LocalDateTime Start) {
        this.Start = Start;
    }

    /**
     * @return the End
     */
    public LocalDateTime getEnd() {
        return End;
    }

    /**
     * @param End the End to set
     */
    public void setEnd(LocalDateTime End) {
        this.End = End;
    }

    /**
     * @return the CallDuration
     */
    public int getCallDuration() {
        return CallDuration;
    }

    /**
     * @param CallDuration the CallDuration to set
     */
    public void setCallDuration(int CallDuration) {
        this.CallDuration = CallDuration;
    }

    /**
     * @return the CallStatus
     */
    public byte getCallStatus() {
        return CallStatus;
    }

    /**
     * @param CallStatus the CallStatus to set
     */
    public void setCallStatus(byte CallStatus) {
        this.CallStatus = CallStatus;
    }

    /**
     * @return the CallType
     */
    public String getCallType() {
        return CallType;
    }

    /**
     * @param CallType the CallType to set
     */
    public void setCallType(String CallType) {
        this.CallType = CallType;
    }

    /**
     * @return the Compression
     */
    public short getCompression() {
        return Compression;
    }

    /**
     * @param Compression the Compression to set
     */
    public void setCompression(short Compression) {
        this.Compression = Compression;
    }

    /**
     * @return the Mark
     */
    public short getMark() {
        return Mark;
    }

    /**
     * @param Mark the Mark to set
     */
    public void setMark(short Mark) {
        this.Mark = Mark;
    }

    /**
     * @return the CallingParty
     */
    public String getCallingParty() {
        return CallingParty;
    }

    /**
     * @param CallingParty the CallingParty to set
     */
    public void setCallingParty(String CallingParty) {
        this.CallingParty = CallingParty;
    }

    /**
     * @return the CalledParty
     */
    public String getCalledParty() {
        return CalledParty;
    }

    /**
     * @param CalledParty the CalledParty to set
     */
    public void setCalledParty(String CalledParty) {
        this.CalledParty = CalledParty;
    }

    /**
     * @return the InitialRetention
     */
    public int getInitialRetention() {
        return InitialRetention;
    }

    /**
     * @param InitialRetention the InitialRetention to set
     */
    public void setInitialRetention(int InitialRetention) {
        this.InitialRetention = InitialRetention;
    }

    /**
     * @return the CustomData
     */
    public List<CustomData> getCustomData() {
        return CustomData;
    }

    /**
     * @param CustomData the CustomData to set
     */
    public void setCustomData(List<CustomData> CustomData) {
        this.CustomData = CustomData;
    }    
    
}
