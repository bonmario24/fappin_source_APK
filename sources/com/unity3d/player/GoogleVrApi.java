package com.unity3d.player;

import java.util.concurrent.atomic.AtomicReference;

public class GoogleVrApi {

    /* renamed from: a */
    private static AtomicReference f16a = new AtomicReference();

    private GoogleVrApi() {
    }

    /* renamed from: a */
    static void m7a() {
        f16a.set((Object) null);
    }

    /* renamed from: a */
    static void m8a(C0067f fVar) {
        f16a.compareAndSet((Object) null, new GoogleVrProxy(fVar));
    }

    /* renamed from: b */
    static GoogleVrProxy m9b() {
        return (GoogleVrProxy) f16a.get();
    }

    public static GoogleVrVideo getGoogleVrVideo() {
        return (GoogleVrVideo) f16a.get();
    }
}
