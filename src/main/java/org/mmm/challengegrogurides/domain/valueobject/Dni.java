package org.mmm.challengegrogurides.domain.valueobject;

import org.mmm.challengegrogurides.domain.exception.InvalidDniException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Dni {

    public static final String FORMAT_DNI = "[0-9]{8}[A-Za-z]{1}";

    private final String value;
    public Dni(String strDni){
        try{
            Pattern p = Pattern.compile(FORMAT_DNI);
            if(!p.matcher(strDni).matches()){
                throw new InvalidDniException("Error");
            }
            this.value = strDni;
        }catch (Exception e){
            throw new InvalidDniException(String.format("Dni has not the format: %s", FORMAT_DNI));
        }
    }

    public String value(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dni dni = (Dni) o;
        return Objects.equals(value, dni.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Dni{value='" + value + "'" +"}";
    }
}
