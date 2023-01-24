package couts.matt.authapplication.Controller;

import couts.matt.authapplication.controller.EventController;
import couts.matt.authapplication.entity.Event;
import couts.matt.authapplication.service.EventService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventControllerTest {

    @Mock
    private EventService mockEventService;

    @InjectMocks
    private EventController eventController;

    @Test
    public void getUpcomingEvents() {
        //Arrange
        var mockEvent = new Event();
        mockEvent.setTitle("Test");
        mockEvent.setEventID(1);
        mockEvent.setDescription("Test Event");

        var mockEventList = new ArrayList<Event>();
        mockEventList.add(mockEvent);

        when(mockEventService.getAllUpcomingEvents()).thenReturn(mockEventList);

        //Act
        var response = eventController.getUpcomingEvents();

        //Assert
        Assert.assertEquals(200, response.getStatusCode().value());
        Assert.assertEquals("Test", Objects.requireNonNull(response.getBody()).get(0).getTitle());
    }
}
