package exceptions;

import static defines.Errors.INVALID_VOTE;


public class InvalidVote extends Exception {
    public InvalidVote(){ super(INVALID_VOTE);}
}
