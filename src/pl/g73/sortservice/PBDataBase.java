package pl.g73.sortservice;

import java.util.Objects;

public class PBDataBase {
    private int size;
    private PhoneBookRecord[] phoneBookArray;

    public PBDataBase(int size) {
        this.size = size;
        phoneBookArray = new PhoneBookRecord[size];
    }

    public int getSize() {
        return size;
    }

    public PhoneBookRecord[] getPhoneBookArray() {
        return phoneBookArray;
    }

    public boolean put(PhoneBookRecord phoneBookRecord) {
        int index = findKeyIndex(phoneBookRecord);

        if (index == -1) {// array is full
            rehash();
            index = findKeyIndex(phoneBookRecord);
        }

        phoneBookArray[index] = phoneBookRecord;
        return true;
    }

    public int findKeyIndex(PhoneBookRecord phoneBookRecord) {
        int index = phoneBookRecord.hashCode() % size;
        while (!(phoneBookArray[index] == null || phoneBookArray[index].getKeyName().equals(phoneBookRecord.getKeyName()))) {
            index = (index + 1) % size;

            if (index == phoneBookRecord.hashCode() % size) {
                return -1;
            }
        }
        return index;
    }

    public int findKeyIndex2(String searchingName) {
        int index = Math.abs(Objects.hash(searchingName)) % size;

        while (!(phoneBookArray[index] == null || phoneBookArray[index].getKeyName().equals(searchingName))) {
            index = (index + 1) % size;

            if (index == Math.abs(Objects.hash(searchingName)) % size) {
                return -1;
            }
        }

        return index;
    }

    private void rehash() {
        this.size *= 2;
        PhoneBookRecord[] oldTable = phoneBookArray.clone();
        PhoneBookRecord[] resizedTable = new PhoneBookRecord[size];
        phoneBookArray = resizedTable;

        for (PhoneBookRecord phoneBookRecord : oldTable) {
            if (phoneBookRecord != null) {
                put(phoneBookRecord);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phoneBookArray.length; i++) {
            if (phoneBookArray[i] != null) {
                sb.append("index: " + i + " - tel." + phoneBookArray[i].getNumber() + ", name: " + phoneBookArray[i].getKeyName() + "\n");
            }
        }
        return sb.toString();
    }
}
