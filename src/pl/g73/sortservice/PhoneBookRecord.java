package pl.g73.sortservice;

import java.util.Objects;

public class PhoneBookRecord {
    private String keyName;
    private String number;

    public PhoneBookRecord(String keyName, String number) {
        this.keyName = keyName;
        this.number = number;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getNumber() {
        return number;
    }

//    @Override
//    public int hashCode() { //too slow
//        byte[] value = this.keyName.getBytes();
//        int h = 0;
//        for (byte b : value) {
//            h = 31 * h + b;
//        }
//        return this.keyName != null ? Math.abs(h) : 0;
//    }

    @Override
    public int hashCode() {
        return Math.abs(Objects.hash(keyName));
    }

    @Override
    public String toString() {
        return "[" + keyName + "," + number + "]";
    }
}
