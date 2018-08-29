package hashing;

public class DirectAddressing {
    String[] phoneBook = new String[10000000];

    private int getInt(String number) {
        return Integer.parseInt(number.replaceAll("\\D", ""));
    }

    public void setName(String number, String name) {
        int idx = this.getInt(number);
        this.phoneBook[idx] = name;
    }

    public String getName(String number) {
        int idx = this.getInt(number);
        return this.phoneBook[idx];
    }
}
