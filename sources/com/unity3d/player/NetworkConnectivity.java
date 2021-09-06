package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

public class NetworkConnectivity extends Activity {

    /* renamed from: a */
    private final int f54a = 0;

    /* renamed from: b */
    private final int f55b;

    /* renamed from: c */
    private final int f56c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public int f57d;

    /* renamed from: e */
    private ConnectivityManager f58e;

    /* renamed from: f */
    private final ConnectivityManager.NetworkCallback f59f;

    public NetworkConnectivity(Context context) {
        int i = 1;
        this.f55b = 1;
        this.f56c = 2;
        this.f57d = 0;
        this.f59f = new ConnectivityManager.NetworkCallback() {
            public final void onAvailable(Network network) {
                super.onAvailable(network);
            }

            public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                NetworkConnectivity networkConnectivity;
                int i;
                super.onCapabilitiesChanged(network, networkCapabilities);
                if (networkCapabilities.hasTransport(0)) {
                    networkConnectivity = NetworkConnectivity.this;
                    i = 1;
                } else {
                    networkConnectivity = NetworkConnectivity.this;
                    i = 2;
                }
                int unused = networkConnectivity.f57d = i;
            }

            public final void onLost(Network network) {
                super.onLost(network);
                int unused = NetworkConnectivity.this.f57d = 0;
            }

            public final void onUnavailable() {
                super.onUnavailable();
                int unused = NetworkConnectivity.this.f57d = 0;
            }
        };
        this.f58e = (ConnectivityManager) context.getSystemService("connectivity");
        this.f58e.registerDefaultNetworkCallback(this.f59f);
        NetworkInfo activeNetworkInfo = this.f58e.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            this.f57d = activeNetworkInfo.getType() != 0 ? 2 : i;
        }
    }

    /* renamed from: a */
    public final int mo70a() {
        return this.f57d;
    }

    /* renamed from: b */
    public final void mo71b() {
        this.f58e.unregisterNetworkCallback(this.f59f);
    }
}
