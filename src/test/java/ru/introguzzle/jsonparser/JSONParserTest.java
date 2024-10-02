package ru.introguzzle.jsonparser;

import org.junit.Test;
import ru.introguzzle.jsonparser.entity.JSONArray;
import ru.introguzzle.jsonparser.entity.JSONObject;

import static org.junit.Assert.*;

public class JSONParserTest {
    private final Parser parser = new JSONParser();

    @Test
    public void test_simple_case() {
        String data = "{\"string_value\":\"Hello, World!\",\"integer_value\":42,\"float_value\":3.1415,\"boolean_true\":true,\"boolean_false\":false,\"null_value\":null,\"object_value\":{\"nested_string\":\"Nested\",\"nested_integer\":123},\"array_of_primitives\":[1,\"two\",false],\"array_of_objects\":[{\"id\":1,\"name\":\"Alice\"},{\"id\":2,\"name\":\"Bob\"}]}";
        JSONObject o = parser.parse(data, JSONObject.class);

        String stringValue = o.get("string_value", String.class);
        Number integerValue = o.get("integer_value", Number.class);
        Number floatValue = o.get("float_value", Number.class);
        Boolean booleanTrue = o.get("boolean_true", Boolean.class);
        Boolean booleanFalse = o.get("boolean_false", Boolean.class);
        Object nullValue = o.get("null_value", Object.class);
        JSONObject objectValue = o.get("object_value", JSONObject.class);

        // Assertions (проверки)
        assertEquals("Hello, World!", stringValue);
        assertEquals(42, integerValue.intValue());
        assertEquals(3.1415, floatValue.doubleValue(), 0.0001);
        assertTrue(booleanTrue);
        assertFalse(booleanFalse);
        assertNull(nullValue);

        // Проверка вложенного объекта
        String nestedString = objectValue.get("nested_string", String.class);
        Number nestedInteger = objectValue.get("nested_integer", Number.class);
        assertEquals("Nested", nestedString);
        assertEquals(123, nestedInteger.intValue());

        // Проверка массива примитивов
        JSONArray arrayOfPrimitives = o.get("array_of_primitives", JSONArray.class);
        assertEquals(1, arrayOfPrimitives.get(0, Number.class).intValue());
        assertEquals("two", arrayOfPrimitives.get(1, String.class));
        assertFalse(arrayOfPrimitives.get(2, Boolean.class));

        // Проверка массива объектов
        JSONArray arrayOfObjects = o.get("array_of_objects", JSONArray.class);
        JSONObject firstObject = arrayOfObjects.get(0, JSONObject.class);
        JSONObject secondObject = arrayOfObjects.get(1, JSONObject.class);

        assertEquals(1, firstObject.get("id", Number.class).intValue());
        assertEquals("Alice", firstObject.get("name", String.class));

        assertEquals(2, secondObject.get("id", Number.class).intValue());
        assertEquals("Bob", secondObject.get("name", String.class));
    }
}