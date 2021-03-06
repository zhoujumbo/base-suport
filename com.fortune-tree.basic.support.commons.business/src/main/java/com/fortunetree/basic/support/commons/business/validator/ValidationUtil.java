package com.fortunetree.basic.support.commons.business.validator;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidationUtil {
    /**
     * 开启快速结束模式 failFast (true)
     */
    private static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(false)
            .buildValidatorFactory()
            .getValidator();
    /**
     * 校验对象
     *
     * @param t bean
     * @param groups 校验组
     * @return ValidResult
     */
    public static <T> ValidResult validateBean(T t,Class<?>...groups) {
        ValidResult result = new ValidationUtil().new ValidResult();
        Set<ConstraintViolation<T>> violationSet = validator.validate(t,groups);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            for (ConstraintViolation<T> violation : violationSet) {
                result.addError(violation.getPropertyPath().toString(), violation.getMessage());
            }
        }
        return result;
    }
    /**
     * 校验bean的某一个属性
     *
     * @param obj          bean
     * @param propertyName 属性名称
     * @return ValidResult
     */
    public static <T> ValidResult validateProperty(T obj, String propertyName) {
        ValidResult result = new ValidationUtil().new ValidResult();
        Set<ConstraintViolation<T>> violationSet = validator.validateProperty(obj, propertyName);
        boolean hasError = violationSet != null && violationSet.size() > 0;
        result.setHasErrors(hasError);
        if (hasError) {
            for (ConstraintViolation<T> violation : violationSet) {
                result.addError(propertyName, violation.getMessage());
            }
        }
        return result;
    }

    public static <T> String validateResult(T t, Class<?>...groups){
        ValidResult validResult = ValidationUtil.validateBean(t, groups);
        if(validResult.hasErrors()){
            return validResult.getErrorsStr();
        }
        return "";
    }
    /**
     * 校验结果类
     */
    public class ValidResult {

        /**
         * 是否有错误
         */
        private boolean hasErrors;

        /**
         * 错误信息
         */
        private List<ErrorMessage> errors;

        public ValidResult() {
            this.errors = new ArrayList<>();
        }
        public boolean hasErrors() {
            return hasErrors;
        }

        public void setHasErrors(boolean hasErrors) {
            this.hasErrors = hasErrors;
        }

        /**
         * 获取所有验证信息
         * @return 集合形式
         */
        public List<ErrorMessage> getAllErrors() {
            return errors;
        }
        /**
         * 获取所有验证信息
         * @return json字符串形式
         */
        public String getErrorsJsonStr(){

            StringBuffer sb = new StringBuffer();
            Map<String,String> map = Maps.newHashMap();
            errors.forEach(error->
                map.put(error.getPropertyPath(),error.getMessage())
            );
            Gson gson = new GsonBuilder().create();
            String content = gson.toJson(map);
            return content;
        }

        /**
         * 获取所有验证信息
         * @return 字符串形式
         */
        public String getErrorsStr(){

            StringBuffer sb = new StringBuffer();
            errors.forEach(error->
//                    map.put(error.getPropertyPath(),error.getMessage())
                    sb.append(error.getPropertyPath()).append(":").append(error.getMessage()).append(";")
            );
            return sb.toString();
        }

        public Map<String,String> getErrorsMap(){

            Map<String,String> map = Maps.newHashMap();
            errors.forEach(error->
                    map.put(error.getPropertyPath(),error.getMessage())
            );
            return map;
        }

        public void addError(String propertyName, String message) {
            this.errors.add(new ErrorMessage(propertyName, message));
        }

        public boolean isHasErrors() {
            return hasErrors;
        }

        public List<ErrorMessage> getErrors() {
            return errors;
        }

        public ValidResult setErrors(List<ErrorMessage> errors) {
            this.errors = errors;
            return this;
        }
    }

    public class ErrorMessage {

        private String propertyPath;

        private String message;

        public ErrorMessage() {
        }

        public ErrorMessage(String propertyPath, String message) {
            this.propertyPath = propertyPath;
            this.message = message;
        }

        public String getPropertyPath() {
            return propertyPath;
        }

        public ErrorMessage setPropertyPath(String propertyPath) {
            this.propertyPath = propertyPath == null ? null : propertyPath.trim();
            return this;
        }

        public String getMessage() {
            return message;
        }

        public ErrorMessage setMessage(String message) {
            this.message = message == null ? null : message.trim();
            return this;
        }
    }

}