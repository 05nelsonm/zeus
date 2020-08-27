package com.zeus.topl_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import io.matthewnelson.topl_core_base.BaseConsts.TorState;
import io.matthewnelson.topl_core_base.BaseConsts.TorNetworkState;
import io.matthewnelson.topl_service.service.components.onionproxy.model.TorPortInfo;

/**
 * Can query for information using the ReactMethods, as well as attach listeners
 * for:
 *  - TorPortChangeEvent
 *  - TorServiceExceptionEvent
 *  - TorStateChangeEvent
 *
 * Events are automatically broadcast while Tor is operating using the appropriate
 * method in TorEventBroadcaster. Those events will be emitted here to the NativeEventEmitter.
 * */
public class TorEventModule extends ReactContextBaseJavaModule {

    @Nullable
    private static ReactApplicationContext reactContext;

    TorEventModule(@NonNull ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "TorEventModule";
    }


    ///////////////////
    /// TorPortInfo ///
    ///////////////////
    private static final String CONTROL_PORT_INFO = "CONTROL_PORT_INFO";
    private static final String DNS_PORT_INFO = "DNS_PORT_INFO";
    private static final String HTTP_PORT_INFO = "HTTP_PORT_INFO";
    private static final String SOCKS_PORT_INFO = "SOCKS_PORT_INFO";
    private static final String TRANS_PORT_INFO = "TRANS_PORT_INFO";

    volatile private static TorPortInfo torPortInfo = new TorPortInfo(null, null, null, null, null);

    @ReactMethod
    public void getControlPortAddress(@NonNull Promise promise) {
        promise.resolve(torPortInfo.getControlPort());
    }

    @ReactMethod
    public void getDnsPortAddress(@NonNull Promise promise) {
        promise.resolve(torPortInfo.getDnsPort());
    }

    @ReactMethod
    public void getHttpPortAddress(@NonNull Promise promise) {
        promise.resolve(torPortInfo.getHttpPort());
    }

    @ReactMethod
    public void getSocksPortAddress(@NonNull Promise promise) {
        promise.resolve(torPortInfo.getSocksPort());
    }

    @ReactMethod
    public void getTransPortAddress(@NonNull Promise promise) {
        promise.resolve(torPortInfo.getTransPort());
    }

    static void updateTorPortInfo(@NonNull TorPortInfo torPortInfo) {
        TorEventModule.torPortInfo = torPortInfo;

        WritableMap params = Arguments.createMap();
        params.putString(CONTROL_PORT_INFO, torPortInfo.getControlPort());
        params.putString(DNS_PORT_INFO, torPortInfo.getDnsPort());
        params.putString(HTTP_PORT_INFO, torPortInfo.getHttpPort());
        params.putString(SOCKS_PORT_INFO, torPortInfo.getSocksPort());
        params.putString(TRANS_PORT_INFO, torPortInfo.getTransPort());
        sendEvent("TorPortChangeEvent", params);
    }


    /////////////////////////////
    /// TorService Exceptions ///
    /////////////////////////////
    static void handleTorServiceException(@NonNull String msg, @NonNull Exception e) {
        sendEvent("TorServiceExceptionEvent", msg);
    }


    /////////////////
    /// Tor State ///
    /////////////////
    private static final String TOR_STATE = "TOR_STATE";
    private static final String TOR_NETWORK_STATE = "TOR_NETWORK_STATE";

    volatile private static String torState = TorState.OFF;
    volatile private static String torNetworkState = TorNetworkState.DISABLED;

    @ReactMethod
    public void getTorState(@NonNull Promise promise) {
        promise.resolve(torState);
    }

    @ReactMethod
    public void getTorNetworkState(@NonNull Promise promise) {
        promise.resolve(torNetworkState);
    }

    static void updateTorState(@TorState String state, @TorNetworkState String networkState) {
        torState = state;
        torNetworkState = networkState;

        WritableMap params = Arguments.createMap();
        params.putString(TOR_STATE, state);
        params.putString(TOR_NETWORK_STATE, networkState);
        sendEvent("TorStateChangeEvent", params);
    }

    private static void sendEvent(@NonNull String eventName, @Nullable WritableMap params) {
        if (reactContext == null) return;
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    private static void sendEvent(@NonNull String eventName, @Nullable String string) {
        if (reactContext == null) return;
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, string);
    }
}
