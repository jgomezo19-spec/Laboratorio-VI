package gt.edu.laboratorio_vi.reservas;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@RequestBody Reserva reserva) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reservaService.crear(reserva));
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> obtenerTodos() {
        return ResponseEntity.ok(reservaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerPorId(@PathVariable Long id) {

        Reserva reserva = reservaService.obtenerPorId(id);

        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizar(
            @PathVariable Long id,
            @RequestBody Reserva reserva) {

        Reserva actualizada = reservaService.actualizar(id, reserva);

        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Reserva> cancelar(@PathVariable Long id) {

        boolean cancelada = reservaService.cancelar(id);

        if (!cancelada) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                reservaService.obtenerPorId(id)
        );
    }
}