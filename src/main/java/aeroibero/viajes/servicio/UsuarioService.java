package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Rol;
import aeroibero.viajes.modelos.Usuario;
import aeroibero.viajes.repositorio.UsuarioLoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")
@Slf4j
public class UsuarioService implements UserDetailsService {
    public static String username;

    @Autowired
    private UsuarioLoginRepository usarioRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usarioRepo.findByUsername(email);

        username = usuario.getUsername();

        System.out.println(username);

        if(usuario == null){
            throw new UsernameNotFoundException(email);
        }

        ArrayList<GrantedAuthority> roles =  new ArrayList<GrantedAuthority>();

        for (Rol rol: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

}
