package app.zeusln.zeus;

import android.app.Application;

import com.facebook.react.PackageList;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.facebook.soloader.SoLoader;
import com.zeus.topl_android.ZeusTorEventBroadcaster;
import com.zeus.topl_android.ZeusTorSettings;

import java.util.List;

import io.matthewnelson.topl_service.TorServiceController;
import io.matthewnelson.topl_service.lifecycle.BackgroundManager;
import io.matthewnelson.topl_service.notification.ServiceNotification;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }
        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          return packages;
        }
        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
      };


  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);

    // setup Tor first before calling any APIs
    setupTor(this);

    // put this anywhere (maybe after successful login)
    TorServiceController.Companion.startTor();
  }

  // commented helper methods out until return type kotlin.Unit issue can be resolved.
//  public void newTorIdentity() {
//    TorServiceController.Companion.newIdentity();
//  }
//
//  public void restartTor() {
//    TorServiceController.Companion.restartTor();
//  }
//
//  /**
//   * A RuntimeException is thrown if this method is called before setupTor. In that event,
//   * this will return `false` instead of throwing an exception. Will return `true` if no
//   * Exception was thrown.
//   * */
//  public boolean startTor() {
//    try {
//      TorServiceController.Companion.startTor();
//      return true;
//    } catch (RuntimeException ignored) {
//      return false;
//    }
//  }
//
//  public void stopTor() {
//    TorServiceController.Companion.stopTor();
//  }

  @Nullable
  public String getControlPort() {
    try {
      return getEventBroadcaster().getControlPortAddress();
    } catch (ClassCastException e) {
      return null;
    }
  }

  @Nullable
  public String getHttpAddress() {
    try {
      return getEventBroadcaster().getHttpPortAddress();
    } catch (ClassCastException e) {
      return null;
    }
  }

  @Nullable
  public String getSocksAddress() {
    try {
      return getEventBroadcaster().getSocksPortAddress();
    } catch (ClassCastException e) {
      return null;
    }
  }

  private ServiceNotification.Builder generateServiceNotificationBuilder() {
    return new ServiceNotification.Builder(
          "ZeusLN Tor",
          "TorService",
          "TorOnionProxyLibrary-Android",
          615
    )
          // customize the images later with your own images
          .setImageTorNetworkingEnabled(R.drawable.tor_stat_network_enabled)
          .setImageTorNetworkingDisabled(R.drawable.tor_stat_network_disabled)
          .setImageTorDataTransfer(R.drawable.tor_stat_network_dataxfer)
          .setImageTorErrors(R.drawable.tor_stat_notifyerr)

          // choose a color that you like more
          .setCustomColor(R.color.purple)

          // lock screen visibility
          .setVisibility(NotificationCompat.VISIBILITY_SECRET)

          // notification buttons (New ID is always present)
          .enableTorRestartButton(true)
          .enableTorStopButton(true)

          // enable/disable showing of notification
          .showNotification(true);
  }

  private BackgroundManager.Builder.Policy generateBackgroundManagerPolicy() {
    return new BackgroundManager.Builder()
          .respectResourcesWhileInBackground(20);
  }

  private void setupTor(Application application) {
    new TorServiceController.Builder(
          application,
          generateServiceNotificationBuilder(),
          generateBackgroundManagerPolicy(),
          BuildConfig.VERSION_CODE,
          new ZeusTorSettings(),
          "common/geoip",
          "common/geoip6"
    )
          .setBuildConfigDebug(BuildConfig.DEBUG)

          // is available from TorServiceController.Companion.appEventBroadcaster
          // just cast it as ZeusTorEventBroadcaster
          .setEventBroadcaster(new ZeusTorEventBroadcaster())
          .build();
  }

  /**
   * Will throw the ClassCastException if called before setupTor() because
   * appEventBroadcaster is null (hasn't been set yet).
   * */
  private ZeusTorEventBroadcaster getEventBroadcaster() throws ClassCastException {
    return (ZeusTorEventBroadcaster) TorServiceController.Companion.getAppEventBroadcaster();
  }
}
