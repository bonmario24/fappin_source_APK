package com.unity3d.player;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/* renamed from: com.unity3d.player.h */
public final class C0069h implements C0066e {
    /* renamed from: a */
    private static boolean m114a(PackageItemInfo packageItemInfo) {
        try {
            return packageItemInfo.metaData.getBoolean("unityplayer.SkipPermissionsDialog");
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public final void mo223a(Activity activity, String str) {
        if (activity != null && str != null) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            if (fragmentManager.findFragmentByTag("96489") == null) {
                C0070i iVar = new C0070i();
                Bundle bundle = new Bundle();
                bundle.putString("PermissionNames", str);
                iVar.setArguments(bundle);
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(0, iVar, "96489");
                beginTransaction.commit();
            }
        }
    }

    /* renamed from: a */
    public final boolean mo224a(Activity activity) {
        try {
            PackageManager packageManager = activity.getPackageManager();
            return m114a((PackageItemInfo) packageManager.getActivityInfo(activity.getComponentName(), 128)) || m114a((PackageItemInfo) packageManager.getApplicationInfo(activity.getPackageName(), 128));
        } catch (Exception unused) {
            return false;
        }
    }
}
