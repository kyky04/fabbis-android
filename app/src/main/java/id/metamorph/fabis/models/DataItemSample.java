package id.metamorph.fabis.models;

import com.google.gson.annotations.SerializedName;

public class DataItemSample {

	@SerializedName("jabatan_dokter_id")
	private Object jabatanDokterId;

	@SerializedName("nama_lengkap")
	private String namaLengkap;

	@SerializedName("photo")
	private Object photo;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("created_by")
	private int createdBy;

	@SerializedName("aktif")
	private boolean aktif;

	@SerializedName("alamat")
	private Object alamat;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("kode")
	private String kode;

	@SerializedName("updated_by")
	private Object updatedBy;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private Object deskripsi;

	@SerializedName("no_tlpn")
	private Object noTlpn;

	@SerializedName("spesialisasi_id")
	private int spesialisasiId;

	public void setJabatanDokterId(Object jabatanDokterId){
		this.jabatanDokterId = jabatanDokterId;
	}

	public Object getJabatanDokterId(){
		return jabatanDokterId;
	}

	public void setNamaLengkap(String namaLengkap){
		this.namaLengkap = namaLengkap;
	}

	public String getNamaLengkap(){
		return namaLengkap;
	}

	public void setPhoto(Object photo){
		this.photo = photo;
	}

	public Object getPhoto(){
		return photo;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public void setAktif(boolean aktif){
		this.aktif = aktif;
	}

	public boolean isAktif(){
		return aktif;
	}

	public void setAlamat(Object alamat){
		this.alamat = alamat;
	}

	public Object getAlamat(){
		return alamat;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setUpdatedBy(Object updatedBy){
		this.updatedBy = updatedBy;
	}

	public Object getUpdatedBy(){
		return updatedBy;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeskripsi(Object deskripsi){
		this.deskripsi = deskripsi;
	}

	public Object getDeskripsi(){
		return deskripsi;
	}

	public void setNoTlpn(Object noTlpn){
		this.noTlpn = noTlpn;
	}

	public Object getNoTlpn(){
		return noTlpn;
	}

	public void setSpesialisasiId(int spesialisasiId){
		this.spesialisasiId = spesialisasiId;
	}

	public int getSpesialisasiId(){
		return spesialisasiId;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"jabatan_dokter_id = '" + jabatanDokterId + '\'' + 
			",nama_lengkap = '" + namaLengkap + '\'' + 
			",photo = '" + photo + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",aktif = '" + aktif + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",kode = '" + kode + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",id = '" + id + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			",no_tlpn = '" + noTlpn + '\'' + 
			",spesialisasi_id = '" + spesialisasiId + '\'' + 
			"}";
		}
}