package com.example.jkrest.demo.event;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping(value="/api/events",produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event){

        URI creatUri = linkTo(EventController.class).slash("{id}").toUri();
        event.setId(10);
        return ResponseEntity.created(creatUri).body(event);




    }
}
