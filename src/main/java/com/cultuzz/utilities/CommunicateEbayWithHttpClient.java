/**
 * 
 */
package com.cultuzz.utilities;


/**
 * @author krishna
 *
 */
public class CommunicateEbayWithHttpClient {
//
//    private String url;
//    private String postData = "";
//    private String responseData = "";
//    private boolean connectionProblems = false;
//    private String contentType = "application/x-www-form-urlencoded";
//    public String cltzObjectID = null;
//    
//    public CommunicateEbayWithHttpClient() {
//    }
//    
//    public CommunicateEbayWithHttpClient(String postData,String url,String cltzObjectID) {
//        setPostData(postData);
//        this.url = url;
//        this.cltzObjectID = cltzObjectID;
//    }
//    
//    
//    
//    public void setPostData(String postData) {
//        this.postData = postData;
//    }
//    
//    public void doHandShakeWithHTTPClient() {
//        System.out.println("doHandShakeWithHTTPClient");   
//	  String response = "";
//	  try {
//                  System.out.println(""+url);
//		  HttpClient client = new HttpClient();
//		  client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
//		  String param = "EndItemId";
//                  String ObjectId = "ObjectId";
//		  System.out.println("Debug @stmt Length :: "+postData.length());
//		  System.out.println("Debug @stmt Length :: sendRequestToChannel");
//		  NameValuePair message = new NameValuePair(param, postData); 
//                  NameValuePair message1 = new NameValuePair(ObjectId, cltzObjectID.toString());
//		  NameValuePair[] data = { message,message1};
//		  PostMethod postMethod = new PostMethod(url);
//		  postMethod.setRequestBody(data);
//		  int statusCode = client.executeMethod(postMethod);
//                  System.out.println("statusCode"+statusCode);
//		  if(statusCode == HttpStatus.SC_OK) {
//			  UtilityClass toolBox = new UtilityClass();
//			  InputStream is = postMethod.getResponseBodyAsStream();
//			  if(is != null){
//				  response += toolBox.parseISToString(is); 
//			  }
//                          System.out.println("response xml is"+response);
//                          
//		  }
//	  	} catch (Exception e) {
//	  		response = "Connection Failed";
//	  		e.printStackTrace();
//	  	}
//    }
//    public boolean isConnectionProblems() {
//        return connectionProblems;
//    }
//    
//    public void setConnectionProblems(boolean connectionProblems) {
//        this.connectionProblems = connectionProblems;
//    }
//    
//    public String getContentType() {
//        return contentType;
//    }
//    
//    public void setContentType(String contentType) {
//        this.contentType = contentType;
//    }
//    
//    public void setResponseData(String responseData) {
//        this.responseData = responseData;
//    }
//    
//    public String getResponseData() {
//        return responseData;
//    }
//    
//    public static String encodeData(String postData) {
//        try {
//            postData = URLEncoder.encode(postData, "UTF-8");
//        } catch (UnsupportedEncodingException ex) {
//            postData = URLEncoder.encode(postData);
//            ex.printStackTrace();
//        }
//        return postData;
//    }
}
