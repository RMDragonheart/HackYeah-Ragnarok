package com.vikings.hackaton.demo.model.db;

import com.vikings.hackaton.demo.model.BodyPart;
import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.Sport;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by patryk on 28.10.17.
 */
@Service
public class DatabaseConnectorMock implements DatabaseConnector {

    List<Sport> sports = Arrays.asList(
            new Sport("lol", Collections.EMPTY_LIST, Collections.EMPTY_LIST),
            new Sport("lol2", Collections.EMPTY_LIST, Collections.EMPTY_LIST),
            new Sport("lol3", Collections.EMPTY_LIST, Collections.EMPTY_LIST)
    );

    List<Injury> injuries = Arrays.asList(
            new Injury("Hand amputation", BodyPart.HAND),
            new Injury("Leg amputation", BodyPart.LEG),
            new Injury("Head amputation", BodyPart.HEAD),
            new Injury("Broken Spine", BodyPart.MAIN_BODY)
    );

    @Override
    public List<Sport> getSports() {
        return sports;
    }

    @Override
    public List<Injury> getInjuries() {
        return injuries;
    }
}
