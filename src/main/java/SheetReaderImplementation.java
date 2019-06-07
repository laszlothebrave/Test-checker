import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;

public class SheetReaderImplementation implements SheetReader {

    private final int UNITS_IN_COLUMNS = 28;
    private final int ANSWERS_IN_COLUMN = 20;
    private final int LINE_THICKNESS = 1;
    private final int COLUMN_NUMBER = 2;
    private final int COLUMN_OFFSET = 6;
    private final int ANSWERS_NUMBER = 5;
    private final int DEFAULT_WIDTH = 794;
    private final int DEFAULT_HEIGHT = 1123;
    private final int UNITS_IN_CODE = 4;
    private final int CODE_OFFSET_UNITS = 1;

    private String get_student_code(Mat test_image, Rect answers_roi, double unit) {
        String alphabet = "ABCDEFGHI";
        boolean[] colored = new boolean[9];
        double char_offset = unit / 2;

        double x = answers_roi.x + unit*((double)(UNITS_IN_COLUMNS/2)-((double)(UNITS_IN_CODE-1)/2));
        double y = answers_roi.y - (CODE_OFFSET_UNITS + UNITS_IN_CODE) * unit;

        Rect code_roi = new Rect(new Point(x,y),new Size(UNITS_IN_CODE*unit,UNITS_IN_CODE*unit));
        Mat code_image = new Mat(test_image,code_roi);

        Imgproc.cvtColor(code_image, code_image, Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(code_image, code_image, 200, 255, Imgproc.THRESH_BINARY_INV);

        for(int i = 0;i<3;i++)
            for(int j=0;j<3;j++){
                Rect char_roi = new Rect(new Point(j*(unit + char_offset),i*(unit+char_offset)),new Size(unit, unit));
                Mat char_image = new Mat(code_image,char_roi);
                int pixel_number = Core.countNonZero(char_image);
                if(pixel_number >= unit*unit/5) colored[3*i+j] = true;
        }

        StringBuilder code = new StringBuilder();
        for(int i=0;i<colored.length;i++){
            if(colored[i]) code.append(alphabet.charAt(i));
        }

        return String.valueOf(code);
    }

    private int get_answer(Mat row, double unit) {
        Imgproc.cvtColor(row,row,Imgproc.COLOR_BGR2GRAY);
        Imgproc.threshold(row, row, 127, 255, Imgproc.THRESH_BINARY_INV);
        int answer_index = 0;
        int max_pixel_number = 0;
        for (int i = 0; i < ANSWERS_NUMBER; i++) {
            Rect answer = new Rect(new Point(unit*i*2,0),new Size(unit,unit));
            Mat answer_mat = new Mat(row,answer);
            int pixel_number = Core.countNonZero(answer_mat);
            if (pixel_number > max_pixel_number) {
                answer_index = i;
                max_pixel_number = pixel_number;
            }
        }
        if(max_pixel_number >= unit*unit/10)
            return answer_index;
        return -1;
    }

    private ArrayList<ArrayList<Integer>> get_answers(Mat answers_image, double unit){
        ArrayList<ArrayList<Integer>> answers = new ArrayList<>();
        for (int i = 0; i < COLUMN_NUMBER; i++) {
            for (int j = 0; j < ANSWERS_IN_COLUMN; j++) {
                Point start_point = new Point(unit * (i * (COLUMN_OFFSET + (2 * ANSWERS_NUMBER + 1)) + 2), (2 * j + 1) * unit);
                Rect row_roi = new Rect(start_point, new Size(unit * (ANSWERS_NUMBER * 2 - 1), unit));
                Mat row = new Mat(answers_image, row_roi);
                ArrayList<Integer> answers_in_row = new ArrayList<>();
                int answer = get_answer(row,unit);
                if(answer!=-1)
                    answers_in_row.add(answer);
                answers.add(answers_in_row);
            }
        }
        return answers;
    }

    private ArrayList<Point> answer_coords(Mat test_image, Mat corner_template) {
        ArrayList<Point> coords = new ArrayList<>();

        int match_method = Imgproc.TM_CCOEFF;
        int angle = 90;
        Point center = new Point(corner_template.cols() / 2, corner_template.rows() / 2);
        Size size = new Size(corner_template.cols(), corner_template.rows());

        for (int i = 0; i < 4; i++) {
            Mat rotation = Imgproc.getRotationMatrix2D(center, angle * i, 1);
            Mat match_result = new Mat();
            Imgproc.warpAffine(corner_template, corner_template, rotation, size);
            Imgproc.matchTemplate(test_image, corner_template, match_result, match_method);
            Core.MinMaxLocResult mmr = Core.minMaxLoc(match_result);
            coords.add(mmr.maxLoc);
        }
        return coords;
    }


    @Override
    public TestEvaluation readSheet(File file) {
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        System.load(dir + "/opencv_java410.dll");
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat test_image = Imgcodecs.imread(file.getPath(), Imgcodecs.IMREAD_COLOR);
        Mat corner_template = Imgcodecs.imread(dir + "/template.jpg");
        Imgproc.resize(test_image,test_image,new Size(DEFAULT_WIDTH,DEFAULT_HEIGHT));
        ArrayList<Point> answer_coords = answer_coords(test_image, corner_template);
        answer_coords.sort((o1, o2) -> {
            if (o1.x < o2.x)
                return 1;
            else if (o1.x == o2.x)
                if (o1.y < o2.y)
                    return 1;
                else return 0;
            else return 0;
        });
        HighGui.imshow("Display3", test_image);

        double column_width = (answer_coords.get(3).x - (answer_coords.get(0).x + corner_template.cols()));
        double column_height = (answer_coords.get(3).y - (answer_coords.get(0).y + corner_template.rows()));
        double unit = column_width / UNITS_IN_COLUMNS;

        Rect answers_roi = new Rect(new Point(answer_coords.get(0).x + corner_template.cols() + LINE_THICKNESS, answer_coords.get(0).y + corner_template.rows() + LINE_THICKNESS), new Size(column_width - LINE_THICKNESS, column_height));
        Mat answers_image = new Mat(test_image, answers_roi);

        get_student_code(test_image,answers_roi,unit);

       // HighGui.imshow("ans", answers_image);
        TestEvaluation testEvaluation = new TestEvaluation();
        testEvaluation.sourceFile = file.toPath();
        testEvaluation.checkedAnswers = get_answers(answers_image, unit);
        testEvaluation.studentCode =  get_student_code(test_image,answers_roi,unit);
        //HighGui.waitKey(0);
        return testEvaluation;
    }
}
