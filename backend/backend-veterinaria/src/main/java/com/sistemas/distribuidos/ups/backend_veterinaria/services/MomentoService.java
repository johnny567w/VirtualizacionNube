package com.sistemas.distribuidos.ups.backend_veterinaria.services;

import com.sistemas.distribuidos.ups.backend_veterinaria.models.Momento;
import com.sistemas.distribuidos.ups.backend_veterinaria.repositories.MomentoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class MomentoService {

    private final MomentoRepository repo;

    @Value("${app.upload.dir:/mnt/nfs-momentos}")
    private String uploadDir;

    public MomentoService(MomentoRepository repo) {
        this.repo = repo;
    }

    public Momento guardarMomento(String descripcion, MultipartFile archivo) throws IOException {
        String filename = UUID.randomUUID() + "_" + archivo.getOriginalFilename();
        Path destino = Paths.get(uploadDir).resolve(filename);
        Files.copy(archivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        Momento momento = new Momento();
        momento.setDescripcion(descripcion);
        momento.setUrl("/api/momentos/imagenes/" + filename);

        return repo.save(momento);
    }

    public List<Momento> listar() {
        return repo.findAll();
    }

    public ResponseEntity<Resource> cargarImagen(String filename) throws IOException {
        Path path = Paths.get(uploadDir).resolve(filename);
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }
        Resource resource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(resource);
    }
}
