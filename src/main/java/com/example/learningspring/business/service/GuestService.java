package com.example.learningspring.business.service;

import com.example.learningspring.business.domain.RoomReservation;
import com.example.learningspring.data.entity.Guest;
import com.example.learningspring.data.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getGuests() {
        return  StreamSupport.stream(guestRepository.findAll().spliterator(), false)
                .sorted((o1, o2) -> {
                    if (o1.getLastName().equals(o2.getLastName())) {
                        return o1.getFirstName().compareTo(o2.getFirstName());
                    }
                    return o1.getLastName().compareTo(o2.getLastName());
                })
                .collect(Collectors.toList());
    }
}
