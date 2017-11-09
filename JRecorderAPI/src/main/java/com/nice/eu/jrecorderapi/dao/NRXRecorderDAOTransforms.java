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

package com.nice.eu.jrecorderapi.dao;

import com.nice.eu.jrecorderapi.commonobjectmodel.CCallDetailFieldSelection;
import com.nice.eu.jrecorderapi.commonobjectmodel.CCallDetailInterface;
import com.nice.eu.jrecorderapi.commonobjectmodel.CChannelInterface;
import com.nice.eu.jrecorderapi.commonobjectmodel.CCustomDataCollectionInterface;
import com.nice.eu.jrecorderapi.commonobjectmodel.CCustomDataInterface;
import com.nice.eu.jrecorderapi.commonobjectmodel.CCustomType;
import com.nice.eu.jrecorderapi.commonobjectmodel.CDirection;
import com.nice.eu.jrecorderapi.commonobjectmodel.CReturnCode;
import com.nice.eu.jrecorderapi.commonobjectmodel.CServerCapabiltiesInterface;
import com.nice.eu.jrecorderapi.commonobjectmodel.CSortDirection;
import com.nice.eu.jrecorderapi.commonobjectmodel.ClassFactory;
import com.nice.eu.jrecorderapi.model.CallDetails;
import com.nice.eu.jrecorderapi.model.Channel;
import com.nice.eu.jrecorderapi.model.CustomData;
import com.nice.eu.jrecorderapi.model.Enumerations;
import com.nice.eu.jrecorderapi.model.Enumerations.Direction;
import com.nice.eu.jrecorderapi.model.Enumerations.ReturnCode;
import com.nice.eu.jrecorderapi.model.Enumerations.SortDirection;
import com.nice.eu.jrecorderapi.model.Enumerations.SortField;
import com.nice.eu.jrecorderapi.model.ServerCapabilities;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that provides a set of transformation function from COM4J COM proxy objects
 * into POJOs. The COM proxies are transformed in the DAO limiting there scope and abstracts
 * the rest of the Java code from the COM layer. 
 * @author swilkinson
 */
public class NRXRecorderDAOTransforms {  
    
    public ServerCapabilities ToServerCapabilties(CServerCapabiltiesInterface com){
        ServerCapabilities caps = new ServerCapabilities();
        
        switch(com.archivingSupportLevel()){
            case CArchivingSupportLevel_NotSupported:
                caps.setArchivingSupportLevel(Enumerations.ArchivingSupportLevel.NotSupported);
                break;
            case CArchivingSupportLevel_Supported:
                caps.setArchivingSupportLevel(Enumerations.ArchivingSupportLevel.Supported);
                break;
            case CArchivingSupportLevel_Extended:
                caps.setArchivingSupportLevel(Enumerations.ArchivingSupportLevel.Extended);
                break;
            default:
                throw new AssertionError(com.archivingSupportLevel().name()); 
        }
        
        switch(com.bulkRetrieveSupportLevel()){
            case CBulkRetrieveSupportLevel_BasicSupport:
                caps.setBulkRetrieveSupportLevel(Enumerations.BulkRetrieveSupportLevel.BasicSupport);
                break;
            case CBulkRetrieveSupportLevel_FullSupport:
                caps.setBulkRetrieveSupportLevel(Enumerations.BulkRetrieveSupportLevel.FullSupport);
                break;
            default:
                throw new AssertionError(com.bulkRetrieveSupportLevel().name());
        }
        
        caps.setCanFindVoxIdsForCdrIds(com.canFindVoxIdsForCdrIds());
        caps.setSupportsCtiFields(com.supportsCtiFields());
        
        return caps;
    }
    
    public Channel ToChannel(CChannelInterface cchan) {
        Channel chan = new Channel();
        
        chan.setChannelId(cchan.channelId());
        chan.setIpAddr(cchan.ipAddr());
        chan.setName(cchan.name());
        chan.setParrotId(cchan.parrotId());
        chan.setPhone(cchan.phone());
        chan.setRecId(cchan.recID());
        
        return chan;
    }
    
