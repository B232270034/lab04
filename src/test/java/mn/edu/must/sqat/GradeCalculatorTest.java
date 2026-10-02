package mn.edu.must.sqat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    private GradeCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new GradeCalculator();
    }

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        double score = 90.0;
        String result = calculator.letterGrade(score);
        assertEquals("A", result);
    }

    @Test
    @DisplayName("Хязгаарын болон ердийн утгуудыг шалгах (letterGrade)")
    void letterGradeBoundaryAndNormalValues() {
        assertAll(
            () -> assertEquals("A", calculator.letterGrade(100.0)),
            () -> assertEquals("B", calculator.letterGrade(89.99)),
            () -> assertEquals("B", calculator.letterGrade(80.0)),
            () -> assertEquals("C", calculator.letterGrade(70.0)),
            () -> assertEquals("D", calculator.letterGrade(60.0)),
            () -> assertEquals("F", calculator.letterGrade(59.99)),
            () -> assertEquals("F", calculator.letterGrade(0.0))
        );
    }

    @Test
    @DisplayName("letterGrade буруу оролтод IllegalArgumentException шидэх ёстой")
    void letterGradeInvalidInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-1.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(100.1));
    }

    @ParameterizedTest
    @DisplayName("letterGrade хязгаарын утгуудыг @ParameterizedTest-ээр шалгах")
    @CsvSource({
        "95.0, A", "90.0, A", "89.99, B", "80.0, B",
        "75.0, C", "70.0, C", "65.0, D", "60.0, D",
        "59.99, F", "30.0, F", "0.0, F"
    })
    void letterGradeBoundariesParameterized(double score, String expectedGrade) {
        assertEquals(expectedGrade, calculator.letterGrade(score));
    }

    @Test
    @DisplayName("totalScore зөв нийлбэр бодох ёстой")
    void totalScoreValidCalculation() {
        double maxScore = calculator.totalScore(10, 40, 10, 10, 30);
        assertEquals(100.0, maxScore, 0.001);
    }

    @Test
    @DisplayName("totalScore сөрөг утгад exception шидэх ёстой")
    void totalScoreNegativeInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.totalScore(-1, 40, 10, 10, 30));
    }

    @Test
    @DisplayName("totalScore дээд хязгаар хэтрэхэд exception шидэх ёстой")
    void totalScoreExceedsLimitThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.totalScore(10, 40.1, 10, 10, 30));
    }

    @ParameterizedTest
    @DisplayName("totalScore зөв нийлбэрүүдийг @ParameterizedTest-ээр шалгах")
    @CsvSource({
        "10, 40, 10, 10, 30, 100.0",
        "0, 0, 0, 0, 0, 0.0",
        "5, 20, 5, 5, 15, 50.0"
    })
    void totalScoreValidParameterized(double att, double lab, double q1, double q2, double exam, double expected) {
        assertEquals(expected, calculator.totalScore(att, lab, q1, q2, exam), 0.001);
    }

    @ParameterizedTest
    @DisplayName("totalScore буруу оролтуудад exception шидэхийг шалгах")
    @CsvSource({
        "-1, 40, 10, 10, 30",
        "11, 40, 10, 10, 30",
        "10, 41, 10, 10, 30",
        "10, 40, 10, 10, 31"
    })
    void totalScoreInvalidParameterized(double att, double lab, double q1, double q2, double exam) {
        assertThrows(IllegalArgumentException.class, () -> calculator.totalScore(att, lab, q1, q2, exam));
    }
}