package web.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class PhotoYsImp {
	private Image img;
	private int width;
	private int height;
	private String path;

	public void photoYs(String fileName, int w, int h) throws IOException {
		path = fileName;
		File file = new File(fileName);// �����ļ�
		img = ImageIO.read(file); // ����Image����
		width = img.getWidth(null); // �õ�Դͼ��
		height = img.getHeight(null); // �õ�Դͼ��

		if (width / height > w / h) {
			resizeByWidth(w);
		} else {
			resizeByHeight(h);
		}
	}

	public void resizeByWidth(int w) throws IOException {
		int h = (int) (height * w / width);
		resize(w, h);
	}

	public void resizeByHeight(int h) throws IOException {
		int w = (int) (width * h / height);
		resize(w, h);
	}

	public void resize(int w, int h) throws IOException {
		// SCALE_SMOOTH 压缩机制
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.SCALE_SMOOTH);
		image.getGraphics().drawImage(img, 0, 0, w, h, null); 
		File destFile = new File(path);
		FileOutputStream out = new FileOutputStream(destFile); 
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image); 
		out.close();
	}

}
