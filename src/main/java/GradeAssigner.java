//Andrzej

public interface GradeAssigner {
    void setAnswerKey (TestEvaluation correctAnswers);
    GradeAndStudentCode assignGrade (TestEvaluation studentAnswers);
}

