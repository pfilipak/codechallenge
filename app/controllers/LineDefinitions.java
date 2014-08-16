package controllers;

import models.LineDefinition;
import controllers.jobs.InsertBatchHelper;
import controllers.models.MetroResponse;

public class LineDefinitions extends MetroController {

	public static void insertBatch() throws Exception {
		String content = params.get("body");
		MetroResponse metroResponse = await(new InsertBatchHelper(content, LineDefinition.class).now());
		responseType(metroResponse);
	}

}
