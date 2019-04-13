//Andrzej

public interface GradeAssigner {
    void setAnswerKey (TestEvaluation correcrAnswers);
    GradeAndStudentCode assignGrade (TestEvaluation studentAnswers);
}
