package com.elearning.examservice.service;

import com.elearning.examservice.entity.Exam;
import com.elearning.examservice.entity.ExamResult;
import com.elearning.examservice.repository.ExamRepository;
import com.elearning.examservice.repository.ExamResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final ExamResultRepository examResultRepository;

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public ExamResult submitResult(Long examId, Long userId, Integer obtainedMarks) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + examId));

        double passThreshold = exam.getTotalMarks() * 0.6;
        boolean passed = obtainedMarks >= passThreshold;

        ExamResult result = ExamResult.builder()
                .examId(examId)
                .userId(userId)
                .score(obtainedMarks.doubleValue())
                .passed(passed)
                .build();

        return examResultRepository.save(result);
    }

    public List<ExamResult> getUserResults(Long userId) {
        return examResultRepository.findByUserId(userId);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with ID: " + id));
    }
}
