package com.vaibhavsinghsde.journalApp.controller;

import com.vaibhavsinghsde.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.vaibhavsinghsde.journalApp.service.journalEntryService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private journalEntryService journalEntryService;

    private Map<String, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("fetch/{id}")
    public JournalEntry fetchJournal(@PathVariable String id){
        return null;
    }

    @PutMapping
    public boolean updateJournal(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }

    @DeleteMapping("remove/{id}")
    public JournalEntry deleteJournal(@PathVariable ObjectId id){
        return null;
    }
}
