package study.refactoring.ch8.moveFunction.after;

public class Account {
    private int daysOverdrawn;
    private AccountType type;

    public double getBankCharge() {
        double result = 4.5;
        if (daysOverdrawn > 0) {
            result += this.type.getOverdraftCharge(daysOverdrawn);
        }
        return result;
    }

}
