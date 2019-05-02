package id.metamorph.fabis.models.pemain;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import id.metamorph.fabis.models.nilai.DataItemNilai;

public class DataItemPemain implements Serializable {

    @SerializedName("nilai")
    private DataItemNilai nilai;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("created_by")
    private Object createdBy;

    @SerializedName("nim")
    private String nim;

    @SerializedName("nama")
    private String nama;

    @SerializedName("foto")
    private String foto;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("berat")
    private String berat;

    @SerializedName("updated_by")
    private int updatedBy;

    @SerializedName("id")
    private int id;

    @SerializedName("posisi")
    private String posisi;

    @SerializedName("tinggi")
    private String tinggi;

    @SerializedName("status")
    private String status;

    @SerializedName("terpilih")
    private String terpilih;

    public String getTerpilih() {
        return terpilih;
    }

    public void setTerpilih(String terpilih) {
        this.terpilih = terpilih;
    }

    private boolean seleksi;

    public boolean isSeleksi() {
        return seleksi;
    }

    public void setSeleksi(boolean seleksi) {
        this.seleksi = seleksi;
    }

    public void setNilai(DataItemNilai nilai) {
        this.nilai = nilai;
    }

    public DataItemNilai getNilai() {
        return nilai;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getBerat() {
        return berat;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    public String getTinggi() {
        return tinggi;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "nilai = '" + nilai + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",created_by = '" + createdBy + '\'' +
                        ",nim = '" + nim + '\'' +
                        ",nama = '" + nama + '\'' +
                        ",foto = '" + foto + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",berat = '" + berat + '\'' +
                        ",updated_by = '" + updatedBy + '\'' +
                        ",id = '" + id + '\'' +
                        ",posisi = '" + posisi + '\'' +
                        ",tinggi = '" + tinggi + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}