package com.exampletaskiter.taskiter.repository;

import com.exampletaskiter.taskiter.entity.EventDetails;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Repository
public class EventDAO {
    List<EventDetails> test = new ArrayList<>();

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public EventInsert eventInsert;


    @PostConstruct
    private void init() throws ParseException {

        List<EventDetails> eventDetailsList = sessionFactory.openSession().createCriteria(EventDetails.class).list();

        if(eventDetailsList.size() == 0){
            initData();
        }

    }

    public List<EventDetails> getEventDetails()  {
        Criteria criteria = sessionFactory.openSession().createCriteria(EventDetails.class);
        List a = criteria.list();


        return a;
    }

    private void initData() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        EventDetails details = new EventDetails();
        Date parsedDate;
        Timestamp dateTime;
        String quality = "Bad";
        for(int i = 0; i < 10; i++){
            parsedDate = dateFormat.parse("2021-01-01 15:00:01");
            dateTime = new java.sql.Timestamp(parsedDate.getTime()+ TimeUnit.MINUTES.toMillis(i*15));
            quality = changeQua(quality);

            details.setDateEvt(dateTime);
            details.setQuality(quality);
            details.setUnits("kg");
            details.setValue(generateValue());

            eventInsert.save(details);

            System.out.println("INSERT INTO event_details ("+details.getDateEvt()+","+details.getUnits()+","+details.getQuality()+","+details.getValue()+")");

        }


    }

    private String changeQua(String q){

        if(q.equals("Good"))
            return "Bad";
        else
            return "Good";
    }

    private Float generateValue(){

        Random r = new Random();
        float random = 0 + r.nextFloat() * (1000 - 0);
        BigDecimal result = new BigDecimal(Float.toString(random));
        result = result.setScale(2, BigDecimal.ROUND_HALF_UP);
        return result.floatValue();
    }


}
