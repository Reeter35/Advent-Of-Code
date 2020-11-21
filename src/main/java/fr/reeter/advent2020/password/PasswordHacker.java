package fr.reeter.advent2020.password;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordHacker {
    @Autowired
    private Logger logger;

    /**
     * Finds all available passwords in given range
     * @param min
     * @param max
     * @return
     */
    public List<String> findAllPasswords(final int min, final int max) {
        List<String> passwords = new ArrayList<>();

        for(int i=min; i<=max; i++) {
            if(isValid(i)) {
                logger.info("Available password: " + i);
                passwords.add(Integer.toString(i));
            }
        }

        return passwords;
    }

    /**
     * Check is current pwd meets criterias
     * This methods
     * @param pwd a string of length 6 (this is not checked by this method)
     * @return
     */
    public boolean isValid(int pwd) {
        /*if(pwd.length()!=6){
            return false;
        }*/

        // Checks if digits are sorted increasingly
        if( nth(pwd,5) < nth(pwd, 4) ||
            nth(pwd,4) < nth(pwd, 3) ||
            nth(pwd,3) < nth(pwd, 2) ||
            nth(pwd,2) < nth(pwd, 1) ||
            nth(pwd,1) < nth(pwd, 0)) {
            return false;
        }

        // Checks it there is at least two digits that are the same
        if( nth(pwd,5) != nth(pwd, 4) &&
            nth(pwd,4) != nth(pwd, 3) &&
            nth(pwd,3) != nth(pwd, 2) &&
            nth(pwd,2) != nth(pwd, 1) &&
            nth(pwd,1) != nth(pwd, 0)) {
            return false;
        }
        else {
            // there are at least two digits that are the same
            // now check if they are not contained in a greater group
            if(nth(pwd,5) == nth(pwd, 4) &&
                nth(pwd, 4) != nth(pwd, 3)) {
                return true;
            }
            if(nth(pwd, 5) != nth(pwd, 4) &&
                nth(pwd,4) == nth(pwd, 3) &&
                nth(pwd, 3) != nth(pwd, 2))  {
                return true;
            }
            if(nth(pwd, 4) != nth(pwd, 3) &&
                nth(pwd,3) == nth(pwd, 2) &&
                nth(pwd, 2) != nth(pwd, 1))  {
                return true;
            }
            if(nth(pwd, 3) != nth(pwd, 2) &&
                nth(pwd,2) == nth(pwd, 1) &&
                nth(pwd, 1) != nth(pwd, 0))  {
                return true;
            }
            if(nth(pwd, 2) != nth(pwd, 1) &&
                nth(pwd,1) == nth(pwd, 0))  {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the nth digit in given number (base 10)
     * @param number
     * @param index
     * @return
     */
    private int nth(int number, int index ) {
        String numberStr = String.valueOf(number);
        char[] digits = numberStr.toCharArray();
        return digits[index];
    }
}
