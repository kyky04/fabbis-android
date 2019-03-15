package id.metamorph.fabis.models.pemain;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PemainResponse{

	@SerializedName("data")
	private List<DataItemPemain> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemPemain> data){
		this.data = data;
	}

	public List<DataItemPemain> getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"PemainResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}