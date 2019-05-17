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
                break;
            case B:
                codeAndGrade.append("B (4.5)");
                break;
            case C:
                codeAndGrade.append("C (4)");
                break;
            case D:
                codeAndGrade.append("D (3.5)");
                break;
            case E:
                codeAndGrade.append("E (3)");
                break;
            case F:
                codeAndGrade.append("F (2)");
                break;

        }

        return codeAndGrade.toString();
    }
}
