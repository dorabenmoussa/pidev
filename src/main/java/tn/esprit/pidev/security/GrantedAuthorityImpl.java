package tn.esprit.pidev.security;

import org.springframework.security.core.GrantedAuthority;
import tn.esprit.pidev.entities.Utilisateur;

public class GrantedAuthorityImpl implements GrantedAuthority {

    private Utilisateur utilisateur;

    public GrantedAuthorityImpl(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String getAuthority() {
        return this.utilisateur.getRole().toString();
    }
}
