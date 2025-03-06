package study.refactoring.ch7.extractClass.before;

public class Person {
    private String name;
    private int officeAreaCode;
    private int officeNumber;

    public String getTelephoneNumber() {
        return String.format("%s %s", this.officeAreaCode, this.officeNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOfficeAreaCode() {
        return officeAreaCode;
    }

    public void setOfficeAreaCode(int officeAreaCode) {
        this.officeAreaCode = officeAreaCode;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }
}
