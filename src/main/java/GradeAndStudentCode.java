public class GradeAndStudentCode {
    String studentCode;
    Grade grade;
    GradeAndStudentCode(String studentCode){
        this.studentCode=studentCode;
    }
    public void setGrade(Grade grade){
        this.grade=grade;
    }

    public String toString(){
        StringBuilder codeAndGrade= new StringBuilder();
        codeAndGrade.append(studentCode);
        codeAndGrade.append(" dear Student your grade is: ");
        switch(grade){
            case A:
                codeAndGrade.append("A (5)");
            case B:
                codeAndGrade.append("B (4.5)");
            case C:
                codeAndGrade.append("C (4)");
            case D:
                codeAndGrade.append("D (3.5)");
            case E:
                codeAndGrade.append("E (3)");
            case F:
                codeAndGrade.append("F (2)");

        }

        return codeAndGrade.toString();
    }
}
