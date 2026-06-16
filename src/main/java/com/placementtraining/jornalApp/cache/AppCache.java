package com.placementtraining.jornalApp.cache;

import com.placementtraining.jornalApp.entity.ConfigJournalAppEntry;
import com.placementtraining.jornalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
   private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> App_Cache=new HashMap<>();

    @PostConstruct
    public void init(){
        List<ConfigJournalAppEntry> all=configJournalAppRepository.findAll();
        for(ConfigJournalAppEntry configJournalAppEntry:all){
        App_Cache.put(configJournalAppEntry.getConfigkey(),configJournalAppEntry.getValue());
        }
 
    }
}
