package com.omg.cmn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omg.code.domain.Code;


public class StringUtil {
	final static Logger LOG = LoggerFactory.getLogger(StringUtil.class);
	/**
	 * 
	 *Method Name:renderPaging
	 *html 특수 문자 url : https://dev.w3.org/html5/html-author/charref
	 *작성일: 2020. 9. 23
	 *작성자: sist
	 *설명:
	 *@param maxNum : 21
	 *@param currPageNo : 1
	 *@param rowPerPage : 10
	 *@param bottomCount : 10 
	 *@param url :/EJBDC/board/board.do
	 *@param scriptName :doSearchPage
	 *@return
	 */
	  public static String renderPaging(int maxNum, int currPageNo, int rowPerPage, int bottomCount,
	   String url, String scriptName) {

	   /**
	    * 총글수: 21
	    * 현재페이지: 1
	    * 한페이지에 보여질 행수: 10
	    * 바닥에 보여질 페이지 수: 10
	    * << 시작페이지
	    * < bottomCount : -10개씩
	    * > bottomCount : +10개씩
	    * >> 마지막 page
	    * 
	    * << < 1 2 3 4 5 6 7 8 9 10 > >>
	    */


	   int maxPageNo = ((maxNum - 1) / rowPerPage) + 1;//3
	   int startPageNo = ((currPageNo - 1) / bottomCount) * bottomCount + 1;//1
	   int endPageNo = ((currPageNo - 1) / bottomCount + 1) * bottomCount;//10
//	   LOG.debug("rowPerPage:"+rowPerPage);
//	   LOG.debug("bottomCount:"+bottomCount);
//	   LOG.debug("maxNum:"+maxNum);
//	   LOG.debug("maxPageNo:"+maxPageNo);
//	   LOG.debug("startPageNo:"+startPageNo);
//	   LOG.debug("endPageNo:"+endPageNo);
	   int nowBlockNo = ((currPageNo - 1) / bottomCount) + 1;//1
	   int maxBlockNo = ((maxNum - 1) / bottomCount) + 1;//3
	   LOG.debug("nowBlockNo:"+nowBlockNo);
	   LOG.debug("maxBlockNo:"+maxBlockNo);
	   int inx = 0;
	   //html 
	   StringBuilder html = new StringBuilder();
	   
	   
	   if (currPageNo > maxPageNo) {
	    return "";
	   }

	   html.append("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">   \n");
	   html.append("<tr>                       \n");
	   html.append("<td align=\"center\">                                                                    \n");
	   //html.append("<ul class=\"pagination pagination-sm\">                                                  \n");
	   html.append("<ul class=\"pagination \">                                                  \n");
	   
	   
	   // <<
	   if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
	    html.append("<li class=\"active\"> <a href=\"javascript:" + scriptName + "( '" + url+ "', 1 );\">  \n");
	    html.append("&laquo;   \n");
	    html.append("</a></li> \n");
	   }

	   // <
	   if (startPageNo > bottomCount) {
	    html.append("<li class=\"active\"> <a href=\"javascript:" + scriptName + "( '" + url + "'," + (startPageNo - bottomCount)+ ");\"> \n");
	    html.append("<        \n");
	    html.append("</a></li>     \n");
	   }


	   // 1 2 3 ... 10 (숫자보여주기)
	   for (inx = startPageNo; inx < maxPageNo && inx <= endPageNo; inx++) {
			if (inx == currPageNo) {// 현재 page
				html.append("<li  class=\"disabled \" 	>");
				html.append("<a  href=\"javascript:#\"  > ");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li>");
			} else {
				html.append("<li  class=\"active\">");
				html.append("<a  href=\"javascript:" + scriptName + "('" + url + "'," + inx + ");\"  > ");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li>");
			}
	   }
	   
	   // >
	   LOG.debug("maxPageNo:"+maxPageNo);
	   LOG.debug("inx:"+inx);
	   if (maxPageNo > inx) {
	    html.append("<li class=\"active\"><a href=\"javascript:" + scriptName + "('" + url + "',"+ ((nowBlockNo * bottomCount) + 1) + ");\"> \n");
	    html.append(">                       \n");
	    html.append("</a></li>              \n");
	   }

	   // >>
	   if (maxPageNo > inx) {
	    html.append("<li class=\"active\"><a href=\"javascript:" + scriptName + "('" + url + "'," + maxPageNo+ ");\">      \n");
	    html.append("&raquo;     \n");
	    html.append("</a></li>    \n");
	   }

	   html.append("</td>   \n");
	   html.append("</tr>   \n");
	   html.append("</table>   \n");

	   return html.toString();
	  }
	
