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
					new Sport(0, "SlackLine", Arrays.asList(1, 2, 3, 5, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23), Arrays.asList(0, 1, 2, 3, 4)),
					new Sport(1, "Swimming", Arrays.asList(2, 3, 8, 32, 33, 34, 35, 36), Arrays.asList(0, 2, 3, 5, 6)),
					new Sport(2, "Climbing", Arrays.asList(4, 2, 3, 6, 7, 8, 9, 10, 11, 24, 25, 26, 27), Arrays.asList(1, 2, 4)),
					new Sport(3, "Running", Arrays.asList(1, 2, 3, 5, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 32, 33, 34, 35), Arrays.asList(0, 2, 3)),
					new Sport(4, "Ice skating", Arrays.asList(1, 2, 3, 5, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 32, 33, 34, 35), Arrays.asList(11)),
					new Sport(5, "Wakeboarding", Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35), Arrays.asList(6)),
					new Sport(6, "Kayaking", Arrays.asList(0, 2, 3, 4, 6, 8, 9, 10, 11, 24, 25, 26, 27, 28, 29, 30, 31), Arrays.asList(5)),
					new Sport(7, "Football", Arrays.asList(1, 2, 3, 5, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 32, 33, 34, 35), Arrays.asList(2, 5, 12)),
					new Sport(8, "Quidditch", Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35), Arrays.asList(2, 12)),
					new Sport(9, "Capoeira", Arrays.asList(1, 2, 3, 5, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 32, 33, 34, 35), Arrays.asList(13)),
					new Sport(10, "Dancing", Arrays.asList(1, 2, 3, 5, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 32, 33, 34, 35), Arrays.asList(2, 10, 13)),
					new Sport(11, "Fencing", Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35), Arrays.asList(9)),
					new Sport(12, "Basketball", Arrays.asList(0, 2, 3, 4, 6, 8, 9, 10, 11, 24, 25, 26, 27, 28, 29, 30, 31), Arrays.asList(2, 12)),
					new Sport(13, "Balloon flying", Arrays.asList(), Arrays.asList(7)),
					new Sport(14, "Skydiving", Arrays.asList(), Arrays.asList(14))
	);

	private static final List<Injury> injuries = Arrays.asList(
					new Injury(0, "Hand amputation", BodyPart.HAND),
					new Injury(1, "Leg amputation", BodyPart.LEG),
					new Injury(2, "Head amputation", BodyPart.HEAD),
					new Injury(3, "Broken Spine - paralysed", BodyPart.MAIN_BODY),
					new Injury(4, "Both hands amputation", BodyPart.HAND),
					new Injury(5, "Both legs amputation", BodyPart.LEG),
					new Injury(6, "No grip in hands", BodyPart.HAND),
					new Injury(7, "Heart problems", BodyPart.MAIN_BODY),

					new Injury(8, "S63.0 Subluxation and dislocation of wrist and hand joints", BodyPart.HAND),
					new Injury(9, "S63.2 Subluxation and dislocation of other finger(s)", BodyPart.HAND),
					new Injury(10, "S63.5 Other and unspecified sprain of wrist", BodyPart.HAND),
					new Injury(11, "S63.6 Other and unspecified sprain of finger(s)", BodyPart.HAND),

					new Injury(12, "S97.0 Crushing injury of ankle", BodyPart.FOOT),
					new Injury(13, "S97.1 Crushing injury of toe", BodyPart.FOOT),
					new Injury(14, "S97.8 Crushing injury of foot", BodyPart.FOOT),
					new Injury(15, "S98.0 Traumatic amputation of foot at ankle level", BodyPart.FOOT),

					new Injury(16, "S86.0 Injury of Achilles tendon", BodyPart.CALF),
					new Injury(17, "S86.9 Injury of unspecified muscle and tendon at lower leg level", BodyPart.CALF),
					new Injury(18, "S88.0 Traumatic amputation at knee level", BodyPart.CALF),
					new Injury(19, "S89.8 Other specified injuries of lower leg", BodyPart.CALF),

					new Injury(20, "S72.0 Fracture of head and neck of femur", BodyPart.THIGH),
					new Injury(21, "S72.1 Pertrochanteric fracture", BodyPart.THIGH),
					new Injury(22, "S72.3 Fracture of shaft of femur", BodyPart.THIGH),
					new Injury(23, "S72.4 Fracture of lower end of femur", BodyPart.THIGH),

					new Injury(24, "S52.0 Fracture of upper end of ulna", BodyPart.FOREARM),
					new Injury(25, "S52.03 Fracture of olecranon process with intraarticular extension of ulna", BodyPart.FOREARM),
					new Injury(26, "S52.1 Fracture of upper end of radius", BodyPart.FOREARM),
					new Injury(27, "S52.3 Fracture of shaft of radius", BodyPart.FOREARM),

                    			new Injury(28, "S40.0 Injury of shoulder and forearm", BodyPart.ARM),
                    			new Injury(29, "S42.1 Fracture of the scapula", BodyPart.ARM),
                    			new Injury(30, "S42.11 Acromion", BodyPart.ARM),
                    			new Injury(31, "S42.14 Spine of scapula", BodyPart.ARM),

					new Injury(32, "S30.0 Contusion of lower back and pelvis", BodyPart.LOWER_BODY),
					new Injury(33, "S30.1 Contusion of abdominal wall", BodyPart.LOWER_BODY),
					new Injury(34, "S30.2 Contusion of external genital organs", BodyPart.LOWER_BODY),
					new Injury(35, "S30.7 Numerous superficial injuries of abdomen, lower back and pelvis", BodyPart.LOWER_BODY),

					new Injury(36, "B35.4 Ringworm of the body", BodyPart.FULL_BODY)
	);

	private static final List<Localisation> localisations = Arrays.asList(
					new Localisation(0, "Platinium Wadowicka", 50.0330614, 19.937865),
					new Localisation(1, "Avatar", 50.0639159, 20.0085965),
					new Localisation(2, "ComComZone", 50.0388068, 19.9987573),
					new Localisation(3, "Cascada", 50.0185564, 19.902369),
					new Localisation(4, "Centrum Wspinaczkowe Forteca", 50.0600219, 19.924803),
					new Localisation(5, "Kolna", 50.0312691, 19.8253711),
					new Localisation(6, "Wakepoint", 50.0312691, 19.8253711),
					new Localisation(7, "Balloon flying", 50.0467088, 19.9345129),
					new Localisation(8, "Parachuting Kraków", 50.0330841, 19.8791921),
					new Localisation(9, "Silkfencing", 50.0632293, 19.9631915),
					new Localisation(10, "Skarlet - szkoła tańca", 50.0604593, 19.9256452),
					new Localisation(11, "Lodowisko Cracovia", 50.0568753, 19.9467302),
					new Localisation(12, "Tauron Arena", 50.0676592, 19.9885267),
					new Localisation(13, "Capoeira Camangula", 50.1779943, 19.1746822),
					new Localisation(14, "Skoki spadochronowe", 50.009044, 19.981104)
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
