package com.example.demo.repository.mysql;

import com.example.demo.model.mysql.MemoryLib;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoryLibRepository extends JpaRepository<MemoryLib, Long> {
    @Query("select a from MemoryLib a order by a.Id desc ")
    List<MemoryLib> findByIdDesc();
}
