package it.sevenbits.project.application.services.validator;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Validator for field values
 */
public interface IPrimitiveFieldValidator {

    /**
     * Validate whether value is not null and empty or contains only spaces, otherwise reject it
     * @param value     Value of field
     * @param errors    Map for errors
     * @param field     Rejected field name
     * @param key       Rejected message key
     */
    void isNotNullOrEmpty(String value, Map<String, String> errors, String field, String key);

    /**
     * Validate whether value is Long, otherwise reject it
     * @param value     Value of field
     * @param errors    Map for errors
     * @param field     Rejected field name
     * @param key       Rejected message key
     */
    void isLong(String value, Map<String, String> errors, String field, String key);

    /**
     * Validate whether value is Integer, otherwise reject it
     * @param value     Value of field
     * @param errors    Map for errors
     * @param field     Rejected field name
     * @param key       Rejected message key
     */
    void isInteger(String value, Map<String, String> errors, String field, String key);

    /**
     * Validate, whether value is shorter than
     * @param value        Value of field
     * @param maxLength    Length allowed
     * @param errors       Map for errors
     * @param field        Rejected field name
     * @param key          Rejected message key
     */
    void shorterThan(String value, Integer maxLength, Map<String, String> errors, String field, String key);

    /**
     * Validate, whether value is longer than
     * @param value        Value of field
     * @param minLength    Minimum length
     * @param errors       Map for errors
     * @param field        Rejected field name
     * @param key          Rejected message key
     */
    void longerThan(String value, Integer minLength, Map<String, String> errors, String field, String key);

    /**
     * Validate, whether value have exact length
     * @param value        Value of field
     * @param length       Value length
     * @param errors       Map for errors
     * @param field        Rejected field name
     * @param key          Rejected message key
     */
    void haveLength(String value, Integer length, Map<String, String> errors, String field, String key);

    /**
     * Validate whether start date is before end date, otherwise reject it
     * @param startDate    Start date
     * @param endDate      End date
     * @param errors       Map for errors
     * @param field        Rejected field name
     * @param key          Rejected message key
     */
    void startDateBeforeEndDate(String startDate, String endDate, Map<String, String> errors, String field, String key);

    /**
     * Validate whether two values are equal, otherwise reject it
     * @param value1       Value 1
     * @param value2       Value 2
     * @param errors       Map for errors
     * @param field        Rejected field name
     * @param key          Rejected message key
     */
    void equalFields(String value1, String value2, Map<String, String> errors, String field, String key);
    /**
     * Reject value
     * @param errors       Map for errors
     * @param field        Rejected field name
     * @param key          Rejected message key
     */
    void rejectValue(Map<String, String> errors, String field, String key);

    /**
     * Validate whether file is not null, otherwise reject it
     * @param file      File
     * @param errors    Map for errors
     * @param field     Rejected field name
     * @param key       Rejected message key
     */
    void isNotNull(MultipartFile file, Map<String, String> errors, String field, String key);
}
