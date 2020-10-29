package com.omg.cmn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
	final static Logger LOG = LoggerFactory.getLogger(StringUtil.class);
	/**
	 * 
	 *Method Name:renderPaging
	 *html ?äπ?àò Î¨∏Ïûê url : https://dev.w3.org/html5/html-author/charref
	 *?ûë?Ñ±?ùº: 2020. 9. 23
	 *?ûë?Ñ±?ûê: sist
	 *?Ñ§Î™?:
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
	    * Ï¥ùÍ??àò: 21
	    * ?òÑ?û¨?éò?ù¥Ïß?: 1
	    * ?ïú?éò?ù¥Ïß??óê Î≥¥Ïó¨Ïß? ?ñâ?àò: 10
	    * Î∞îÎã•?óê Î≥¥Ïó¨Ïß? ?éò?ù¥Ïß? ?àò: 10
	    * << ?ãú?ûë?éò?ù¥Ïß?
	    * < bottomCount : -10Í∞úÏî©
	    * > bottomCount : +10Í∞úÏî©
	    * >> ÎßàÏ?Îß? page
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
	   html.append("<ul class=\"pagination pagination-sm\">                                                  \n");
	   
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


	   // 1 2 3 ... 10 (?à´?ûêÎ≥¥Ïó¨Ï£ºÍ∏∞)
	   for (inx = startPageNo; inx < maxPageNo && inx <= endPageNo; inx++) {
			if (inx == currPageNo) {// ?òÑ?û¨ page
				html.append("<li  class=\"disabled\" 	>");
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
	
//	/**
//	 * select?Éù?Ñ±
//	 * @param list
//	 * @param selectBoxNm
//	 * @param selectNm
//	 * @param allYN
//	 * @return String
//	 */
//	public static String makeSelectBox(List<CodeVO> list,
//                                       String selectBoxNm,
//                                       String selectNm,
//                                       boolean allYN) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("<select name='"+selectBoxNm+"' id='"+selectBoxNm+"' class=\"form-control input-sm\"> \n");
//		
//		//?†ÑÏ≤?
//		if(allYN==true) {
//			sb.append("<option value=\"\">?†ÑÏ≤?</option> \n");
//		}
//		
//		if(null !=list) {
//			for(CodeVO vo:list) {
//				sb.append("<option value='"+vo.getDetCode()+"' ");
//				//selectbox?Ñ†?Éù
//				if(selectNm.equals(vo.getDetCode())) {
//					sb.append(" selected ");
//				}
//				sb.append(">");
//				//ÏΩîÎìú?ù¥Î¶?
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
     * request NVL(?õêÎ≥?,ÏπòÌôò)
	 * @param val(?õêÎ≥? String)
	 * @param rep(ÏπòÌôò String)
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
    *  ?òï?ãù ?Ç†Ïß? Ï∂úÎ†•
    * @param format(yyyy/MM/dd)
    * @return format Date String
    */
	public static String formatDate(String format) {
		Date date = new Date();//?Ç†Ïß? Í∞ùÏ≤¥
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
	
		
	
}

