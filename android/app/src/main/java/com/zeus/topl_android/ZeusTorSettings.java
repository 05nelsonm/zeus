package com.zeus.topl_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import io.matthewnelson.topl_core_base.TorSettings;

public class ZeusTorSettings extends TorSettings {

    @NonNull
    @Override
    public String getConnectionPadding() {
        return DEFAULT__HAS_CONNECTION_PADDING;
    }

    @Nullable
    @Override
    public String getCustomTorrc() {
        return null;
    }

    @Override
    public boolean getDisableNetwork() {
        return DEFAULT__DISABLE_NETWORK;
    }

    @NonNull
    @Override
    public String getDnsPort() {
        return DEFAULT__DNS_PORT;
    }

    @Nullable
    @Override
    public String getEntryNodes() {
        return DEFAULT__ENTRY_NODES;
    }

    @Nullable
    @Override
    public String getExcludeNodes() {
        return DEFAULT__EXCLUDED_NODES;
    }

    @Nullable
    @Override
    public String getExitNodes() {
        return DEFAULT__EXIT_NODES;
    }

    @Override
    public boolean getHasBridges() {
        return DEFAULT__HAS_BRIDGES;
    }

    @Override
    public boolean getHasCookieAuthentication() {
        return DEFAULT__HAS_COOKIE_AUTHENTICATION;
    }

    @Override
    public boolean getHasDebugLogs() {
        return DEFAULT__HAS_DEBUG_LOGS;
    }

    @Override
    public boolean getHasDormantCanceledByStartup() {
        return DEFAULT__HAS_DORMANT_CANCELED_BY_STARTUP;
    }

    @Override
    public boolean getHasIsolationAddressFlagForTunnel() {
        return DEFAULT__HAS_ISOLATION_ADDRESS_FLAG_FOR_TUNNEL;
    }

    @Override
    public boolean getHasOpenProxyOnAllInterfaces() {
        return DEFAULT__HAS_OPEN_PROXY_ON_ALL_INTERFACES;
    }

    @Override
    public boolean getHasReachableAddress() {
        return DEFAULT__HAS_REACHABLE_ADDRESS;
    }

    @Override
    public boolean getHasReducedConnectionPadding() {
        return DEFAULT__HAS_REDUCED_CONNECTION_PADDING;
    }

    @Override
    public boolean getHasSafeSocks() {
        return DEFAULT__HAS_SAFE_SOCKS;
    }

    @Override
    public boolean getHasStrictNodes() {
        return DEFAULT__HAS_STRICT_NODES;
    }

    @Override
    public boolean getHasTestSocks() {
        return DEFAULT__HAS_TEST_SOCKS;
    }

    @NonNull
    @Override
    public String getHttpTunnelPort() {
        return "auto";
    }

    @Override
    public boolean isAutoMapHostsOnResolve() {
        return DEFAULT__IS_AUTO_MAP_HOSTS_ON_RESOLVE;
    }

    @Override
    public boolean isRelay() {
        return DEFAULT__IS_RELAY;
    }

    @NonNull
    @Override
    public List<String> getListOfSupportedBridges() {
        List<String> bridgeType = new ArrayList<String>();
        bridgeType.add(SupportedBridges.MEEK);
        bridgeType.add(SupportedBridges.OBFS4);
        bridgeType.add(SupportedBridges.SNOWFLAKE);
        return bridgeType;
    }

    @Nullable
    @Override
    public String getProxyHost() {
        return DEFAULT__PROXY_HOST;
    }

    @Nullable
    @Override
    public String getProxyPassword() {
        return DEFAULT__PROXY_PASSWORD;
    }

    @Nullable
    @Override
    public Integer getProxyPort() {
        return null;
    }

    @Nullable
    @Override
    public String getProxySocks5Host() {
        return DEFAULT__PROXY_SOCKS5_HOST;
    }

    @Nullable
    @Override
    public Integer getProxySocks5ServerPort() {
        return null;
    }

    @Nullable
    @Override
    public String getProxyType() {
        return DEFAULT__PROXY_TYPE;
    }

    @Nullable
    @Override
    public String getProxyUser() {
        return DEFAULT__PROXY_USER;
    }

    @NonNull
    @Override
    public String getReachableAddressPorts() {
        return DEFAULT__REACHABLE_ADDRESS_PORTS;
    }

    @Nullable
    @Override
    public String getRelayNickname() {
        return DEFAULT__RELAY_NICKNAME;
    }

    @Nullable
    @Override
    public Integer getRelayPort() {
        return null;
    }

    @Override
    public boolean getRunAsDaemon() {
        return false;
    }

    @NonNull
    @Override
    public String getSocksPort() {
        return "auto";
    }

    @NonNull
    @Override
    public String getTransPort() {
        return DEFAULT__TRANS_PORT;
    }

    @Override
    public boolean getUseSocks5() {
        return DEFAULT__USE_SOCKS5;
    }

    @Nullable
    @Override
    public String getVirtualAddressNetwork() {
        return "10.192.0.2/10";
    }
}
