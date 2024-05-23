package ru.parmacoworking.exceptions;

/**
 * Исключение выбрасывается при конфликте с существующими данными
 */
public class EntityConflictException extends BaseException{

    public EntityConflictException(String message) {
        super(message);
    }
}
