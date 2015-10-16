package edu.catysonpurdue.rotccadetprofiler;

/**
 * Created by Cody on 10/14/2015.
 */

    interface GetUserCallback {

        /**
         * Invoked when background task is completed
         */

        public abstract void done(User returnedUser);
}
