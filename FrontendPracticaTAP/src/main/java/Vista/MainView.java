package Vista;


import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.VaadinServletConfiguration;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import Controlador.Conexion;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */

public class MainView extends UI{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        Conexion conexion = Conexion.getSingletonInstance();
        
        Label inicioSesion = new Label ("INICIAR SESIÓN");
        Label registroNuevoUsuario = new Label ("REGISTRAR NUEVO USUARIO");
        Label usuarioCreado = new Label ("Usuario creado correctamente!");
        
        final TextField usuario = new TextField();
        usuario.setCaption("Usuario:");
        final TextField contraseña = new TextField();
        contraseña.setCaption("Contraseña:");

        Button btnConectar = new Button("CONECTARSE");
        Button btnRegistrarse = new Button("REGISTRARSE");
        Button btnRegistroNuevo = new Button ("REGISTRARSE");
        
        btnConectar.addClickListener(e -> {
        	layout.removeComponent(inicioSesion);
        	layout.removeComponent(usuario);
        	layout.removeComponent(contraseña);
        	layout.removeComponent(btnConectar);
        	layout.removeComponent(btnRegistrarse);
        	
        	try {
        		if(conexion.login(usuario.getValue(), contraseña.getValue())) {
        			layout.addComponent(new Label("Sesión iniciada con el usuario " + usuario.getValue()));
        			TareasView tareas = new TareasView();
        			layout.addComponent(tareas);
        		}
        		else
        			layout.addComponent(new Label("Datos de inicio de sesión incorrectos"));
        	} 
        	catch (Exception exception) {
				exception.printStackTrace();
			}
        });
        
        btnRegistrarse.addClickListener(e -> {
        	layout.removeComponent(inicioSesion);
        	layout.removeComponent(usuario);
        	layout.removeComponent(contraseña);
        	layout.removeComponent(btnConectar);
        	layout.removeComponent(btnRegistrarse);
            layout.addComponents(registroNuevoUsuario, usuario, contraseña, btnRegistroNuevo);
        });
               
        //lo que se muestra al abrir el localhost:8080
        layout.addComponents(inicioSesion, usuario, contraseña, btnConectar, btnRegistrarse);		 
        
        btnRegistroNuevo.addClickListener(e -> {
        	layout.removeComponent(registroNuevoUsuario);
        	layout.removeComponent(usuario);
        	layout.removeComponent(contraseña);
        	layout.removeComponent(btnRegistroNuevo);
        	
            try {
            	if(conexion.crearUsuario(usuario.getValue(), contraseña.getValue())) {
            		layout.addComponent(new Label("Usuario creado correctamente"));
            		layout.addComponents(usuarioCreado, inicioSesion, usuario, contraseña, btnConectar, btnRegistrarse);
            	}
            	else
            		layout.addComponent(new Label("Ya existe un usuario creado con el nombre introducido"));
            		
            }
            catch(Exception exception) {
				exception.printStackTrace();
            	
            }
        });
        
        if(conexion.getUsuario() != null) {
        	layout.addComponent(new Label("Sesión ya iniciada"));
			TareasView tareas = new TareasView();
			layout.addComponent(tareas);
        }

        setContent(layout);
    }
	
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainView.class, productionMode = false)
    public static class MainViewServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
    }
}
