package com.elearning.examservice.controller;

import com.elearning.examservice.entity.Exam;
import com.elearning.examservice.entity.ExamResult;
import com.elearning.examservice.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam createdExam = examService.createExam(exam);
        return ResponseEntity.ok(createdExam);
    }

    @PostMapping("/{examId}/submit")
    public ResponseEntity<ExamResult> submitExamResult(
            @PathVariable Long examId,
            @RequestParam Long userId,
            @RequestParam Integer obtainedMarks) {
        ExamResult result = examService.submitResult(examId, userId, obtainedMarks);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/results/{userId}")
    public ResponseEntity<List<ExamResult>> getUserResults(@PathVariable Long userId) {
        List<ExamResult> results = examService.getUserResults(userId);
        return ResponseEntity.ok(results);
    }
}
