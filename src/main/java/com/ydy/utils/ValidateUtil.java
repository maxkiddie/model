package com.ydy.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

public class ValidateUtil {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static <T> Map<String, String> validateEntity(T obj) {
		Map<String, String> hashMap = new HashMap<String, String>();
		Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		if (set != null && !set.isEmpty()) {
			for (ConstraintViolation<T> cv : set) {
				hashMap.put(cv.getPropertyPath().toString(), cv.getMessage());
			}
		}
		return hashMap;
	}
}
