package com.placementtraining.jornalApp.service;

import com.placementtraining.jornalApp.entity.JournalEntry;
import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.repository.JournalEntryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

                    userService.saveEntry(user);
                } else {
                    System.out.println("user not exist");
                    ;
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
        User user =userService.findByUsername(username);
        user.getJournalEntries().removeIf(x ->user.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);

    }
}
