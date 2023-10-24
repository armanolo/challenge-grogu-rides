package org.mmm.challengegrogurides.domain.valueobject;

import org.mmm.challengegrogurides.domain.exception.InvalidUserNameException;

import java.util.Objects;
import java.util.regex.Pattern;

public class UserName {
    public static final String FORMAT_NAME = "\\b[A-Za-z]{2,}(?:\\s+[A-Za-z]{2,})+\\b";
    public static final String MESSAGE_ERROR = "%s is an invalid name, at least with two words (+4letters)";

    private final String value;

    public UserName(String fullName){
        try{
            Pattern p = Pattern.compile(FORMAT_NAME);
            if(!p.matcher(fullName).matches()){
                throw new InvalidUserNameException("Error");
            }
            this.value = fullName;
        }catch (Exception e){
            throw new InvalidUserNameException(
                    String.format(MESSAGE_ERROR,fullName)
            );
        }
    }

    public String value(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserName userName = (UserName) o;
        return Objects.equals(value, userName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "UserName{" +
                "value='" + value + '\'' +
                '}';
    }
}
