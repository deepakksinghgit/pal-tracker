package io.pivotal.pal.tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public TimeEntryController (TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }


    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntry);
        return new ResponseEntity(createdTimeEntry,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry==null){
            return new ResponseEntity(timeEntry,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(timeEntry,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeEntryRepository.list();
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(id,timeEntry);
        if(updatedTimeEntry==null){
            return new ResponseEntity(timeEntry,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(updatedTimeEntry,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable Long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        timeEntryRepository.delete(id);
        return new ResponseEntity(timeEntry,HttpStatus.NO_CONTENT);
    }
}
