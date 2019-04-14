import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;


public class SheetReaderImplementation implements SheetReader{

    @Override
    public TestEvaluation readSheet(File file) {
        Mat imageArray;
        imageArray= Imgcodecs.imread(file.getPath(),Imgcodecs.IMREAD_COLOR);
        HighGui.namedWindow("Display",HighGui.WINDOW_NORMAL);
        HighGui.imshow("Display", imageArray);
        HighGui.waitKey();
        return new TestEvaluation();
    }
}
