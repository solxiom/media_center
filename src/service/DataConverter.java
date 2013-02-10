/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import service.domain.DataObject;
import service.domain.ImdbDataObject;
import service.domain.OmdbDataObject;
import service.domain.tomatoes.TMDataObject;

/**
 *
 * @author kavan
 */
public interface DataConverter {

    DataObject convert(OmdbDataObject object);

    DataObject convert(ImdbDataObject object);

    DataObject convert(TMDataObject object);
}
