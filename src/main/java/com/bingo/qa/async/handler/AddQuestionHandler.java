package com.bingo.qa.async.handler;

import com.bingo.qa.async.EventHandler;
import com.bingo.qa.async.EventModel;
import com.bingo.qa.async.EventType;
import com.bingo.qa.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bingo on 2018/6/5.
 */

@Component
public class AddQuestionHandler implements EventHandler{

    private static final Logger logger = LoggerFactory.getLogger(AddQuestionHandler.class);

    @Autowired
    SearchService searchService;

    @Override
    public void doHandler(EventModel model) {
        try {
            // 增加问题时，为问题增加索引
            searchService.indexQuestion(model.getEntityId(),
                    model.getExt("title"), model.getExt("content"));
        } catch (Exception e) {
            logger.error("增加索引失败" + e.getMessage());
        }
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.ADD_QUESTION);
    }
}
