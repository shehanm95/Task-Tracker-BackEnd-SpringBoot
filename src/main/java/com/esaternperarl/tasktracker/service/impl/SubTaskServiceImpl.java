package com.esaternperarl.tasktracker.service.impl;

import com.esaternperarl.tasktracker.dto.SubTaskDto;
import com.esaternperarl.tasktracker.entity.SubTask;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.mappers.SubTaskMapper;
import com.esaternperarl.tasktracker.repo.SubTaskRepo;
import com.esaternperarl.tasktracker.repo.TaskRepo;
import com.esaternperarl.tasktracker.service.SubTaskService;
import com.esaternperarl.tasktracker.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubTaskServiceImpl implements SubTaskService {
    private final SubTaskRepo subTaskRepo;
    private final SubTaskMapper subTaskMapper;
    private final TaskRepo taskRepo;
    private final Logger logger = LoggerFactory.getLogger(SubTaskServiceImpl.class);

    @Autowired
    @Lazy
    private TaskService taskService;

    @Override
    public SubTask add(SubTaskDto subTaskDto) {
        return taskRepo.findById(subTaskDto.getTaskDto().getId())
                .map(task -> subTaskRepo.save(subTaskMapper.toEntity(subTaskDto))
                )
                .orElseThrow(() -> {
                    logger.error("Requested with a bad task ID: {}", subTaskDto.getTaskDto().getId());
                    return new IllegalArgumentException("Task not found. Please submit with a valid task ID.");
                });
    }

    @Override
    public List<SubTask> findAll() {
        return subTaskRepo.findAll();
    }

    @Override
    @Transactional
    public Task update(SubTaskDto subTaskDto) {
        return subTaskRepo.findById(subTaskDto.getId())
                .map(subTask -> {
                    if(subTask.getTask().getId().equals(subTaskDto.getTaskDto().getId())){
                        SubTask savedSubTask = subTaskRepo.save(subTaskMapper.toEntity(subTaskDto));
                        //setting Main task Finished Rate and save;
                        Task mainTask = taskService.taskFinishedRateSetter(savedSubTask.getTask().getId()); //should I save this inside the private method or here, as we are in a DB Transactional method
                        return taskRepo.save(mainTask);

                    }else {
                        throw new IllegalArgumentException("it's task id is not matching with its original subTask");
                    }
                })
                .orElseThrow(
                        ()-> new IllegalArgumentException("original sub Task is not Exist in the database")
                );
    }



    @Override
    @Transactional
    public void delete(Long id) {
        subTaskRepo.findById(id).ifPresentOrElse(subTaskRepo::delete,  ()-> new IllegalArgumentException("Sub Task Doesn't exist in the database"));

    }


}
