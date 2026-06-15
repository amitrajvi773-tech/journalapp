package com.placementtraining.jornalApp.Schedular;

import com.placementtraining.jornalApp.cache.AppCache;
import com.placementtraining.jornalApp.entity.JournalEntry;
import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.enums.Sentiment;
import com.placementtraining.jornalApp.repository.UserRepositoryImp;
import com.placementtraining.jornalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserSchedular {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImp userRepositoryImp;

//    @Autowired
//    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;
//    @Scheduled(cron="0 * * ? * *")
    public void fetchUserAndSaMail(){
        List<User> all= userRepositoryImp.getUserBySA();
        for(User user:all){
            List<JournalEntry> journalEntries=user.getJournalEntries();
            List<Sentiment> sentiments=journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime
                    .now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());

            Map<Sentiment,Integer> sentimentCount=new HashMap<>();
            for(Sentiment sentiment:sentiments){
                if(sentiment != null){
                    sentimentCount.put(sentiment,sentimentCount.getOrDefault(sentiment,0)+1);
                }
             Sentiment mostFrequentSentiment=null;
             int maxCount=0;
             for(Map.Entry<Sentiment,Integer> entry:sentimentCount.entrySet()){
                 if(entry.getValue()>maxCount){
                     maxCount= entry.getValue();
                     mostFrequentSentiment=entry.getKey();
                 }
             }
             if(mostFrequentSentiment != null){
                 emailService.sendMail(user.getEmail(), "hey this is Sentiment pf last  days  ", mostFrequentSentiment.toString());
             }
            }

        }
        
    }


   @Scheduled(cron="0 0 0/10 * ? * *")
    public void cleasAppCache(){
        appCache.init();
    }
}
