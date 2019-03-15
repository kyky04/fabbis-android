package id.metamorph.fabis.models.nilai;


import com.google.gson.annotations.SerializedName;


public class DataItemNilai {

	@SerializedName("defence")
	private String defence;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_pemain")
	private String idPemain;

	@SerializedName("speed")
	private String speed;

	@SerializedName("pemain")
	private Pemain pemain;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("dribble7")
	private String dribble7;

	@SerializedName("pass1")
	private String pass1;

	@SerializedName("dribble6")
	private String dribble6;

	@SerializedName("pass2")
	private String pass2;

	@SerializedName("dribble5")
	private String dribble5;

	@SerializedName("pass3")
	private String pass3;

	@SerializedName("id")
	private int id;

	@SerializedName("dribble4")
	private String dribble4;

	@SerializedName("pass4")
	private String pass4;

	@SerializedName("dribble8")
	private String dribble8;

	@SerializedName("dribble3")
	private String dribble3;

	@SerializedName("dribble2")
	private String dribble2;

	@SerializedName("dribble1")
	private String dribble1;

	@SerializedName("shooting2")
	private String shooting2;

	@SerializedName("shooting1")
	private String shooting1;

	@SerializedName("serangan")
	private String serangan;

	@SerializedName("created_by")
	private Object createdBy;

	@SerializedName("shooting4")
	private String shooting4;

	@SerializedName("updated_by")
	private Object updatedBy;

	@SerializedName("shooting3")
	private String shooting3;

	public void setDefence(String defence){
		this.defence = defence;
	}

	public String getDefence(){
		return defence;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setIdPemain(String idPemain){
		this.idPemain = idPemain;
	}

	public String getIdPemain(){
		return idPemain;
	}

	public void setSpeed(String speed){
		this.speed = speed;
	}

	public String getSpeed(){
		return speed;
	}

	public void setPemain(Pemain pemain){
		this.pemain = pemain;
	}

	public Pemain getPemain(){
		return pemain;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setDribble7(String dribble7){
		this.dribble7 = dribble7;
	}

	public String getDribble7(){
		return dribble7;
	}

	public void setPass1(String pass1){
		this.pass1 = pass1;
	}

	public String getPass1(){
		return pass1;
	}

	public void setDribble6(String dribble6){
		this.dribble6 = dribble6;
	}

	public String getDribble6(){
		return dribble6;
	}

	public void setPass2(String pass2){
		this.pass2 = pass2;
	}

	public String getPass2(){
		return pass2;
	}

	public void setDribble5(String dribble5){
		this.dribble5 = dribble5;
	}

	public String getDribble5(){
		return dribble5;
	}

	public void setPass3(String pass3){
		this.pass3 = pass3;
	}

	public String getPass3(){
		return pass3;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDribble4(String dribble4){
		this.dribble4 = dribble4;
	}

	public String getDribble4(){
		return dribble4;
	}

	public void setPass4(String pass4){
		this.pass4 = pass4;
	}

	public String getPass4(){
		return pass4;
	}

	public void setDribble8(String dribble8){
		this.dribble8 = dribble8;
	}

	public String getDribble8(){
		return dribble8;
	}

	public void setDribble3(String dribble3){
		this.dribble3 = dribble3;
	}

	public String getDribble3(){
		return dribble3;
	}

	public void setDribble2(String dribble2){
		this.dribble2 = dribble2;
	}

	public String getDribble2(){
		return dribble2;
	}

	public void setDribble1(String dribble1){
		this.dribble1 = dribble1;
	}

	public String getDribble1(){
		return dribble1;
	}

	public void setShooting2(String shooting2){
		this.shooting2 = shooting2;
	}

	public String getShooting2(){
		return shooting2;
	}

	public void setShooting1(String shooting1){
		this.shooting1 = shooting1;
	}

	public String getShooting1(){
		return shooting1;
	}

	public void setSerangan(String serangan){
		this.serangan = serangan;
	}

	public String getSerangan(){
		return serangan;
	}

	public void setCreatedBy(Object createdBy){
		this.createdBy = createdBy;
	}

	public Object getCreatedBy(){
		return createdBy;
	}

	public void setShooting4(String shooting4){
		this.shooting4 = shooting4;
	}

	public String getShooting4(){
		return shooting4;
	}

	public void setUpdatedBy(Object updatedBy){
		this.updatedBy = updatedBy;
	}

	public Object getUpdatedBy(){
		return updatedBy;
	}

	public void setShooting3(String shooting3){
		this.shooting3 = shooting3;
	}

	public String getShooting3(){
		return shooting3;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"defence = '" + defence + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_pemain = '" + idPemain + '\'' + 
			",speed = '" + speed + '\'' + 
			",pemain = '" + pemain + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",dribble7 = '" + dribble7 + '\'' + 
			",pass1 = '" + pass1 + '\'' + 
			",dribble6 = '" + dribble6 + '\'' + 
			",pass2 = '" + pass2 + '\'' + 
			",dribble5 = '" + dribble5 + '\'' + 
			",pass3 = '" + pass3 + '\'' + 
			",id = '" + id + '\'' + 
			",dribble4 = '" + dribble4 + '\'' + 
			",pass4 = '" + pass4 + '\'' + 
			",dribble8 = '" + dribble8 + '\'' + 
			",dribble3 = '" + dribble3 + '\'' + 
			",dribble2 = '" + dribble2 + '\'' + 
			",dribble1 = '" + dribble1 + '\'' + 
			",shooting2 = '" + shooting2 + '\'' + 
			",shooting1 = '" + shooting1 + '\'' + 
			",serangan = '" + serangan + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",shooting4 = '" + shooting4 + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",shooting3 = '" + shooting3 + '\'' + 
			"}";
		}
}