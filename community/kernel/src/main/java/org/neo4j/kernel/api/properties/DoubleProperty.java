/**
 * Copyright (c) 2002-2013 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.api.properties;

import org.neo4j.kernel.impl.nioneo.store.PropertyData;
import org.neo4j.kernel.impl.nioneo.store.PropertyDatas;

final class DoubleProperty extends FullSizeProperty
{
    private final double value;

    DoubleProperty( long propertyKeyId, double value )
    {
        super( propertyKeyId );
        this.value = value;
    }

    @Override
    public boolean valueEquals( Object other )
    {
        if ( other instanceof Double )
        {
            return value == (double) other;
        }

        return valueCompare( value, other );
    }

    @Override
    public Double value()
    {
        return value;
    }

    @Override
    int valueHash()
    {
        long temp = Double.doubleToLongBits( value );
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    boolean hasEqualValue( FullSizeProperty that )
    {
        return Double.compare( this.value, ((DoubleProperty) that).value ) == 0;
    }

    @Override
    @Deprecated
    public PropertyData asPropertyDataJustForIntegration()
    {
        return PropertyDatas.forDouble( (int) propertyKeyId, -1, value );
    }
}
