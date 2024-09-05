//LocalDateTimeAdapter, який реалізує спеціальний адаптер для серіалізації та десеріалізації об'єктів типу LocalDateTime
// в JSON і назад, використовуючи бібліотеку Gson. Ось пояснення кожної частини цього коду:

package org.example;

import com.google.gson.TypeAdapter; //клас з бібліотеки Gson, який використовується для створення спеціальних (кастомних) адаптерів для серіалізації/десеріалізації об'єктів у JSON.
import com.google.gson.stream.JsonReader;//класи використовуються для читання і запису JSON-даних відповідно.
import com.google.gson.stream.JsonWriter;

import java.io.IOException; //є частиною стандартної бібліотеки Java і представляє виняток, який викидається, коли виникає проблема вводу/виводу (наприклад, під час роботи з файлами, мережевими операціями або потоками даних).
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  //Використовується для форматування об'єктів LocalDateTime у певний текстовий формат і парсингу строк назад у LocalDateTime.

//реалізує спеціальні методи для серіалізації та десеріалізації LocalDateTime.
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    //константний об'єкт для форматування дати у рядковий формат ISO ("yyyy-MM-dd'T'HH:mm:ss"), який буде використовуватися під час серіалізації та десеріалізації LocalDateTime.
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        if (localDateTime != null) {
            jsonWriter.value(localDateTime.format(formatter));
        } else {
            jsonWriter.nullValue();
        }
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        String dateTime = jsonReader.nextString();
        return LocalDateTime.parse(dateTime, formatter);
    }
}