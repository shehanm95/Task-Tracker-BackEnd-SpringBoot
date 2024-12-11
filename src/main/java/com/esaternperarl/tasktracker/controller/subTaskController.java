package com.esaternperarl.tasktracker.controller;

import com.esaternperarl.tasktracker.dto.SubTaskDto;
import com.esaternperarl.tasktracker.entity.SubTask;
import com.esaternperarl.tasktracker.mappers.SubTaskMapper;
import com.esaternperarl.tasktracker.service.SubTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subTask")
@RequiredArgsConstructor
public class subTaskController {
    private final SubTaskMapper subTaskMapper;
    private final SubTaskService subTaskService;

    @PostMapping("/add")
    public SubTask addSubtask( @RequestBody SubTaskDto subTaskDto){
        return subTaskService.add(subTaskDto);
    }

    @GetMapping("/all")
    public List<SubTaskDto> findAll(){
        return subTaskService.findAll().stream().map(subTaskMapper::toDto).toList();
    }

    @PutMapping("/update")
    public SubTaskDto updateSubtask(@RequestBody SubTaskDto subTaskDto){
        return subTaskMapper.toDto(subTaskService.update(subTaskDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSubTask(@PathVariable UUID id){
       subTaskService.delete(id);
    }
}
