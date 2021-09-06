package com.unity3d.player;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;

final class ReflectionHelper {
    protected static boolean LOG = false;
    protected static final boolean LOGV = false;

    /* renamed from: a */
    private static C0017a[] f61a = new C0017a[4096];
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static long f62b = 0;

    /* renamed from: com.unity3d.player.ReflectionHelper$a */
    private static class C0017a {

        /* renamed from: a */
        public volatile Member f68a;

        /* renamed from: b */
        private final Class f69b;

        /* renamed from: c */
        private final String f70c;

        /* renamed from: d */
        private final String f71d;

        /* renamed from: e */
        private final int f72e = (((((this.f69b.hashCode() + 527) * 31) + this.f70c.hashCode()) * 31) + this.f71d.hashCode());

        C0017a(Class cls, String str, String str2) {
            this.f69b = cls;
            this.f70c = str;
            this.f71d = str2;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C0017a) {
                C0017a aVar = (C0017a) obj;
                return this.f72e == aVar.f72e && this.f71d.equals(aVar.f71d) && this.f70c.equals(aVar.f70c) && this.f69b.equals(aVar.f69b);
            }
        }

        public final int hashCode() {
            return this.f72e;
        }
    }

    /* renamed from: com.unity3d.player.ReflectionHelper$b */
    protected interface C0018b extends InvocationHandler {
        /* renamed from: a */
        void mo76a(long j, boolean z);
    }

    ReflectionHelper() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|(1:10)|11|12|(1:14)(1:19)) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return 0.0f;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001e */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0024 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static float m36a(java.lang.Class r1, java.lang.Class r2) {
        /*
            boolean r0 = r1.equals(r2)
            if (r0 == 0) goto L_0x0009
            r1 = 1065353216(0x3f800000, float:1.0)
            return r1
        L_0x0009:
            boolean r0 = r1.isPrimitive()
            if (r0 != 0) goto L_0x0028
            boolean r0 = r2.isPrimitive()
            if (r0 != 0) goto L_0x0028
            java.lang.Class r0 = r1.asSubclass(r2)     // Catch:{ ClassCastException -> 0x001e }
            if (r0 == 0) goto L_0x001e
            r1 = 1056964608(0x3f000000, float:0.5)
            return r1
        L_0x001e:
            java.lang.Class r1 = r2.asSubclass(r1)     // Catch:{ ClassCastException -> 0x0028 }
            if (r1 == 0) goto L_0x0028
            r1 = 1036831949(0x3dcccccd, float:0.1)
            return r1
        L_0x0028:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.ReflectionHelper.m36a(java.lang.Class, java.lang.Class):float");
    }

    /* renamed from: a */
    private static float m37a(Class cls, Class[] clsArr, Class[] clsArr2) {
        if (clsArr2.length == 0) {
            return 0.1f;
        }
        int i = 0;
        if ((clsArr == null ? 0 : clsArr.length) + 1 != clsArr2.length) {
            return 0.0f;
        }
        float f = 1.0f;
        if (clsArr != null) {
            int length = clsArr.length;
            int i2 = 0;
            while (i < length) {
                f *= m36a(clsArr[i], clsArr2[i2]);
                i++;
                i2++;
            }
        }
        return f * m36a(cls, clsArr2[clsArr2.length - 1]);
    }

    /* renamed from: a */
    private static Class m39a(String str, int[] iArr) {
        while (iArr[0] < str.length()) {
            int i = iArr[0];
            iArr[0] = i + 1;
            char charAt = str.charAt(i);
            if (charAt != '(' && charAt != ')') {
                if (charAt == 'L') {
                    int indexOf = str.indexOf(59, iArr[0]);
                    if (indexOf == -1) {
                        return null;
                    }
                    String substring = str.substring(iArr[0], indexOf);
                    iArr[0] = indexOf + 1;
                    try {
                        return Class.forName(substring.replace('/', '.'));
                    } catch (ClassNotFoundException unused) {
                        return null;
                    }
                } else if (charAt == 'Z') {
                    return Boolean.TYPE;
                } else {
                    if (charAt == 'I') {
                        return Integer.TYPE;
                    }
                    if (charAt == 'F') {
                        return Float.TYPE;
                    }
                    if (charAt == 'V') {
                        return Void.TYPE;
                    }
                    if (charAt == 'B') {
                        return Byte.TYPE;
                    }
                    if (charAt == 'C') {
                        return Character.TYPE;
                    }
                    if (charAt == 'S') {
                        return Short.TYPE;
                    }
                    if (charAt == 'J') {
                        return Long.TYPE;
                    }
                    if (charAt == 'D') {
                        return Double.TYPE;
                    }
                    if (charAt == '[') {
                        return Array.newInstance(m39a(str, iArr), 0).getClass();
                    }
                    C0068g.Log(5, "! parseType; " + charAt + " is not known!");
                    return null;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private static synchronized void m42a(C0017a aVar, Member member) {
        synchronized (ReflectionHelper.class) {
            aVar.f68a = member;
            f61a[aVar.hashCode() & (f61a.length - 1)] = aVar;
        }
    }

    /* renamed from: a */
    private static synchronized boolean m43a(C0017a aVar) {
        synchronized (ReflectionHelper.class) {
            C0017a aVar2 = f61a[aVar.hashCode() & (f61a.length - 1)];
            if (!aVar.equals(aVar2)) {
                return false;
            }
            aVar.f68a = aVar2.f68a;
            return true;
        }
    }

    /* renamed from: a */
    private static Class[] m44a(String str) {
        Class a;
        int i = 0;
        int[] iArr = {0};
        ArrayList arrayList = new ArrayList();
        while (iArr[0] < str.length() && (a = m39a(str, iArr)) != null) {
            arrayList.add(a);
        }
        Class[] clsArr = new Class[arrayList.size()];
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            clsArr[i] = (Class) it.next();
            i++;
        }
        return clsArr;
    }

    protected static void endUnityLaunch() {
        f62b++;
    }

    protected static Constructor getConstructorID(Class cls, String str) {
        Constructor constructor;
        C0017a aVar = new C0017a(cls, "", str);
        if (m43a(aVar)) {
            constructor = (Constructor) aVar.f68a;
        } else {
            Class[] a = m44a(str);
            float f = 0.0f;
            Constructor[] constructors = cls.getConstructors();
            int length = constructors.length;
            int i = 0;
            Constructor constructor2 = null;
            while (true) {
                if (i >= length) {
                    break;
                }
                Constructor constructor3 = constructors[i];
                float a2 = m37a(Void.TYPE, constructor3.getParameterTypes(), a);
                if (a2 > f) {
                    if (a2 == 1.0f) {
                        constructor2 = constructor3;
                        break;
                    }
                    constructor2 = constructor3;
                    f = a2;
                }
                i++;
            }
            m42a(aVar, (Member) constructor2);
            constructor = constructor2;
        }
        if (constructor != null) {
            return constructor;
        }
        throw new NoSuchMethodError("<init>" + str + " in class " + cls.getName());
    }

    protected static Field getFieldID(Class cls, String str, String str2, boolean z) {
        Field field;
        String str3 = str;
        String str4 = str2;
        boolean z2 = z;
        Class cls2 = cls;
        C0017a aVar = new C0017a(cls2, str3, str4);
        if (m43a(aVar)) {
            field = (Field) aVar.f68a;
        } else {
            Class[] a = m44a(str2);
            float f = 0.0f;
            Field field2 = null;
            while (cls2 != null) {
                Field[] declaredFields = cls2.getDeclaredFields();
                int length = declaredFields.length;
                Field field3 = field2;
                float f2 = f;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        f = f2;
                        field2 = field3;
                        break;
                    }
                    Field field4 = declaredFields[i];
                    if (z2 == Modifier.isStatic(field4.getModifiers()) && field4.getName().compareTo(str3) == 0) {
                        float a2 = m37a((Class) field4.getType(), (Class[]) null, a);
                        if (a2 > f2) {
                            if (a2 == 1.0f) {
                                field2 = field4;
                                f = a2;
                                break;
                            }
                            field3 = field4;
                            f2 = a2;
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
                if (f == 1.0f || cls2.isPrimitive() || cls2.isInterface() || cls2.equals(Object.class) || cls2.equals(Void.TYPE)) {
                    break;
                }
                cls2 = cls2.getSuperclass();
            }
            m42a(aVar, (Member) field2);
            field = field2;
        }
        if (field != null) {
            return field;
        }
        Object[] objArr = new Object[4];
        objArr[0] = z2 ? "static" : "non-static";
        objArr[1] = str3;
        objArr[2] = str4;
        objArr[3] = cls2.getName();
        throw new NoSuchFieldError(String.format("no %s field with name='%s' signature='%s' in class L%s;", objArr));
    }

    protected static String getFieldSignature(Field field) {
        Class<?> type = field.getType();
        if (type.isPrimitive()) {
            String name = type.getName();
            return "boolean".equals(name) ? "Z" : "byte".equals(name) ? "B" : "char".equals(name) ? "C" : "double".equals(name) ? "D" : "float".equals(name) ? "F" : "int".equals(name) ? "I" : "long".equals(name) ? "J" : "short".equals(name) ? "S" : name;
        } else if (type.isArray()) {
            return type.getName().replace('.', '/');
        } else {
            return "L" + type.getName().replace('.', '/') + ";";
        }
    }

    protected static Method getMethodID(Class cls, String str, String str2, boolean z) {
        Method method;
        C0017a aVar = new C0017a(cls, str, str2);
        if (m43a(aVar)) {
            method = (Method) aVar.f68a;
        } else {
            Class[] a = m44a(str2);
            float f = 0.0f;
            Method method2 = null;
            while (cls != null) {
                Method[] declaredMethods = cls.getDeclaredMethods();
                int length = declaredMethods.length;
                Method method3 = method2;
                float f2 = f;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        f = f2;
                        method2 = method3;
                        break;
                    }
                    Method method4 = declaredMethods[i];
                    if (z == Modifier.isStatic(method4.getModifiers()) && method4.getName().compareTo(str) == 0) {
                        float a2 = m37a((Class) method4.getReturnType(), method4.getParameterTypes(), a);
                        if (a2 > f2) {
                            if (a2 == 1.0f) {
                                method2 = method4;
                                f = a2;
                                break;
                            }
                            method3 = method4;
                            f2 = a2;
                        } else {
                            continue;
                        }
                    }
                    i++;
                }
                if (f == 1.0f || cls.isPrimitive() || cls.isInterface() || cls.equals(Object.class) || cls.equals(Void.TYPE)) {
                    break;
                }
                cls = cls.getSuperclass();
            }
            m42a(aVar, (Member) method2);
            method = method2;
        }
        if (method != null) {
            return method;
        }
        Object[] objArr = new Object[4];
        objArr[0] = z ? "static" : "non-static";
        objArr[1] = str;
        objArr[2] = str2;
        objArr[3] = cls.getName();
        throw new NoSuchMethodError(String.format("no %s method with name='%s' signature='%s' in class L%s;", objArr));
    }

    /* access modifiers changed from: private */
    public static native void nativeProxyFinalize(long j);

    /* access modifiers changed from: private */
    public static native Object nativeProxyInvoke(long j, String str, Object[] objArr);

    /* access modifiers changed from: private */
    public static native void nativeProxyLogJNIInvokeException(long j);

    protected static Object newProxyInstance(long j, Class cls) {
        return newProxyInstance(j, new Class[]{cls});
    }

    protected static Object newProxyInstance(final long j, final Class[] clsArr) {
        return Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), clsArr, new C0018b() {

            /* renamed from: c */
            private long f65c = ReflectionHelper.f62b;

            /* renamed from: d */
            private long f66d;

            /* renamed from: e */
            private boolean f67e;

            /* renamed from: a */
            private Object m46a(Object obj, Method method, Object[] objArr) {
                if (objArr == null) {
                    try {
                        objArr = new Object[0];
                    } catch (NoClassDefFoundError unused) {
                        C0068g.Log(6, String.format("Java interface default methods are only supported since Android Oreo", new Object[0]));
                        ReflectionHelper.nativeProxyLogJNIInvokeException(this.f66d);
                        return null;
                    }
                }
                Class<?> declaringClass = method.getDeclaringClass();
                Constructor<MethodHandles.Lookup> declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
                declaredConstructor.setAccessible(true);
                return declaredConstructor.newInstance(new Object[]{declaringClass, 2}).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
            }

            /* renamed from: a */
            public final void mo76a(long j, boolean z) {
                this.f66d = j;
                this.f67e = z;
            }

            /* access modifiers changed from: protected */
            public final void finalize() {
                try {
                    if (this.f65c == ReflectionHelper.f62b) {
                        ReflectionHelper.nativeProxyFinalize(j);
                    }
                } finally {
                    super.finalize();
                }
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
                if (r6 != 0) goto L_0x003d;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object invoke(java.lang.Object r6, java.lang.reflect.Method r7, java.lang.Object[] r8) {
                /*
                    r5 = this;
                    long r0 = r5.f65c
                    long r2 = com.unity3d.player.ReflectionHelper.f62b
                    int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                    if (r4 == 0) goto L_0x0012
                    r6 = 6
                    java.lang.String r7 = "Scripting proxy object was destroyed, because Unity player was unloaded."
                    com.unity3d.player.C0068g.Log(r6, r7)
                    r6 = 0
                    return r6
                L_0x0012:
                    r0 = 0
                    r5.f66d = r0
                    r2 = 0
                    r5.f67e = r2
                    long r2 = r2
                    java.lang.String r4 = r7.getName()
                    java.lang.Object r2 = com.unity3d.player.ReflectionHelper.nativeProxyInvoke(r2, r4, r8)
                    boolean r3 = r5.f67e
                    if (r3 == 0) goto L_0x0037
                    int r0 = r7.getModifiers()
                    r0 = r0 & 1024(0x400, float:1.435E-42)
                    if (r0 != 0) goto L_0x0034
                    java.lang.Object r6 = r5.m46a(r6, r7, r8)
                    return r6
                L_0x0034:
                    long r6 = r5.f66d
                    goto L_0x003d
                L_0x0037:
                    long r6 = r5.f66d
                    int r8 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
                    if (r8 == 0) goto L_0x0040
                L_0x003d:
                    com.unity3d.player.ReflectionHelper.nativeProxyLogJNIInvokeException(r6)
                L_0x0040:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.unity3d.player.ReflectionHelper.C00161.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
            }
        });
    }

    protected static void setNativeExceptionOnProxy(Object obj, long j, boolean z) {
        ((C0018b) Proxy.getInvocationHandler(obj)).mo76a(j, z);
    }
}
