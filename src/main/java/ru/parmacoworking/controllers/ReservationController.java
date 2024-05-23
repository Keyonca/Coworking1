package ru.parmacoworking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.parmacoworking.entity.Reservation;
import ru.parmacoworking.service.ReservationService;


import java.util.List;

@RestController
@RequestMapping("api/v1/reservation")
public class ReservationController {

    private final ReservationService resrvationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.resrvationService = reservationService;
    }

    @GetMapping
    @PreAuthorize("hasPermission('reservation', 'read')")
    public List<Reservation> findAll(){
        return resrvationService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('reservation', 'read')")
    public Reservation findById(@PathVariable String id){
        return resrvationService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasPermission('reservation', 'create')")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation create(@RequestBody Reservation reservation){
        return resrvationService.create(reservation);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('reservation', 'delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        resrvationService.delete(id);
    }
}
