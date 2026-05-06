// ملف البرنامج الرئيسي
import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);
    static Pharmacy ph = new Pharmacy();
    static Admin admin = new Admin();

    public static void main(String[] args) {
        ph.load();
        if (!login()) { System.out.println("قفلنا."); return; }
        int op;
        do {
            System.out.println("\n1.اضافة 2.بحث 3.تحديث 4.عرض 5.خروج");
            op = readInt("اختر: ");
            if (op == 1) add();
            else if (op == 2) search();
            else if (op == 3) update();
            else if (op == 4) show();
        } while (op != 5);
        ph.save();
    }

    static boolean login() {
        for (int i = 3; i > 0; i--) {
            System.out.print("اسم المستخدم: "); String u = in.nextLine();
            System.out.print("كلمة المرور: ");   String p = in.nextLine();
            if (admin.check(u, p)) { System.out.println("تم الدخول."); return true; }
            System.out.println("غلط. باقي " + (i-1) + " محاولات.");
        }
        return false;
    }

    static void add() {
        System.out.print("نوع (1=عادي 2=وصفة): "); int t = readInt("");
        System.out.print("الكود: "); String c = in.nextLine();
        if (ph.byCode(c) != null) { System.out.println("الكود موجود."); return; }
        System.out.print("الاسم: "); String n = in.nextLine();
        double pr = readDouble("السعر: ");
        int q = readInt("الكمية: ");
        DrugStatus s = readStatus();
        if (t == 2) {
            System.out.print("يحتاج وصفة (true/false): ");
            boolean rx = in.nextBoolean(); in.nextLine();
            ph.add(c, n, pr, q, s, rx);
        } else {
            ph.add(c, n, pr, q, s);
        }
        System.out.println("تم.");
    }

    static void search() {
        if (ph.size() == 0) { System.out.println("مافي ادوية."); return; }
        System.out.print("بحث بـ (1=كود 2=اسم): "); int ch = readInt("");
        if (ch == 1) {
            System.out.print("الكود: "); Medicine m = ph.byCode(in.nextLine());
            if (m != null) m.print(); else System.out.println("مو موجود.");
        } else {
            System.out.print("الاسم: "); Medicine[] arr = ph.byName(in.nextLine());
            if (arr.length == 0) System.out.println("مافي شي.");
            else for (Medicine x : arr) x.print();
        }
    }

    static void update() {
        if (ph.size() == 0) { System.out.println("فارغ."); return; }
        System.out.print("الكود: "); String c = in.nextLine();
        if (ph.byCode(c) == null) { System.out.println("مو موجود."); return; }
        System.out.print("عدل (1=سعر 2=كمية 3=حالة): "); int u = readInt("");
        if (u == 1) ph.updatePrice(c, readDouble("سعر جديد: "));
        else if (u == 2) ph.updateQty(c, readInt("كمية جديدة: "));
        else if (u == 3) ph.updateStatus(c, readStatus());
        System.out.println("تم.");
    }

    static void show() {
        if (ph.size() == 0) { System.out.println("فارغ."); return; }
        System.out.print("1=الكل 2=حسب الحالة: "); int c = readInt("");
        if (c == 1) ph.showAll();
        else ph.showByStatus(readStatus());
    }

    static DrugStatus readStatus() {
        System.out.print("الحالة (1=متاح 2=غير متاح 3=منتهي): ");
        int s = readInt("");
        if (s == 2) return DrugStatus.UNAVAILABLE;
        else if (s == 3) return DrugStatus.EXPIRED;
        else return DrugStatus.AVAILABLE;
    }

    static int readInt(String msg) {
        while (true) {
            System.out.print(msg);
            try { return Integer.parseInt(in.nextLine()); }
            catch (Exception e) { System.out.println("رقم صحيح!"); }
        }
    }
    static double readDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try { return Double.parseDouble(in.nextLine()); }
            catch (Exception e) { System.out.println("رقم!"); }
        }
    }
}