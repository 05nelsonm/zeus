package com.zeus.topl_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.matthewnelson.topl_service.service.components.onionproxy.model.TorPortInfo;
import io.matthewnelson.topl_service.service.components.onionproxy.model.TorServiceEventBroadcaster;

/**
 * Ports are broadcast and then set to each variable _after_ Tor has been bootstrapped.
 * If Tor's state changes from ON to anything else (via restart, or stopping), null will
 * be broadcast.
 *
 * This class was instantiated in MainApplication.setupTor and is available from
 * TorServiceController.Companion.appEventBroadcaster
 */
public class TorEventBroadcaster extends TorServiceEventBroadcaster {

    ///////////////////
    /// TorPortInfo ///
    ///////////////////
    @Override
    public void broadcastPortInformation(@NonNull TorPortInfo torPortInfo) {
        TorEventModule.updateTorPortInfo(torPortInfo);
    }


    /////////////////
    /// Bandwidth ///
    /////////////////
    @Override
    public void broadcastBandwidth(@NonNull String bytesRead, @NonNull String bytesWritten) {}


    //////////////////////
    /// Debug Messages ///
    //////////////////////
    @Override
    public void broadcastDebug(@NonNull String msg) {}


    //////////////////////////
    /// Exception Messages ///
    //////////////////////////
    /**
     * Filters for TorService class, which is the only class that will broadcast an exception
     * if there is an issue with Tor starting/stopping/etc.
     */
    @Override
    public void broadcastException(@Nullable String msg, @NonNull Exception e) {
        if (msg != null && msg.contains("|TorService|")) {
            TorEventModule.handleTorServiceException(msg, e);
        }
        e.printStackTrace();
    }


    ////////////////////
    /// Log Messages ///
    ////////////////////
    @Override
    public void broadcastLogMessage(@Nullable String logMessage) {}


    ///////////////////////
    /// Notice Messages ///
    ///////////////////////
    @Override
    public void broadcastNotice(@NonNull String msg) {}


    /////////////////
    /// Tor State ///
    /////////////////
    @Override
    public void broadcastTorState(@NonNull String torState, @NonNull String torNetworkState) {
        TorEventModule.updateTorState(torState, torNetworkState);
    }
}
