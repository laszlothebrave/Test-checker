import java.io.File;

public class CantReadCorrectAnswers extends Exception{
    File file;

    CantReadCorrectAnswers (File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Can't evaluate file: " + file.getPath().toString();
    }
}
