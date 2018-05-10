package com.mandy.b2c.core.daos.questionnaire.impl;

import com.mandy.b2c.core.daos.questionnaire.QuestionnaireDao;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.core.daos.questionnaire.impl
 * @ClassName QuestionnaireDaoImpl
 * @Description 问卷调查Dao
 * @Date 2018/5/8 9:59
 */
public class QuestionnaireDaoImpl implements QuestionnaireDao {
    private static final Logger LOG = LoggerFactory.getLogger(QuestionnaireDaoImpl.class);
    private static final String QUERY_QUESTIONNAIRE_PAGE = "SELECT {q.pk} FROM {Questionnaire AS q} WHERE {q.deleted}=?deleted ORDER BY {q.modifiedtime} DESC";

    @Resource
    private FlexibleSearchService flexibleSearchService;
    @Resource
    private PagedFlexibleSearchService pagedFlexibleSearchService;
}
