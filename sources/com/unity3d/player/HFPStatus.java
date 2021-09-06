package com.unity3d.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;

public class HFPStatus {

    /* renamed from: a */
    private Context f43a;

    /* renamed from: b */
    private BroadcastReceiver f44b = null;

    /* renamed from: c */
    private Intent f45c = null;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f46d = false;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public AudioManager f47e = null;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public int f48f = C0013a.f50a;

    /* renamed from: com.unity3d.player.HFPStatus$a */
    enum C0013a {
        ;

        static {
            f53d = new int[]{f50a, f51b, f52c};
        }
    }

    public HFPStatus(Context context) {
        this.f43a = context;
        this.f47e = (AudioManager) this.f43a.getSystemService("audio");
        initHFPStatusJni();
    }

    private final native void deinitHFPStatusJni();

    private final native void initHFPStatusJni();

    /* renamed from: a */
    public final void mo64a() {
        deinitHFPStatusJni();
    }

    /* access modifiers changed from: protected */
    public boolean getHFPStat() {
        return this.f48f == C0013a.f51b;
    }

    /* access modifiers changed from: protected */
    public void requestHFPStat() {
        this.f44b = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                if (intExtra == -1) {
                    return;
                }
                if (intExtra == 0) {
                    if (HFPStatus.this.f46d) {
                        HFPStatus.this.f47e.setMode(0);
                    }
                    boolean unused = HFPStatus.this.f46d = false;
                } else if (intExtra == 1) {
                    int unused2 = HFPStatus.this.f48f = C0013a.f51b;
                    if (!HFPStatus.this.f46d) {
                        HFPStatus.this.f47e.stopBluetoothSco();
                    } else {
                        HFPStatus.this.f47e.setMode(3);
                    }
                } else if (intExtra == 2) {
                    if (HFPStatus.this.f48f == C0013a.f51b) {
                        boolean unused3 = HFPStatus.this.f46d = true;
                    } else {
                        int unused4 = HFPStatus.this.f48f = C0013a.f52c;
                    }
                }
            }
        };
        this.f45c = this.f43a.registerReceiver(this.f44b, new IntentFilter("android.media.ACTION_SCO_AUDIO_STATE_UPDATED"));
        try {
            this.f47e.startBluetoothSco();
        } catch (NullPointerException unused) {
            C0068g.Log(5, "startBluetoothSco() failed. no bluetooth device connected.");
        }
    }
}
