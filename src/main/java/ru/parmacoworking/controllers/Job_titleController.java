package ru.parmacoworking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.parmacoworking.entity.Job_title;
import ru.parmacoworking.service.Job_titleService;


import java.util.List;

@RestController
@RequestMapping("api/v1/job_title")
public class Job_titleController {

    private final Job_titleService job_titleService;

    @Autowired
    public Job_titleController(Job_titleService job_titleService) {
        this.job_titleService = job_titleService;
    }

    @GetMapping
    @PreAuthorize("hasPermission('job_title', 'read')")
    public List<Job_title> findAll(){
        return job_titleService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasPermission('job_title', 'read')")
    public Job_title findById(@PathVariable String id){
        return job_titleService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasPermission('job_title', 'create')")
    @ResponseStatus(HttpStatus.CREATED)
    public Job_title create(@RequestBody Job_title job_title){
        return job_titleService.create(job_title);
    }

    @PutMapping
    @PreAuthorize("hasPermission('job_title', 'update')")
    @ResponseStatus(HttpStatus.OK)
    public Job_title update(@RequestBody Job_title job_title){
        return job_titleService.update(job_title);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission('job_title', 'delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id){
        job_titleService.delete(id);
    }
}
