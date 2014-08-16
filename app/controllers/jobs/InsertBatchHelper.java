package controllers.jobs;

import models.MetroModel;
import play.Logger;
import play.jobs.Job;
import controllers.models.MetroResponse;
import controllers.util.ResponseCode;

public class InsertBatchHelper extends Job<MetroResponse>{

	private String content;
	private Class<? extends MetroModel> model;

	public InsertBatchHelper(String content, Class<? extends MetroModel> model) {
		this.content = content;
		this.model = model;
	}

	@Override
	public MetroResponse doJobWithResult() throws Exception {
		try {
			Logger.info("From file: %s", content);
			String[] lines = content.split("\n");
			
			String[] headers = model.newInstance().getHeaders();
			String[] headersFromFile = lines[0].split(",");
			
//			HeaderLenghtValidator defaultValidator = new HeaderLenghtValidator(headers, headersFromFile);
//			if (defaultValidator.hasError()){
//				renderText(defaultValidator.getErrorMessage());
//			}
			
			if (headers.length != headersFromFile.length){
				String responseMessage = "Invalid File. Must have " + headers.length + " headers in file. I've found " + headersFromFile.length;
				return  new MetroResponse(ResponseCode.GENERIC.getCode(),  responseMessage);
			}
			
			for (int i = 0; i < headers.length; i++) {
				if (!headers[i].equals(headersFromFile[i].replace("\"", ""))){
					System.out.println(headers[i] + " - " + headersFromFile[i]);
					return  new MetroResponse(ResponseCode.GENERIC.getCode(), "Invalid File. There is not header: " + headers[i]);
				}
			}
			
			for (int i = 1; i < lines.length; i++) {
				MetroModel instance = model.newInstance();
				instance.setContent(lines[i]);
				instance.saveOrUpdate(); 
			}
			String message = "Number of " + model.getClass().getSimpleName() + " saves: " + (lines.length - 1);
			return new MetroResponse(ResponseCode.APPROVED.getCode(), message);
		} catch (Exception e) {
			Logger.error(e, e.getMessage());
			return new MetroResponse(ResponseCode.GENERIC.getCode(), "ERROR: " + e.getMessage());
		}
	}

}
