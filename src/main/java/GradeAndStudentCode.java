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
                codeAndGrade.append("5 bardzo dobre");
                break;
            case B:
                codeAndGrade.append("4.5 dobre+");
                break;
            case C:
                codeAndGrade.append("4 dobre");
                break;
            case D:
                codeAndGrade.append("3.5 dostateczny+");
                break;
            case E:
                codeAndGrade.append("3 dostateczny");
                break;
            case F:
                codeAndGrade.append("2 nie dostateczny");
                break;

        }

        return codeAndGrade.toString();
    }
}
