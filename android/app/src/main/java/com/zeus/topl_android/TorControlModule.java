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

public class TorControlModule extends ReactContextBaseJavaModule {

    @Nullable
    private static ReactApplicationContext reactContext;

    TorControlModule(@NonNull ReactApplicationContext context) {
        super(context);
        reactContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "TorControlModule";
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
}
