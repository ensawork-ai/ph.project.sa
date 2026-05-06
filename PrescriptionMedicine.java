// ملف دواء الوصفة الطبية
public class PrescriptionMedicine extends Medicine {
    private boolean rx;

    public PrescriptionMedicine() {
        super();
        this.rx = false;
    }

    public PrescriptionMedicine(String code, String name, double price, int qty,
                                 DrugStatus status, boolean rx) {
        super(code, name, price, qty, status);
        this.rx = rx;
    }

    public boolean needRx() { return rx; }
    public void setRx(boolean rx) { this.rx = rx; }

    @Override
    public void print() {
        super.print();
        System.out.println("  >> يحتاج وصفة: " + (rx ? "نعم" : "لا"));
    }
}