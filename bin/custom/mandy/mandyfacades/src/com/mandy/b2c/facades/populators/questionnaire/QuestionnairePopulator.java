package com.mandy.b2c.facades.populators.questionnaire;

import com.mandy.b2c.core.model.QuestionnaireModel;
import com.mandy.platform.data.QuestionnaireData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.facades.populators.questionnaire
 * @ClassName QuestionnairePopulator
 * @Description 问卷调查，从Model转换成Data数据
 * @Date 2018/5/8 16:58
 */
public class QuestionnairePopulator implements Populator<QuestionnaireModel, QuestionnaireData> {
    private static final Logger LOG = LoggerFactory.getLogger(QuestionnairePopulator.class);

    @Override
    public void populate(QuestionnaireModel questionnaireModel, QuestionnaireData questionnaireData)
        throws ConversionException {
        BeanUtils.copyProperties(questionnaireModel,questionnaireData);
    }
}
