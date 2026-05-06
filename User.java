// ملف المستخدم
public class User {
    private String user;
    private String pass;

    public User() { this("user", "pass"); }

    public User(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public boolean check(String u, String p) {
        return this.user.equals(u) && this.pass.equals(p);
    }
}