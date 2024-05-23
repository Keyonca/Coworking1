package ru.parmacoworking.exceptions;

import org.springframework.util.Assert;

/**
 * Исключение выбрасывается при попытке удаления сущности, на которую есть ссылки у других сущностей
 */
public class EntityHasDetailsException extends BaseException{

    public EntityHasDetailsException(String message) {
        super(message);
    }

    public EntityHasDetailsException(String type, Object id) {
        this(formatMessage(type, id));
    }

    private static String formatMessage(String type, Object id){
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.notNull(id, "Идентификатор объекта не может быть null");
        Assert.hasText(id.toString(), "Идентификатор не может быть пустым");
        return String.format("%s ссылается на удаляемый объект с идентификатором $s ", type, id);
    }
}
