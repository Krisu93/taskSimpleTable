package com.exampletaskiter.taskiter.services;

import com.exampletaskiter.taskiter.entity.EventDetails;
import com.exampletaskiter.taskiter.repository.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    public EventDetails  getLastValue()  {
        List<EventDetails> simpleList = eventDAO.getEventDetails().stream()
                .sorted(Comparator.comparing(EventDetails::getDateEvt).reversed())
                .collect(Collectors.toList());

        return simpleList.get(0);
    }

    public Double  getAvgValue(String dateFrom, String dateTo) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDateFrom,parsedDateTo;
        try{
             parsedDateFrom = dateFormat.parse(dateFrom);
             parsedDateTo = dateFormat.parse(dateTo);
        } catch (Exception e){
            System.out.println("Bad parameter: "+ e.toString());
            return null;
        }

        Timestamp dateTimeFrom = new java.sql.Timestamp(parsedDateFrom.getTime());
        Timestamp dateTimeTo = new java.sql.Timestamp(parsedDateTo.getTime());

        List<EventDetails> simpleList = eventDAO.getEventDetails().stream()
                //.filter(dates -> !dates.getQuality().equals("Bad") && dates.getDateEvt().after(dateFrom)  && dates.getDateEvt().before(dateTo))
                .filter(dates -> (dates.getDateEvt().compareTo(dateTimeFrom) > 0 || dates.getDateEvt().compareTo(dateTimeFrom) == 0) && (dates.getDateEvt().compareTo(dateTimeTo) < 0 || dates.getDateEvt().compareTo(dateTimeTo) == 0))
                .collect(Collectors.toList());


        return simpleList.stream().mapToDouble(EventDetails::getValue).average().orElse(0);
    }

    public List<EventDetails>  getGoodValues(String dateFrom, String dateTo) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDateFrom,parsedDateTo;
        try{
            parsedDateFrom = dateFormat.parse(dateFrom);
            parsedDateTo = dateFormat.parse(dateTo);
        } catch (Exception e){
            System.out.println("Bad parameter: "+ e.toString());
            return null;
        }

        Timestamp dateTimeFrom = new java.sql.Timestamp(parsedDateFrom.getTime());
        Timestamp dateTimeTo = new java.sql.Timestamp(parsedDateTo.getTime());

        List<EventDetails> simpleList = eventDAO.getEventDetails().stream()
                .filter(dates -> !dates.getQuality().equals("Bad") && (dates.getDateEvt().compareTo(dateTimeFrom) > 0 || dates.getDateEvt().compareTo(dateTimeFrom) == 0) && (dates.getDateEvt().compareTo(dateTimeTo) < 0 || dates.getDateEvt().compareTo(dateTimeTo) == 0))
                .collect(Collectors.toList());

        return simpleList;
    }

    public List<EventDetails>  getInterValues(String dateFrom, String dateTo, Integer intervals) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDateFrom,parsedDateTo;
        try{
            parsedDateFrom = dateFormat.parse(dateFrom);
            parsedDateTo = dateFormat.parse(dateTo);
        } catch (Exception e){
            System.out.println("Bad parameter: "+ e.toString());
            return null;
        }

        Timestamp dateTimeFrom = new java.sql.Timestamp(parsedDateFrom.getTime());
        Timestamp dateTimeTo = new java.sql.Timestamp(parsedDateTo.getTime());


        List<EventDetails> simpleList = eventDAO.getEventDetails().stream()
                .filter(dates ->  (dates.getDateEvt().compareTo(dateTimeFrom) > 0 || dates.getDateEvt().compareTo(dateTimeFrom) == 0) && (dates.getDateEvt().compareTo(dateTimeTo) < 0 || dates.getDateEvt().compareTo(dateTimeTo) == 0))
                .collect(Collectors.toList());


        List<EventDetails> finalList = new ArrayList<>();
        Timestamp dateInter = simpleList.stream()
                .sorted(Comparator.comparing(EventDetails::getDateEvt))
                .collect(Collectors.toList()).get(0).getDateEvt();

        for (int i = 0; i < simpleList.size(); i++){
            if(simpleList.get(i).getDateEvt().compareTo(dateInter) == 0){
                finalList.add(simpleList.get(i));
                dateInter.setTime(dateInter.getTime() + TimeUnit.MINUTES.toMillis(intervals));
            }

        }


        return finalList;
    }


}
