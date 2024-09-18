package university.wcsb.iCalendar.utilities;

import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.RandomUidGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class VEventFactory {
    private final VEvent vEvent;

    public VEventFactory(String eventName, LocalDate eventDate, LocalTime eventStartTime, LocalTime eventEndTime, String eventLocation) {
        if (eventName == null || eventName.isBlank() || eventDate == null || eventStartTime == null || eventEndTime == null || eventLocation == null || eventLocation.isBlank()) {
            throw new IllegalArgumentException("Trying to create a VEventFactory with invalid data.");
        }
        this.vEvent = createVEvent(eventName, eventDate, eventStartTime, eventEndTime, eventLocation);
    }

    private VEvent createVEvent(
            String eventName,
            LocalDate eventDate,
            LocalTime eventStartTime,
            LocalTime eventEndTime,
            String eventLocation
    ) {
        ZonedDateTime eventStartZonedDateTime = ZonedDateTime.of(eventDate, eventStartTime, ZoneId.of("Europe/Rome"));
        ZonedDateTime eventEndZonedDateTime = ZonedDateTime.of(eventDate, eventEndTime, ZoneId.of("Europe/Rome"));
        Location eventLocationProperty = new Location(eventLocation);

        VEvent vEvent = new VEvent(eventStartZonedDateTime, eventEndZonedDateTime, eventName);
        vEvent.add(eventLocationProperty);

        Uid eventUid = new RandomUidGenerator().generateUid();
        vEvent.add(eventUid);

        return vEvent;
    }

    public VEvent getVEvent() {
        return vEvent;
    }
}
