package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.c */
class C0063c {

    /* renamed from: a */
    protected C0082o f205a = null;

    /* renamed from: b */
    protected C0067f f206b = null;

    /* renamed from: c */
    protected Context f207c = null;

    /* renamed from: d */
    protected String f208d = null;

    /* renamed from: e */
    protected String f209e = "";

    C0063c(String str, C0067f fVar) {
        this.f209e = str;
        this.f206b = fVar;
    }

    /* access modifiers changed from: protected */
    public void reportError(String str) {
        C0067f fVar = this.f206b;
        if (fVar != null) {
            fVar.reportError(this.f209e + " Error [" + this.f208d + "]", str);
            return;
        }
        C0068g.Log(6, this.f209e + " Error [" + this.f208d + "]: " + str);
    }

    /* access modifiers changed from: protected */
    public void runOnUiThread(Runnable runnable) {
        Context context = this.f207c;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
            return;
        }
        C0068g.Log(5, "Not running " + this.f209e + " from an Activity; Ignoring execution request...");
    }

    /* access modifiers changed from: protected */
    public boolean runOnUiThreadWithSync(final Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return true;
        }
        final Semaphore semaphore = new Semaphore(0);
        runOnUiThread(new Runnable() {
            public final void run() {
                try {
                    runnable.run();
                } catch (Exception e) {
                    C0063c cVar = C0063c.this;
                    cVar.reportError("Exception unloading Google VR on UI Thread. " + e.getLocalizedMessage());
                } catch (Throwable th) {
                    semaphore.release();
                    throw th;
                }
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                return true;
            }
            reportError("Timeout waiting for vr state change!");
            return false;
        } catch (InterruptedException e) {
            reportError("Interrupted while trying to acquire sync lock. " + e.getLocalizedMessage());
            return false;
        }
    }
}
