package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.MediaController;

/* renamed from: com.unity3d.player.p */
public final class C0084p extends FrameLayout implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static boolean f249a = false;

    /* renamed from: b */
    private final Context f250b;

    /* renamed from: c */
    private final SurfaceView f251c;

    /* renamed from: d */
    private final SurfaceHolder f252d;

    /* renamed from: e */
    private final String f253e;

    /* renamed from: f */
    private final int f254f;

    /* renamed from: g */
    private final int f255g;

    /* renamed from: h */
    private final boolean f256h;

    /* renamed from: i */
    private final long f257i;

    /* renamed from: j */
    private final long f258j;

    /* renamed from: k */
    private final FrameLayout f259k;

    /* renamed from: l */
    private final Display f260l;

    /* renamed from: m */
    private int f261m;

    /* renamed from: n */
    private int f262n;

    /* renamed from: o */
    private int f263o;

    /* renamed from: p */
    private int f264p;

    /* renamed from: q */
    private MediaPlayer f265q;

    /* renamed from: r */
    private MediaController f266r;

    /* renamed from: s */
    private boolean f267s = false;

    /* renamed from: t */
    private boolean f268t = false;

    /* renamed from: u */
    private int f269u = 0;

    /* renamed from: v */
    private boolean f270v = false;

    /* renamed from: w */
    private boolean f271w = false;

    /* renamed from: x */
    private C0085a f272x;

    /* renamed from: y */
    private C0086b f273y;

    /* renamed from: z */
    private volatile int f274z = 0;

    /* renamed from: com.unity3d.player.p$a */
    public interface C0085a {
        /* renamed from: a */
        void mo284a(int i);
    }

    /* renamed from: com.unity3d.player.p$b */
    public class C0086b implements Runnable {

        /* renamed from: b */
        private C0084p f276b;

        /* renamed from: c */
        private boolean f277c = false;

        public C0086b(C0084p pVar) {
            this.f276b = pVar;
        }

        /* renamed from: a */
        public final void mo285a() {
            this.f277c = true;
        }

        public final void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
            if (!this.f277c) {
                if (C0084p.f249a) {
                    C0084p.m148b("Stopping the video player due to timeout.");
                }
                this.f276b.CancelOnPrepare();
            }
        }
    }

    protected C0084p(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, C0085a aVar) {
        super(context);
        this.f272x = aVar;
        this.f250b = context;
        this.f259k = this;
        this.f251c = new SurfaceView(context);
        this.f252d = this.f251c.getHolder();
        this.f252d.addCallback(this);
        this.f259k.setBackgroundColor(i);
        this.f259k.addView(this.f251c);
        this.f260l = ((WindowManager) this.f250b.getSystemService("window")).getDefaultDisplay();
        this.f253e = str;
        this.f254f = i2;
        this.f255g = i3;
        this.f256h = z;
        this.f257i = j;
        this.f258j = j2;
        if (f249a) {
            m148b("fileName: " + this.f253e);
        }
        if (f249a) {
            m148b("backgroundColor: " + i);
        }
        if (f249a) {
            m148b("controlMode: " + this.f254f);
        }
        if (f249a) {
            m148b("scalingMode: " + this.f255g);
        }
        if (f249a) {
            m148b("isURL: " + this.f256h);
        }
        if (f249a) {
            m148b("videoOffset: " + this.f257i);
        }
        if (f249a) {
            m148b("videoLength: " + this.f258j);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    /* renamed from: a */
    private void m146a(int i) {
        this.f274z = i;
        C0085a aVar = this.f272x;
        if (aVar != null) {
            aVar.mo284a(this.f274z);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m148b(String str) {
        Log.i("Video", "VideoPlayer: " + str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|19|20|21) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x007f */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m150c() {
        /*
            r8 = this;
            android.media.MediaPlayer r0 = r8.f265q
            if (r0 == 0) goto L_0x001c
            android.view.SurfaceHolder r1 = r8.f252d
            r0.setDisplay(r1)
            boolean r0 = r8.f270v
            if (r0 != 0) goto L_0x001b
            boolean r0 = f249a
            if (r0 == 0) goto L_0x0016
            java.lang.String r0 = "Resuming playback"
            m148b(r0)
        L_0x0016:
            android.media.MediaPlayer r0 = r8.f265q
            r0.start()
        L_0x001b:
            return
        L_0x001c:
            r0 = 0
            r8.m146a((int) r0)
            r8.doCleanUp()
            android.media.MediaPlayer r0 = new android.media.MediaPlayer     // Catch:{ Exception -> 0x00ce }
            r0.<init>()     // Catch:{ Exception -> 0x00ce }
            r8.f265q = r0     // Catch:{ Exception -> 0x00ce }
            boolean r0 = r8.f256h     // Catch:{ Exception -> 0x00ce }
            if (r0 == 0) goto L_0x003c
            android.media.MediaPlayer r0 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            android.content.Context r1 = r8.f250b     // Catch:{ Exception -> 0x00ce }
            java.lang.String r2 = r8.f253e     // Catch:{ Exception -> 0x00ce }
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x00ce }
            r0.setDataSource(r1, r2)     // Catch:{ Exception -> 0x00ce }
            goto L_0x0090
        L_0x003c:
            long r0 = r8.f258j     // Catch:{ Exception -> 0x00ce }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x005c
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00ce }
            java.lang.String r1 = r8.f253e     // Catch:{ Exception -> 0x00ce }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00ce }
            android.media.MediaPlayer r2 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            java.io.FileDescriptor r3 = r0.getFD()     // Catch:{ Exception -> 0x00ce }
            long r4 = r8.f257i     // Catch:{ Exception -> 0x00ce }
            long r6 = r8.f258j     // Catch:{ Exception -> 0x00ce }
            r2.setDataSource(r3, r4, r6)     // Catch:{ Exception -> 0x00ce }
        L_0x0058:
            r0.close()     // Catch:{ Exception -> 0x00ce }
            goto L_0x0090
        L_0x005c:
            android.content.res.Resources r0 = r8.getResources()     // Catch:{ Exception -> 0x00ce }
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch:{ Exception -> 0x00ce }
            java.lang.String r1 = r8.f253e     // Catch:{ IOException -> 0x007f }
            android.content.res.AssetFileDescriptor r0 = r0.openFd(r1)     // Catch:{ IOException -> 0x007f }
            android.media.MediaPlayer r1 = r8.f265q     // Catch:{ IOException -> 0x007f }
            java.io.FileDescriptor r2 = r0.getFileDescriptor()     // Catch:{ IOException -> 0x007f }
            long r3 = r0.getStartOffset()     // Catch:{ IOException -> 0x007f }
            long r5 = r0.getLength()     // Catch:{ IOException -> 0x007f }
            r1.setDataSource(r2, r3, r5)     // Catch:{ IOException -> 0x007f }
            r0.close()     // Catch:{ IOException -> 0x007f }
            goto L_0x0090
        L_0x007f:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00ce }
            java.lang.String r1 = r8.f253e     // Catch:{ Exception -> 0x00ce }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00ce }
            android.media.MediaPlayer r1 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            java.io.FileDescriptor r2 = r0.getFD()     // Catch:{ Exception -> 0x00ce }
            r1.setDataSource(r2)     // Catch:{ Exception -> 0x00ce }
            goto L_0x0058
        L_0x0090:
            android.media.MediaPlayer r0 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            android.view.SurfaceHolder r1 = r8.f252d     // Catch:{ Exception -> 0x00ce }
            r0.setDisplay(r1)     // Catch:{ Exception -> 0x00ce }
            android.media.MediaPlayer r0 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            r1 = 1
            r0.setScreenOnWhilePlaying(r1)     // Catch:{ Exception -> 0x00ce }
            android.media.MediaPlayer r0 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            r0.setOnBufferingUpdateListener(r8)     // Catch:{ Exception -> 0x00ce }
            android.media.MediaPlayer r0 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            r0.setOnCompletionListener(r8)     // Catch:{ Exception -> 0x00ce }
            android.media.MediaPlayer r0 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            r0.setOnPreparedListener(r8)     // Catch:{ Exception -> 0x00ce }
            android.media.MediaPlayer r0 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            r0.setOnVideoSizeChangedListener(r8)     // Catch:{ Exception -> 0x00ce }
            android.media.MediaPlayer r0 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            r1 = 3
            r0.setAudioStreamType(r1)     // Catch:{ Exception -> 0x00ce }
            android.media.MediaPlayer r0 = r8.f265q     // Catch:{ Exception -> 0x00ce }
            r0.prepareAsync()     // Catch:{ Exception -> 0x00ce }
            com.unity3d.player.p$b r0 = new com.unity3d.player.p$b     // Catch:{ Exception -> 0x00ce }
            r0.<init>(r8)     // Catch:{ Exception -> 0x00ce }
            r8.f273y = r0     // Catch:{ Exception -> 0x00ce }
            java.lang.Thread r0 = new java.lang.Thread     // Catch:{ Exception -> 0x00ce }
            com.unity3d.player.p$b r1 = r8.f273y     // Catch:{ Exception -> 0x00ce }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00ce }
            r0.start()     // Catch:{ Exception -> 0x00ce }
            return
        L_0x00ce:
            r0 = move-exception
            boolean r1 = f249a
            if (r1 == 0) goto L_0x00eb
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "error: "
            r1.<init>(r2)
            java.lang.String r2 = r0.getMessage()
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            m148b(r0)
        L_0x00eb:
            r0 = 2
            r8.m146a((int) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0084p.m150c():void");
    }

    /* renamed from: d */
    private void m151d() {
        if (!isPlaying()) {
            m146a(1);
            if (f249a) {
                m148b("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.f270v) {
                start();
            }
        }
    }

    public final void CancelOnPrepare() {
        m146a(2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo260a() {
        return this.f270v;
    }

    public final boolean canPause() {
        return true;
    }

    public final boolean canSeekBackward() {
        return true;
    }

    public final boolean canSeekForward() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void destroyPlayer() {
        if (f249a) {
            m148b("destroyPlayer");
        }
        if (!this.f270v) {
            pause();
        }
        doCleanUp();
    }

    /* access modifiers changed from: protected */
    public final void doCleanUp() {
        C0086b bVar = this.f273y;
        if (bVar != null) {
            bVar.mo285a();
            this.f273y = null;
        }
        MediaPlayer mediaPlayer = this.f265q;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.f265q = null;
        }
        this.f263o = 0;
        this.f264p = 0;
        this.f268t = false;
        this.f267s = false;
    }

    public final int getAudioSessionId() {
        MediaPlayer mediaPlayer = this.f265q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getAudioSessionId();
    }

    public final int getBufferPercentage() {
        if (this.f256h) {
            return this.f269u;
        }
        return 100;
    }

    public final int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.f265q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }

    public final int getDuration() {
        MediaPlayer mediaPlayer = this.f265q;
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getDuration();
    }

    public final boolean isPlaying() {
        boolean z = this.f268t && this.f267s;
        MediaPlayer mediaPlayer = this.f265q;
        return mediaPlayer == null ? !z : mediaPlayer.isPlaying() || !z;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (f249a) {
            m148b("onBufferingUpdate percent:" + i);
        }
        this.f269u = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (f249a) {
            m148b("onCompletion called");
        }
        destroyPlayer();
        m146a(3);
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 || (this.f254f == 2 && i != 0 && !keyEvent.isSystem())) {
            destroyPlayer();
            m146a(3);
            return true;
        }
        MediaController mediaController = this.f266r;
        return mediaController != null ? mediaController.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (f249a) {
            m148b("onPrepared called");
        }
        C0086b bVar = this.f273y;
        if (bVar != null) {
            bVar.mo285a();
            this.f273y = null;
        }
        int i = this.f254f;
        if (i == 0 || i == 1) {
            this.f266r = new MediaController(this.f250b);
            this.f266r.setMediaPlayer(this);
            this.f266r.setAnchorView(this);
            this.f266r.setEnabled(true);
            Context context = this.f250b;
            if (context instanceof Activity) {
                this.f266r.setSystemUiVisibility(((Activity) context).getWindow().getDecorView().getSystemUiVisibility());
            }
            this.f266r.show();
        }
        this.f268t = true;
        if (this.f268t && this.f267s) {
            m151d();
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f254f == 2 && action == 0) {
            destroyPlayer();
            m146a(3);
            return true;
        }
        MediaController mediaController = this.f266r;
        return mediaController != null ? mediaController.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (f249a) {
            m148b("onVideoSizeChanged called " + i + "x" + i2);
        }
        if (i != 0 && i2 != 0) {
            this.f267s = true;
            this.f263o = i;
            this.f264p = i2;
            if (this.f268t && this.f267s) {
                m151d();
            }
        } else if (f249a) {
            m148b("invalid video width(" + i + ") or height(" + i2 + ")");
        }
    }

    public final void pause() {
        MediaPlayer mediaPlayer = this.f265q;
        if (mediaPlayer != null) {
            if (this.f271w) {
                mediaPlayer.pause();
            }
            this.f270v = true;
        }
    }

    public final void seekTo(int i) {
        MediaPlayer mediaPlayer = this.f265q;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(i);
        }
    }

    public final void start() {
        if (f249a) {
            m148b("Start");
        }
        MediaPlayer mediaPlayer = this.f265q;
        if (mediaPlayer != null) {
            if (this.f271w) {
                mediaPlayer.start();
            }
            this.f270v = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (f249a) {
            m148b("surfaceChanged called " + i + " " + i2 + "x" + i3);
        }
        if (this.f261m != i2 || this.f262n != i3) {
            this.f261m = i2;
            this.f262n = i3;
            if (this.f271w) {
                updateVideoLayout();
            }
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (f249a) {
            m148b("surfaceCreated called");
        }
        this.f271w = true;
        m150c();
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (f249a) {
            m148b("surfaceDestroyed called");
        }
        this.f271w = false;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        if (r5 <= r3) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        r0 = (int) (((float) r1) * r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005d, code lost:
        if (r5 >= r3) goto L_0x004f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateVideoLayout() {
        /*
            r8 = this;
            boolean r0 = f249a
            if (r0 == 0) goto L_0x0009
            java.lang.String r0 = "updateVideoLayout"
            m148b(r0)
        L_0x0009:
            android.media.MediaPlayer r0 = r8.f265q
            if (r0 != 0) goto L_0x000e
            return
        L_0x000e:
            int r0 = r8.f261m
            if (r0 == 0) goto L_0x0016
            int r0 = r8.f262n
            if (r0 != 0) goto L_0x0034
        L_0x0016:
            android.content.Context r0 = r8.f250b
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.util.DisplayMetrics r1 = new android.util.DisplayMetrics
            r1.<init>()
            android.view.Display r0 = r0.getDefaultDisplay()
            r0.getMetrics(r1)
            int r0 = r1.widthPixels
            r8.f261m = r0
            int r0 = r1.heightPixels
            r8.f262n = r0
        L_0x0034:
            int r0 = r8.f261m
            int r1 = r8.f262n
            boolean r2 = r8.f267s
            if (r2 == 0) goto L_0x0065
            int r2 = r8.f263o
            float r3 = (float) r2
            int r4 = r8.f264p
            float r5 = (float) r4
            float r3 = r3 / r5
            float r5 = (float) r0
            float r6 = (float) r1
            float r5 = r5 / r6
            int r6 = r8.f255g
            r7 = 1
            if (r6 != r7) goto L_0x0058
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x0053
        L_0x004f:
            float r1 = (float) r0
            float r1 = r1 / r3
            int r1 = (int) r1
            goto L_0x006e
        L_0x0053:
            float r0 = (float) r1
            float r0 = r0 * r3
            int r0 = (int) r0
            goto L_0x006e
        L_0x0058:
            r7 = 2
            if (r6 != r7) goto L_0x0060
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 < 0) goto L_0x0053
            goto L_0x004f
        L_0x0060:
            if (r6 != 0) goto L_0x006e
            r0 = r2
            r1 = r4
            goto L_0x006e
        L_0x0065:
            boolean r2 = f249a
            if (r2 == 0) goto L_0x006e
            java.lang.String r2 = "updateVideoLayout: Video size is not known yet"
            m148b(r2)
        L_0x006e:
            int r2 = r8.f261m
            if (r2 != r0) goto L_0x0076
            int r2 = r8.f262n
            if (r2 == r1) goto L_0x00a1
        L_0x0076:
            boolean r2 = f249a
            if (r2 == 0) goto L_0x0093
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "frameWidth = "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r3 = "; frameHeight = "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            m148b(r2)
        L_0x0093:
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r3 = 17
            r2.<init>(r0, r1, r3)
            android.widget.FrameLayout r0 = r8.f259k
            android.view.SurfaceView r1 = r8.f251c
            r0.updateViewLayout(r1, r2)
        L_0x00a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.C0084p.updateVideoLayout():void");
    }
}
