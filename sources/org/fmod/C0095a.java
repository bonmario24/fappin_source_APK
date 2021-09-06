package org.fmod;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.ByteBuffer;

/* renamed from: org.fmod.a */
final class C0095a implements Runnable {

    /* renamed from: a */
    private final FMODAudioDevice f311a;

    /* renamed from: b */
    private final ByteBuffer f312b;

    /* renamed from: c */
    private final int f313c;

    /* renamed from: d */
    private final int f314d;

    /* renamed from: e */
    private final int f315e = 2;

    /* renamed from: f */
    private volatile Thread f316f;

    /* renamed from: g */
    private volatile boolean f317g;

    /* renamed from: h */
    private AudioRecord f318h;

    /* renamed from: i */
    private boolean f319i;

    C0095a(FMODAudioDevice fMODAudioDevice, int i, int i2) {
        this.f311a = fMODAudioDevice;
        this.f313c = i;
        this.f314d = i2;
        this.f312b = ByteBuffer.allocateDirect(AudioRecord.getMinBufferSize(i, i2, 2));
    }

    /* renamed from: d */
    private void m172d() {
        AudioRecord audioRecord = this.f318h;
        if (audioRecord != null) {
            if (audioRecord.getState() == 1) {
                this.f318h.stop();
            }
            this.f318h.release();
            this.f318h = null;
        }
        this.f312b.position(0);
        this.f319i = false;
    }

    /* renamed from: a */
    public final int mo305a() {
        return this.f312b.capacity();
    }

    /* renamed from: b */
    public final void mo306b() {
        if (this.f316f != null) {
            mo307c();
        }
        this.f317g = true;
        this.f316f = new Thread(this);
        this.f316f.start();
    }

    /* renamed from: c */
    public final void mo307c() {
        while (this.f316f != null) {
            this.f317g = false;
            try {
                this.f316f.join();
                this.f316f = null;
            } catch (InterruptedException unused) {
            }
        }
    }

    public final void run() {
        int i = 3;
        while (this.f317g) {
            if (!this.f319i && i > 0) {
                m172d();
                this.f318h = new AudioRecord(1, this.f313c, this.f314d, this.f315e, this.f312b.capacity());
                boolean z = true;
                if (this.f318h.getState() != 1) {
                    z = false;
                }
                this.f319i = z;
                if (this.f319i) {
                    this.f312b.position(0);
                    this.f318h.startRecording();
                    i = 3;
                } else {
                    Log.e("FMOD", "AudioRecord failed to initialize (status " + this.f318h.getState() + ")");
                    i += -1;
                    m172d();
                }
            }
            if (this.f319i && this.f318h.getRecordingState() == 3) {
                AudioRecord audioRecord = this.f318h;
                ByteBuffer byteBuffer = this.f312b;
                this.f311a.fmodProcessMicData(this.f312b, audioRecord.read(byteBuffer, byteBuffer.capacity()));
                this.f312b.position(0);
            }
        }
        m172d();
    }
}
