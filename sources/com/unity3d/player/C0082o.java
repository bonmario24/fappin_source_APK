package com.unity3d.player;

import java.lang.reflect.Method;
import java.util.HashMap;

/* renamed from: com.unity3d.player.o */
final class C0082o {

    /* renamed from: a */
    private HashMap f243a = new HashMap();

    /* renamed from: b */
    private Class f244b = null;

    /* renamed from: c */
    private Object f245c = null;

    /* renamed from: com.unity3d.player.o$a */
    class C0083a {

        /* renamed from: a */
        public Class[] f246a;

        /* renamed from: b */
        public Method f247b = null;

        public C0083a(Class[] clsArr) {
            this.f246a = clsArr;
        }
    }

    public C0082o(Class cls, Object obj) {
        this.f244b = cls;
        this.f245c = obj;
    }

    /* renamed from: a */
    private void m143a(String str, C0083a aVar) {
        try {
            aVar.f247b = this.f244b.getMethod(str, aVar.f246a);
        } catch (Exception e) {
            C0068g.Log(6, "Exception while trying to get method " + str + ". " + e.getLocalizedMessage());
            aVar.f247b = null;
        }
    }

    /* renamed from: a */
    public final Object mo257a(String str, Object... objArr) {
        StringBuilder sb;
        if (!this.f243a.containsKey(str)) {
            sb = new StringBuilder("No definition for method ");
            sb.append(str);
            str = " can be found";
        } else {
            C0083a aVar = (C0083a) this.f243a.get(str);
            if (aVar.f247b == null) {
                m143a(str, aVar);
            }
            if (aVar.f247b == null) {
                sb = new StringBuilder("Unable to create method: ");
            } else {
                try {
                    return objArr.length == 0 ? aVar.f247b.invoke(this.f245c, new Object[0]) : aVar.f247b.invoke(this.f245c, objArr);
                } catch (Exception e) {
                    C0068g.Log(6, "Error trying to call delegated method " + str + ". " + e.getLocalizedMessage());
                    return null;
                }
            }
        }
        sb.append(str);
        C0068g.Log(6, sb.toString());
        return null;
    }

    /* renamed from: a */
    public final void mo258a(String str, Class[] clsArr) {
        this.f243a.put(str, new C0083a(clsArr));
    }
}
