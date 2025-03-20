package study.refactoring.ch9.changeReferenceToValue.before;

public class Person {
    private final TelephoneNumber telephoneNumber;

    public Person(TelephoneNumber telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getOfficeAreaCode() {
        return telephoneNumber.areaCode;
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        telephoneNumber.areaCode = officeAreaCode;
    }

    public String getOfficeNumber() {
        return telephoneNumber.number;
    }

    public void setOfficeNumber(String officeNumber) {
        this.telephoneNumber.number = officeNumber;
    }

    public static class TelephoneNumber {
        private String areaCode;
        private String number;

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
