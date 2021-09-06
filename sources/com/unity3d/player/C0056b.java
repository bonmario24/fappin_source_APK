package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* renamed from: com.unity3d.player.b */
public final class C0056b {

    /* renamed from: b */
    private static CameraManager f166b;

    /* renamed from: c */
    private static String[] f167c;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static Semaphore f168e = new Semaphore(1);

    /* renamed from: A */
    private CameraCaptureSession.CaptureCallback f169A = new CameraCaptureSession.CaptureCallback() {
        public final void onCaptureCompleted(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, TotalCaptureResult totalCaptureResult) {
            C0056b.this.m80a(captureRequest.getTag());
        }

        public final void onCaptureFailed(CameraCaptureSession cameraCaptureSession, CaptureRequest captureRequest, CaptureFailure captureFailure) {
            C0068g.Log(5, "Camera2: Capture session failed " + captureRequest.getTag() + " reason " + captureFailure.getReason());
            C0056b.this.m80a(captureRequest.getTag());
        }

        public final void onCaptureSequenceAborted(CameraCaptureSession cameraCaptureSession, int i) {
        }

        public final void onCaptureSequenceCompleted(CameraCaptureSession cameraCaptureSession, int i, long j) {
        }
    };

    /* renamed from: B */
    private final CameraDevice.StateCallback f170B = new CameraDevice.StateCallback() {
        public final void onClosed(CameraDevice cameraDevice) {
            C0056b.f168e.release();
        }

        public final void onDisconnected(CameraDevice cameraDevice) {
            C0068g.Log(5, "Camera2: CameraDevice disconnected.");
            C0056b.this.m78a(cameraDevice);
            C0056b.f168e.release();
        }

        public final void onError(CameraDevice cameraDevice, int i) {
            C0068g.Log(6, "Camera2: Error opeining CameraDevice " + i);
            C0056b.this.m78a(cameraDevice);
            C0056b.f168e.release();
        }

        public final void onOpened(CameraDevice cameraDevice) {
            CameraDevice unused = C0056b.this.f174d = cameraDevice;
            C0056b.f168e.release();
        }
    };

    /* renamed from: C */
    private final ImageReader.OnImageAvailableListener f171C = new ImageReader.OnImageAvailableListener() {
        public final void onImageAvailable(ImageReader imageReader) {
            if (C0056b.f168e.tryAcquire()) {
                Image acquireNextImage = imageReader.acquireNextImage();
                if (acquireNextImage != null) {
                    Image.Plane[] planes = acquireNextImage.getPlanes();
                    if (acquireNextImage.getFormat() == 35 && planes != null && planes.length == 3) {
                        C0065d h = C0056b.this.f173a;
                        ByteBuffer buffer = planes[0].getBuffer();
                        ByteBuffer buffer2 = planes[1].getBuffer();
                        ByteBuffer buffer3 = planes[2].getBuffer();
                        h.mo23a(buffer, buffer2, buffer3, planes[0].getRowStride(), planes[1].getRowStride(), planes[1].getPixelStride());
                    } else {
                        C0068g.Log(6, "Camera2: Wrong image format.");
                    }
                    if (C0056b.this.f188s != null) {
                        C0056b.this.f188s.close();
                    }
                    Image unused = C0056b.this.f188s = acquireNextImage;
                }
                C0056b.f168e.release();
            }
        }
    };

