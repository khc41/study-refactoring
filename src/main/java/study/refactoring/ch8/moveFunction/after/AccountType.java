package study.refactoring.ch8.moveFunction.after;

public class AccountType {
    private boolean isPremium;

    public boolean isPremium() {
        return isPremium;
    }

    public double getOverdraftCharge(int daysOverdrawn) {
        if (isPremium) {
            final int baseCharge = 10;
            if (daysOverdrawn <= 7) {
                return baseCharge;
            }
            return baseCharge + (daysOverdrawn - 7) * 0.85;
        }
        return daysOverdrawn * 1.75;
    }
}
