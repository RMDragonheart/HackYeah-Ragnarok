package com.vikings.hackaton.demo.model.db;

import com.vikings.hackaton.demo.model.BodyPart;
import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.Localisation;
import com.vikings.hackaton.demo.model.Sport;
import com.vikings.hackaton.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DatabaseConnectorMock implements DatabaseConnector {

    private static final List<Sport> sports = Arrays.asList(
            new Sport(0, "SlackLine", Arrays.asList(1, 2, 3, 5), Arrays.asList(0, 1, 2, 3, 4)),
            new Sport(1, "Swimming", Arrays.asList(2, 3), Arrays.asList(0, 2, 3, 5, 6)),
            new Sport(2, "Climbing", Arrays.asList(4, 2, 3, 6, 7), Arrays.asList(1, 2, 4)),
            new Sport(3, "Running", Arrays.asList(1, 2, 3, 5), Arrays.asList(0, 2, 3)),
            new Sport(4, "Ice skating", Arrays.asList(1, 2, 3, 5), Arrays.asList(11)),
            new Sport(5, "Wakeboarding", Arrays.asList(2, 3, 4, 5, 6, 7), Arrays.asList(6)),
            new Sport(6, "Kayaking", Arrays.asList(0, 2, 3, 4, 6), Arrays.asList(5)),
            new Sport(7, "Football", Arrays.asList(1, 2, 3, 5), Arrays.asList(2,5,12)),
            new Sport(8, "Quidditch", Arrays.asList(0, 1, 2, 3, 4, 5, 6), Arrays.asList(2, 12)),
            new Sport(9, "Capoeira", Arrays.asList(1, 2, 3, 5), Arrays.asList(13)),
            new Sport(10, "Dancing", Arrays.asList(1, 2, 3, 5), Arrays.asList(2, 10, 13)),
            new Sport(11, "Fencing", Arrays.asList(1, 2, 3, 4, 5, 6), Arrays.asList(9)),
            new Sport(12, "Basketball", Arrays.asList(2,3,4,6), Arrays.asList(2, 12)),
            new Sport(13, "Balloon flying", Arrays.asList(), Arrays.asList(7))
    );

    private static final List<Injury> injuries = Arrays.asList(
            new Injury(0, "Hand amputation", BodyPart.HAND),
            new Injury(1, "Leg amputation", BodyPart.LEG),
            new Injury(2, "Head amputation", BodyPart.HEAD),
            new Injury(3, "Broken Spine - paralysed", BodyPart.MAIN_BODY),
            new Injury(4, "Both hands amputation", BodyPart.HAND),
            new Injury(5, "Both legs amputation", BodyPart.LEG),
            new Injury(6, "No grip in hands", BodyPart.HAND),
            new Injury(7, "Heart problems", BodyPart.MAIN_BODY)
    );

    private static final List<Localisation> localisations = Arrays.asList(
            new Localisation(0, "Platinium Wadowicka", 50.0330614,19.937865),
            new Localisation(1, "Avatar", 50.0639159,20.0085965),
            new Localisation(2, "ComComZone", 50.0388068,19.9987573),
            new Localisation(3, "Cascada", 50.0185564,19.902369),
            new Localisation(4, "Centrum Wspinaczkowe Forteca", 50.0600219,19.924803),
            new Localisation(5, "Kolna", 50.0312691,19.8253711),
            new Localisation(6, "Wakepoint", 50.0312691,19.8253711),
            new Localisation(7, "Balloon flying", 50.0467088,19.9345129),
            new Localisation(8, "Parachuting Kraków", 50.0330841,19.8791921),
            new Localisation(9, "Silkfencing", 50.0632293,19.9631915),
            new Localisation(10, "Skarlet - szkoła tańca", 50.0604593,19.9256452),
            new Localisation(11, "Lodowisko Cracovia", 50.0568753,19.9467302),
            new Localisation(12, "Tauron Arena", 50.0676592,19.9885267),
            new Localisation(13, "Capoeira Camangula", 50.1779943,19.1746822)
    );

    private static final List<User> users = Collections.singletonList(new User("user", "pwd", Collections.singletonList(1), 1, 1));

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

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void addLocalisation(Localisation localisation) {
        localisation.setId(localisations.size());
        localisations.add(localisation);
    }
}
