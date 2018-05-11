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

    /**
     * 根据code查询问卷调查
     * @param code
     * @return
     */
    QuestionnaireData getQuestionnaireDataByCode(String code);

    /**
     * 分页查询问卷调查
     * @param pageableData
     * @return
     */
    SearchPageData<QuestionnaireData> getQuestionnairePageData(PageableData pageableData);

    /**
     * 新增问卷调查
     * @param questionnaireData
     */
    void createQuestionnaire(QuestionnaireData questionnaireData);

    /**
     * 修改问卷调查
     * @param questionnaireData
     */
    void editQuestionnaire(QuestionnaireData questionnaireData);

    /**
     * 删除问卷调查
     * @param code
     */
    void deleteQuestionnaire(String code);
}
