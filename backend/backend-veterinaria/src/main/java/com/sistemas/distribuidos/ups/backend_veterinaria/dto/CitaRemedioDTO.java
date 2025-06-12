package com.sistemas.distribuidos.ups.backend_veterinaria.dto;

public class CitaRemedioDTO {

    private Long id;
    private Long citaId;
    private Long remedioId;
    private Integer dosis;

    public CitaRemedioDTO() {
    }

    public CitaRemedioDTO(Long id, Long citaId, Long remedioId,   Integer   dosis) {
        this.id = id;
        this.citaId = citaId;
        this.remedioId = remedioId;
        this.dosis = dosis;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCitaId() {
        return citaId;
    }

    public Long getRemedioId() {
        return remedioId;
    }
    public void setRemedioId(Long remedioId) {
        this.remedioId = remedioId;
    }

    public Integer getDosis() {
        return dosis;
    }
    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }
    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }


}
