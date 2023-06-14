package pe.edu.upeu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.edu.upeu.coon.ConnS;
import pe.edu.upeu.modelo.ResultadoTO;

public class ResultadoDao implements ResultadoDaoI {
    ConnS instance = ConnS.getInstance();
    Connection conexion = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<ResultadoTO> listarResultados() {
        List<ResultadoTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM resultados";
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ResultadoTO to = new ResultadoTO();
                to.setIdResultado(rs.getInt("id_resultado"));
                to.setNombrePartida(rs.getString("nombre_partida"));
                to.setNombreJugador1(rs.getString("nombre_jugador1"));
                to.setNombreJugador2(rs.getString("nombre_jugador2"));
                to.setGanador(rs.getString("ganador"));
                to.setPunto(rs.getInt("punto"));
                to.setEstado(rs.getString("estado"));
                lista.add(to);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public int actualizarResultado(ResultadoTO re) {
        int exec = 0;
        String sql = "UPDATE resultados SET nombre_partida=?, nombre_jugador1=?, nombre_jugador2=?, ganador=?, punto=?, estado=? WHERE id_resultado=?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, re.getNombrePartida());
            ps.setString(2, re.getNombreJugador1());
            ps.setString(3, re.getNombreJugador2());
            ps.setString(4, re.getGanador());
            ps.setInt(5, re.getPunto());
            ps.setString(6, re.getEstado());
            ps.setInt(7, re.getIdResultado());
            exec = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exec;
    }


    @Override
    public int crearResultado(ResultadoTO re) {
        int exec = 0;
        int id = 0;
        String sql = "INSERT INTO resultados(nombre_partida, nombre_jugador1, nombre_jugador2, ganador, punto, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, re.getNombrePartida());
            ps.setString(2, re.getNombreJugador1());
            ps.setString(3, re.getNombreJugador2());
            ps.setString(4, re.getGanador());
            ps.setInt(5, re.getPunto());
            ps.setString(6, re.getEstado());
            exec = ps.executeUpdate();
            
            // Obtener el último ID insertado.
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) { 
                id=rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    @Override
    public void borrarTodosLosResultados() {
    String sql = "DELETE FROM resultados";
    try {
        ps = conexion.prepareStatement(sql);
        ps.executeUpdate();
        System.out.println("Se borraron todos los resultados de la tabla 'resultados'");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    
    
    
    
}

