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
using System.Runtime.InteropServices;

namespace RecorderAPI2COM.Model
{

//Suppress deprecated warnings on API enum
#pragma warning disable 0612

    /// <summary>
    /// COM visible Recorder API ReturnCode enumeration
    /// </summary>
    [Guid("29D61908-8C6C-4D42-9331-595AD733AF7C")]
    public enum CReturnCode
    {
        UNKNOWN = ReturnCode.UNKNOWN,
        //
        // Summary:
        //     The method executed successfully.
        OK = ReturnCode.OK,
        //
        // Summary:
        //     Generic error return. An non-specified error has been found.
        ERROR = ReturnCode.ERROR,
        //
        // Summary:
        //     One of the parameters of the method was not in the allowed ranges, or a combination
        //     of the parameters is not correct.
        PARAMETER_ERROR = ReturnCode.PARAMETER_ERROR,
        //
        // Summary:
        //     An internal server system error occurred. Please report the error to CyberTech,
        //     including the log for the server reporting the error.
        INTERNAL_SERVER_ERROR = ReturnCode.INTERNAL_SERVER_ERROR,
        //
        // Summary:
        //     The request tries to access information that is not allowed according to the
        //     user's profile.
        REQUEST_NOT_IN_PROFILE = ReturnCode.REQUEST_NOT_IN_PROFILE,
        //
        // Summary:
        //     The connection is not initialized. Please connect first.
        SYSTEM_NOT_INITIALIZED = ReturnCode.SYSTEM_NOT_INITIALIZED,
        //
        // Summary:
        //     The system is already initialized (i.e. the user is already connected).
        SYSTEM_ALREADY_INITIALIZED = ReturnCode.SYSTEM_ALREADY_INITIALIZED,
        //
        // Summary:
        //     There is no license available for the client.
        NO_LICENSE_AVAILABLE = ReturnCode.NO_LICENSE_AVAILABLE,
        //
        // Summary:
        //     The login failed. Either username or password is incorrect or the user has insufficient
        //     rights to log on to the Recorder API.
        LOGIN_FAILED = ReturnCode.LOGIN_FAILED,
        //
        // Summary:
        //     The client is not logged in.
        NOT_LOGGED_IN = ReturnCode.NOT_LOGGED_IN,
        //
        // Summary:
        //     GetWindowsUser() failed.
        GET_USERNAME_ERROR = ReturnCode.GET_USERNAME_ERROR,
        //
        // Summary:
        //     The TCP channel needed for connections between client and server could not be
        //     registered.
        //
        // Remarks:
        //     THe TCP port may already be in use.
        REMOTING_SETUP_REGCHAN_ERROR = ReturnCode.REMOTING_SETUP_REGCHAN_ERROR,
        //
        // Summary:
        //     Can not connect to the remote server. Hostname or port may be incorrect. Service
        //     may not be started.
        REMOTING_SETUP_CONNECT_ERROR = ReturnCode.REMOTING_SETUP_CONNECT_ERROR,
        //
        // Summary:
        //     A remoting exception occurred.
        REMOTING_EXCEPTION = ReturnCode.REMOTING_EXCEPTION,
        //
        // Summary:
        //     The database subsystem failed to initialise. See the API service log for details.
        DATABASE_INIT_FAILURE = ReturnCode.DATABASE_INIT_FAILURE,
        //
        // Summary:
        //     The Parrot API subsystem failed to initialise. See the API service log for details.
        PARROT_INIT_FAILURE = ReturnCode.PARROT_INIT_FAILURE,
        //
        // Summary:
        //     The web subsystem failed to initialise. See the API service log for details.
        WEB_INIT_FAILURE = ReturnCode.WEB_INIT_FAILURE,
        //
        // Summary:
        //     The recorder API subsystem failed to initialise. See the API service log for
        //     details.
        RECORDER_INIT_FAILURE = ReturnCode.RECORDER_INIT_FAILURE,
        //
        // Summary:
        //     An error occurred while communicating with the database. See the API service
        //     log for details.
        //
        // Remarks:
        //     Possible errors for instance are: Could not connect. Could not get reader object.
        //     Could not execute query. Could not read the result from the query.
        DATABASE_ERROR = ReturnCode.DATABASE_ERROR,
        //
        // Summary:
        //     An error occurred while communicating with the web server. See the API service
        //     log for details.
        WEBSERVER_ERROR = ReturnCode.WEBSERVER_ERROR,
        //
        // Summary:
        //     not in use anymore. Replaced with CyberTech.RecorderApi.ReturnCode.WEBSERVER_ERROR.

