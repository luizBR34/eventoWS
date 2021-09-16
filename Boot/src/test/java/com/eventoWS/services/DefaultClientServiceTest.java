package com.eventoWS.services;

import com.eventoApp.models.Event;
import com.eventoApp.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultClientServiceTest {

    @Mock
    private ClientService clientService;


    @Test
    public void eventListWithoutErrorTest() {

        given(clientService.eventList("sarah")).willReturn(buildEventList("sarah"));
        assertThat(clientService.eventList("sarah")).isEqualTo(buildEventList("sarah"));
    }


    @Test
    public void seekEventTest() {

        Event build = Event.builder().code(01).name("John's wedding").city("New York").date("12-03-2019").time("11:00").user(User.builder().id(1l).userName("sarah").build()).build();

        given(clientService.seekEvent(01)).willReturn(build);
        assertThat(clientService.seekEvent(01)).isEqualTo(build);
        verify(clientService, times(1)).seekEvent(01);
    }


    @Test
    public void saveEventTest() {

        //Given
        Event build = Event.builder().code(01).name("John's wedding").city("New York").date("12-03-2019").time("11:00").user(User.builder().id(1l).userName("sarah").build()).build();
        clientService.saveEvent(build);

        //Then
        verify(clientService, times(1)).saveEvent(build);
    }


    @Test
    public void deleteEventTest() {
        //Given
        clientService.deleteEvent(01);

        //Then
        verify(clientService, times(1)).deleteEvent(01);
        assertThat(clientService.seekEvent(01)).isNull();
    }


    private List<Event> buildEventList(String username) {

        List<Event> eventList = Arrays.asList(Event.builder().code(01).name("John's wedding").city("New York").date("12-03-2019").time("11:00").user(User.builder().id(1l).userName("sarah").build()).build(),
                                              Event.builder().code(01).name("Team's meeting").city("São Paulo").date("15-08-2020").time("11:00").user(User.builder().id(1l).userName("sarah").build()).build(),
                                              Event.builder().code(01).name("BBQ's Kevin").city("Paris").date("21-10-2021").time("12:00").user(User.builder().id(1l).userName("richard").build()).build());

        return eventList.stream()
                        .filter(u -> u.getUser().getUserName().equals(username))
                        .collect(Collectors.toList());
    }
}
