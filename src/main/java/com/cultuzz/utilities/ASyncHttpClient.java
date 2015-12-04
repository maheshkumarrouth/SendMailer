package com.cultuzz.utilities;


import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Request;
import com.ning.http.client.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Component;

import java.io.*;


@Component
public class ASyncHttpClient {
	public void postXMLToCS(String str){
		AsyncHttpClient asy = new AsyncHttpClient();
		try {
			str = "otaRQ=" + java.net.URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Request request = asy.preparePost("http://localhost:8080/cultswitch/processOTA").
            setHeader("Content-Type","application/x-www-form-urlencoded").    
            setBody(str).
            build();
        
         ListenableFuture<Integer> f= null;
         try {
			f = asy.executeRequest(request,new AsyncCompletionHandler<Integer>(){                     
			             @Override
			             public Integer onCompleted(Response response) throws Exception{
			                 // Do something with the Response
			                 System.out.println(response.getStatusCode());
			                 InputStream is = response.getResponseBodyAsStream();
			   			  if(is != null){
			   				java.io.BufferedReader in = new BufferedReader(new InputStreamReader(is));
			   		        StringBuffer sb = new StringBuffer();
			   		        try {
			   		            String line = null;
			   		            
			   		            while((line = in.readLine()) != null) {
			   		                sb.append(line + "\n");
			   		            }
			   		         System.out.println("sb.toString()"+sb.toString());
			   		        } catch(Exception ex) {
			   		            
			   		        } finally {
			   		            try {
			   		                is.close();
			   		            } catch(Exception ex) {
			   		            }
			   		            
			   		        }// try / catch / finally
			   			  }
			   			     
			                 return response.getStatusCode();
			             }
			             
			             @Override
			             public void onThrowable(Throwable t){
			                 // Something wrong happened.
			             }
			         });
		try {
			System.out.println("status"+f.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			asy.close();
		}
	}
	
    public String parseISToString(java.io.InputStream is) {
        java.io.BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        try {
            String line = null;
            
            while((line = in.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch(Exception ex) {
            
        } finally {
            try {
                is.close();
            } catch(Exception ex) {
            }
            
        }// try / catch / finally
        
        return sb.toString();
        
    }// parseISToString
}
