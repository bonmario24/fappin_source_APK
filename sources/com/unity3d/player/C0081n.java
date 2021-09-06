package com.unity3d.player;

/* renamed from: com.unity3d.player.n */
final class C0081n {

    /* renamed from: a */
    private static boolean f238a = false;

    /* renamed from: b */
    private boolean f239b = false;

    /* renamed from: c */
    private boolean f240c = false;

    /* renamed from: d */
    private boolean f241d = true;

    /* renamed from: e */
    private boolean f242e = false;

    C0081n() {
    }

    /* renamed from: a */
    static void m132a() {
        f238a = true;
    }

    /* renamed from: b */
    static void m133b() {
        f238a = false;
    }

    /* renamed from: c */
    static boolean m134c() {
        return f238a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo248a(boolean z) {
        this.f239b = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo249b(boolean z) {
        this.f241d = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo250c(boolean z) {
        this.f242e = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final void mo251d(boolean z) {
        this.f240c = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final boolean mo252d() {
        return this.f241d;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final boolean mo253e() {
        return this.f242e;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final boolean mo254f() {
        return f238a && this.f239b && !this.f241d && !this.f240c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final boolean mo255g() {
        return this.f240c;
    }

    public final String toString() {
        return super.toString();
    }
}
