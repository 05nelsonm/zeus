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
 * */
public class ZeusTorEventBroadcaster extends TorServiceEventBroadcaster {

    ///////////////////////
    /// PortInformation ///
    ///////////////////////
    volatile private TorPortInfo torPortInfo = new TorPortInfo(null, null, null, null, null);

    public TorPortInfo getTorPortInfo() {
        return torPortInfo;
    }

    @Override
    public void broadcastPortInformation(@NonNull TorPortInfo torPortInfo) {
        this.torPortInfo = torPortInfo;
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
    @Override
    public void broadcastException(@Nullable String msg, @NonNull Exception e) {}

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
    private String torState = TorState.OFF;
    private String torNetworkState = TorNetworkState.DISABLED;

    @NonNull
    public String getTorState() {
        return torState;
    }

    @NonNull
    public String getTorNetworkState() {
        return torNetworkState;
    }

    @Override
    public void broadcastTorState(@NonNull String torState, @NonNull String torNetworkState) {
        this.torState = torState;
        this.torNetworkState = torNetworkState;
    }
}
