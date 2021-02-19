package com.exampletaskiter.taskiter.controller;

import com.exampletaskiter.taskiter.entity.EventDetails;
import com.exampletaskiter.taskiter.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.util.List;


@Controller
public class EventController {

    @Autowired
    private EventService eventService;


    // REST for task 1 - Return the latest value, along with timestamp and quality
    @GetMapping(value = "/getLastValue", produces = "application/json")
    public ResponseEntity<EventDetails> getLastValue() throws ParseException {

        EventDetails eventDetails = eventService.getLastValue();

        System.out.println("Task 1 - [Last Value]: "+eventDetails.getValue()+ " [DateTime]: "+eventDetails.getDateEvt()+ " [Quality]: "+eventDetails.getQuality());

        return new ResponseEntity<EventDetails>(eventDetails, HttpStatus.OK);
    }

    // REST for task 2 - Return the average value between two date/times (tip: what about Bad values?)
    @PostMapping(value = "/getAvgValue", produces = "application/json")
    public ResponseEntity<Double> getAvgValue(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) throws ParseException {


        Double avgValue = eventService.getAvgValue(dateFrom, dateTo);

        if(avgValue != null){
            System.out.println("Task 2 - [Average value between "+dateFrom+" and "+dateTo+"]: "+avgValue);
            return new ResponseEntity<Double>(avgValue, HttpStatus.OK);
        } else
            return new ResponseEntity<Double>(HttpStatus.BAD_REQUEST);


    }

    // REST for task 3 - Return the series of good values between two date/times
    @PostMapping(value = "/getGoodValues", produces = "application/json")
    public ResponseEntity<List> getGoodValues(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) throws ParseException {


        List<EventDetails> goodValuesList = eventService.getGoodValues(dateFrom, dateTo);

        if(goodValuesList != null){
            System.out.println("Task 3 - [List of Good Values between "+dateFrom+" and "+dateTo+"]:");
            for (EventDetails event: goodValuesList)
                System.out.println(event.getValue());
            return new ResponseEntity<List>(goodValuesList, HttpStatus.OK);
        } else
            return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);


    }

    // REST for task 4 - (Optional) Return a series of the interpolated values at X minute intervals between two date/times
    @PostMapping(value = "/getInterValues", produces = "application/json")
    public ResponseEntity<List> getInterValues(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo, @RequestParam("intervals") Integer intervals) throws ParseException {


        List<EventDetails> goodValuesList = eventService.getInterValues(dateFrom, dateTo, intervals);

        if(goodValuesList != null){
            System.out.println("Task 4 - [List of Interpolated Values for interval "+intervals+"[m] between "+dateFrom+" and "+dateTo+"]:");
            for (EventDetails event: goodValuesList)
                System.out.println(event.getValue());
            return new ResponseEntity<List>(goodValuesList, HttpStatus.OK);
        } else
            return new ResponseEntity<List>(HttpStatus.BAD_REQUEST);


    }


}
