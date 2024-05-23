package ru.parmacoworking.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ru.parmacoworking.entity.Job_title;
import ru.parmacoworking.entity.User;
import ru.parmacoworking.exceptions.EntityAlreadyExistsException;
import ru.parmacoworking.exceptions.EntityIllegalArgumentException;
import ru.parmacoworking.exceptions.EntityNotFoundException;
import ru.parmacoworking.jpa.Job_titleRepository;
import ru.parmacoworking.service.Job_titleService;

import java.util.List;

@Service
public class DefaultJob_titleService implements Job_titleService {

    private final Job_titleRepository job_titleRepository;

    @Autowired
    public DefaultJob_titleService(Job_titleRepository job_titleRepository) {
        this.job_titleRepository = job_titleRepository;
    }

    public List<Job_title> findAll() {
        return job_titleRepository.findAll();
    }

    public Job_title findById(Object id) {
        Job_title job_title;
        if (id == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Long parsedId;
        try {
            parsedId = Long.valueOf((String) id);
        } catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор" +
                    "к нужному типу, текст ошибки %s", ex));
        }

        job_title = job_titleRepository.findOne(parsedId);

        if (job_title == null) {
            throw new EntityNotFoundException(User.TYPE_NAME, parsedId);
        }
        return job_title;
    }

    public Job_title create(Job_title job_title) {
        if (job_title == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }
        if (job_title.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Job_title existedJob_title = job_titleRepository.findOne(job_title.getId());
        if (existedJob_title != null) {
            throw new EntityAlreadyExistsException(Job_title.TYPE_NAME, job_title.getId());
        }
        return job_titleRepository.save(job_title);
    }

    @Override
    public Job_title update(Job_title job_title) {
        if (job_title == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }
        if (job_title.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Job_title existedJob_title = job_titleRepository.findOne(job_title.getId());
        if (existedJob_title == null) {
            throw new EntityNotFoundException(Job_title.TYPE_NAME, job_title.getId());
        }
        return job_titleRepository.save(job_title);
    }

    public void delete(Object id) {
        Job_title job_title = findById(id);
        job_titleRepository.delete(job_title);
    }
}


