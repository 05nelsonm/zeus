package com.zeus.topl_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import io.matthewnelson.topl_service.TorServiceController;

public class ZeusTorModule extends ReactContextBaseJavaModule {

    private static ReactApplicationContext reactContext;

    ZeusTorModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "ZeusTorModule";
    }

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

    @ReactMethod
    public void getControlPortAddress(Callback successCallback, Callback errorCallback) {
        try {
            successCallback.invoke(getEventBroadcaster().getTorPortInfo().getControlPort());
        } catch (ClassCastException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void getHttpPortAddress(Callback successCallback, Callback errorCallback) {
        try {
            successCallback.invoke(getEventBroadcaster().getTorPortInfo().getHttpPort());
        } catch (ClassCastException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void getSocksPortAddress(Callback successCallback, Callback errorCallback) {
        try {
            successCallback.invoke(getEventBroadcaster().getTorPortInfo().getSocksPort());
        } catch (ClassCastException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void getTorState(Callback successCallback, Callback errorCallback) {
        try {
            successCallback.invoke(getEventBroadcaster().getTorState());
        } catch (ClassCastException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    @ReactMethod
    public void getTorNetworkState(Callback successCallback, Callback errorCallback) {
        try {
            successCallback.invoke(getEventBroadcaster().getTorNetworkState());
        } catch (ClassCastException e) {
            errorCallback.invoke(e.getMessage());
        }
    }

    /**
     * Will throw the ClassCastException if called before MainApplication.setupTor() because
     * appEventBroadcaster is null (hasn't been set yet).
     * */
    private ZeusTorEventBroadcaster getEventBroadcaster() throws ClassCastException {
        return (ZeusTorEventBroadcaster) TorServiceController.Companion.getAppEventBroadcaster();
    }
}
