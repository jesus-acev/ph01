package cl.ufro.dci.perfecthostapi.auth.service;

import cl.ufro.dci.perfecthostapi.domain.Cuenta;
import cl.ufro.dci.perfecthostapi.domain.Rol;
import cl.ufro.dci.perfecthostapi.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("Cuenta")
    private CuentaRepository<Cuenta> userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cuenta user = userRepo.findByCueCorreo(username);
        if (user == null) {
            throw new UsernameNotFoundException("Correo no encontrado");
        }
        List<GrantedAuthority> authorities = getAuthorities(user);
        return new User(user.getCueCorreo(), user.getCueClave(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> getAuthorities(Cuenta cuenta) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Rol rol : cuenta.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(rol.getRoleName()));
        }
        return authorities;
    }
}
