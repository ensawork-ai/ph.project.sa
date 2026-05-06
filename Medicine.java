// ملف الدواء الأساسي
public class Medicine {
    private String code;
    private String name;
    private double price;
    private int qty;
    private DrugStatus status;

    public Medicine() {
        this("000", "غير معروف", 0, 0, DrugStatus.AVAILABLE);
    }

    public Medicine(String code, String name, double price, int qty, DrugStatus status) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.status = status;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
    public DrugStatus getStatus() { return status; }
    public void setStatus(DrugStatus status) { this.status = status; }

    public void print() {
        System.out.println("كود: " + code + " | اسم: " + name + " | سعر: " + price
                + " | كمية: " + qty + " | حالة: " + status.arabic());
    }
}