	/**
	 * select생성
	 * @param list
	 * @param selectBoxNm
	 * @param selectNm
	 * @param allYN
	 * @return String
	 */
//	public static String makeSelectBox(List<CodeVO> list,
//                                       String selectBoxNm,
//                                       String selectNm,
//                                       boolean allYN) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("<select name='"+selectBoxNm+"' id='"+selectBoxNm+"' class=\"form-control input-sm\"> \n");
//		
//		//전체
//		if(allYN==true) {
//			sb.append("<option value=\"\">전체</option> \n");
//		}
//		
//		if(null !=list) {
//			for(CodeVO vo:list) {
//				sb.append("<option value='"+vo.getDetCode()+"' ");
//				//selectbox선택
//				if(selectNm.equals(vo.getDetCode())) {
//					sb.append(" selected ");
//				}
//				sb.append(">");
//				//코드이름
//				sb.append(vo.getDetNm());
//				sb.append("</option>\n");
//			}
//		} 
//		
//		sb.append("</select>");
//		return sb.toString();
//	}
	
	

	//nvl
    /**
     * request NVL(원본,치환)
	 * @param val(원본 String)
	 * @param rep(치환 String)
     * @return String
     */
	public static String nvl(String val,String rep) {
		if(null == val || "".equals(val)) {
			val = rep;
		}else {
			// \n    -> <br/>
			// space -> &nbsp;
			
		}
		
		return val.trim();
	}
	
	
	public static String replace(String source,String org,String replace) {
		String resultStr="";
		resultStr = source.replaceAll(replace, org);
		return resultStr;
	}
	
   /**
    *  형식 날짜 출력
    * @param format(yyyy/MM/dd)
    * @return format Date String
    */
	public static String formatDate(String format) {
		Date date = new Date();//날짜 객체
		SimpleDateFormat  sdf01=new SimpleDateFormat(format);
		
		return sdf01.format(date);
	}
	
	/**
	 * UUID: 32 length
	 */
	public static String getUUID() {
		String str = "";
		UUID  uuid=UUID.randomUUID();
		str = uuid.toString().replaceAll("-", "");		
		return str;
	}
	
	/**
	 * 
	 * @param format
	 * @return
	 */
	public static String getPK(String format) {
	
		return formatDate(format)+getUUID();
	}
	
	public static List<String> makeForeach(String codeList, String gb){
		if(null == codeList || "".equals(codeList)) {
			return null;
		}
		
		List<String> cdList=new ArrayList<String>();
		String[] aCode=codeList.split(gb);
		for(int i=0;i<aCode.length;i++) {
			cdList.add(aCode[i].toString());
		}
		return cdList;
	}
	
	public static String makeSelectBox(List<Code> list,
							           String selectBoxNm,
							           String selectNm,
							           boolean allYN) 
	{
		StringBuilder sb = new StringBuilder();
		//sb.append("<select name='"+selectBoxNm+"' id='"+selectBoxNm+"' class=\"form-control input-sm\"> \n"); //2020.11.11 jgt SB.admin ver
		sb.append("<select name='"+selectBoxNm+"' id='"+selectBoxNm+"' class=\"custom-select custom-select form-control form-control-sm\"> \n");
		
		//custom-select custom-select-sm form-control form-control-sm
		
		//전체
		if(allYN==true) {
		sb.append("<option value=\"\">전체</option> \n");
		}
		
		if(null !=list) {
		for(Code vo:list) {
		sb.append("<option value='"+vo.getDetCode()+"' ");
		//selectbox선택
		if(selectNm.equals(vo.getDetCode())) {
		sb.append(" selected ");
		}
		sb.append(">");
		//코드이름
		sb.append(vo.getDetNm());
		sb.append("</option>\n");
		}
		} 
		
		sb.append("</select>");
		return sb.toString();
	
	}
	
	/**
	 * CodeList중에 특정 마스터 코드 추출
	 * @param list
	 * @param codeNm
	 * @return
	 */
	public static List<Code> getCodeSearch(List<Code> list, String codeNm){
		List<Code> codeList =new ArrayList<Code>();
		
		for(Code vo:list) {
			if(vo.getMstCode().equalsIgnoreCase(codeNm)) {
				codeList.add(vo);
				LOG.debug("vo:" + vo);
			}
		}
		
		return codeList;
	}
	
}
