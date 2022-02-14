package com.ibermatica.saga.camas;

import com.ibermatica.saga.model.DatosIngresoDTO;
import com.ibermatica.saga.model.HistorialDTO;
import com.ibermatica.saga.model.PacienteDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("camas")
public class CamasController {

    @Autowired
    private CamasRepository camasRepository;

    @GetMapping
    public List<Cama> getAllCamas() {
        return camasRepository.findAll();
    }

    @PostMapping("asignar")
    public Cama ubicarPaciente(@RequestBody final DatosUbicacionPaciente datosUbicacionPaciente) throws Exception {
        // Mock del proceso devolviendo una cama libre cualquiera
        final Cama cama = camasRepository
                .findAll()
                .stream()
                .filter(item -> Cama.EstadoOcupacion.DISPONIBLE == item.getOcupacion())
                .findFirst()
                .orElseThrow(Exception::new);

        cama.setOcupacion(Cama.EstadoOcupacion.RESERVADO);
        return camasRepository.save(cama);
    }

    @PatchMapping("confirmar-reserva/{id}")
    public void confirmarReserva(@PathVariable final Integer id) {
        camasRepository.findById(id).ifPresent(
                cama -> {
                    cama.setOcupacion(Cama.EstadoOcupacion.OCUPADO);
                    camasRepository.save(cama);
                }
        );
    }

    @PatchMapping("liberar-reserva/{id}")
    public void liberarReserva(@PathVariable final Integer id) {
        camasRepository.findById(id).ifPresent(
                cama -> {
                    cama.setOcupacion(Cama.EstadoOcupacion.DISPONIBLE);
                    camasRepository.save(cama);
                }
        );
    }

    @Data
    static class DatosUbicacionPaciente {
        private DatosIngresoDTO datosIngreso;
        private PacienteDTO paciente;
        private HistorialDTO historial;
    }
}
