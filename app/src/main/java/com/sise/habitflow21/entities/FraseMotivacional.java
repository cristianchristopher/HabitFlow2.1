package com.sise.habitflow21.entities;

import java.util.Date;

public class FraseMotivacional {
    private Integer idFrase;
    private String frase;
    private String autor;
    private Date fechaCreacion;
    private Boolean estadoAuditoria;

    public Integer getIdFrase() {
        return idFrase;
    }

    public void setIdFrase(Integer idFrase) {
        this.idFrase = idFrase;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getEstadoAuditoria() {
        return estadoAuditoria;
    }

    public void setEstadoAuditoria(Boolean estadoAuditoria) {
        this.estadoAuditoria = estadoAuditoria;
    }
}
