package Test_Classes_Retry;

import Common_API_Methods_Post.Post_API_Methods;
import java.time.LocalDateTime;

import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import Request_Repository_Post.Post_RequestBody;

public class Post_Retry {

	public static void extracter() {

		for (int i = 1; i <= 5; i++) {

			int statusCode = Post_API_Methods.ResponseStatusCode(Post_RequestBody.BaseURI(),
					Post_RequestBody.Post_Resource(), Post_RequestBody.Post_Req_TC1());
			System.out.println("Status Code: " + statusCode);

			if (statusCode == 201) {

				String ResponseBody = Post_API_Methods.ResponseBody(Post_RequestBody.BaseURI(),
						Post_RequestBody.Post_Resource(), Post_RequestBody.Post_Req_TC1());
				System.out.println("ResponseBody \n" + ResponseBody);
				String RequestBody = Post_API_Methods.ResponseBody(Post_RequestBody.BaseURI(),
						Post_RequestBody.Post_Resource(), Post_RequestBody.Post_Req_TC1());
				validator(ResponseBody, RequestBody);

				break;

			} else {
				System.out.println(i + "Incorrect status code received :" + statusCode);
			}
		}
	}

	public static void validator(String Response, String Request) {
		JsonPath JspRequest= new JsonPath(Request);
		String Req_name=JspRequest.getString("name");
		String Req_job=JspRequest.getString("job");
		LocalDateTime currentdate=LocalDateTime.now();
		String expecteddate=currentdate.toString().substring(0,11);
		
		JsonPath JspResponse= new JsonPath(Response);
		String Res_name=JspRequest.getString("name");
		String Res_job=JspRequest.getString("job");
		String Res_createdAt=JspResponse.getString("createdAt");
		Res_createdAt=Res_createdAt.substring(0,11);
		
		Assert.assertEquals(Res_name, Req_name);
		Assert.assertEquals(Res_job, Req_job);
		Assert.assertEquals(Res_createdAt, expecteddate);

	}
}