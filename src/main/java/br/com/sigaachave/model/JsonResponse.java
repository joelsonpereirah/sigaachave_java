package br.com.sigaachave.model;

import com.google.gson.JsonObject;

public class JsonResponse {
	
	private JsonObject response;
	
	public JsonResponse(String code, String message) {
		
		response = new JsonObject();
		response.addProperty("code", code);
		response.addProperty("message", message);
	}
	
	@Override
	public String toString() {
		
		return response.toString();
	}
}
