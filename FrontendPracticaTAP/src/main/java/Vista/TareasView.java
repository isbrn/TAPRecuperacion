package Vista;


import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import Controlador.Conexion;
import Controlador.TareasEnListas;
import Modelo.TareasLista;

public class TareasView extends UI{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void init(VaadinRequest request) {


	    final VerticalLayout layout = new VerticalLayout();
		
		Conexion conexion = Conexion.getSingletonInstance();
		TareasEnListas tareasEnListas = TareasEnListas.getSingletonInstance();
		
       Label añadirTarea = new Label ("AÑADIR NUEVA TAREA");
        
		//Etiquetas
        final TextField nombre = new TextField();
        nombre.setCaption("Nombre: ");
        
        final TextField descripcion = new TextField();
        descripcion.setCaption("Descriocion: ");
        
        final TextField lista = new TextField();
        lista.setCaption("Lista: ");
		
		RadioButtonGroup<String> prioridad = new RadioButtonGroup<>("Prioridad: ");
		prioridad.setItems("Default", "Low", "Medium", "High");
		
		RadioButtonGroup<String> estado = new RadioButtonGroup<>("Estado: ");
		estado.setItems("Completado", "No completado");
		
		DateField fecha = new DateField();
		fecha.setDateFormat("dd/MM/yyy");
		
		//Botones
		Button btnAddTareas = new Button("AÑADIR TAREAS");
		Button btnVerTareas = new Button("VER TAREAS");
		Button btnAñadirTarea = new Button("AÑADIR");
		Button btnBorrarTarea = new Button ("BORRAR");
		
		
		//Código para mostrar tareas
		conexion.leerLista();
		conexion.leerTareas();
		tareasEnListas.organizar();

		layout.addComponents(btnAddTareas, btnVerTareas);
		
		btnAddTareas.addClickListener(e ->{
			layout.removeComponent(btnAddTareas);
        	layout.removeComponent(btnVerTareas);
			layout.addComponents(añadirTarea, nombre, descripcion, lista, prioridad, fecha, estado, btnAñadirTarea);
		});
		
		btnVerTareas.addClickListener(e ->{
			layout.removeComponent(btnAddTareas);
        	layout.removeComponent(btnVerTareas);
			layout.addComponents(btnBorrarTarea);
		});
	
		for (TareasLista tarea : tareasEnListas.tareas) {
			

			btnBorrarTarea.setId(Integer.toString(tarea.idTarea));
			
			btnBorrarTarea.addClickListener(e ->{
				Conexion conexionBorrar = Conexion.getSingletonInstance();
				
				layout.removeComponent(btnBorrarTarea);
				layout.addComponents(btnBorrarTarea);
				
	        	try {
					if(conexionBorrar.borrarTarea(btnBorrarTarea.getId().toString())){
						layout.addComponent(new Label("Tarea borrada correctamente"));
						layout.removeAllComponents();
					}
					else {
						layout.addComponent(new Label("Se ha producido un error y la tarea no ha sido borrada correctamente"));
					}
	        	}
	        	catch(Exception exception) {
	        		exception.printStackTrace();
	        		layout.addComponent(new Label("Se ha producido un error y la tarea no ha sido borrada correctamente"));
	        	}
			});
		}
		
		btnAñadirTarea.addClickListener(e -> {
			layout.removeComponent(añadirTarea);
        	layout.removeComponent(nombre);
        	layout.removeComponent(descripcion);
        	layout.removeComponent(lista);
        	layout.removeComponent(prioridad);
        	layout.removeComponent(fecha);
        	layout.removeComponent(estado);
        	layout.removeComponent(btnAñadirTarea);
        	
        	Conexion conexionTarea = Conexion.getSingletonInstance();
        	
        	try {
        		if(conexionTarea.crearTarea(nombre.getValue(), descripcion.getValue(), prioridad.getValue(), estado.getValue(), fecha.getValue(), lista.getValue())){

        			layout.addComponent(new Label("Tarea creada correctamente"));
        			UI.getCurrent().getPage().reload();
        		}
        		else
        			layout.addComponent(new Label("Se ha producido un error y la tarea no ha sido creada"));
        	} 
        	catch (Exception exception) {
				exception.printStackTrace();
				layout.addComponent(new Label("Se ha producido un error y la tarea no ha sido creada"));
			}
        });
		
		
		if (!tareasEnListas.tareas.isEmpty()) {
			for(String organizarLista : tareasEnListas.listas) {
				
			}
		}
	
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
