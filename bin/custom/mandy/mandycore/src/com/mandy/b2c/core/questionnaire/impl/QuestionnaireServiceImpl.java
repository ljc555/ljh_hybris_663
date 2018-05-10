package com.mandy.b2c.core.questionnaire.impl;

import com.mandy.b2c.core.daos.questionnaire.QuestionnaireDao;
import com.mandy.b2c.core.questionnaire.QuestionnaireService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.core.questionnaire.impl
 * @ClassName QuestionnaireServiceImpl
 * @Description 问卷调查Service
 * @Date 2018/5/8 16:34
 */
public class QuestionnaireServiceImpl implements QuestionnaireService{

    @Resource
    private QuestionnaireDao questionnaireDao;
}
