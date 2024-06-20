package BackEndC3.ClinicaOdontologica.Security;

import BackEndC3.ClinicaOdontologica.Entity.Usuario;
import BackEndC3.ClinicaOdontologica.Entity.UsuarioRole;
import BackEndC3.ClinicaOdontologica.Repository.UsuarioRepository;
import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Datos implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passcode ="admin123";
        String passEncryption= bCryptPasswordEncoder.encode(passcode);
        Usuario usuario = new Usuario("Usuario", "usuario123", "usuario@gmail.com", passEncryption, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);

    }
}
