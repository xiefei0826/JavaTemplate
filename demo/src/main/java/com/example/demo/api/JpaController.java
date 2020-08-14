package com.example.demo.api;

import com.example.demo.repository.mysql.MemoryLibRepository;
import com.example.demo.model.mysql.MemoryLib;
import com.example.demo.model.mysql.StatusType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jpa")
public class JpaController {

    private final MemoryLibRepository memoryLibRepository;

    public JpaController(MemoryLibRepository memoryLibRepository) {
        this.memoryLibRepository = memoryLibRepository;
    }

    @GetMapping("/test")
    public List<MemoryLib> Get() {
        var data= memoryLibRepository.findAll();

        data.forEach(memoryLib -> memoryLib.setStatus(StatusType.Activity));
        memoryLibRepository.saveAll(data);
        return memoryLibRepository.findByIdDesc();
    }

}
