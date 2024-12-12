package com.esaternperarl.tasktracker.service.impl;

import com.esaternperarl.tasktracker.dto.TaskDto;
import com.esaternperarl.tasktracker.entity.SubTask;
import com.esaternperarl.tasktracker.entity.Task;
import com.esaternperarl.tasktracker.mappers.TaskMapper;
import com.esaternperarl.tasktracker.repo.SubTaskRepo;
import com.esaternperarl.tasktracker.repo.TaskRepo;
import com.esaternperarl.tasktracker.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final SubTaskRepo subTaskRepo;

    @Override
    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    @Override
    public Task add(TaskDto taskDto) {

        return taskRepo.save(taskMapper.toEntity(taskDto));
    }

    @Override
    public Task update(TaskDto taskDto) {
        return taskRepo.findById(taskDto.getId())
                .map((task) -> {
                    return taskRepo.save(taskMapper.toEntity(taskDto));
                    // Save the updated entity.
                })
                .orElseThrow(() ->
                new IllegalArgumentException("Task ID does not exist"));
    }

    @Override
    public void delete(Long id) {
        taskRepo.findById(id)
                .ifPresentOrElse(taskRepo::delete,()-> {
        throw new IllegalArgumentException("This task doesn't exist in the database...!");});
    }

    @Override
    public Task getById(Long id) {
        return this.taskFinishedRateSetter(id);
        //return taskRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Requested Task Cannot Find In The Database...!"));
    }

    @Override
    /**
     * Calculates and updates the finishing rate of a task based on its subtasks.
     *
     * Note:
     * - This method updates the finishing rate and completion status of the task.
     * - The returned task must be explicitly saved within a transactional method
     *   to persist these changes in the database.
     *
     * @param id the unique identifier of the task
     * @return the updated Task object with finishing rate and completion status
     * @throws IllegalArgumentException if the task is not found in the database
     */
    public Task taskFinishedRateSetter(Long taskId) {
        // Retrieve task from repository
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Main task cannot be found in the database by ID"));

        // Get the list of subtasks
        List<SubTask> subTaskList = task.getSubTaskList();
        int allSubTaskCount = subTaskList.size();

        // Handle case with no subtasks
        if (allSubTaskCount == 0) {
            task.setFinishingRate(0.0);
            task.setIsFinished(false);
            return task;
        }
       // Count finished subtasks
        long finishedSubTaskCount = subTaskList.stream()
                .filter(subTask -> subTask != null && Boolean.TRUE.equals(subTask.getIsFinished())) // Null-safe filtering
                .count();

        // Calculate the finishing rate
        double finishedRate = ((double) finishedSubTaskCount / allSubTaskCount) * 100;

        // Set finishing rate and completion status
        task.setFinishingRate(finishedRate);
        task.setIsFinished(finishedRate == 100.0);

        return task;
    }

    @Override
    @Transactional
    public Task markAsFinished(Long id) {
        // Retrieve task from repository
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Main task cannot be found in the database by ID"));

        // Get the list of subtasks
        List<SubTask> subTaskList = task.getSubTaskList();
        //marking them as finished;
        subTaskList.forEach(subTask -> subTask.setIsFinished(true));
        //saving finishedSubtask List
        subTaskRepo.saveAll(subTaskList);
        task.setFinishingRate(100.0);
        task.setIsFinished(true);
        return taskRepo.save(task);
    }


}
