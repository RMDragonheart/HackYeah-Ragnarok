package com.vikings.hackaton.demo.model.db;

import com.vikings.hackaton.demo.model.BodyPart;
import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.Localisation;
import com.vikings.hackaton.demo.model.Sport;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DatabaseConnectorMock implements DatabaseConnector {

    private static final List<Sport> sports = Arrays.asList(
            new Sport(0, "SlackLine", Arrays.asList(1, 2, 3), Arrays.asList(1, 4)),
            new Sport(1, "Swimming", Collections.singletonList(2), Arrays.asList(0, 2, 3)),
            new Sport(2, "Climbing", Arrays.asList(0, 2), Arrays.asList(1, 4)),
            new Sport(3, "Running", Arrays.asList(1, 2), Arrays.asList(0, 2, 3))
    );

    private static final List<Injury> injuries = Arrays.asList(
            new Injury(0, "Hand amputation", BodyPart.HAND),
            new Injury(1, "Leg amputation", BodyPart.LEG),
            new Injury(2, "Head amputation", BodyPart.HEAD),
            new Injury(3, "Broken Spine", BodyPart.MAIN_BODY)
    );

    private static final List<Localisation> localisations = Arrays.asList(
            new Localisation(0, "Platinium Wadowicka", 1, 1),
            new Localisation(1, "Fundacja Avalon", 2, 2),
            new Localisation(2, "ComComZone", 3, 3),
            new Localisation(3, "Cascada", 4, 4),
            new Localisation(4, "Centrum Wspinaczkowe Forteca", 4, 4)
    );

    @Override
    public List<Sport> getSports() {
        return sports;
    }

    @Override
    public List<Injury> getInjuries() {
        return injuries;
    }

    @Override
    public List<Localisation> getLocalisations() {
        return localisations;
    }
}
