package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice implements Runnable {

    /* renamed from: h */
    private static int f300h = 0;

    /* renamed from: i */
    private static int f301i = 1;

    /* renamed from: j */
    private static int f302j = 2;

    /* renamed from: k */
    private static int f303k = 3;

    /* renamed from: a */
    private volatile Thread f304a = null;

    /* renamed from: b */
    private volatile boolean f305b = false;

    /* renamed from: c */
    private AudioTrack f306c = null;

    /* renamed from: d */
    private boolean f307d = false;

    /* renamed from: e */
    private ByteBuffer f308e = null;

    /* renamed from: f */
    private byte[] f309f = null;

    /* renamed from: g */
    private volatile C0095a f310g;

    private native int fmodGetInfo(int i);

    private native int fmodProcess(ByteBuffer byteBuffer);

    private void releaseAudioTrack() {
        AudioTrack audioTrack = this.f306c;
        if (audioTrack != null) {
            if (audioTrack.getState() == 1) {
                this.f306c.stop();
            }
            this.f306c.release();
            this.f306c = null;
        }
        this.f308e = null;
        this.f309f = null;
        this.f307d = false;
    }

    public synchronized void close() {
        stop();
    }

    /* access modifiers changed from: package-private */
    public native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isRunning() {
        return this.f304a != null && this.f304a.isAlive();
    }

    public void run() {
        int i = 3;
        while (this.f305b) {
            if (!this.f307d && i > 0) {
                releaseAudioTrack();
                int fmodGetInfo = fmodGetInfo(f300h);
                int round = Math.round(((float) AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2)) * 1.1f) & -4;
                int fmodGetInfo2 = fmodGetInfo(f301i);
                int fmodGetInfo3 = fmodGetInfo(f302j) * fmodGetInfo2 * 4;
                this.f306c = new AudioTrack(3, fmodGetInfo, 3, 2, fmodGetInfo3 > round ? fmodGetInfo3 : round, 1);
                this.f307d = this.f306c.getState() == 1;
                if (this.f307d) {
                    this.f308e = ByteBuffer.allocateDirect(fmodGetInfo2 * 2 * 2);
                    this.f309f = new byte[this.f308e.capacity()];
                    this.f306c.play();
                    i = 3;
                } else {
                    Log.e("FMOD", "AudioTrack failed to initialize (status " + this.f306c.getState() + ")");
                    releaseAudioTrack();
                    i += -1;
                }
            }
            if (this.f307d) {
                if (fmodGetInfo(f303k) == 1) {
                    fmodProcess(this.f308e);
                    ByteBuffer byteBuffer = this.f308e;
                    byteBuffer.get(this.f309f, 0, byteBuffer.capacity());
                    this.f306c.write(this.f309f, 0, this.f308e.capacity());
                    this.f308e.position(0);
                } else {
                    releaseAudioTrack();
                }
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.f304a != null) {
            stop();
        }
        this.f304a = new Thread(this, "FMODAudioDevice");
        this.f304a.setPriority(10);
        this.f305b = true;
        this.f304a.start();
        if (this.f310g != null) {
            this.f310g.mo306b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.f310g == null) {
            this.f310g = new C0095a(this, i, i2);
            this.f310g.mo306b();
        }
        return this.f310g.mo305a();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0001 */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x0001 A[LOOP:0: B:1:0x0001->B:16:0x0001, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void stop() {
        /*
            r1 = this;
            monitor-enter(r1)
        L_0x0001:
            java.lang.Thread r0 = r1.f304a     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0011
            r0 = 0
            r1.f305b = r0     // Catch:{ all -> 0x001c }
            java.lang.Thread r0 = r1.f304a     // Catch:{ InterruptedException -> 0x0001 }
            r0.join()     // Catch:{ InterruptedException -> 0x0001 }
            r0 = 0
            r1.f304a = r0     // Catch:{ InterruptedException -> 0x0001 }
            goto L_0x0001
        L_0x0011:
            org.fmod.a r0 = r1.f310g     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x001a
            org.fmod.a r0 = r1.f310g     // Catch:{ all -> 0x001c }
            r0.mo307c()     // Catch:{ all -> 0x001c }
        L_0x001a:
            monitor-exit(r1)
            return
        L_0x001c:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fmod.FMODAudioDevice.stop():void");
    }

    public synchronized void stopAudioRecord() {
        if (this.f310g != null) {
            this.f310g.mo307c();
            this.f310g = null;
        }
    }
}
