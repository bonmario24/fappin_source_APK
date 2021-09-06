package com.unity3d.player;

import android.util.Log;

/* renamed from: com.unity3d.player.g */
final class C0068g {

    /* renamed from: a */
    protected static boolean f213a = false;

    protected static void Log(int i, String str) {
        if (!f213a) {
            if (i == 6) {
                Log.e("Unity", str);
            }
            if (i == 5) {
                Log.w("Unity", str);
            }
        }
    }
}
