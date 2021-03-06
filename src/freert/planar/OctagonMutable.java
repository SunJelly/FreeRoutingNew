package freert.planar;
/*
 *  Copyright (C) 2014  Damiano Bolla  website www.engidea.com
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License at <http://www.gnu.org/licenses/> 
 *   for more details.
 *
 */

/**
 * mutable octagon with double coordinates (see geometry.planar.IntOctagon)
 */
public final class OctagonMutable implements PlaObject
   {
   public double lx;
   public double ly;
   public double rx;
   public double uy;
   public double ulx;
   public double lrx;
   public double llx;
   public double urx;

   public OctagonMutable()
      {
      set_empty();
      }
   
   @Override
   public final boolean is_NaN ()
      {
      return false;
      }
   
   public void set_empty()
      {
      lx = Integer.MAX_VALUE;
      ly = Integer.MAX_VALUE;
      rx = Integer.MIN_VALUE;
      uy = Integer.MIN_VALUE;
      ulx = Integer.MAX_VALUE;
      lrx = Integer.MIN_VALUE;
      llx = Integer.MAX_VALUE;
      urx = Integer.MIN_VALUE;
      }

   
   /**
    * enlarges the octagon so that it contains p_point
    */
   public void join(PlaPointFloat p_point)
      {
      lx = Math.min(p_point.v_x, lx);
      ly = Math.min(p_point.v_y, ly);
      rx = Math.max(rx, p_point.v_x);
      uy = Math.max(uy, p_point.v_y);

      double tmp = p_point.v_x - p_point.v_y;
      ulx = Math.min(ulx, tmp);
      lrx = Math.max(lrx, tmp);

      tmp = p_point.v_x + p_point.v_y;
      llx = Math.min(llx, tmp);
      urx = Math.max(urx, tmp);
      }
   
   
   /**
    * calculates the smallest IntOctagon containing this octagon.
    */
   public ShapeTileOctagon to_octagon()
      {
      if (rx < lx || uy < ly || lrx < ulx || urx < llx)
         {
         return ShapeTileOctagon.EMPTY;
         }
      
      return new ShapeTileOctagon(
            Math.floor(lx), 
            Math.floor(ly), 
            Math.ceil(rx), 
            Math.ceil(uy), 
            Math.floor(ulx), 
            Math.ceil(lrx), 
            Math.floor(llx),
            Math.ceil(urx));
      }
   }