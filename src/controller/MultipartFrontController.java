package controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

// 파일 전송할 때 사용
// 게시글에 들어가는 이미지는 주소 "/image/첨부파일id/파일명"으로 하기
@WebServlet(name="multipartFrontController", urlPatterns="/multipart_front/*")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1,	// 1 MB
		maxFileSize = -1,
		maxRequestSize = -1,
		location = "D:/attached_file")			// ImageServer와 같게
public class MultipartFrontController extends FrontController {
	public MultipartFrontController() {
//		controllerMap.put("", new Controller());
	}
}