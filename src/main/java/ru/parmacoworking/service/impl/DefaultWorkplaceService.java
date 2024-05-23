package ru.parmacoworking.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ru.parmacoworking.entity.Reservation;
import ru.parmacoworking.entity.User;
import ru.parmacoworking.entity.Workplace;
import ru.parmacoworking.exceptions.EntityAlreadyExistsException;
import ru.parmacoworking.exceptions.EntityHasDetailsException;
import ru.parmacoworking.exceptions.EntityIllegalArgumentException;
import ru.parmacoworking.exceptions.EntityNotFoundException;
import ru.parmacoworking.jpa.ReservationRepository;
import ru.parmacoworking.jpa.WorkplaceRepository;
import ru.parmacoworking.service.WorkplaceService;

import java.util.List;

@Service
public class DefaultWorkplaceService implements WorkplaceService {

    private final WorkplaceRepository workplaceRepository;

    private final ReservationRepository reservationRepository;

    @Autowired
    public DefaultWorkplaceService(WorkplaceRepository workplaceRepository, ReservationRepository reservationRepository) {
        this.workplaceRepository = workplaceRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Workplace> findAll() {
        return workplaceRepository.findAll();
    }


    public Workplace findById(Object id) {
        Workplace workplace;
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

        workplace = workplaceRepository.findOne(parsedId);

        if (workplace == null) {
            throw new EntityNotFoundException(User.TYPE_NAME, parsedId);
        }
        return workplace;
    }

    public Workplace create(Workplace workplace) {
        if (workplace == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }
        if (workplace.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Workplace existedWorkplace = workplaceRepository.findOne(workplace.getId());
        if (existedWorkplace != null) {
            throw new EntityAlreadyExistsException(User.TYPE_NAME, workplace.getId());
        }
        return workplaceRepository.save(workplace);
    }

    @Override
    public Workplace update(Workplace workplace) {
        if (workplace == null) {
            throw new EntityIllegalArgumentException("Созданный объект не может быть null");
        }
        if (workplace.getId() == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }
        Workplace existedWorkplace = workplaceRepository.findOne(workplace.getId());
        if (existedWorkplace == null) {
            throw new EntityNotFoundException(Workplace.TYPE_NAME, workplace.getId());
        }
        return workplaceRepository.save(workplace);
    }

    public void delete(Object id) {
        Workplace workplace = findById(id);
        List<Reservation> reservations = reservationRepository.findByWorkplace(workplace);
        if (reservations.size() > 0) {
            throw new EntityHasDetailsException(Reservation.TYPE_NAME, workplace.getId());
        }
        workplaceRepository.delete(workplace);
    }

}