    /* renamed from: D */
    private final SurfaceTexture.OnFrameAvailableListener f172D = new SurfaceTexture.OnFrameAvailableListener() {
        public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
            C0056b.this.f173a.mo22a(surfaceTexture);
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C0065d f173a = null;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public CameraDevice f174d;

    /* renamed from: f */
    private HandlerThread f175f;

    /* renamed from: g */
    private Handler f176g;

    /* renamed from: h */
    private Rect f177h;

    /* renamed from: i */
    private Rect f178i;

    /* renamed from: j */
    private int f179j;

    /* renamed from: k */
    private int f180k;

    /* renamed from: l */
    private float f181l = -1.0f;

    /* renamed from: m */
    private float f182m = -1.0f;

    /* renamed from: n */
    private int f183n;

    /* renamed from: o */
    private int f184o;

    /* renamed from: p */
    private boolean f185p = false;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public Range f186q;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public ImageReader f187r = null;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public Image f188s;
    /* access modifiers changed from: private */

    /* renamed from: t */
    public CaptureRequest.Builder f189t;
    /* access modifiers changed from: private */

    /* renamed from: u */
    public CameraCaptureSession f190u = null;
    /* access modifiers changed from: private */

    /* renamed from: v */
    public Object f191v = new Object();

    /* renamed from: w */
    private int f192w;

    /* renamed from: x */
    private SurfaceTexture f193x;
    /* access modifiers changed from: private */

    /* renamed from: y */
    public Surface f194y = null;

    /* renamed from: z */
    private int f195z = C0062a.f203c;

    /* renamed from: com.unity3d.player.b$a */
    private enum C0062a {
        ;

        static {
            f204d = new int[]{f201a, f202b, f203c};
        }
    }

    protected C0056b(C0065d dVar) {
        this.f173a = dVar;
        m95g();
    }

    /* renamed from: a */
    public static int m69a(Context context) {
        return m89c(context).length;
    }

    /* renamed from: a */
    public static int m70a(Context context, int i) {
        try {
            return ((Integer) m82b(context).getCameraCharacteristics(m89c(context)[i]).get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        } catch (CameraAccessException e) {
            C0068g.Log(6, "Camera2: CameraAccessException " + e);
            return 0;
        }
    }

    /* renamed from: a */
    private static int m71a(Range[] rangeArr, int i) {
        int i2 = -1;
        double d = Double.MAX_VALUE;
        for (int i3 = 0; i3 < rangeArr.length; i3++) {
            int intValue = ((Integer) rangeArr[i3].getLower()).intValue();
            int intValue2 = ((Integer) rangeArr[i3].getUpper()).intValue();
            float f = (float) i;
            if (f + 0.1f > ((float) intValue) && f - 0.1f < ((float) intValue2)) {
                return i;
            }
            double min = (double) ((float) Math.min(Math.abs(i - intValue), Math.abs(i - intValue2)));
            if (min < d) {
                i2 = i3;
                d = min;
            }
        }
        return ((Integer) (i > ((Integer) rangeArr[i2].getUpper()).intValue() ? rangeArr[i2].getUpper() : rangeArr[i2].getLower())).intValue();
    }

    /* renamed from: a */
    private static Rect m72a(Size[] sizeArr, double d, double d2) {
        Size[] sizeArr2 = sizeArr;
        double d3 = Double.MAX_VALUE;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < sizeArr2.length; i3++) {
            int width = sizeArr2[i3].getWidth();
            int height = sizeArr2[i3].getHeight();
            double d4 = (double) width;
            Double.isNaN(d4);
            double abs = Math.abs(Math.log(d / d4));
            double d5 = (double) height;
            Double.isNaN(d5);
            double abs2 = abs + Math.abs(Math.log(d2 / d5));
            if (abs2 < d3) {
                i = width;
                i2 = height;
                d3 = abs2;
            }
        }
        return new Rect(0, 0, i, i2);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m78a(CameraDevice cameraDevice) {
        synchronized (this.f191v) {
            this.f190u = null;
        }
        cameraDevice.close();
        this.f174d = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m80a(Object obj) {
        if (obj == "Focus") {
            this.f185p = false;
            synchronized (this.f191v) {
                if (this.f190u != null) {
                    try {
                        this.f189t.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                        this.f189t.setTag("Regular");
                        this.f190u.setRepeatingRequest(this.f189t.build(), this.f169A, this.f176g);
                    } catch (CameraAccessException e) {
                        C0068g.Log(6, "Camera2: CameraAccessException " + e);
                    }
                }
            }
        } else if (obj == "Cancel focus") {
            synchronized (this.f191v) {
                if (this.f190u != null) {
                    m101j();
                }
            }
        }
    }

    /* renamed from: a */
    private static Size[] m81a(CameraCharacteristics cameraCharacteristics) {
        String str;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            str = "Camera2: configuration map is not available.";
        } else {
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(35);
            if (outputSizes != null && outputSizes.length != 0) {
                return outputSizes;
            }
            str = "Camera2: output sizes for YUV_420_888 format are not avialable.";
        }
        C0068g.Log(6, str);
        return null;
    }

    /* renamed from: b */
    private static CameraManager m82b(Context context) {
        if (f166b == null) {
            f166b = (CameraManager) context.getSystemService("camera");
        }
        return f166b;
    }

    /* renamed from: b */
    private void m84b(CameraCharacteristics cameraCharacteristics) {
        this.f180k = ((Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue();
        if (this.f180k > 0) {
            this.f178i = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
            float width = ((float) this.f178i.width()) / ((float) this.f178i.height());
            float width2 = ((float) this.f177h.width()) / ((float) this.f177h.height());
            if (width2 > width) {
                this.f183n = 0;
                this.f184o = (int) ((((float) this.f178i.height()) - (((float) this.f178i.width()) / width2)) / 2.0f);
            } else {
                this.f184o = 0;
                this.f183n = (int) ((((float) this.f178i.width()) - (((float) this.f178i.height()) * width2)) / 2.0f);
            }
            this.f179j = Math.min(this.f178i.width(), this.f178i.height()) / 20;
        }
    }

    /* renamed from: b */
    public static boolean m86b(Context context, int i) {
        try {
            return ((Integer) m82b(context).getCameraCharacteristics(m89c(context)[i]).get(CameraCharacteristics.LENS_FACING)).intValue() == 0;
        } catch (CameraAccessException e) {
            C0068g.Log(6, "Camera2: CameraAccessException " + e);
            return false;
        }
    }

    /* renamed from: c */
    public static boolean m88c(Context context, int i) {
        try {
            return ((Integer) m82b(context).getCameraCharacteristics(m89c(context)[i]).get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF)).intValue() > 0;
        } catch (CameraAccessException e) {
            C0068g.Log(6, "Camera2: CameraAccessException " + e);
            return false;
        }
    }

    /* renamed from: c */
    private static String[] m89c(Context context) {
        if (f167c == null) {
            try {
                f167c = m82b(context).getCameraIdList();
            } catch (CameraAccessException e) {
                C0068g.Log(6, "Camera2: CameraAccessException " + e);
                f167c = new String[0];
            }
        }
        return f167c;
    }

    /* renamed from: d */
    public static int[] m91d(Context context, int i) {
        try {
            Size[] a = m81a(m82b(context).getCameraCharacteristics(m89c(context)[i]));
            if (a == null) {
                return null;
            }
            int[] iArr = new int[(a.length * 2)];
            for (int i2 = 0; i2 < a.length; i2++) {
                int i3 = i2 * 2;
                iArr[i3] = a[i2].getWidth();
                iArr[i3 + 1] = a[i2].getHeight();
            }
            return iArr;
        } catch (CameraAccessException e) {
            C0068g.Log(6, "Camera2: CameraAccessException " + e);
            return null;
        }
    }

    /* renamed from: g */
    private void m95g() {
        this.f175f = new HandlerThread("CameraBackground");
        this.f175f.start();
        this.f176g = new Handler(this.f175f.getLooper());
    }

    /* renamed from: h */
    private void m98h() {
        this.f175f.quit();
        try {
            this.f175f.join(4000);
            this.f175f = null;
            this.f176g = null;
        } catch (InterruptedException e) {
            this.f175f.interrupt();
            C0068g.Log(6, "Camera2: Interrupted while waiting for the background thread to finish " + e);
        }
    }

    /* renamed from: i */
    private void m100i() {
        try {
            if (!f168e.tryAcquire(4, TimeUnit.SECONDS)) {
                C0068g.Log(5, "Camera2: Timeout waiting to lock camera for closing.");
                return;
            }
            this.f174d.close();
            try {
                if (!f168e.tryAcquire(4, TimeUnit.SECONDS)) {
                    C0068g.Log(5, "Camera2: Timeout waiting to close camera.");
                }
            } catch (InterruptedException e) {
                C0068g.Log(6, "Camera2: Interrupted while waiting to close camera " + e);
            }
            this.f174d = null;
            f168e.release();
        } catch (InterruptedException e2) {
            C0068g.Log(6, "Camera2: Interrupted while trying to lock camera for closing " + e2);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public void m101j() {
        try {
            if (this.f180k != 0 && this.f181l >= 0.0f && this.f181l <= 1.0f && this.f182m >= 0.0f) {
                if (this.f182m <= 1.0f) {
                    this.f185p = true;
                    int width = (int) ((((float) (this.f178i.width() - (this.f183n * 2))) * this.f181l) + ((float) this.f183n));
                    double height = (double) (this.f178i.height() - (this.f184o * 2));
                    double d = (double) this.f182m;
                    Double.isNaN(d);
                    Double.isNaN(height);
                    double d2 = height * (1.0d - d);
                    double d3 = (double) this.f184o;
                    Double.isNaN(d3);
                    int i = (int) (d2 + d3);
                    int max = Math.max(this.f179j + 1, Math.min(width, (this.f178i.width() - this.f179j) - 1));
                    int max2 = Math.max(this.f179j + 1, Math.min(i, (this.f178i.height() - this.f179j) - 1));
                    this.f189t.set(CaptureRequest.CONTROL_AF_REGIONS, new MeteringRectangle[]{new MeteringRectangle(max - this.f179j, max2 - this.f179j, this.f179j * 2, this.f179j * 2, 999)});
                    this.f189t.set(CaptureRequest.CONTROL_AF_MODE, 1);
                    this.f189t.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
                    this.f189t.setTag("Focus");
                    this.f190u.capture(this.f189t.build(), this.f169A, this.f176g);
                    return;
                }
            }
            this.f189t.set(CaptureRequest.CONTROL_AF_MODE, 4);
            this.f189t.setTag("Regular");
            if (this.f190u != null) {
                this.f190u.setRepeatingRequest(this.f189t.build(), this.f169A, this.f176g);
            }
        } catch (CameraAccessException e) {
            C0068g.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* renamed from: k */
    private void m102k() {
        try {
            if (this.f190u != null) {
                this.f190u.stopRepeating();
                this.f189t.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                this.f189t.set(CaptureRequest.CONTROL_AF_MODE, 0);
                this.f189t.setTag("Cancel focus");
                this.f190u.capture(this.f189t.build(), this.f169A, this.f176g);
            }
        } catch (CameraAccessException e) {
            C0068g.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* renamed from: a */
    public final Rect mo200a() {
        return this.f177h;
    }

    /* renamed from: a */
    public final boolean mo201a(float f, float f2) {
        if (this.f180k <= 0) {
            return false;
        }
        if (!this.f185p) {
            this.f181l = f;
            this.f182m = f2;
            synchronized (this.f191v) {
                if (!(this.f190u == null || this.f195z == C0062a.f202b)) {
                    m102k();
                }
            }
            return true;
        }
        C0068g.Log(5, "Camera2: Setting manual focus point already started.");
        return false;
    }

    /* renamed from: a */
    public final boolean mo202a(Context context, int i, int i2, int i3, int i4, int i5) {
        try {
            CameraCharacteristics cameraCharacteristics = f166b.getCameraCharacteristics(m89c(context)[i]);
            if (((Integer) cameraCharacteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2) {
                C0068g.Log(5, "Camera2: only LEGACY hardware level is supported.");
                return false;
            }
            Size[] a = m81a(cameraCharacteristics);
            if (!(a == null || a.length == 0)) {
                this.f177h = m72a(a, (double) i2, (double) i3);
                Range[] rangeArr = (Range[]) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES);
                if (rangeArr == null || rangeArr.length == 0) {
                    C0068g.Log(6, "Camera2: target FPS ranges are not avialable.");
                } else {
                    int a2 = m71a(rangeArr, i4);
                    this.f186q = new Range(Integer.valueOf(a2), Integer.valueOf(a2));
                    try {
                        if (!f168e.tryAcquire(4, TimeUnit.SECONDS)) {
                            C0068g.Log(5, "Camera2: Timeout waiting to lock camera for opening.");
                            return false;
                        }
                        try {
                            f166b.openCamera(m89c(context)[i], this.f170B, this.f176g);
                            try {
                                if (!f168e.tryAcquire(4, TimeUnit.SECONDS)) {
                                    C0068g.Log(5, "Camera2: Timeout waiting to open camera.");
                                    return false;
                                }
                                f168e.release();
                                this.f192w = i5;
                                m84b(cameraCharacteristics);
                                return this.f174d != null;
                            } catch (InterruptedException e) {
                                C0068g.Log(6, "Camera2: Interrupted while waiting to open camera " + e);
                            }
                        } catch (CameraAccessException e2) {
                            C0068g.Log(6, "Camera2: CameraAccessException " + e2);
                            f168e.release();
                            return false;
                        }
                    } catch (InterruptedException e3) {
                        C0068g.Log(6, "Camera2: Interrupted while trying to lock camera for opening " + e3);
                        return false;
                    }
                }
            }
            return false;
        } catch (CameraAccessException e4) {
            C0068g.Log(6, "Camera2: CameraAccessException " + e4);
            return false;
        }
    }

    /* renamed from: b */
    public final void mo203b() {
        if (this.f174d != null) {
            mo206e();
            m100i();
            this.f169A = null;
            this.f194y = null;
            this.f193x = null;
            Image image = this.f188s;
            if (image != null) {
                image.close();
                this.f188s = null;
            }
            ImageReader imageReader = this.f187r;
            if (imageReader != null) {
                imageReader.close();
                this.f187r = null;
            }
        }
        m98h();
    }

    /* renamed from: c */
    public final void mo204c() {
        if (this.f187r == null) {
            this.f187r = ImageReader.newInstance(this.f177h.width(), this.f177h.height(), 35, 2);
            this.f187r.setOnImageAvailableListener(this.f171C, this.f176g);
            this.f188s = null;
            int i = this.f192w;
            if (i != 0) {
                this.f193x = new SurfaceTexture(i);
                this.f193x.setDefaultBufferSize(this.f177h.width(), this.f177h.height());
                this.f193x.setOnFrameAvailableListener(this.f172D, this.f176g);
                this.f194y = new Surface(this.f193x);
            }
        }
        try {
            if (this.f190u == null) {
                this.f174d.createCaptureSession(Arrays.asList(this.f194y != null ? new Surface[]{this.f194y, this.f187r.getSurface()} : new Surface[]{this.f187r.getSurface()}), new CameraCaptureSession.StateCallback() {
                    public final void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
                        C0068g.Log(6, "Camera2: CaptureSession configuration failed.");
                    }

                    public final void onConfigured(CameraCaptureSession cameraCaptureSession) {
                        if (C0056b.this.f174d != null) {
                            synchronized (C0056b.this.f191v) {
                                CameraCaptureSession unused = C0056b.this.f190u = cameraCaptureSession;
                                try {
                                    CaptureRequest.Builder unused2 = C0056b.this.f189t = C0056b.this.f174d.createCaptureRequest(1);
                                    if (C0056b.this.f194y != null) {
                                        C0056b.this.f189t.addTarget(C0056b.this.f194y);
                                    }
                                    C0056b.this.f189t.addTarget(C0056b.this.f187r.getSurface());
                                    C0056b.this.f189t.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, C0056b.this.f186q);
                                    C0056b.this.m101j();
                                } catch (CameraAccessException e) {
                                    C0068g.Log(6, "Camera2: CameraAccessException " + e);
                                }
                            }
                        }
                    }
                }, this.f176g);
            } else if (this.f195z == C0062a.f202b) {
                this.f190u.setRepeatingRequest(this.f189t.build(), this.f169A, this.f176g);
            }
            this.f195z = C0062a.f201a;
        } catch (CameraAccessException e) {
            C0068g.Log(6, "Camera2: CameraAccessException " + e);
        }
    }

    /* renamed from: d */
    public final void mo205d() {
        synchronized (this.f191v) {
            if (this.f190u != null) {
                try {
                    this.f190u.stopRepeating();
                    this.f195z = C0062a.f202b;
                } catch (CameraAccessException e) {
                    C0068g.Log(6, "Camera2: CameraAccessException " + e);
                }
            }
        }
    }

    /* renamed from: e */
    public final void mo206e() {
        synchronized (this.f191v) {
            if (this.f190u != null) {
                try {
                    this.f190u.abortCaptures();
                } catch (CameraAccessException e) {
                    C0068g.Log(6, "Camera2: CameraAccessException " + e);
                }
                this.f190u.close();
                this.f190u = null;
                this.f195z = C0062a.f203c;
            }
        }
    }
}
