import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Тесты лучше вынести в отдельный от основного кода класс
public class ReversePolishNotationCalculatorTest {

    ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator(); //Нет модификатора доступа у этого поля.

    // Проверяем на пробелы - вместо комментариев можно использовать @DisplayName("Проверяем на пробелы"), через эту аннотацию можно задать тесту подходящее имя.
    @Test
    public void shouldSplit() {
        int value = calculator.calculatePolishNotation("  1   3  +  ");
        assertEquals(4, value); //две строчки можно совместить в одну - assertEquals(4, calculator.calculatePolishNotation("  1   3  +  ")), 
        //стоит добавлять в ассерты какие-нибудь маркерные сообщения, так можно будет понять на каком конкретно моменте тест падает.
    }

    //Проверка сложения
    @Test
    public void shouldCalculateAddition() {
        int value = calculator.calculatePolishNotation("1 2 +");
        assertEquals(3, value);
    }

    // Проверка сложения MAX величин
    @Test
    public void shouldCalculateAdditionMax() {
        int valueMax = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder().append(valueMax);
        int value = calculator.calculatePolishNotation(sb.toString() + " " + sb.toString() + " +");
        assertEquals((valueMax + valueMax), value);
    }

    // Проверка сложения MIN величин
    @Test
    public void shouldCalculateAdditionMin() {
        int valueMin = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder().append(valueMin);
        int value = calculator.calculatePolishNotation(sb.toString() + " " + sb.toString() + " +");
        assertEquals((valueMin + valueMin), value);
    }

    // Проверка сложения отрицательных чисел
    @Test
    public void shouldCalculateNegateAddition() {
        int value = calculator.calculatePolishNotation("-1 -2 +");
        assertEquals(-3, value);
    }

    // Проверка вычитания
    @Test
    public void shouldCalculateSubtraction() {
        int value = calculator.calculatePolishNotation("2 1 -");
        assertEquals(1, value);
    }

    // Проверка вычитания MAX величин
    @Test
    public void shouldCalculateSubtractionMax() {
        int valueMax = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder().append(valueMax);
        int value = calculator.calculatePolishNotation(sb.toString() + " " + sb.toString() + " -");
        assertEquals((valueMax - valueMax), value);
    }

    // Проверка вычитания MIN величин
    @Test
    public void shouldCalculateSubtractionMin() {
        int valueMin = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder().append(valueMin);
        int value = calculator.calculatePolishNotation(sb.toString() + " " + sb.toString() + " -");
        assertEquals((valueMin - valueMin), value);
    }

    // Проверка умножения
    @Test
    public void shouldCalculateMultiply() {
        int value = calculator.calculatePolishNotation("2 1 *");
        assertEquals(2, value);
    }

    // Проверка умножения MIN величин
    @Test
    public void shouldCalculateMultiplyMin() {
        int valueMin = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder().append(valueMin);
        int value = calculator.calculatePolishNotation(sb.toString() + " " + sb.toString() + " *");
        assertEquals((valueMin * valueMin), value);
    }

    // Проверка умножения MAX величин
    @Test
    public void shouldCalculateMultiplyMax() {
        int valueMax = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder().append(valueMax);
        int value = calculator.calculatePolishNotation(sb.toString() + " " + sb.toString() + " *");
        assertEquals((valueMax * valueMax), value);
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}
