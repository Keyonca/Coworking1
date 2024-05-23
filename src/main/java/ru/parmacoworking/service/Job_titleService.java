package ru.parmacoworking.service;

import ru.parmacoworking.entity.Job_title;

import java.util.List;

public interface Job_titleService {

    List<Job_title> findAll();

    Job_title findById(Object id);

    Job_title create(Job_title job_title);

    Job_title update(Job_title job_title);

    void delete(Object id);
}
