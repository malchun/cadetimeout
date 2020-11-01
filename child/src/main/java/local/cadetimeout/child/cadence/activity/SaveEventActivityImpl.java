package local.cadetimeout.child.cadence.activity;

import local.cadetimeout.child.domain.Event;
import local.cadetimeout.child.domain.EventRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveEventActivityImpl implements SaveEventActivity {

    private EventRepository eventRepository;

    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }
}
