package ru.parmacoworking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.parmacoworking.entity.Workplace;
import ru.parmacoworking.service.WorkplaceService;


import java.util.List;

@RestController
@RequestMapping("api/v1/workplace")
public class WorkplaceController {

    private final WorkplaceService workplaceService;

    @Autowired
    public WorkplaceController(WorkplaceService workplaceService) {
        this.workplaceService = workplaceService;
    }

    @GetMapping
    @PreAuthorize("hasPermission('workplace', 'read')")
    public List<Workplace> findAll(){
        return workplaceService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('workplace', 'read')")
    public Workplace findById(@PathVariable String id){
        return workplaceService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasPermission('workplace', 'create')")
    @ResponseStatus(HttpStatus.CREATED)
    public Workplace create(@RequestBody Workplace workplace){
        return workplaceService.create(workplace);
    }

    @PutMapping
    @PreAuthorize("hasPermission('workplace', 'update')")
    @ResponseStatus(HttpStatus.OK)
    public Workplace update(@RequestBody Workplace workplace){
        return workplaceService.update(workplace);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('workplace', 'delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        workplaceService.delete(id);
    }
}
