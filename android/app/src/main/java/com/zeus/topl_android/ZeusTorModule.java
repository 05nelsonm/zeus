package com.zeus.topl_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import io.matthewnelson.topl_core_base.BaseConsts.TorState;
import io.matthewnelson.topl_core_base.BaseConsts.TorNetworkState;
import io.matthewnelson.topl_service.TorServiceController;
import io.matthewnelson.topl_service.service.components.onionproxy.model.TorPortInfo;

public class ZeusTorModule extends ReactContextBaseJavaModule {

    @Nullable
    private static ReactApplicationContext reactContext;

    ZeusTorModule(@NonNull ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "ZeusTorModule";
    }


    ////////////////////////////
    /// TorServiceController ///
    ////////////////////////////
    @ReactMethod
    public void newTorIdentity() {
        TorServiceController.Companion.newIdentity();
    }

    @ReactMethod
    public void restartTor() {
        TorServiceController.Companion.restartTor();
    }

    @ReactMethod
    public void startTor(Callback errorCallback) {
        try {
            TorServiceController.Companion.startTor();
        } catch (RuntimeException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void stopTor() {
        TorServiceController.Companion.stopTor();
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
    public void getControlPortAddress(Promise promise) {
        promise.resolve(torPortInfo.getControlPort());
    }

    @ReactMethod
    public void getDnsPortAddress(Promise promise) {
        promise.resolve(torPortInfo.getDnsPort());
    }

    @ReactMethod
    public void getHttpPortAddress(Promise promise) {
        promise.resolve(torPortInfo.getHttpPort());
    }

    @ReactMethod
    public void getSocksPortAddress(Promise promise) {
        promise.resolve(torPortInfo.getSocksPort());
    }

    @ReactMethod
    public void getTransPortAddress(Promise promise) {
        promise.resolve(torPortInfo.getTransPort());
    }

    static void updateTorPortInfo(TorPortInfo torPortInfo) {
        ZeusTorModule.torPortInfo = torPortInfo;

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
    public void getTorState(Promise promise) {
        promise.resolve(torState);
    }

    @ReactMethod
    public void getTorNetworkState(Promise promise) {
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

    private static void sendEvent(String eventName, @Nullable WritableMap params) {
        if (reactContext == null) return;
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    private static void sendEvent(String eventName, @Nullable String string) {
        if (reactContext == null) return;
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, string);
    }
}
