// ملف إدارة الصيدلية
import java.io.*;

public class Pharmacy {
    private Medicine[] meds = new Medicine[100];
    private int count = 0;
    private final String fileName = "medicines.txt";

    public void load() {
        BufferedReader r = null;
        try {
            r = new BufferedReader(new FileReader(fileName));
            String l;
            while ((l = r.readLine()) != null && count < meds.length) {
                if (l.trim().isEmpty()) continue;
                String[] p = l.split(",");
                try {
                    if (p.length == 5) {
                        Medicine m = new Medicine(p[0], p[1],
                                Double.parseDouble(p[2]), Integer.parseInt(p[3]),
                                DrugStatus.valueOf(p[4]));
                        meds[count++] = m;
                    } else if (p.length == 6) {
                        PrescriptionMedicine pm = new PrescriptionMedicine(p[0], p[1],
                                Double.parseDouble(p[2]), Integer.parseInt(p[3]),
                                DrugStatus.valueOf(p[4]), Boolean.parseBoolean(p[5]));
                        meds[count++] = pm;
                    }
                } catch (Exception e) {
                    System.out.println("سطر فيه مشكلة: " + l);
                }
            }
        } catch (IOException e) {
            System.out.println("مافي ملف قديم.");
        } finally {
            try { if (r != null) r.close(); } catch (IOException e) {}
        }
    }

    public void save() {
        PrintWriter w = null;
        try {
            w = new PrintWriter(new FileWriter(fileName));
            for (int i = 0; i < count; i++) {
                Medicine m = meds[i];
                String line = m.getCode() + "," + m.getName() + "," + m.getPrice()
                        + "," + m.getQty() + "," + m.getStatus();
                if (m instanceof PrescriptionMedicine) {
                    line = line + "," + ((PrescriptionMedicine)m).needRx();
                }
                w.println(line);
            }
        } catch (IOException e) {
            System.out.println("ما قدرنا نحفظ.");
        } finally {
            if (w != null) w.close();
        }
    }

    public void add(Medicine m) {
        if (count < meds.length) meds[count++] = m;
    }
    public void add(String c, String n, double pr, int q, DrugStatus s) {
        add(new Medicine(c, n, pr, q, s));
    }
    public void add(String c, String n, double pr, int q, DrugStatus s, boolean rx) {
        add(new PrescriptionMedicine(c, n, pr, q, s, rx));
    }

    public Medicine byCode(String c) {
        for (int i = 0; i < count; i++)
            if (meds[i].getCode().equalsIgnoreCase(c)) return meds[i];
        return null;
    }

    public Medicine[] byName(String n) {
        Medicine[] tmp = new Medicine[count];
        int num = 0;
        for (int i = 0; i < count; i++)
            if (meds[i].getName().equalsIgnoreCase(n)) tmp[num++] = meds[i];
        Medicine[] res = new Medicine[num];
        for (int i = 0; i < num; i++) res[i] = tmp[i];
        return res;
    }

    public void updatePrice(String code, double np) {
        Medicine m = byCode(code);
        if (m != null) m.setPrice(np);
    }
    public void updateQty(String code, int nq) {
        Medicine m = byCode(code);
        if (m != null) m.setQty(nq);
    }
    public void updateStatus(String code, DrugStatus st) {
        Medicine m = byCode(code);
        if (m != null) m.setStatus(st);
    }

    public void showAll() {
        for (int i = 0; i < count; i++) meds[i].print();
    }
    public void showByStatus(DrugStatus s) {
        for (int i = 0; i < count; i++)
            if (meds[i].getStatus() == s) meds[i].print();
    }

    public int size() { return count; }
}