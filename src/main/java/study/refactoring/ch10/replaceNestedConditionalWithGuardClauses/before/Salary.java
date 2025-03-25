package study.refactoring.ch10.replaceNestedConditionalWithGuardClauses.before;

public class Salary {

    public int adjustedCapital(Instrument anInstrument) {
        int result = 0;
        if (anInstrument.capital > 0){
            if(anInstrument.interestRate > 0 && anInstrument.duration > 0){
                result = (anInstrument.income / anInstrument.duration) * anInstrument.adjustmentFactor;
            }
        }
        return result;
    }

    public static class Instrument {
        private int capital;
        private int interestRate;
        private int duration;
        private int income;
        private int adjustmentFactor;
    }
}
