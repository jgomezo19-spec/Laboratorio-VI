package gt.edu.laboratorio_vi.reservas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final List<Reserva> reservas = new ArrayList<>();
    private Long siguienteId = 1L;

    public List<Reserva> obtenerTodos() {
        return reservas;
    }

    public Reserva obtenerPorId(Long id) {
        return reservas.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Reserva crear(Reserva reserva) {
        reserva.setId(siguienteId++);
        reservas.add(reserva);
        return reserva;
    }

    public Reserva actualizar(Long id, Reserva datos) {
        Reserva reserva = obtenerPorId(id);

        if (reserva == null) {
            return null;
        }

        reserva.setNombreCliente(datos.getNombreCliente());
        reserva.setHabitacion(datos.getHabitacion());
        reserva.setFechaEntrada(datos.getFechaEntrada());
        reserva.setFechaSalida(datos.getFechaSalida());
        reserva.setEstado(datos.getEstado());

        return reserva;
    }

    public boolean cancelar(Long id) {
        Reserva reserva = obtenerPorId(id);

        if (reserva == null) {
            return false;
        }

        reserva.setEstado("CANCELADA");
        return true;
    }
}