package com.omg.cmn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	final Logger LOG = LoggerFactory.getLogger(DownloadView.class);
	
	public DownloadView() 
	{
		setContentType("application/download;charset=utf-8");	
	}
	
	/**
	 * 다운로드 파일을 저장파일에서 원본 파일로 변환.
	 * @param orgFileNm
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void setDownloadFileName(String originName,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//브라우저 정보: IE/None IE
		String userAgent = request.getHeader("User-Agent");
		LOG.debug("originName1111111111111111:"+originName);
		LOG.debug("userAgent:"+userAgent);
		LOG.debug("URLEncoder.encode(originName:"+originName);
		
		//인코딩 되서 변환될 String
		String encodedFilename = null;
		
		//브라우저별 인코딩이 다름 : 파일이름 치환!
		
		//IE 11 version부터 식별자가 Trident로 변경
		if((userAgent.indexOf("Trident") >-1) || (userAgent.indexOf("MSIE")>-1)) 
		{
			//LOG.debug("[1]");
			//IE(file이름에 space가 있으면 치환 필요!)
			originName = URLEncoder.encode(originName,"utf-8").replaceAll("\\+", "%20");
	    //chrome
		}
		else if( (userAgent.indexOf("Chrome") >-1)  ) 
		{
			LOG.debug("[2]");
			originName = URLEncoder.encode(originName,"utf-8");
			
//			StringBuffer sb = new StringBuffer();
//
//            for (int i = 0; i < originName.length(); i++) {
//
//                   char c = originName.charAt(i);
//
//                   if (c > '~') {
//
//                         sb.append(URLEncoder.encode("" + c, "UTF-8"));
//
//                   } else {
//
//                         sb.append(c);
//
//                   }
//
//            }
//
//            encodedFilename = sb.toString();
			
		//그외	
		}else 
		{
			//LOG.debug("[3]");
			originName = URLEncoder.encode(originName,"utf-8");
		}
		
		response.setHeader("Content-Disposition", "attachment; fileName=\""+originName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
	}
	
	/**
	 * inputstream 파일을 읽어서 outputstream으로 파일 출력.
	 * @param downloadFile
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void downloadFile(File downloadFile,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FileInputStream  in=new FileInputStream(downloadFile);
		
		OutputStream   out= response.getOutputStream();
		try {
		FileCopyUtils.copy(in, out);
		out.flush();
		}catch(IOException e) {
			throw e;
		}finally {
			if(null !=in) {
				try {
				in.close();
				}catch(IOException i) {
					
				}
			}
			
			if(null !=out) {
				try {
				out.close();
				}catch(IOException i) {
					
				}
			}			
			
			
		}
		
	}
	
	
	//downloadView진입
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		setResponseContentType(request, response);
		
//		//		//data연결
//		model.addObject("orgFileNm", orgFileNm);//원본파일명
//		//file
//		File downloadFile=new File(saveFileNm);
//		model.addObject("downloadFile", downloadFile);//다운로드 파일 
        
		String originName = (String) model.get("originName");
		File downloadFile = (File) model.get("downloadFile");
		
		LOG.debug("originName:"+originName);
		LOG.debug("downloadFile:"+downloadFile);
		
		setDownloadFileName(originName,request,response);
		
		downloadFile(downloadFile,request,response);
		

	}

}
