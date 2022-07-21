package tn.esprit.pidev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Utilisateur;
import tn.esprit.pidev.services.api.UtilisateurService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UtilisateurService utilisateurService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return utilisateurService.findUtilisateurByEmail(s);
    }
}
