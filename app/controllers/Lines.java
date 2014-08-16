package controllers;

import models.Line;
import controllers.jobs.InsertBatchHelper;
import controllers.models.MetroResponse;


public class Lines extends MetroController {

	public static void insertBatch() throws Exception {
		String content = params.get("body");
		MetroResponse metroResponse = await(new InsertBatchHelper(content, Line.class).now());
		responseType(metroResponse);
	}
	
}
