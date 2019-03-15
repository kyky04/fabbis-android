package id.metamorph.fabis.models.nilai;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class NilaiResponse{

	@SerializedName("data")
	private List<DataItemNilai> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemNilai> data){
		this.data = data;
	}

	public List<DataItemNilai> getData(){
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
			"NilaiResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}