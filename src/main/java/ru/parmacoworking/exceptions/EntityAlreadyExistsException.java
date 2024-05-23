package ru.parmacoworking.exceptions;

import org.springframework.util.Assert;

/**
 * Исключение выбрасывается при повторном создании сущности с заданным ключом
 */

public class EntityAlreadyExistsException extends BaseException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String type, Object id) {
        this(formatMessage(type, id));
    }

    private static String formatMessage(String type, Object id){
        Assert.hasText(type, "Тип не может быть пустым");
        Assert.notNull(id, "Идентификатор объекта не может быть null");
        Assert.hasText(id.toString(), "Идентификатор не может быть пустым");
        return String.format("%s с ключом $s уже существует", type, id);
    }
}
