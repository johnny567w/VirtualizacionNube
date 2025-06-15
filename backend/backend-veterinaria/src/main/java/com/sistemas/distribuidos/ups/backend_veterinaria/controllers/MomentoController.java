package com.sistemas.distribuidos.ups.backend_veterinaria.controllers;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Momento;
import com.sistemas.distribuidos.ups.backend_veterinaria.services.MomentoService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/momentos")
public class MomentoController {

    private final MomentoService servicio;

    public MomentoController(MomentoService servicio) {
        this.servicio = servicio;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Momento> subirMomento(
            @RequestParam("descripcion") String descripcion,
            @RequestParam("foto") MultipartFile archivo
    ) throws IOException {
        Momento m = servicio.guardarMomento(descripcion, archivo);
        return ResponseEntity.ok(m);
    }

    @GetMapping
    public List<Momento> listar() {
        return servicio.listar();
    }

    @GetMapping("/imagenes/{filename}")
    public ResponseEntity<Resource> verImagen(@PathVariable String filename) throws IOException {
        Path path = Paths.get("/mnt/nfs-momentos").resolve(filename);
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(path.toUri());

        String contentType = Files.probeContentType(path);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(resource);
    }

}
