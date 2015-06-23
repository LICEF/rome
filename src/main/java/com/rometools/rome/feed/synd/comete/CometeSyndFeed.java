package com.rometools.rome.feed.synd.comete;

import java.util.List;

import com.rometools.rome.feed.synd.SyndFeed;

import com.rometools.rome.feed.module.comete.DCSubject;

public interface CometeSyndFeed extends SyndFeed {

    public List<DCSubject> getTopics();
    public void setTopics(List<DCSubject> topics);

}
