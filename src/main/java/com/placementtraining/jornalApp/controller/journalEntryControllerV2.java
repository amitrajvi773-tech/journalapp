package com.placementtraining.jornalApp.controller;

import com.placementtraining.jornalApp.entity.JournalEntry;
import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.service.JournalEntryService;
import com.placementtraining.jornalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getallentryfromuser(@PathVariable String username) {
        User user=userService.findByUsername(username);
        List<JournalEntry> all=user.getJournalEntries();
        if(all !=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myentry,@PathVariable String username){
        try {
            journalEntryService.saveEntry(myentry,username);
            return new ResponseEntity<>(myentry,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<?> getJournalbyid(@PathVariable Integer id){
        Optional<JournalEntry> journalEntry=journalEntryService.findbyid(id);
        if(journalEntry.isPresent()){
       return new ResponseEntity<>(journalEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @DeleteMapping("/id/{username}/{myid}")
    public boolean  deletebyid(@PathVariable Integer myid,String username){
        journalEntryService.deleteid(myid,username);
        return true;
    }

    @PutMapping("/id/{username}/{id}")
    public JournalEntry updatebyid(
            @PathVariable Integer id,
            @RequestBody JournalEntry myentry,
            @PathVariable String username){
        JournalEntry old=journalEntryService.findbyid(id).orElse(null);
        if(old!=null){
            old.setName(myentry.getName() !=null && !myentry.getName().isEmpty() ? myentry.getName(): old.getName());
            old.setContent(myentry.getContent() !=null && !myentry.getContent().isEmpty() ? myentry.getContent(): old.getContent());

        }
 journalEntryService.putsaveEntry(old);
        return old;
    }



}
