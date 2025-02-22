package com.example.quizapp.service;

import com.example.quizapp.model.*;
import com.example.quizapp.repository.QuizRepository;
import com.example.quizapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;


    @Transactional
    public Task assignQuizToBatch(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(String id, Task task) {
        return taskRepository.findById(id).map(existingTask -> {
            existingTask.setBatchId(task.getBatchId());
            existingTask.setQuizId(task.getQuizId());
            existingTask.setStartTime(task.getStartTime());
            existingTask.setEndTime(task.getEndTime());
            return taskRepository.save(existingTask);
        }).orElse(null);
    }


    public List<TaskResults> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResults> taskResults = new ArrayList<>();
        for (Task task : tasks) {
            TaskResults taskResult = new TaskResults();
            taskResult.setTask(task);
            List<Answer> answers = answerService.getAnswersByTaskId(task.getId());
            Quiz quiz = quizService.getQuizById(task.getQuizId());
            List<User> users = userService.getUsersByBatchId(task.getBatchId());

            List<Integer> correctAnswers = new ArrayList<>();
            for (Question question : quiz.getQuestions()) {
                correctAnswers.add(Integer.parseInt(question.getCorrectOption()));
            }

            int totalQuestions = correctAnswers.size();

            List<Map<String, Object>> scores = new ArrayList<>();
            for( User user : users){
                boolean isSubmitted = false;
                for(Answer answer : answers){
                    if(answer.getStudentId().equals(user.getId())){
                        int userScore = 0;
                        for(int i = 0; i < answer.getAnswerOptions().size(); i++){
                            if(answer.getAnswerOptions().get(i) == correctAnswers.get(i)){
                                userScore++;
                            }
                        }
                        Map<String, Object> score = Map.of("student", user, "score",  (userScore *100 /totalQuestions));
                        scores.add(score);
                        isSubmitted = true;
                        break;
                    }
                }
                if(isSubmitted){
                    continue;
                }
                Map<String, Object> score = Map.of("student", user, "score",  -1);
                scores.add(score);
            }

            taskResult.setScores(scores);
            taskResults.add(taskResult);
        }
        return taskResults;
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByBatchId(String batchId) {
        return taskRepository.findByBatchId(batchId);
    }

    public List<TasksResponse> getTasksForStudent(User user) {
        List<Task> tasks = taskRepository.findByBatchId(user.getBatchId());
        List<Answer> answers = answerService.getAnswersByUserId(user.getId());
        List<TasksResponse> tasksResponseList = new ArrayList<>();
        for (Task task : tasks) {
            TasksResponse tasksResponse = new TasksResponse();
            tasksResponse.setTask(task);
            tasksResponse.setStatus("Not Submitted");

            boolean isSubmitted = false;
            for (Answer answer : answers) {
                if (answer.getTaskId().equals(task.getId())) {
                    tasksResponse.setStatus("Submitted");
                    tasksResponse.setAnswer(answer);
                    isSubmitted = true;
                    break;
                }
            }
            Quiz quiz = quizService.getQuizById(task.getQuizId());
            if(!isSubmitted) {
                for(Question question : quiz.getQuestions()){
                    question.setCorrectOption(null);
                }
            }
            tasksResponse.setQuiz(quiz);
            tasksResponseList.add(tasksResponse);
        }

        return tasksResponseList;
    }
}


