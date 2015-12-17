/*
 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package uk.co.nickthecoder.familyalbum.model;

import java.util.Comparator;

public class SipAgeComparator implements Comparator<SubjectInPhoto>
{

    public SipAgeComparator()
    {
    }

    public int compare(SubjectInPhoto sipA, SubjectInPhoto sipB)
    {
        Integer yearA = sipA.getPhoto().getYear();
        Integer yearB = sipB.getPhoto().getYear();

        if (yearA == null) {
            if (yearB == null) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (yearB == null) {
                return -1;
            } else {
                return yearA.compareTo(yearB);
            }
        }

    }

    // -------------------- [[Test / Debug]] --------------------

}

// ---------- End Of Class SipAgeComparator ----------