    public CallDetails ToCallDetails(CCallDetailInterface cdets) {
                
        CallDetails dets = new CallDetails();
        
        dets.setCallDuration(cdets.callDuration());
        dets.setCallStatus(cdets.status());
        dets.setCallType(cdets.callType());
        dets.setCalledParty(cdets.calledParty());
        dets.setCallingParty(cdets.callingParty());
        dets.setChannel(cdets.channel());
        dets.setChannelName(cdets.chanName());
        dets.setCli(cdets.cli());
        dets.setCompression(cdets.compression());
        dets.setCvsKey(cdets.cvsKey());
        
        switch (cdets.direction()) {
            case CDirection_Inbound:
                dets.setDirection(Enumerations.Direction.Inbound);
                break;
            case CDirection_Outbound:
                dets.setDirection(Enumerations.Direction.Outbound);
                break;
            case CDirection_Unknown:
            default:
                dets.setDirection(Enumerations.Direction.Unknown);
        }
        
        dets.setStart(LocalDateTime.parse(cdets.start(), DateTimeFormatter.ISO_DATE_TIME));
        dets.setEnd(LocalDateTime.parse(cdets.end(), DateTimeFormatter.ISO_DATE_TIME));
        
        dets.setIdUser(cdets.idUser());
        dets.setInitialRetention(cdets.initialRetention());
        dets.setMark(cdets.mark());
        dets.setPhone(cdets.phone());
        dets.setUserHandle(cdets.userHandle());
        
        CCustomDataCollectionInterface customData = cdets.customData();
        
        //Custom data
        for (int i = 0; i < customData.length(); i++) {
            dets.getCustomData().add(ToCustomData(customData.getByIndex(i)));
        }
  
        return dets;
    }
    
    public CCallDetailInterface ToCallDetails(CallDetails deets) {
        
        CCallDetailInterface cdeets = ClassFactory.createCCallDetail();
        
        cdeets.callType(deets.getCallType());
        cdeets.calledParty(deets.getCalledParty());
        cdeets.callingParty(deets.getCallingParty());
        cdeets.channel(deets.getChannel());
        cdeets.chanName(deets.getChannelName());
        cdeets.cli(deets.getCli());
        cdeets.compression(deets.getCompression());
        cdeets.cvsKey(deets.getCvsKey());
        
        switch (deets.getDirection()) {
            case Inbound:
                cdeets.direction(CDirection.CDirection_Inbound);
                break;
            case Outbound:
                cdeets.direction(CDirection.CDirection_Outbound);
                break;
            case Unknown:
            default:
                cdeets.direction(CDirection.CDirection_Unknown);
        }
               
        cdeets.idUser(deets.getIdUser());
        cdeets.initialRetention(deets.getInitialRetention());
        cdeets.mark(deets.getMark());
        cdeets.phone(deets.getPhone());
        cdeets.userHandle(deets.getUserHandle());
        
        //Custom data
        for (CustomData data : deets.getCustomData()) {
            cdeets.addCustomData(ToCustomData(data));
        }
        
        return cdeets;
        
    }
    
    public CCustomDataInterface ToCustomData(CustomData data) {
            CCustomDataInterface cdata = ClassFactory.createCCustomData();
            
            cdata.name(data.getName());
            cdata.displayName(data.getDisplayName());
            cdata.type(ToCustomType(data.getCustomType()));
            cdata.value(data.getValue());
            
            return cdata;
    }
    
    public CustomData ToCustomData(CCustomDataInterface cdata) {
            CustomData data = new CustomData();
            
            data.setName(cdata.name());
            data.setDisplayName(cdata.displayName());
            data.setCustomType(ToCustomType(cdata.type()));
            data.setValue(cdata.value());
            
            return data;
    }
    
    public CCustomType ToCustomType(Enumerations.CustomTypes type) {
        switch (type){
            case Text:
                return CCustomType.CCustomType_Text;
            case Numeric:
                return CCustomType.CCustomType_Numeric;
            case DateTime:
                return CCustomType.CCustomType_DateTime;
            case Boolean:
                return CCustomType.CCustomType_Boolean;
            default:
                throw new AssertionError(type.name());
        }
    }
    
