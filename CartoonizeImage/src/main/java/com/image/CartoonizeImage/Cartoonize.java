package com.image.CartoonizeImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Cartoonize {

	static {
		nu.pattern.OpenCV.loadLocally();	
	}

	public static void img() {
		Mat img = Imgcodecs.imread("watermelon.png");
		
		Mat gray = new Mat();
		Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY);
		
		Mat blur = new Mat();
		Imgproc.medianBlur(gray, blur, 5);
		
		Mat edges = new Mat();
		Imgproc.adaptiveThreshold(blur,edges, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 9, 9);
		
		Mat colorImg = new Mat();
		Imgproc.bilateralFilter(edges, colorImg, 9, 250, 250);
		
		Mat cartoon = new Mat();
		Core.bitwise_and(colorImg, edges, cartoon);
		
		Imgcodecs.imwrite("watermelon-bw.png", cartoon);
	}
	
}
