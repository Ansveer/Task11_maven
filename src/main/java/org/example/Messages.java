package org.example;

import static org.example.Main.*;

@Print(style = "*", color = RED)
public class Messages {
    @Print(style = "@", color = GREEN)
    public String happyBirthday() {
        return "С днём рождения!";
    }
    @Print(style = "!", color = YELLOW)
    public String warning() {
        return "Предупреждение";
    }
    @Print
    public String error() {
        return "Ошибка";
    }
    public String error2() {
        return "Вторая ошибка";
    }
    @Print(style = "?", color = BLUE)
    public String end() {
        return "Конец";
    }
}