    public Enumerations.CustomTypes ToCustomType(CCustomType type) {
        switch (type) {
            case CCustomType_Text:
                return Enumerations.CustomTypes.Text;
            case CCustomType_Numeric:
                return Enumerations.CustomTypes.Numeric;
            case CCustomType_DateTime:
                return Enumerations.CustomTypes.DateTime;
            case CCustomType_Boolean:
                return Enumerations.CustomTypes.Boolean;
            default:
                throw new AssertionError(type.name());
        }
    }
                        
    public ReturnCode ToReturnCode(CReturnCode ccode) {
        
        switch (ccode) {
            case CReturnCode_UNKNOWN:
                return Enumerations.ReturnCode.Unknown;
            case CReturnCode_OK:
                return Enumerations.ReturnCode.Ok;
            case CReturnCode_ERROR:
                return Enumerations.ReturnCode.Error;
            case CReturnCode_PARAMETER_ERROR:
                return Enumerations.ReturnCode.ParameterError;
            case CReturnCode_INTERNAL_SERVER_ERROR:
                return Enumerations.ReturnCode.InternalServerError;
            case CReturnCode_REQUEST_NOT_IN_PROFILE:
                return Enumerations.ReturnCode.RequestNotInProfile;
            case CReturnCode_SYSTEM_NOT_INITIALIZED:
                return Enumerations.ReturnCode.SystemNotInitialized;
            case CReturnCode_SYSTEM_ALREADY_INITIALIZED:
                return Enumerations.ReturnCode.SystemAlreadyInitialized;
            case CReturnCode_NO_LICENSE_AVAILABLE:
                return Enumerations.ReturnCode.NoLicenseAvailable;
            case CReturnCode_LOGIN_FAILED:
                return Enumerations.ReturnCode.LoginFailed;
            case CReturnCode_NOT_LOGGED_IN:
                return Enumerations.ReturnCode.NotLoggedIn;
            case CReturnCode_GET_USERNAME_ERROR:
                return Enumerations.ReturnCode.GetUsernameError;
            case CReturnCode_REMOTING_SETUP_REGCHAN_ERROR:
                return Enumerations.ReturnCode.RemotingSetupRegChanError;
            case CReturnCode_REMOTING_SETUP_CONNECT_ERROR:
                return Enumerations.ReturnCode.RemotingSetupConnectError;
            case CReturnCode_REMOTING_EXCEPTION:
                return Enumerations.ReturnCode.RemotingException;
            case CReturnCode_DATABASE_INIT_FAILURE:
                return Enumerations.ReturnCode.DatabaseInitFailure;
            case CReturnCode_PARROT_INIT_FAILURE:
                return Enumerations.ReturnCode.ParrotInitFailure;
            case CReturnCode_WEB_INIT_FAILURE:
                return Enumerations.ReturnCode.WebInitFailure;
            case CReturnCode_RECORDER_INIT_FAILURE:
                return Enumerations.ReturnCode.RecorderInitFailure;
            case CReturnCode_DATABASE_ERROR:
                return Enumerations.ReturnCode.DatabaseError;
            case CReturnCode_WEBSERVER_ERROR:
                return Enumerations.ReturnCode.WebServerError;
            case CReturnCode_HTTP_PROTO_NOT_SET:
                return Enumerations.ReturnCode.HttpProtoNotSet;
            case CReturnCode_WEBSERVER_INTERNAL_ERROR:
                return Enumerations.ReturnCode.WebServerInternalError;
            case CReturnCode_HTTP_LOGIN_FAILURE:
                return Enumerations.ReturnCode.HttpLoginFailure;
            case CReturnCode_HTTP_GETCALL_FAILURE:
                return Enumerations.ReturnCode.HttpGetCallFailure;
            case CReturnCode_HTTP_FETCHAUDIO_FAILURE:
                return Enumerations.ReturnCode.HttpFetchAudioFailure;
            case CReturnCode_ERROR_IPCONFIG:
                return Enumerations.ReturnCode.ErrorIpConfig;
            case CReturnCode_UNKNOWN_USER:
                return Enumerations.ReturnCode.UnknownUser;
            case CReturnCode_USER_NOT_FREESEATING:
                return Enumerations.ReturnCode.UserNotFreeSeating;
            case CReturnCode_UNKNOWN_CHANNEL:
                return Enumerations.ReturnCode.UnknownChannel;
            case CReturnCode_CHANNEL_NOT_FREESEATING:
                return Enumerations.ReturnCode.ChannelNotFreeSeating;
            case CReturnCode_CANNOT_UPDATE_READONLY_FIELDS:
                return Enumerations.ReturnCode.CannotUpdateReadonlyFields;
            case CReturnCode_CHANNEL_OUT_OF_RANGE:
                return Enumerations.ReturnCode.ChannelOutOfRange;
            case CReturnCode_CHANNEL_NOT_REALTIME:
                return Enumerations.ReturnCode.ChannelNotRealtime;
            case CReturnCode_ALREADY_IN_REQUESTED_STATE:
                return Enumerations.ReturnCode.AlreadyInRequestedState;
            case CReturnCode_RETENTION_CAN_ONLY_BE_EXTENDED:
                return Enumerations.ReturnCode.RetentionCanOnlyBeExtended;
            case CReturnCode_LITIGATION_HOLD_MAX_HOLDS_REACHED:
                return Enumerations.ReturnCode.LitigationHoldMaxHoldsReached;
            case CReturnCode_LITIGATION_HOLD_ALREADY_SET:
                return Enumerations.ReturnCode.LitigationHoldAlreadySet;
            case CReturnCode_LITIGATION_HOLD_NOT_FOUND:
                return Enumerations.ReturnCode.LitigationHoldNotFound;
            case CReturnCode_LITIGATION_HOLD_NO_HOLD_FOR_USER_FOUND:
                return Enumerations.ReturnCode.LitigationHoldNoHoldForUserFound;
            case CReturnCode_FEATURE_NOT_SUPPORTED:
                return Enumerations.ReturnCode.FeatureNotSupported;
            case CReturnCode_CANCELLED_BY_USER:
                return Enumerations.ReturnCode.CancelledByUser;
            default:
                return Enumerations.ReturnCode.Unknown;
                
        }
    }
        
