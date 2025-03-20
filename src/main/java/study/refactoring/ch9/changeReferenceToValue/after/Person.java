package study.refactoring.ch9.changeReferenceToValue.after;

import java.util.Objects;

public class Person {
    private TelephoneNumber telephoneNumber;

    public Person(TelephoneNumber telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getOfficeAreaCode() {
        return telephoneNumber.areaCode;
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        this.telephoneNumber = new TelephoneNumber(officeAreaCode, getOfficeNumber());
    }

    public String getOfficeNumber() {
        return telephoneNumber.number;
    }

    public void setOfficeNumber(String officeNumber) {
        this.telephoneNumber = new TelephoneNumber(getOfficeAreaCode(), officeNumber);
    }

    public static class TelephoneNumber {
        private String areaCode;
        private String number;

        public TelephoneNumber(String areaCode, String number) {
            this.areaCode = areaCode;
            this.number = number;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            TelephoneNumber that = (TelephoneNumber) object;
            return Objects.equals(areaCode, that.areaCode) && Objects.equals(number, that.number);
        }

        @Override
        public int hashCode() {
            return Objects.hash(areaCode, number);
        }
    }
}
