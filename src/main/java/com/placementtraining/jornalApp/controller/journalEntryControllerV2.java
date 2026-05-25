package com.placementtraining.jornalApp.controller;

import com.placementtraining.jornalApp.entity.JournalEntry;
import com.placementtraining.jornalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {

        return journalEntryService.getALL();
    }

    @GetMapping("/id/{myid}")
    public JournalEntry getJournalbyid(@PathVariable Integer myid){
        return journalEntryService.getid(myid).orElse(null);

    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myentry){
        journalEntryService.saveEntry(myentry);
        return true;

    }

    @DeleteMapping("/id/{myid}")
    public boolean  deletebyid(@PathVariable Integer myid){
        journalEntryService.deleteid(myid);
return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntry updatebyid(@PathVariable Integer id,@RequestBody JournalEntry myentry){
        JournalEntry old=journalEntryService.getid(id).orElse(null);
        if(old!=null){
            old.setName(myentry.getName() !=null && !myentry.getName().isEmpty() ? myentry.getName(): old.getName());
            old.setContent(myentry.getContent() !=null && !myentry.getContent().isEmpty() ? myentry.getContent(): old.getContent());

        }
 journalEntryService.saveEntry(old);
        return old;
    }



}
