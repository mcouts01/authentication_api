package couts.matt.authapplication.Service;

import couts.matt.authapplication.Mock.EventMock;
import couts.matt.authapplication.entity.Event;
import couts.matt.authapplication.repository.EventRepository;
import couts.matt.authapplication.service.EventService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @Mock
    private EventRepository mockEventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void testGetAllUpcomingEvents() {
        //Arrange
        var mockEventList = new ArrayList<Event>();
        mockEventList.add(EventMock.getEventMock());

        when(mockEventRepository.findAllAfterStartDate(ArgumentMatchers.any(LocalDateTime.class))).thenReturn(Optional.of(mockEventList));

        //Act
        var eventList = eventService.getAllUpcomingEvents();

        //Assert
        Assert.assertNotNull(eventList);
        Assert.assertEquals("Test", eventList.get(0).getTitle());
    }
}
