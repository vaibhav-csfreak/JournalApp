package com.vaibhavsinghsde.journalApp.controller;

import com.vaibhavsinghsde.journalApp.entity.JournalEntry;
import com.vaibhavsinghsde.journalApp.entity.User;
import com.vaibhavsinghsde.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vaibhavsinghsde.journalApp.service.journalEntryService;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private journalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    private Map<String, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public ResponseEntity<?> getAllJournalEntries(){
        List<JournalEntry> entries = journalEntryService.getAll();
        if(entries != null && !entries.isEmpty()){
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> entries = user.getJournalEntries();
        if(entries != null && !entries.isEmpty()){
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("fetch/{id}")
    public ResponseEntity<JournalEntry> fetchJournal(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateJournal(@PathVariable ObjectId id, @PathVariable String userName, @RequestBody JournalEntry myEntry){
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if(old != null) {
            old.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent() != null && !myEntry.getContent().equals("") ? myEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("remove/{userName}/{id}")
    public ResponseEntity<?> deleteJournal(@PathVariable ObjectId id, @PathVariable String userName){
        journalEntryService.deleteById(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
