package controllers;

import controllers.models.MetroResponse;
import controllers.util.ResponseCode;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http.Header;

public class MetroController extends CRUD {

//	@Before
    static void checkAuthentification() {
		Header apiKeyHeader = request.headers.get("api-key");
		Header securityTokenHeader = request.headers.get("security-token");

		if (apiKeyHeader == null || securityTokenHeader == null){
			MetroResponse metroResponse = new MetroResponse(ResponseCode.GENERIC.getCode(), "Header must have attributes mandatory.");
			responseType(metroResponse);
		}
		
		String securityToken = securityTokenHeader.value();
		if ("m3tR0LonDr35".equals(apiKeyHeader.value())){
			//TODO securityToken
		} else {
			MetroResponse metroResponse = new MetroResponse(ResponseCode.GENERIC.getCode(), "Access denied.");
			responseType(metroResponse);
		}
	}
	
	protected static void responseType(MetroResponse metroResponse) {
	    	String accept = request.headers.get("accept").value();
			if (accept.contains("json")) {
				renderJSON(metroResponse);
			} else {
				renderXml(metroResponse);
			}
		}
	
}
