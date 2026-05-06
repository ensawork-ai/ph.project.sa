// ملف حالة الدواء
public enum DrugStatus {
    AVAILABLE, UNAVAILABLE, EXPIRED;

    public String arabic() {
        if (this == AVAILABLE) return "متاح";
        else if (this == UNAVAILABLE) return "غير متاح";
        else return "منتهي الصلاحية";
    }
}