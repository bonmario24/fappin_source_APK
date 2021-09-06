package com.unity3d.player;

import android.os.Build;

/* renamed from: com.unity3d.player.j */
public final class C0071j {

    /* renamed from: a */
    static final boolean f214a = (Build.VERSION.SDK_INT >= 21);

    /* renamed from: b */
    static final boolean f215b = (Build.VERSION.SDK_INT >= 23);

    /* renamed from: c */
    static final boolean f216c;

    /* renamed from: d */
    static final C0066e f217d = (f215b ? new C0069h() : null);

    static {
        boolean z = true;
        if (Build.VERSION.SDK_INT < 24) {
            z = false;
        }
        f216c = z;
    }
}
