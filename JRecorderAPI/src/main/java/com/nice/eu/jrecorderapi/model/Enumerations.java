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
 * A collection of enumerations for call and channel models
 * @author swilkinson
 */
public class Enumerations {
    
    public enum ChannelState{
        Started,
        Stopped
    }
    
    public enum CustomTypes{
        Text,
        Numeric,
        DateTime,
        Boolean
    }
    
    public enum ArchivingSupportLevel{
        NotSupported,
        Supported,
        Extended
    }
    
    public enum BulkRetrieveSupportLevel{
        BasicSupport,
        FullSupport
    }
    
    public enum Direction{
        Unknown,
        Inbound,
        Outbound
    }
    
    public enum SortDirection{
        Ascending,
        Decending
    }
    
    public enum SortField{
        Start,
        End,
        CvsKey,
        Channel,
        Status,
        Mark,
        IdUser,
        CLI,
        Phone,
        PbxCallId
    }
    
    public enum ReturnCode{
        Unknown,
        Ok,
        Error,
        ParameterError,
        InternalServerError,
        RequestNotInProfile,
        SystemNotInitialized,
        SystemAlreadyInitialized,
        NoLicenseAvailable,
        LoginFailed,
        NotLoggedIn,
        GetUsernameError,
        RemotingSetupRegChanError,
        RemotingSetupConnectError,
        RemotingException,
        DatabaseInitFailure,
        ParrotInitFailure,
        WebInitFailure,
        RecorderInitFailure,
        DatabaseError,
        WebServerError,
        HttpProtoNotSet,
        WebServerInternalError,
        HttpLoginFailure,
        HttpGetCallFailure,
        HttpFetchAudioFailure,
        ErrorIpConfig,
        UnknownUser,
        UserNotFreeSeating,
        UnknownChannel,
        ChannelNotFreeSeating,
        CannotUpdateReadonlyFields,
        ChannelOutOfRange,
        ChannelNotRealtime,
        AlreadyInRequestedState,
        RetentionCanOnlyBeExtended,
        LitigationHoldMaxHoldsReached,
        LitigationHoldAlreadySet,
        LitigationHoldNotFound,
        LitigationHoldNoHoldForUserFound,
        FeatureNotSupported,
        CancelledByUser        
    }
    
}