        HTTP_PROTO_NOT_SET = ReturnCode.HTTP_PROTO_NOT_SET,
        //
        // Summary:
        //     not in use anymore. Replaced with CyberTech.RecorderApi.ReturnCode.WEBSERVER_ERROR.
        WEBSERVER_INTERNAL_ERROR = ReturnCode.WEBSERVER_INTERNAL_ERROR,
        //
        // Summary:
        //     not in use anymore. Replaced with CyberTech.RecorderApi.ReturnCode.WEBSERVER_ERROR.
        HTTP_LOGIN_FAILURE = ReturnCode.HTTP_LOGIN_FAILURE,
        //
        // Summary:
        //     not in use anymore. Replaced with CyberTech.RecorderApi.ReturnCode.WEBSERVER_ERROR.
        HTTP_GETCALL_FAILURE = ReturnCode.HTTP_GETCALL_FAILURE,
        //
        // Summary:
        //     not in use anymore. Replaced with CyberTech.RecorderApi.ReturnCode.WEBSERVER_ERROR.
        HTTP_FETCHAUDIO_FAILURE = ReturnCode.HTTP_FETCHAUDIO_FAILURE,
        //
        // Summary:
        //     The local hostname or IP address could not be determined.
        ERROR_IPCONFIG = ReturnCode.ERROR_IPCONFIG,
        //
        // Summary:
        //     The user is unknown.
        UNKNOWN_USER = ReturnCode.UNKNOWN_USER,
        //
        // Summary:
        //     The User cannot register for free seating.
        USER_NOT_FREESEATING = ReturnCode.USER_NOT_FREESEATING,
        //
        // Summary:
        //     The channel number, or host name is unknown.
        UNKNOWN_CHANNEL = ReturnCode.UNKNOWN_CHANNEL,
        //
        // Summary:
        //     The channel cannot be used for free seating.
        CHANNEL_NOT_FREESEATING = ReturnCode.CHANNEL_NOT_FREESEATING,
        //
        // Summary:
        //     Not all fields in a CallDetail record can be used for updating a call.
        CANNOT_UPDATE_READONLY_FIELDS = ReturnCode.CANNOT_UPDATE_READONLY_FIELDS,
        //
        // Summary:
        //     The given channel does not exist in the system.
        CHANNEL_OUT_OF_RANGE = ReturnCode.CHANNEL_OUT_OF_RANGE,
        //
        // Summary:
        //     Channels is not real-time.
        CHANNEL_NOT_REALTIME = ReturnCode.CHANNEL_NOT_REALTIME,
        //
        // Summary:
        //     The channels is already in the requested state.
        ALREADY_IN_REQUESTED_STATE = ReturnCode.ALREADY_IN_REQUESTED_STATE,
        //
        // Summary:
        //     The retention for an archived file can only be extended.
        RETENTION_CAN_ONLY_BE_EXTENDED = ReturnCode.RETENTION_CAN_ONLY_BE_EXTENDED,
        //
        // Summary:
        //     The maximum number of litigation holds has already been reached for this call.
        LITIGATION_HOLD_MAX_HOLDS_REACHED = ReturnCode.LITIGATION_HOLD_MAX_HOLDS_REACHED,
        //
        // Summary:
        //     Lititgation hold was already set for this user for this call.
        LITIGATION_HOLD_ALREADY_SET = ReturnCode.LITIGATION_HOLD_ALREADY_SET,
        //
        // Summary:
        //     On remove, the litigation hold was not found for this user.
        LITIGATION_HOLD_NOT_FOUND = ReturnCode.LITIGATION_HOLD_NOT_FOUND,
        // Summary:
        //     On remove, no litigation hold was found for this user.
        LITIGATION_HOLD_NO_HOLD_FOR_USER_FOUND = ReturnCode.LITIGATION_HOLD_NO_HOLD_FOR_USER_FOUND,
        //
        // Summary:
        //     This feature is not supported on this system. Please check CyberTech.RecorderApi.Client.GetServerCapabilities(CyberTech.RecorderApi.ServerCapabilities@).
        FEATURE_NOT_SUPPORTED = ReturnCode.FEATURE_NOT_SUPPORTED,
        //
        // Summary:
        //     The operation was cancelled by the user.
        CANCELLED_BY_USER = ReturnCode.CANCELLED_BY_USER

    }

#pragma warning restore 0612
}
