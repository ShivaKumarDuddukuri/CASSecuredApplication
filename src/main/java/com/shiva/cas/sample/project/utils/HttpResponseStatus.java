package com.shiva.cas.sample.project.utils;

public enum HttpResponseStatus {

	SUCCESS("Success"), FAILURE("Failure"), MISSING_PARAMETER("MissingParamter"), INVALID_REQUEST(
			"InvalidRequest"), NOT_FOUND("NotFound");

	private HttpResponseStatus(String value) {

	}
}
