package vn.codegym.model;

public class Email {
    private String language;
    private int pageSize;
    private boolean spamFillter;
    private String signature;

    public Email() {
    }

    public Email(String language, int pageSize, boolean spamFillter, String signature) {
        this.language = language;
        this.pageSize = pageSize;
        this.spamFillter = spamFillter;
        this.signature = signature;
    }

    public Email(String[] strings, int pageSize, int i, String hoa) {
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isSpamFillter() {
        return spamFillter;
    }

    public void setSpamFillter(boolean spamFillter) {
        this.spamFillter = spamFillter;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