    public CDirection ToDirection(Direction dir) {
        
        switch(dir){
            case Inbound:
                return CDirection.CDirection_Inbound;
            case Outbound:
                return CDirection.CDirection_Outbound;
            case Unknown:
            default:
                return CDirection.CDirection_Unknown;
        }
    
    }
    
    public CSortDirection ToSortDirection(SortDirection dir) {
        switch (dir){
            case Ascending:
                return CSortDirection.CSortDirection_Ascending;
            case Decending:
            default:
                return CSortDirection.CSortDirection_Decending;
        }
    }
    
    public CCallDetailFieldSelection ToFieldSelection(SortField field) {
        switch(field){
            case Start:
                return CCallDetailFieldSelection.CCallDetailFieldSelection_Start;
            case End:
                return CCallDetailFieldSelection.CCallDetailFieldSelection_End;
            case CvsKey:
                return CCallDetailFieldSelection.CCallDetailFieldSelection_CvsKey;
            case Channel:
                return CCallDetailFieldSelection.CCallDetailFieldSelection_Channel;
            case Status:
                return CCallDetailFieldSelection.CCallDetailFieldSelection_Status;
            case Mark:
                return CCallDetailFieldSelection.CCallDetailFieldSelection_Mark;
            case IdUser:
                return CCallDetailFieldSelection.CCallDetailFieldSelection_IdUser;
            case CLI:
                return CCallDetailFieldSelection.CCallDetailFieldSelection_CLI;
            case PbxCallId:
                return CCallDetailFieldSelection.CCallDetailFieldSelection_PbxCallId;
            case Phone:
            default:
                return CCallDetailFieldSelection.CCallDetailFieldSelection_Phone;
        }
    }
    
    String ToDateTimeString(OffsetDateTime time) {
        //Java XXX produces 'Z' for UTC timezones, whilst C# expects +00:00
        //For non UTC timezone XXX produces +|-mm:hh formats
        return time.format(DateTimeFormatter.ofPattern("YYYY-MM-dd'T'HH:mm:ss.SSSXXX")).replace("Z", "+00:00");
    }
}

