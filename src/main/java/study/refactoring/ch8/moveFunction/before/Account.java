package study.refactoring.ch8.moveFunction.before;

public class Account {
    private int daysOverdrawn;
    private AccountType type;

    public double getBankCharge() {
        double result = 4.5;
        if (daysOverdrawn > 0) {
            result += getOverdraftCharge();
        }
        return result;
    }

    public double getOverdraftCharge() {
        if (type.isPremium()) {
            final int baseCharge = 10;
            if (daysOverdrawn <= 7) {
                return baseCharge;
            }
            return baseCharge + (daysOverdrawn - 7) * 0.85;
        }
        return daysOverdrawn * 1.75;
    }
}
