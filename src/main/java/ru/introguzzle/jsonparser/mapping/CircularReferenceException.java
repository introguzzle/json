package ru.introguzzle.jsonparser.mapping;

public class CircularReferenceException extends MappingException {
    public CircularReferenceException(Object object) {
        super("Circular reference: " + object);
    }
}