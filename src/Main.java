import util.ConexionBD;

import businessEntity.Asistencia;
import businessEntity.Empleado;
import businessEntity.Horario;
import businessEntity.Turno;
import businessEntity.Usuario;
import dao.AsistenciaDAO;
import dao.EmpleadoDAO;
import dao.HorarioDAO;
import dao.TurnoDAO;
import dao.UsuarioDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.getConnection()) {
            EmpleadoDAO empleadoDAO = new EmpleadoDAO(conn);

            Empleado e = new Empleado();
            e.setDni("12345679");
            e.setNombres("Juan");
            e.setApellidos("Pérez");
            e.setCargo("Médico");
            e.setArea("Cardiología");
            e.setEstado(true);

            empleadoDAO.insertar(e);

            empleadoDAO.obtenerTodos().forEach(emp -> {
                System.out.println(emp.getNombres() + " " + emp.getApellidos());
            });
            
            TurnoDAO turnoDAO = new TurnoDAO(conn);
            String str1 = "08:06";
            LocalTime time1 = LocalTime.parse(str1);
            String str2 = "14:30";
            LocalTime time2 = LocalTime.parse(str2);
            
            Turno t = new Turno();
            t.setNombre("Mañana");
            t.setDescripcion("Turno Matutino");
            t.setHoraInicio(time1);
            t.setHoraFin(time2);
            
            turnoDAO.insertar(t);
            
            turnoDAO.obtenerTodos().forEach(tur -> {
                System.out.println(tur.toString());
            });
            
            AsistenciaDAO asistenciaDAO = new AsistenciaDAO(conn);
            
            String str = "2025-06-01 08:05";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
            
            Asistencia a = new Asistencia();
            a.setIdEmpleado(1);
            a.setIdHorario(1);
            a.setFechaHora(dateTime);
            a.setEstado("Presente");
            a.setJustificacion("");
            
            asistenciaDAO.insertar(a);
            
            asistenciaDAO.obtenerTodos().forEach(asi -> {
                System.out.println(asi.toString());
            });
            
            HorarioDAO horarioDAO = new HorarioDAO(conn);
            str1 = "08:06";
            LocalTime time3 = LocalTime.parse(str1);
            str2 = "14:30";
            LocalTime time4 = LocalTime.parse(str2);
            String str3 = "2025-06-01";
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(str3, formato);
            
            Horario h = new Horario();
            h.setIdEmpleado(1);
            h.setIdTurno(1);
            h.setHoraInicio(time3);
            h.setHoraFin(time4);
            h.setFecha(date);
            h.setEstado(true);
            
            horarioDAO.insertar(h);
            
            horarioDAO.obtenerTodos().forEach(hor -> {
                System.out.println(hor.toString());
            });
                        
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);

            Usuario u = new Usuario();
            u.setIdEmpleado(1);
            u.setUsername("JuanitoPerez");
            u.setPassword("123456");
            u.setEstado(true);

            usuarioDAO.insertar(u);

            usuarioDAO.obtenerTodos().forEach(usu -> {
                System.out.println(usu.toString());
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}