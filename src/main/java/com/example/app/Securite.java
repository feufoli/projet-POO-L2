package com.example.app;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Securite {
    String mdp ;
    byte[] mdp_int ;

    public Securite(String mdp){
        this.mdp = mdp ;
        MessageDigest message ;
        try {
            message = MessageDigest.getInstance("SHA-256") ;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        mdp_int = message.digest(mdp.getBytes(StandardCharsets.UTF_8));
    }

    public byte[] getMdp_int() {
        return mdp_int;
    }
}
