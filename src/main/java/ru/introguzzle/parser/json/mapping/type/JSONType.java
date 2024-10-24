package ru.introguzzle.parser.json.mapping.type;

import org.jetbrains.annotations.Nullable;
import ru.introguzzle.parser.json.entity.JSONObject;
import ru.introguzzle.parser.json.mapping.context.MappingContext;
import ru.introguzzle.parser.json.mapping.Mapper;

public enum JSONType implements TypeHandler {
    UNSPECIFIED(null),
    NUMBER(TypeHandlers.getTypeHandler(Number.class)),
    BOOLEAN(TypeHandlers.getTypeHandler(Boolean.class)),
    STRING(TypeHandlers.getTypeHandler(String.class)),
    ARRAY(TypeHandlers.getTypeHandler(Object[].class)),
    OBJECT(TypeHandlers.getTypeHandler(JSONObject.class)),;

    private final @Nullable TypeHandler handler;

    JSONType(@Nullable TypeHandler handler) {
        this.handler = handler;
    }

    @Override
    public Object handle(Mapper mapper, Object value, MappingContext context) {
        if (handler == null) {
            throw new AssertionError("Shouldn't be called");
        }

        return handler.handle(mapper, value, context);
    }
}
