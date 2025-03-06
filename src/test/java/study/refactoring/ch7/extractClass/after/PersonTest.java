package study.refactoring.ch7.extractClass.after;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

    @Test
    void testGetTelephoneNumber() {
        Person person = new Person("heechul", new TelephoneNumber("010", "12345678"));

        assertThat(person.getTelephoneNumber()).isEqualTo("010 12345678");
    }

}