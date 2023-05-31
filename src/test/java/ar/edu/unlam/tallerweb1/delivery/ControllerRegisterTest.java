package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegister;
import ar.edu.unlam.tallerweb1.domain.usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerRegisterTest {

    private ControllerRegister controllerRegister;
    private ServicioRegister ServicioRegister;
    private HttpServletRequest request;
    private HttpSession sesion;

    @Before
    public void init() {
        ServicioRegister = mock(ServicioRegister.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        controllerRegister = new ControllerRegister(this.ServicioRegister);

    }

    @Test
    public void dadoUnUsuarioQueSeQuiereCrear() {

        ModelAndView vista = controllerRegister.irARegister();

        assertThat(vista.getViewName()).isEqualTo("registroUsuario");

    }

    @Test
    public void aPartirDeDatosValidosDeberiaRedireccionarmeAlLogin() {

        DatosRegister datosRegister = new DatosRegister();

        String email = "pantunez@alumno.unlam.edu.ar";
        String password = "pablito";
        String verificatedPassword = "pablito";
        String role = "alumno";

        datosRegister.setEmail(email);
        datosRegister.setPassword(password);
        datosRegister.setVerificatedPassword(verificatedPassword);
        datosRegister.setRole(role);

        when(ServicioRegister.consultarUsuario(any())).thenReturn(null);

        ModelAndView vista = controllerRegister.registrarme(datosRegister);

        //asserts
        assertThat(datosRegister).isNotNull();
        assertThat(datosRegister.getPassword()).isNotNull();
        assertThat(datosRegister.getVerificatedPassword()).isNotNull();
        assertThat(datosRegister.getPassword()).isEqualTo(datosRegister.getVerificatedPassword());
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isEqualTo("redirect:/login");
    }

    @Test
    public void aPartirDeEncontrarElMailDeberiaDarErrorDeYaExiste() {

        DatosRegister datosRegister = new DatosRegister();
        String email = "pantunez@alumno.unlam.edu.ar";
        String password = "pablito";
        String verificatedPassword = "pablito";
        String role = "alumno";
        datosRegister.setEmail(email);
        datosRegister.setPassword(password);
        datosRegister.setVerificatedPassword(verificatedPassword);
        datosRegister.setRole(role);

        Usuario user = new Usuario();

        user.setEmail("pantunez@alumno.unlam.edu.ar");
        user.setActivo(true);
        user.setRol("alumno");
        user.setPassword("pablito");
        user.setId(1L);

        ModelMap model = new ModelMap();
        model.put("error", "El mail ingresado ya existe en nuestro sistema");

        when(ServicioRegister.consultarUsuario(any())).thenReturn(user);

        ModelAndView vista = controllerRegister.registrarme(datosRegister);

        //asserts
        assertThat(datosRegister).isNotNull();
        assertThat(datosRegister.getPassword()).isNotNull();
        assertThat(datosRegister.getVerificatedPassword()).isNotNull();
        assertThat(datosRegister.getPassword()).isEqualTo(datosRegister.getVerificatedPassword());
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isEqualTo("register");

        assertThat(vista.getModel()).isEqualTo(model);
    }

    @Test
    public void aPartirDeContrasenasDistintasDeberiaMostrarRegister() {
        //preparacion de los datos y servicios
        DatosRegister datosRegister = new DatosRegister();
        String email = "pantunez@alumno.unlam.edu.ar";
        String password = "pablito";
        String verificatedPassword = "pablito2";
        String role = "alumno";
        datosRegister.setEmail(email);
        datosRegister.setPassword(password);
        datosRegister.setVerificatedPassword(verificatedPassword);
        datosRegister.setRole(role);
        ModelMap model = new ModelMap();
        model.put("error", "Las contraseñas no coinciden");

        //llamado al metodo
        ModelAndView vista = controllerRegister.registrarme(datosRegister);

        //asserts
        assertThat(datosRegister).isNotNull();
        assertThat(datosRegister.getPassword()).isNotNull();
        assertThat(datosRegister.getVerificatedPassword()).isNotNull();
        assertThat(datosRegister.getPassword()).isNotEqualTo(datosRegister.getVerificatedPassword());
        assertThat(vista).isNotNull();
        assertThat(vista.getViewName()).isNotNull();
        assertThat(vista.getViewName()).isNotEmpty();
        assertThat(vista.getViewName()).isEqualTo("register");
        assertThat(vista.getModelMap()).isNotNull();
        assertThat(vista.getModelMap()).isNotEmpty();
        assertThat(vista.getModelMap()).isEqualTo(model);
    }

}
