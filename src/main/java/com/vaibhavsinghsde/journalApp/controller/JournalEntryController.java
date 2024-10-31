package com.vaibhavsinghsde.journalApp.controller;

import com.vaibhavsinghsde.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }
    @GetMapping("fetch/{id}")
    public JournalEntry fetchJournal(@PathVariable Long id){
        return journalEntries.get(id);
    }
    @PutMapping
    public boolean updateJournal(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }
    @DeleteMapping("remove/{id}")
    public JournalEntry deleteJournal(@PathVariable Long id){
        return journalEntries.remove(id);
    }
}
