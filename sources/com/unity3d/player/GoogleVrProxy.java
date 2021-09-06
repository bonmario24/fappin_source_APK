package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import com.unity3d.player.GoogleVrVideo;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

class GoogleVrProxy extends C0063c implements GoogleVrVideo {

    /* renamed from: f */
    private boolean f17f = false;

    /* renamed from: g */
    private boolean f18g = false;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public Runnable f19h = null;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public Vector f20i = new Vector();

    /* renamed from: j */
    private SurfaceView f21j = null;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public C0011a f22k = new C0011a();

    /* renamed from: l */
    private Thread f23l = null;

    /* renamed from: m */
    private Handler f24m = new Handler(Looper.getMainLooper()) {
        public final void handleMessage(Message message) {
            if (message.what != 135711) {
                super.handleMessage(message);
                return;
            }
            switch (message.arg1) {
                case 2147483645:
                    Iterator it = GoogleVrProxy.this.f20i.iterator();
                    while (it.hasNext()) {
                        ((GoogleVrVideo.GoogleVrVideoCallbacks) it.next()).onFrameAvailable();
                    }
                    return;
                case 2147483646:
                    Surface surface = (Surface) message.obj;
                    Iterator it2 = GoogleVrProxy.this.f20i.iterator();
                    while (it2.hasNext()) {
                        ((GoogleVrVideo.GoogleVrVideoCallbacks) it2.next()).onSurfaceAvailable(surface);
                    }
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    };

    /* renamed from: com.unity3d.player.GoogleVrProxy$a */
    class C0011a {

        /* renamed from: a */
        public boolean f36a = false;

        /* renamed from: b */
        public boolean f37b = false;

        /* renamed from: c */
        public boolean f38c = false;

        /* renamed from: d */
        public boolean f39d = false;

        /* renamed from: e */
        public boolean f40e = true;

        /* renamed from: f */
        public boolean f41f = false;

        C0011a() {
        }

        /* renamed from: a */
        public final boolean mo59a() {
            return this.f36a && this.f37b;
        }

        /* renamed from: b */
        public final void mo60b() {
            this.f36a = false;
            this.f37b = false;
            this.f39d = false;
            this.f40e = true;
            this.f41f = false;
        }
    }

    public GoogleVrProxy(C0067f fVar) {
        super("Google VR", fVar);
        initVrJni();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m12a(boolean z) {
        this.f22k.f39d = z;
    }

    /* renamed from: a */
    private boolean m13a(ClassLoader classLoader) {
        try {
            Class<?> loadClass = classLoader.loadClass("com.unity3d.unitygvr.GoogleVR");
            C0082o oVar = new C0082o(loadClass, loadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            oVar.mo258a("initialize", new Class[]{Activity.class, Context.class, SurfaceView.class, Boolean.TYPE, Handler.class});
            oVar.mo258a("deinitialize", new Class[0]);
            oVar.mo258a("load", new Class[]{Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Runnable.class});
            oVar.mo258a("enable", new Class[]{Boolean.TYPE});
            oVar.mo258a("unload", new Class[0]);
            oVar.mo258a("pause", new Class[0]);
            oVar.mo258a("resume", new Class[0]);
            oVar.mo258a("getGvrLayout", new Class[0]);
            oVar.mo258a("getVideoSurfaceId", new Class[0]);
            oVar.mo258a("getVideoSurface", new Class[0]);
            this.f205a = oVar;
            return true;
        } catch (Exception e) {
            reportError("Exception initializing GoogleVR from Unity library. " + e.getLocalizedMessage());
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public boolean m16d() {
        return this.f22k.f39d;
    }

    /* renamed from: e */
    private void m18e() {
        Activity activity = (Activity) this.f207c;
        if (this.f18g && !this.f22k.f41f && activity != null) {
            this.f22k.f41f = true;
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(268435456);
            activity.startActivity(intent);
        }
    }

    /* renamed from: f */
    private static boolean m19f() {
        return Build.VERSION.SDK_INT >= 24;
    }

    private final native void initVrJni();

    private final native boolean isQuiting();

    private final native void setVrVideoTransform(float[][] fArr);

    /* renamed from: a */
    public final void mo40a(Intent intent) {
        if (intent != null && intent.getBooleanExtra("android.intent.extra.VR_LAUNCH", false)) {
            this.f18g = true;
        }
    }

    /* renamed from: a */
    public final boolean mo41a() {
        return this.f22k.f36a;
    }

    /* renamed from: a */
    public final boolean mo42a(Activity activity, Context context, SurfaceView surfaceView, Runnable runnable) {
        String str;
        boolean z;
        if (activity == null || context == null || surfaceView == null || runnable == null) {
            str = "Invalid parameters passed to Google VR initialization.";
        } else {
            this.f22k.mo60b();
            this.f207c = context;
            this.f19h = runnable;
            if (this.f18g && !m19f()) {
                str = "Daydream requires a device that supports an api version of 24 (Nougat) or better.";
            } else if (!m13a(UnityPlayer.class.getClassLoader())) {
                return false;
            } else {
                try {
                    z = ((Boolean) this.f205a.mo257a("initialize", activity, context, surfaceView, Boolean.valueOf(this.f18g), this.f24m)).booleanValue();
                } catch (Exception e) {
                    reportError("Exception while trying to initialize Unity Google VR Library. " + e.getLocalizedMessage());
                    z = false;
                }
                if (!z) {
                    str = "Unable to initialize GoogleVR library.";
                } else {
                    this.f21j = surfaceView;
                    this.f22k.f36a = true;
                    this.f208d = "";
                    return true;
                }
            }
        }
        reportError(str);
        return false;
    }

    /* renamed from: b */
    public final void mo43b() {
        resumeGvrLayout();
    }

    /* renamed from: c */
    public final void mo44c() {
        SurfaceView surfaceView = this.f21j;
        if (surfaceView != null) {
            surfaceView.getHolder().setSizeFromLayout();
        }
    }

    public void deregisterGoogleVrVideoListener(GoogleVrVideo.GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (this.f20i.contains(googleVrVideoCallbacks)) {
            googleVrVideoCallbacks.onSurfaceUnavailable();
            this.f20i.remove(googleVrVideoCallbacks);
        }
    }

    /* access modifiers changed from: protected */
    public Object getVideoSurface() {
        if (m16d() && !this.f22k.f40e) {
            try {
                return this.f205a.mo257a("getVideoSurface", new Object[0]);
            } catch (Exception e) {
                reportError("Exception caught while Getting GoogleVR Video Surface. " + e.getLocalizedMessage());
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getVideoSurfaceId() {
        if (m16d() && !this.f22k.f40e) {
            try {
                return ((Integer) this.f205a.mo257a("getVideoSurfaceId", new Object[0])).intValue();
            } catch (Exception e) {
                reportError("Exception caught while getting Video Surface ID from GoogleVR. " + e.getLocalizedMessage());
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public long loadGoogleVr(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (!this.f22k.f36a) {
            return 0;
        }
        AtomicLong atomicLong = new AtomicLong(0);
        this.f208d = (z || z2) ? "Daydream" : "Cardboard";
        final AtomicLong atomicLong2 = atomicLong;
        final boolean z6 = z;
        final boolean z7 = z2;
        final boolean z8 = z3;
        final boolean z9 = z4;
        final boolean z10 = z5;
        if (!runOnUiThreadWithSync(new Runnable() {
            public final void run() {
                try {
                    atomicLong2.set(((Long) GoogleVrProxy.this.f205a.mo257a("load", Boolean.valueOf(z6), Boolean.valueOf(z7), Boolean.valueOf(z8), Boolean.valueOf(z9), Boolean.valueOf(z10), GoogleVrProxy.this.f19h)).longValue());
                    GoogleVrProxy.this.f22k.f37b = true;
                } catch (Exception e) {
                    GoogleVrProxy googleVrProxy = GoogleVrProxy.this;
                    googleVrProxy.reportError("Exception caught while loading GoogleVR. " + e.getLocalizedMessage());
                    atomicLong2.set(0);
                }
            }
        }) || atomicLong.longValue() == 0) {
            reportError("Google VR had a fatal issue while loading. VR will not be available.");
        }
        return atomicLong.longValue();
    }

    /* access modifiers changed from: protected */
    public void pauseGvrLayout() {
        if (this.f22k.mo59a() && !this.f22k.f40e) {
            if (m16d()) {
                Iterator it = this.f20i.iterator();
                while (it.hasNext()) {
                    ((GoogleVrVideo.GoogleVrVideoCallbacks) it.next()).onSurfaceUnavailable();
                }
            }
            if (this.f205a != null) {
                this.f205a.mo257a("pause", new Object[0]);
            }
            this.f22k.f40e = true;
        }
    }

    public void registerGoogleVrVideoListener(GoogleVrVideo.GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (!this.f20i.contains(googleVrVideoCallbacks)) {
            this.f20i.add(googleVrVideoCallbacks);
            Surface surface = (Surface) getVideoSurface();
            if (surface != null) {
                googleVrVideoCallbacks.onSurfaceAvailable(surface);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void resumeGvrLayout() {
        if (this.f22k.mo59a() && this.f22k.f40e) {
            if (this.f205a != null) {
                this.f205a.mo257a("resume", new Object[0]);
            }
            this.f22k.f40e = false;
        }
    }

    /* access modifiers changed from: protected */
    public void setGoogleVrModeEnabled(final boolean z) {
        if (this.f22k.mo59a() && this.f206b != null && this.f207c != null) {
            if (!z && isQuiting()) {
                m18e();
            }
            runOnUiThread(new Runnable() {
                public final void run() {
                    if (z != GoogleVrProxy.this.m16d()) {
                        try {
                            if (z) {
                                if (!GoogleVrProxy.this.m16d()) {
                                    if (GoogleVrProxy.this.f205a == null || GoogleVrProxy.this.f206b == null || GoogleVrProxy.this.f206b.addViewToPlayer((View) GoogleVrProxy.this.f205a.mo257a("getGvrLayout", new Object[0]), true)) {
                                        if (GoogleVrProxy.this.f205a != null) {
                                            GoogleVrProxy.this.f205a.mo257a("enable", true);
                                        }
                                        GoogleVrProxy.this.m12a(true);
                                        return;
                                    }
                                    GoogleVrProxy.this.reportError("Unable to add Google VR to view hierarchy.");
                                    return;
                                }
                            }
                            if (!z && GoogleVrProxy.this.m16d()) {
                                GoogleVrProxy.this.m12a(false);
                                if (GoogleVrProxy.this.f205a != null) {
                                    GoogleVrProxy.this.f205a.mo257a("enable", false);
                                }
                                if (GoogleVrProxy.this.f205a != null && GoogleVrProxy.this.f206b != null) {
                                    GoogleVrProxy.this.f206b.removeViewFromPlayer((View) GoogleVrProxy.this.f205a.mo257a("getGvrLayout", new Object[0]));
                                }
                            }
                        } catch (Exception e) {
                            GoogleVrProxy googleVrProxy = GoogleVrProxy.this;
                            googleVrProxy.reportError("Exception enabling Google VR on UI Thread. " + e.getLocalizedMessage());
                        }
                    }
                }
            });
        }
    }

    public void setVideoLocationTransform(float[] fArr) {
        float[][] fArr2 = (float[][]) Array.newInstance(float.class, new int[]{4, 4});
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                fArr2[i][i2] = fArr[(i * 4) + i2];
            }
        }
        setVrVideoTransform(fArr2);
    }

    /* access modifiers changed from: protected */
    public void unloadGoogleVr() {
        if (this.f22k.f39d) {
            setGoogleVrModeEnabled(false);
        }
        if (this.f22k.f38c) {
            this.f22k.f38c = false;
        }
        this.f21j = null;
        runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    if (GoogleVrProxy.this.f205a != null) {
                        GoogleVrProxy.this.f205a.mo257a("unload", new Object[0]);
                        GoogleVrProxy.this.f205a.mo257a("deinitialize", new Object[0]);
                        GoogleVrProxy.this.f205a = null;
                    }
                    GoogleVrProxy.this.f22k.f37b = false;
                } catch (Exception e) {
                    GoogleVrProxy googleVrProxy = GoogleVrProxy.this;
                    googleVrProxy.reportError("Exception unloading Google VR on UI Thread. " + e.getLocalizedMessage());
                }
            }
        });
    }
}
