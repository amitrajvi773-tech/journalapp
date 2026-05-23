package com.placementtraining.jornalApp.service;

import com.placementtraining.jornalApp.entity.JournalEntry;
import com.placementtraining.jornalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService  {
    @Autowired
    JournalEntryRepository journalEntryRepository;

    public void saveEntry(  JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getALL() {
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getid(Integer myid){
        return journalEntryRepository.findById(myid);
    }
    public void deleteid(Integer id){
         journalEntryRepository.deleteById(id);

    }
}
