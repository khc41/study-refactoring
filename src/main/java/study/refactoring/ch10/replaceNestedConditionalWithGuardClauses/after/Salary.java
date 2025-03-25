package study.refactoring.ch10.replaceNestedConditionalWithGuardClauses.after;

public class Salary {

    public int adjustedCapital(Instrument anInstrument) {
        if (anInstrument.capital <= 0
                || anInstrument.interestRate <= 0
                || anInstrument.duration <= 0) {
            return 0;
        }
        return (anInstrument.income / anInstrument.duration) * anInstrument.adjustmentFactor;
    }

    public static class Instrument {
        private int capital;
        private int interestRate;
        private int duration;
        private int income;
        private int adjustmentFactor;
    }
}
