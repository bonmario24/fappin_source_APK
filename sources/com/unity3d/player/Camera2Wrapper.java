package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;

public class Camera2Wrapper implements C0065d {

    /* renamed from: a */
    private Context f13a;

    /* renamed from: b */
    private C0056b f14b = null;

    /* renamed from: c */
    private final int f15c = 100;

    public Camera2Wrapper(Context context) {
        this.f13a = context;
        initCamera2Jni();
    }

    /* renamed from: a */
    private static int m3a(float f) {
        return (int) Math.min(Math.max((f * 2000.0f) - 0.0040893555f, -900.0f), 900.0f);
    }

    private final native void deinitCamera2Jni();

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i, int i2, int i3);

    private final native void nativeSurfaceTextureReady(Object obj);

    /* renamed from: a */
    public final void mo21a() {
        deinitCamera2Jni();
        closeCamera2();
    }

    /* renamed from: a */
    public final void mo22a(Object obj) {
        nativeSurfaceTextureReady(obj);
    }

    /* renamed from: a */
    public final void mo23a(Object obj, Object obj2, Object obj3, int i, int i2, int i3) {
        nativeFrameReady(obj, obj2, obj3, i, i2, i3);
    }

    /* access modifiers changed from: protected */
    public void closeCamera2() {
        C0056b bVar = this.f14b;
        if (bVar != null) {
            bVar.mo203b();
        }
        this.f14b = null;
    }

    /* access modifiers changed from: protected */
    public int getCamera2Count() {
        if (C0071j.f214a) {
            return C0056b.m69a(this.f13a);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int[] getCamera2Resolutions(int i) {
        if (C0071j.f214a) {
            return C0056b.m91d(this.f13a, i);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getCamera2SensorOrientation(int i) {
        if (C0071j.f214a) {
            return C0056b.m70a(this.f13a, i);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public Object getCameraFocusArea(float f, float f2) {
        int a = m3a(f);
        int a2 = m3a(1.0f - f2);
        return new Camera.Area(new Rect(a - 100, a2 - 100, a + 100, a2 + 100), 1000);
    }

    /* access modifiers changed from: protected */
    public Rect getFrameSizeCamera2() {
        C0056b bVar = this.f14b;
        return bVar != null ? bVar.mo200a() : new Rect();
    }

    /* access modifiers changed from: protected */
    public boolean initializeCamera2(int i, int i2, int i3, int i4, int i5) {
        if (!C0071j.f214a || this.f14b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        this.f14b = new C0056b(this);
        return this.f14b.mo202a(this.f13a, i, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public boolean isCamera2AutoFocusPointSupported(int i) {
        if (C0071j.f214a) {
            return C0056b.m88c(this.f13a, i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isCamera2FrontFacing(int i) {
        if (C0071j.f214a) {
            return C0056b.m86b(this.f13a, i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void pauseCamera2() {
        C0056b bVar = this.f14b;
        if (bVar != null) {
            bVar.mo205d();
        }
    }

    /* access modifiers changed from: protected */
    public boolean setAutoFocusPoint(float f, float f2) {
        C0056b bVar;
        if (!C0071j.f214a || (bVar = this.f14b) == null) {
            return false;
        }
        return bVar.mo201a(f, f2);
    }

    /* access modifiers changed from: protected */
    public void startCamera2() {
        C0056b bVar = this.f14b;
        if (bVar != null) {
            bVar.mo204c();
        }
    }

    /* access modifiers changed from: protected */
    public void stopCamera2() {
        C0056b bVar = this.f14b;
        if (bVar != null) {
            bVar.mo206e();
        }
    }
}
