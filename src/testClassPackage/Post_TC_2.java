package testClassPackage;
import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonFunctionPackage.API_Common_Function;
import commonFunctionPackage.Utility_Common_Function;
import io.restassured.path.json.JsonPath;
import requestRepositoryPackage.post_req_repository;
 
public class Post_TC_2 {
	
	public static void execute() throws IOException {
		for (int i = 0 ; i<5 ; i++) 
	{
			String baseURI= post_req_repository.base_URI();
			String requestBody = post_req_repository.post_req_tc2();
			String resource = post_req_repository.post_resource();
			int statuscode = API_Common_Function.res_statusCode(baseURI,resource,requestBody);
			
			if (statuscode == 201) 
			{
			 String responseBody = API_Common_Function.response_body(baseURI,resource,requestBody);
			 Post_TC_2.validator(responseBody, statuscode,requestBody);
			Utility_Common_Function.evidencecreator("Post_TC_2" , requestBody, responseBody);
			 break;
			}
			else 
			{
				System.out.println("Correct Status Code is not found hence retring the API");
			}
				
	}
			
	} 
	
		public static void validator (String responseBody, int statuscode, String requestBody) {
			
	
		JsonPath jspresponse = new JsonPath(responseBody);
		String res_name = jspresponse.getString("name");
		String res_job = jspresponse.getString("job");
		String res_id = jspresponse.getString("id"); 
		String res_createdAt = jspresponse.getString("createdAt");
		
		

		
		/*String requestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		System.out.println(requestBody);*/
		
		JsonPath jsprequestBody = new JsonPath(requestBody);
		String req_name = jsprequestBody.getString("name");
		String req_job = jsprequestBody.getString("job");
		LocalDateTime Date = LocalDateTime.now();
		String exp_date = Date.toString().substring(0,10);
		System.out.println(exp_date);
		
		res_createdAt = res_createdAt.substring(0,10);
	 System.out.println(responseBody);
		Assert.assertEquals(req_name,res_name);
		Assert.assertEquals(req_job,res_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(exp_date,res_createdAt);
	
		
	}

}