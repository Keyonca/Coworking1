package ru.parmacoworking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.parmacoworking.entity.Booking_history;
import ru.parmacoworking.service.Booking_historyService;

import java.util.List;

@RestController
@RequestMapping("api/v1/booking_history")
public class Booking_historyController {

    private final Booking_historyService booking_historyService;

    @Autowired
    public Booking_historyController(Booking_historyService booking_historyService) {
        this.booking_historyService = booking_historyService;
    }

    @GetMapping
    @PreAuthorize("hasPermission('booking_history', 'read')")
    public List<Booking_history> findAll(){
        return booking_historyService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('booking_history', 'read')")
    public Booking_history findById(@PathVariable String id){
        return booking_historyService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasPermission('booking_history', 'create')")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking_history create(@RequestBody Booking_history booking_history){
        return booking_historyService.create(booking_history);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('booking_history', 'delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        booking_historyService.delete(id);
    }
}
