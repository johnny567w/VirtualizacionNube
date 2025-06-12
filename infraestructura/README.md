
# Parte 1: Configuración del Entorno - Proyecto de Virtualización en la Nube

Este documento describe la configuración de la infraestructura necesaria para implementar una aplicación de tres capas en Google Cloud Platform.

##  Infraestructura creada

### 1. Máquinas virtuales creadas

| Nombre         | Función          | SO             | Zona           | IP Interna     | IP Pública | Etiqueta     |
|----------------|------------------|----------------|----------------|----------------|------------|--------------|
| frontend-vm     | Servidor web (Angular/React) | Ubuntu 22.04 LTS | us-central1-a   | 10.128.0.X  | Sí         | `frontend`   |
| backend-vm      | Lógica de negocio (API REST) | Ubuntu 22.04 LTS | us-central1-a   | 10.128.0.X  | No          | `backend`    |
| db-vm           | Base de datos PostgreSQL     | Ubuntu 22.04 LTS | us-central1-a   | 10.128.0.X  | No          | `db`         |
| nfs-server-vm   | Almacenamiento compartido    | Ubuntu 22.04 LTS | us-central1-a   | 10.128.0.5  | No          | `nfs-server` |

*Nota: Las IPs internas pueden cambiar al reiniciar si no son fijas. Se deben verificar al momento de conexión.*

---

##  Reglas de firewall creadas

| Nombre                  | Dirección | Objetivo (etiqueta) | Protocolo/puerto | Fuente             | Descripción                       |
|-------------------------|-----------|----------------------|------------------|---------------------|-----------------------------------|
| permitir-frontend-http-https | Ingress   | `frontend`           | TCP: 80, 443     | 0.0.0.0/0           | Acceso web al frontend            |
| frontend-a-backend      | Ingress   | `backend`            | TCP: 3000/8080   | etiqueta: `frontend` | Comunicación frontend ↔ backend  |
| backend-a-db            | Ingress   | `db`                 | TCP: 5432        | etiqueta: `backend`  | Comunicación backend ↔ BD         |
| permitir-nfs            | Ingress   | `nfs-server`         | TCP: 2049        | 10.128.0.0/20       | Acceso NFS desde red interna      |

---

## 📁 Almacenamiento Compartido NFS

-  Servidor: `nfs-server-vm`
-  Directorio compartido: `/srv/shared-data`
-  Acceso desde: `frontend-vm`, `backend-vm`
-  Punto de montaje en las demás VMs: `/mnt/shared-data`
-  Pruebas exitosas de lectura/escritura cruzada

---

##  Comandos clave utilizados


### Configuración `/etc/exports`

```
/srv/shared-data 10.128.0.0/20(rw,sync,no_subtree_check,no_root_squash)
```

### Montaje en clientes

```bash
sudo apt update
sudo apt install nfs-common -y
sudo mkdir -p /mnt/shared-data
sudo mount -o nolock 10.128.0.5:/srv/shared-data /mnt/shared-data
```

---

