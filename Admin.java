// ملف الأدمن
public class Admin extends User {
    public Admin() {
        super("admin", "1234");
    }

    public Admin(String u, String p) {
        super(u, p);
    }
}