package study.refactoring.ch8.moveField.before;

import java.time.LocalDateTime;

public class CustomerContract {
    private LocalDateTime startDate;

    public CustomerContract(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}
