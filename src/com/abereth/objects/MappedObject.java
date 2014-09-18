package com.abereth.objects;

import com.abereth.world.Map;

/**
 * Created by Bobby on 18/09/2014.
 */
public interface MappedObject {

    /**
     * @return CoordinateX, relative to the map
     */
    short getCX();
    /**
     * @return CoordinateY, relative to the map
     */
    short getCY();

    short setCX( short cX );
    short setCY( short cY );

    /**
     * @return Get the current map that the object is bound to
     */
    Map getMap();

}
