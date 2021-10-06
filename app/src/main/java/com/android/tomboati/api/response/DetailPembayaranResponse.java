package com.android.tomboati.api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailPembayaranResponse{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("error")
	private boolean error;

	@SerializedName("message")
	private String message;

	public List<DataItem> getData(){
		return data;
	}

	public boolean isError(){
		return error;
	}

	public String getMessage(){
		return message;
	}

	public static class DataItem{

		@SerializedName("IDDETAILPEMBAYARAN")
		private String iDDETAILPEMBAYARAN;

		@SerializedName("DESKRIPSI")
		private String dESKRIPSI;

		@SerializedName("STATUSPEMBAYARAN")
		private String sTATUSPEMBAYARAN;

		@SerializedName("IDPEMBAYARAN")
		private String iDPEMBAYARAN;

		@SerializedName("JUMLAHPEMBAYARAN")
		private String jUMLAHPEMBAYARAN;

		@SerializedName("TANGGALPEMBAYARAN")
		private String tANGGALPEMBAYARAN;

		@SerializedName("BUKTIPEMBAYARAN")
		private String bUKTIPEMBAYARAN;

		@SerializedName("ISSEEN")
		private String iSSEEN;

		public String getIDDETAILPEMBAYARAN(){
			return iDDETAILPEMBAYARAN;
		}

		public String getDESKRIPSI(){
			return dESKRIPSI;
		}

		public String getSTATUSPEMBAYARAN(){
			return sTATUSPEMBAYARAN;
		}

		public String getIDPEMBAYARAN(){
			return iDPEMBAYARAN;
		}

		public String getJUMLAHPEMBAYARAN(){
			return jUMLAHPEMBAYARAN;
		}

		public String getTANGGALPEMBAYARAN(){
			return tANGGALPEMBAYARAN;
		}

		public String getBUKTIPEMBAYARAN(){
			return bUKTIPEMBAYARAN;
		}

		public String getISSEEN(){
			return iSSEEN;
		}
	}
}