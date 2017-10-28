package com.vikings.hackaton.demo.model.db;

import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.Localisation;
import com.vikings.hackaton.demo.model.Sport;

import java.util.List;

public interface DatabaseConnector {

    List<Sport> getSports();

    List<Injury> getInjuries();

    List<Localisation> getLocalisations();
}
