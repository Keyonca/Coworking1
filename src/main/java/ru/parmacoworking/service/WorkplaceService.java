package ru.parmacoworking.service;

import ru.parmacoworking.entity.Workplace;

import java.util.List;

public interface WorkplaceService {

    List<Workplace> findAll();

    Workplace findById(Object id);

    Workplace create(Workplace workplace);

    Workplace update(Workplace workplace);

    void delete(Object id);
}
