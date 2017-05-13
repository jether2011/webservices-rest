package br.unisal.api.interfaces;

import br.unisal.api.model.City;

/**
 * 
 * @author JETHER ROIS
 *
 */
public interface DistanceCalculate {
	double calculateDistanceInKilometers(City from, City to);
	double calculateDistanceInMilles(City from, City to);
}
