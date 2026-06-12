package com.placementtraining.jornalApp.controller;

import com.placementtraining.jornalApp.entity.JournalEntry;
import com.placementtraining.jornalApp.entity.User;
import com.placementtraining.jornalApp.service.JournalEntryService;
import com.placementtraining.jornalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

//    @GetMapping("/test")
//    public String test() {
//        return "working";
//    }

    @GetMapping("/user")
    public ResponseEntity<?> getallentryfromuser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        User user = userService.findByUsername(username);

        if(user == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user.getJournalEntries(), HttpStatus.OK);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myentry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.saveEntry(myentry,username);
            return new ResponseEntity<>(myentry,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<?> getJournalbyid(@PathVariable Integer myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userService.findByUsername(username);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=journalEntryService.findbyid(myid);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/id/{myid}")
    public boolean  deletebyid(@PathVariable Integer myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        journalEntryService.deleteid(myid,username);
        return true;
    }


    @PutMapping("/id/{myid}")
    public ResponseEntity<?> updatebyid(@PathVariable Integer myid, @RequestBody JournalEntry myentry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user=userService.findByUsername(username);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=journalEntryService.findbyid(myid);

        if(journalEntry.isPresent()){
            JournalEntry old=journalEntry.get();

            old.setName(myentry.getName() !=null && !myentry.getName().isEmpty() ? myentry.getName(): old.getName());
            old.setContent(myentry.getContent() !=null && !myentry.getContent().isEmpty() ? myentry.getContent(): old.getContent());
                journalEntryService.putsaveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);        }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }



}
