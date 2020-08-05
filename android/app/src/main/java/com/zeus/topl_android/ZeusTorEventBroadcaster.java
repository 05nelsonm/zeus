package com.zeus.topl_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.matthewnelson.topl_service.service.components.onionproxy.TorServiceEventBroadcaster;

/**
 * Ports are broadcast and then set to each variable _after_ Tor has been bootstrapped.
 * If Tor's state changes from ON to anything else (via restart, or stopping), null will
 * be broadcast.
 *
 * This class was instantiated in MainApplication.setupTor and is available from
 * TorServiceController.Companion.appEventBroadcaster
 * */
public class ZeusTorEventBroadcaster extends TorServiceEventBroadcaster {

    ///////////////////////////
    /// ControlPort Address ///
    ///////////////////////////
    @Nullable
    volatile private String controlPortAddress = null;

    /**
     * Will return either:
     *   - null (if Tor is not Bootstrapped)
     *   - 127.0.0.1:PORT (where PORT is the automatically chosen port Tor set for
     *   the Control Port) as a String
     * */
    @Nullable
    public String getControlPortAddress() {
        return controlPortAddress;
    }

    @Override
    public void broadcastControlPortAddress(@Nullable String controlPortAddress) {
        this.controlPortAddress = controlPortAddress;
    }

    ///////////////////////
    /// HttpPortAddress ///
    ///////////////////////
    @Nullable
    volatile private String httpPortAddress = null;

    /**
     * Will return either:
     *   - null (if Tor is not Bootstrapped)
     *   - 127.0.0.1:PORT (where PORT is the automatically chosen port Tor set for
     *   the http proxy) as a String
     * */
    @Nullable
    public String getHttpPortAddress() {
        return httpPortAddress;
    }

    @Override
    public void broadcastHttpPortAddress(@Nullable String httpPortAddress) {
        this.httpPortAddress = httpPortAddress;
    }

    /////////////////////////
    /// SocksPort Address ///
    /////////////////////////
    @Nullable
    volatile private String socksPortAddress = null;

    /**
     * Will return either:
     *   - null (if Tor is not Bootstrapped)
     *   - 127.0.0.1:PORT (where PORT is the automatically chosen port Tor set for
     *   the Socks5 proxy) as a String
     * */
    @Nullable
    public String getSocksPortAddress() {
        return socksPortAddress;
    }

    @Override
    public void broadcastSocksPortAddress(@Nullable String socksPortAddress) {
        this.socksPortAddress = socksPortAddress;
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
