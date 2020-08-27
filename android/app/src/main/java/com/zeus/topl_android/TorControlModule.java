package com.zeus.topl_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import io.matthewnelson.topl_service.TorServiceController;

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
    public void startTor(@Nullable Callback errorCallback) {
        try {
            // Runtime Exception is thrown if MainApplication.setupTor
            // is not invoked (so basically never... but should catch it none the less)
            TorServiceController.Companion.startTor();
        } catch (RuntimeException e) {
            if (errorCallback != null) {
                errorCallback.invoke(e.getMessage());
            }
        }
    }

    @ReactMethod
    public void stopTor() {
        TorServiceController.Companion.stopTor();
    }
}
