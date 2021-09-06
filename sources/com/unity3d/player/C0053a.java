package com.unity3d.player;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

/* renamed from: com.unity3d.player.a */
final class C0053a {

    /* renamed from: a */
    private final Context f158a;

    /* renamed from: b */
    private final AudioManager f159b;

    /* renamed from: c */
    private C0054a f160c;

    /* renamed from: com.unity3d.player.a$a */
    private class C0054a extends ContentObserver {

        /* renamed from: b */
        private final C0055b f162b;

        /* renamed from: c */
        private final AudioManager f163c;

        /* renamed from: d */
        private final int f164d = 3;

        /* renamed from: e */
        private int f165e;

        public C0054a(Handler handler, AudioManager audioManager, int i, C0055b bVar) {
            super(handler);
            this.f163c = audioManager;
            this.f162b = bVar;
            this.f165e = audioManager.getStreamVolume(this.f164d);
        }

        public final boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        public final void onChange(boolean z, Uri uri) {
            int streamVolume;
            AudioManager audioManager = this.f163c;
            if (audioManager != null && this.f162b != null && (streamVolume = audioManager.getStreamVolume(this.f164d)) != this.f165e) {
                this.f165e = streamVolume;
                this.f162b.onAudioVolumeChanged(streamVolume);
            }
        }
    }

    /* renamed from: com.unity3d.player.a$b */
    public interface C0055b {
        void onAudioVolumeChanged(int i);
    }

    public C0053a(Context context) {
        this.f158a = context;
        this.f159b = (AudioManager) context.getSystemService("audio");
    }

    /* renamed from: a */
    public final void mo196a() {
        if (this.f160c != null) {
            this.f158a.getContentResolver().unregisterContentObserver(this.f160c);
            this.f160c = null;
        }
    }

    /* renamed from: a */
    public final void mo197a(C0055b bVar) {
        this.f160c = new C0054a(new Handler(), this.f159b, 3, bVar);
        this.f158a.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.f160c);
    }
}
