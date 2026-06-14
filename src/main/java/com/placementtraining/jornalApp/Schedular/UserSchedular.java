package com.placementtraining.jornalApp.Schedular;

import com.placementtraining.jornalApp.cache.AppCache;
import com.placementtraining.jornalApp.entity.JournalEntry;
import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.repository.UserRepositoryImp;
import com.placementtraining.jornalApp.service.EmailService;
import com.placementtraining.jornalApp.service.SentimentAnalysisService;
import com.placementtraining.jornalApp.service.UserDetailServiceimp;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserSchedular {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImp userRepositoryImp;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache
    @Scheduled(cron="0 * * ? * *")
    public void fetchUserAndSaMail(){
        List<User> all= userRepositoryImp.getUserBySA();
        for(User user:all){
            List<JournalEntry> journalEntries=user.getJournalEntries();
            List<String> filterEntries=journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime
                    .now().minus(7, ChronoUnit.DAYS))).map(JournalEntry::getContent).collect(Collectors.toList());

            String entry = String.join(" ",filterEntries);
            String sentiment=sentimentAnalysisService.getSentiment(entry);
            emailService.sendMail(user.getEmail(),"this is corn method sending email for 7 days continously",sentiment);


        }
        
    }
    @Scheduled(cron="0 0 0/10 * ? * *")
    public void cleasAppCache(){
        appCache.init();
    }
}
