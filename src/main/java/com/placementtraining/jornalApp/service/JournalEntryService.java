package com.placementtraining.jornalApp.service;

import com.placementtraining.jornalApp.entity.JournalEntry;
import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.repository.JournalEntryRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class JournalEntryService  {
    @Autowired
    JournalEntryRepository journalEntryRepository;

    @Autowired
    UserService userService;



    @Transactional
    public void saveEntry(  JournalEntry journalEntry,String username){
       User user =userService.findByUsername(username);
        try {
            if (user != null) {
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);}
            else{
                System.out.println("user not exist");
            }
        } catch (Exception e) {
            throw new RuntimeException("error coming in saveEntry",e);
        }
    }
    public void putsaveEntry(  JournalEntry journalEntry){
       journalEntryRepository.save(journalEntry);
        }

    public List<JournalEntry> getALL() {

        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findbyid(Integer myid){

        return journalEntryRepository.findById(myid);
    }

    public void deleteid(Integer id, String username){
        try{
        User user =userService.findByUsername(username);
        boolean removed= user.getJournalEntries().removeIf(x ->x.getId().equals(id));
        if(removed){
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
            System.out.println("removed by id");}}
        catch (Exception e) {
            log.error("id not found",e);
            throw new RuntimeException("error occur during deleting journalentry",e);
        }


    }
}
