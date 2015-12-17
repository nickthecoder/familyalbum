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

package familyalbum.util;

import java.util.LinkedList;

/**
  Creates thumbnails in a seperate thread
*/

public class JobQueue
  implements Runnable
{

  private Thread _thread;

  private LinkedList<JobQueueEntry> _queue;

  public JobQueue()
  {
    _queue = new LinkedList<JobQueueEntry>();
  }

  public void add( JobQueueEntry entry )
  {
    if ( _queue.contains( entry ) ) {
      // Do nothing
    } else {
      _queue.add( entry );
      if ( ( _thread == null ) || ( ! _thread.isAlive() ) ) {
        _thread = new Thread( this );
        _thread.start();
      }
    }
  }

  public void run()
  {
    //System.out.println( "Starting a new thread" );

    _thread = Thread.currentThread();

    while( ! _queue.isEmpty() ) {
      JobQueueEntry item = (JobQueueEntry) _queue.getFirst();
      _queue.removeFirst();

      item.run();
    }

  }

}

