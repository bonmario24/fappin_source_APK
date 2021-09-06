package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import com.unity3d.player.C0084p;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: com.unity3d.player.q */
final class C0087q {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public UnityPlayer f278a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Context f279b = null;

    /* renamed from: c */
    private C0094a f280c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final Semaphore f281d = new Semaphore(0);
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final Lock f282e = new ReentrantLock();
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C0084p f283f = null;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public int f284g = 2;

    /* renamed from: h */
    private boolean f285h = false;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public boolean f286i = false;

    /* renamed from: com.unity3d.player.q$a */
    public interface C0094a {
        /* renamed from: a */
        void mo143a();
    }

    C0087q(UnityPlayer unityPlayer) {
        this.f278a = unityPlayer;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m161d() {
        C0084p pVar = this.f283f;
        if (pVar != null) {
            this.f278a.removeViewFromPlayer(pVar);
            this.f286i = false;
            this.f283f.destroyPlayer();
            this.f283f = null;
            C0094a aVar = this.f280c;
            if (aVar != null) {
                aVar.mo143a();
            }
        }
    }

    /* renamed from: a */
    public final void mo287a() {
        this.f282e.lock();
        C0084p pVar = this.f283f;
        if (pVar != null) {
            if (this.f284g == 0) {
                pVar.CancelOnPrepare();
            } else if (this.f286i) {
                this.f285h = pVar.mo260a();
                if (!this.f285h) {
                    this.f283f.pause();
                }
            }
        }
        this.f282e.unlock();
    }

    /* renamed from: a */
    public final boolean mo288a(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, C0094a aVar) {
        this.f282e.lock();
        this.f280c = aVar;
        this.f279b = context;
        this.f281d.drainPermits();
        this.f284g = 2;
        final String str2 = str;
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        final boolean z2 = z;
        final long j3 = j;
        final long j4 = j2;
        runOnUiThread(new Runnable() {
            public final void run() {
                if (C0087q.this.f283f != null) {
                    C0068g.Log(5, "Video already playing");
                    int unused = C0087q.this.f284g = 2;
                    C0087q.this.f281d.release();
                    return;
                }
                C0087q qVar = C0087q.this;
                C0084p unused2 = qVar.f283f = new C0084p(qVar.f279b, str2, i4, i5, i6, z2, j3, j4, new C0084p.C0085a() {
                    /* renamed from: a */
                    public final void mo284a(int i) {
                        C0087q.this.f282e.lock();
                        int unused = C0087q.this.f284g = i;
                        if (i == 3 && C0087q.this.f286i) {
                            C0087q.this.runOnUiThread(new Runnable() {
                                public final void run() {
                                    C0087q.this.m161d();
                                    C0087q.this.f278a.resume();
                                }
                            });
                        }
                        if (i != 0) {
                            C0087q.this.f281d.release();
                        }
                        C0087q.this.f282e.unlock();
                    }
                });
                if (C0087q.this.f283f != null) {
                    C0087q.this.f278a.addView(C0087q.this.f283f);
                }
            }
        });
        boolean z3 = false;
        try {
            this.f282e.unlock();
            this.f281d.acquire();
            this.f282e.lock();
            if (this.f284g != 2) {
                z3 = true;
            }
        } catch (InterruptedException unused) {
        }
        runOnUiThread(new Runnable() {
            public final void run() {
                C0087q.this.f278a.pause();
            }
        });
        runOnUiThread((!z3 || this.f284g == 3) ? new Runnable() {
            public final void run() {
                C0087q.this.m161d();
                C0087q.this.f278a.resume();
            }
        } : new Runnable() {
            public final void run() {
                if (C0087q.this.f283f != null) {
                    C0087q.this.f278a.addViewToPlayer(C0087q.this.f283f, true);
                    boolean unused = C0087q.this.f286i = true;
                    C0087q.this.f283f.requestFocus();
                }
            }
        });
        this.f282e.unlock();
        return z3;
    }

    /* renamed from: b */
    public final void mo289b() {
        this.f282e.lock();
        C0084p pVar = this.f283f;
        if (pVar != null && this.f286i && !this.f285h) {
            pVar.start();
        }
        this.f282e.unlock();
    }

    /* renamed from: c */
    public final void mo290c() {
        this.f282e.lock();
        C0084p pVar = this.f283f;
        if (pVar != null) {
            pVar.updateVideoLayout();
        }
        this.f282e.unlock();
    }

    /* access modifiers changed from: protected */
    public final void runOnUiThread(Runnable runnable) {
        Context context = this.f279b;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        } else {
            C0068g.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}
