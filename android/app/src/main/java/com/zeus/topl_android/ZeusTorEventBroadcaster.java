package com.zeus.topl_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.matthewnelson.topl_service.service.components.onionproxy.TorServiceEventBroadcaster;

public class ZeusTorEventBroadcaster extends TorServiceEventBroadcaster {


    @Override
    public void broadcastControlPortAddress(@Nullable String controlPortAddress) {

    }

    @Override
    public void broadcastHttpPortAddress(@Nullable String httpPortAddress) {

    }

    @Override
    public void broadcastSocksPortAddress(@Nullable String socksPortAddress) {

    }

    @Override
    public void broadcastBandwidth(@NonNull String bytesRead, @NonNull String bytesWritten) {

    }

    @Override
    public void broadcastDebug(@NonNull String msg) {

    }

    @Override
    public void broadcastException(@Nullable String msg, @NonNull Exception e) {

    }

    @Override
    public void broadcastLogMessage(@Nullable String logMessage) {

    }

    @Override
    public void broadcastNotice(@NonNull String msg) {

    }

    @Override
    public void broadcastTorState(@NonNull String torState, @NonNull String torNetworkState) {

    }
}
