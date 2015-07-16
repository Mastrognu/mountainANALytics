package it.polimi.awt.repository;

import it.polimi.awt.domain.Mountain;
import it.polimi.awt.domain.Photo;
import it.polimi.awt.domain.Province;

import java.util.List;

public interface IJpaGenericAccess {

	/**
	 *
	 * @param photo The Photo to insert into the database
	 */
	public void insertPhoto(Photo photo);

	/**
	 * This method is used to know if a Mountain is part of the subset we have
	 * in the database. Since it can happen to have more than one Mountain with
	 * a very similar name, we return a list of all of them; nonetheless, most
	 * of the times the list will have no more than one item
	 *
	 * @param mquery The Mountain to find in the database
	 * @return A list of Mountain found in the database based on the input
	 */
	public List<Mountain> getMultiMountain(Mountain mountain);

	/**
	 *
	 * @param queryText The text to query in the province table
	 * @return The Province if found, null otherwise
	 */
	public Province getProvince(String provinceName);

	/**
	 *
	 * @param mountainName
	 * @return
	 */
	public Mountain getMountain(String mountainName);
}
