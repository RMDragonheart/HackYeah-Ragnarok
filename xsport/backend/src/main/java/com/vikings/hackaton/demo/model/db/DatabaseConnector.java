package com.vikings.hackaton.demo.model.db;

import com.vikings.hackaton.demo.model.Injury;
import com.vikings.hackaton.demo.model.Sport;

import java.util.List;

/**
 * Created by patryk on 28.10.17.
 */
public interface DatabaseConnector {

    List<Sport> getSports();

    List<Injury> getInjuries();
}
