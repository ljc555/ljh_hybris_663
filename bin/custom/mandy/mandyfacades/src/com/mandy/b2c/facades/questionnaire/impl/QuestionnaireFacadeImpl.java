package com.mandy.b2c.facades.questionnaire.impl;

import com.mandy.b2c.core.enums.DeleteFlag;
import com.mandy.b2c.core.enums.QuestionnaireSource;
import com.mandy.b2c.core.model.QuestionnaireModel;
import com.mandy.b2c.core.questionnaire.QuestionnaireService;
import com.mandy.b2c.facades.commons.DefaultBaseFacade;
import com.mandy.b2c.facades.questionnaire.QuestionnaireFacade;
import com.mandy.platform.data.QuestionnaireData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import javax.annotation.Resource;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.facades.questionnaire.impl
 * @ClassName QuestionnaireFacadeImpl
 * @Description 调查问卷Facade业务
 * @Date 2018/5/8 16:43
 */
public class QuestionnaireFacadeImpl extends DefaultBaseFacade implements QuestionnaireFacade {

    @Resource
    private QuestionnaireService questionnaireService;
    @Resource
    private UserService userService;
    @Resource
    ModelService modelService;
    @Resource(name = "questionnaireConverter")
    private Converter<QuestionnaireModel, QuestionnaireData> questionnaireConverter;

    @Override
    public QuestionnaireData getQuestionnaireDataByCode(String code) {
        QuestionnaireModel questionnaireModel = questionnaireService.getQuestionnaireDataByCode(code,(CustomerModel)userService.getCurrentUser());

        if(questionnaireModel!=null) {
            return questionnaireConverter.convert(questionnaireModel);
        }

        return null;
    }

    @Override
    public SearchPageData<QuestionnaireData> getQuestionnairePageData(PageableData pageableData) {
        SearchPageData<QuestionnaireModel> questionnaireModel = questionnaireService.getQuestionnairePageData(pageableData,(CustomerModel)userService.getCurrentUser());

        final SearchPageData<QuestionnaireData> data = convertPageData(questionnaireModel, questionnaireConverter);

        return data;
    }

    @Override
    public void createQuestionnaire(QuestionnaireData questionnaireData) {
        if(questionnaireData != null) {
            QuestionnaireModel questionnaireModel = modelService.create(QuestionnaireModel.class);
            questionnaireModel.setCode(getCode());
            questionnaireModel.setIfBuy(questionnaireData.getIfBuy());
            questionnaireModel.setManufactor(questionnaireData.getManufactor());
            if(questionnaireData.getPrice() != null) {
                questionnaireModel.setPrice(createPriceRowModel(questionnaireData.getPrice()));
            }
            questionnaireModel.setProductCode(questionnaireData.getProductCode());
            questionnaireModel.setProductName(questionnaireData.getProductName());

            if(questionnaireData.getSource() != null) {
                questionnaireModel
                    .setSource(QuestionnaireSource.valueOf(questionnaireData.getSource()));
            }

            questionnaireModel.setRemark(questionnaireData.getRemark());
            questionnaireModel.setOwner(userService.getCurrentUser());

            modelService.save(questionnaireModel);
        }
    }

    @Override
    public void editQuestionnaire(QuestionnaireData questionnaireData) {
        if(questionnaireData != null) {
            QuestionnaireModel questionnaireModel = questionnaireService.getQuestionnaireDataByCode(questionnaireData.getCode(),(CustomerModel)userService.getCurrentUser());

            if(questionnaireModel !=null ) {
                questionnaireModel.setIfBuy(questionnaireData.getIfBuy());
                questionnaireModel.setManufactor(questionnaireData.getManufactor());
                if (questionnaireData.getPrice() != null) {
                    questionnaireModel.setPrice(createPriceRowModel(questionnaireData.getPrice()));
                }
                questionnaireModel.setProductCode(questionnaireData.getProductCode());
                questionnaireModel.setProductName(questionnaireData.getProductName());

                if (questionnaireData.getSource() != null) {
                    questionnaireModel
                        .setSource(QuestionnaireSource.valueOf(questionnaireData.getSource()));
                }

                questionnaireModel.setRemark(questionnaireData.getRemark());

                modelService.save(questionnaireModel);
            }
        }
    }

    @Override
    public void deleteQuestionnaire(String code) {
        if(code != null) {
            QuestionnaireModel questionnaireModel = questionnaireService.getQuestionnaireDataByCode(code,(CustomerModel)userService.getCurrentUser());

            if(questionnaireModel !=null) {
                questionnaireModel.setDeleted(DeleteFlag.DELETED);

                modelService.save(questionnaireModel);
            }
        }
    }
}
