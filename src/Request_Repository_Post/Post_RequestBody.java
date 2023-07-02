package Request_Repository_Post;

public class Post_RequestBody {

	public static String BaseURI() {
		String baseURI="https://reqres.in/";
		return baseURI;
	}
	
	public static String Post_Resource() {
		String post_Resource="api/users";
		return post_Resource;
	}
	
	public static String Post_Req_TC1() {
		String RequestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		return RequestBody;
	}

}