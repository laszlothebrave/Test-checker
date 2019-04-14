import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class SheetReaderImplementation implements SheetReader{

    @Override
    public TestEvaluation readSheet(File file) {
        int thres = 10;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat test_image;
        test_image = Imgcodecs.imread(file.getPath(),Imgcodecs.IMREAD_COLOR);
        HighGui.namedWindow("Display",HighGui.WINDOW_AUTOSIZE);
        Mat test_image_gray = new Mat();
        Imgproc.cvtColor(test_image, test_image_gray, Imgproc.COLOR_BGR2GRAY);
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        // Imgproc.blur(test_image_gray, test_image_gray, new Size(3,3) );
        // Imgproc.threshold(test_image_gray,test_image_gray,127,255,0);
        Mat canny = new Mat();
        Imgproc.Canny(test_image_gray, canny, thres, thres*2, 3 );
        Imgproc.findContours(canny, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        for (int i=0; i<contours.size(); i++)
        {
            double peri = Imgproc.arcLength(new MatOfPoint2f(contours.get(i).toArray()),true);
            MatOfPoint2f approx = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()),approx,0.04*peri,true);
            if(approx.toArray().length==4)
                 Imgproc.drawContours( test_image, contours, (int)i,  new Scalar(0,200,100),3,1,  hierarchy, 0 );
        }

        HighGui.imshow("Display", test_image);
        HighGui.imshow("Display2", test_image_gray);
        HighGui.waitKey();
        return new TestEvaluation();
    }
}
