package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long ,TimeEntry> localRepo = new HashMap();


    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long newId = (this.list().size()+1);
        localRepo.put(newId,timeEntry);
        timeEntry.setId(newId);
        return localRepo.get(newId);
    }

    @Override
    public TimeEntry find(long id) {
        return localRepo.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<>();
        localRepo.entrySet().stream().forEach(e->{
            list.add(e.getValue());
        });
        return list;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        localRepo.put(id,timeEntry);
        return localRepo.get(id);
    }

    @Override
    public void delete(long id) {
        localRepo.remove(id);
    }
}
