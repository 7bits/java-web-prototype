package it.sevenbits.project.application.services.validator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Field validator
 */
@Service
public class PrimitiveFieldValidator implements IPrimitiveFieldValidator {

    /** Pattern for whitespaces */
    private static final String WHITESPACE_PATTERN = "\\s+";

    /**
     * Validate whether value is not null and empty or contains only spaces, otherwise reject it
     *
     * @param value  Value of field
     * @param errors Map for errors
     * @param field  Rejected field name
     * @param key    Rejected message key
     */
    @Override
    public void isNotNullOrEmpty(
            final String value,
            final Map<String, String> errors,
            final String field,
            final String key
    ) {
        if (!errors.containsKey(field)) {
            if (value == null) {
                errors.put(field, key);
            } else if (value.isEmpty()) {
                errors.put(field, key);
            } else if (value.matches(WHITESPACE_PATTERN)) {
                errors.put(field, key);
            }
        }
    }

    /**
     * Validate whether value is Long, otherwise reject it
     *
     * @param value  Value of field
     * @param errors Map for errors
     * @param field  Rejected field name
     * @param key    Rejected message key
     */
    @Override
    public void isLong(final String value, final Map<String, String> errors, final String field, final String key) {
        Boolean checkFailed = false;
        if (value != null && !errors.containsKey(field)) {
            try {
                Long.parseLong(value);
            } catch (Exception e) {
                checkFailed = true;
            }
            if (checkFailed) {
                errors.put(field, key);
            }
        }
    }

    /**
     * Validate whether value is Integer, otherwise reject it
     *
     * @param value  Value of field
     * @param errors Map for errors
     * @param field  Rejected field name
     * @param key    Rejected message key
     */
    @Override
    public void isInteger(final String value, final Map<String, String> errors, final String field, final String key) {
        Boolean checkFailed = false;
        if (value != null && !errors.containsKey(field)) {
            try {
                Integer.parseInt(value);
            } catch (Exception e) {
                checkFailed = true;
            }
            if (checkFailed) {
                errors.put(field, key);
            }
        }
    }

    /**
     * Validate, whether value is too long
     *
     * @param value     Value of field
     * @param maxLength Length allowed
     * @param errors    Map for errors
     * @param field     Rejected field name
     * @param key       Rejected message key
     */
    @Override
    public void shorterThan(
            final String value,
            final Integer maxLength,
            final Map<String, String> errors,
            final String field,
            final String key
    ) {
        if (value != null && !errors.containsKey(field)) {
            if (value.length() > maxLength) {
                errors.put(field, key);
            }
        }
    }

    /**
     * Validate, whether value is longer than
     *
     * @param value     Value of field
     * @param minLength Minimum length
     * @param errors    Map for errors
     * @param field     Rejected field name
     * @param key       Rejected message key
     */
    @Override
    public void longerThan(
            final String value,
            final Integer minLength,
            final Map<String, String> errors,
            final String field,
            final String key
    ) {
        if (value != null && !errors.containsKey(field)) {
            if (value.length() < minLength) {
                errors.put(field, key);
            }
        }
    }

    /**
     * Validate, whether value have exact length
     *
     * @param value  Value of field
     * @param length Value length
     * @param errors Map for errors
     * @param field  Rejected field name
     * @param key    Rejected message key
     */
    @Override
    public void haveLength(
            final String value,
            final Integer length,
            final Map<String, String> errors,
            final String field,
            final String key
    ) {
        if (value != null && !errors.containsKey(field)) {
            if (value.length() != length) {
                errors.put(field, key);
            }
        }
    }

    /**
     * Validate whether start date is before end date, otherwise reject it
     *
     * @param startDate Start date
     * @param endDate   End date
     * @param errors    Map for errors
     * @param field     Rejected field name
     * @param key       Rejected message key
     */
    @Override
    public void startDateBeforeEndDate(
            final String startDate,
            final String endDate,
            final Map<String, String> errors,
            final String field,
            final String key
    ) {
        try {
            if (startDate != null && endDate != null && !errors.containsKey(field)) {
                Long start = Long.parseLong(startDate);
                Long end = Long.parseLong(endDate);
                if (start > end) {
                    errors.put(field, key);
                }
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    /**
     * Validate whether two values are equal, otherwise reject it
     *
     * @param value1       Value 1
     * @param value2       Value 2
     * @param errors Map for errors
     * @param field  Rejected field name
     * @param key    Rejected message key
     */
    @Override
    public void equalFields(
            final String value1,
            final String value2,
            final Map<String, String> errors,
            final String field,
            final String key
    ) {
        if (value1 != null && value2 != null && !errors.containsKey(field)) {
            if (!value1.equals(value2)) {
                errors.put(field, key);
            }
        }
    }

    /**
     * Reject value
     *
     * @param errors Map for errors
     * @param field  Rejected field name
     * @param key    Rejected message key
     */
    @Override
    public void rejectValue(final Map<String, String> errors, final String field, final String key) {
        errors.put(field, key);
    }

    /**
     * Validate whether file is not null, otherwise reject it
     *
     * @param file   File
     * @param errors Map for errors
     * @param field  Rejected field name
     * @param key    Rejected message key
     */
    @Override
    public void isNotNull(
            final MultipartFile file,
            final Map<String, String> errors,
            final String field,
            final String key
    ) {
        if (file.isEmpty() && !errors.containsKey(field)) {
            errors.put(field, key);
        }
    }
}
