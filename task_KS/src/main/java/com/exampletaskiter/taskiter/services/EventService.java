package com.exampletaskiter.taskiter.services;

import com.exampletaskiter.taskiter.entity.EventDetails;

import java.text.ParseException;
import java.util.List;

public interface EventService {

    EventDetails  getLastValue() ;
    Double  getAvgValue(String dateFrom, String dateTo) throws ParseException;
    List<EventDetails> getGoodValues(String dateFrom, String dateTo) throws ParseException;
    List<EventDetails> getInterValues(String dateFrom, String dateTo, Integer intervals) throws ParseException;
}

