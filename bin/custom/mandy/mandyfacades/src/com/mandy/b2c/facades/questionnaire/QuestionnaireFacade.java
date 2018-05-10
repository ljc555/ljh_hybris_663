package com.mandy.b2c.facades.questionnaire;

import com.mandy.platform.data.QuestionnaireData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.facades.questionnaire
 * @ClassName QuestionnaireFacade
 * @Description 调查问卷Facade业务
 * @Date 2018/5/8 16:42
 */
public interface QuestionnaireFacade {

    QuestionnaireData getQuestionnaireDataByCode(String code);

    SearchPageData<QuestionnaireData> getQuestionnairePageData(PageableData pageableData);

    void createQuestionnaire(QuestionnaireData questionnaireData);
}
