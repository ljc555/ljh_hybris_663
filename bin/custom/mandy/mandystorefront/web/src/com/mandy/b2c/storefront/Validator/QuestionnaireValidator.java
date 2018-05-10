package com.mandy.b2c.storefront.Validator;

import com.mandy.b2c.storefront.forms.QuestionnaireForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.storefront.Validator
 * @ClassName QuestionnaireValidator
 * @Description 数据校验
 * @Date 2018/5/10 15:09
 */
@Component("questionnaireValidator")
public class QuestionnaireValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return QuestionnaireForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        QuestionnaireForm form = (QuestionnaireForm)o;

        if (StringUtils.isEmpty(form.getProductName())) {
            errors.rejectValue("productName", "profile.questionnaire.productName.invalid");
        }

        if (form.getIfBuy() != null) {
            errors.rejectValue("ifBuy", "profile.questionnaire.ifBuy.invalid");
        }
    }
}
