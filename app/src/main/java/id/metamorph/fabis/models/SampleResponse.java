package id.metamorph.fabis.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SampleResponse{

	@SerializedName("data")
	private List<DataItemSample> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemSample> data){
		this.data = data;
	}

	public List<DataItemSample> getData(){
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
			"SampleResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}