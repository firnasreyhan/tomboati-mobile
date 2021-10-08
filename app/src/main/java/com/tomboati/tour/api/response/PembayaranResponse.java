package com.tomboati.tour.api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PembayaranResponse{

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

		@SerializedName("IDTRANSAKSI")
		private String iDTRANSAKSI;

		@SerializedName("TANGGALJATUHTEMPO")
		private String tANGGALJATUHTEMPO;

		@SerializedName("STATUSPEMBAYARAN")
		private String sTATUSPEMBAYARAN;

		@SerializedName("IDPEMBAYARAN")
		private String iDPEMBAYARAN;

		@SerializedName("TANGGALPEMBAYARAN")
		private String tANGGALPEMBAYARAN;

		@SerializedName("TOTALPEMBAYARAN")
		private String tOTALPEMBAYARAN;

		@SerializedName("SISAPEMBAYARAN")
		private String sISAPEMBAYARAN;

		public String getIDTRANSAKSI(){
			return iDTRANSAKSI;
		}

		public String getTANGGALJATUHTEMPO(){
			return tANGGALJATUHTEMPO;
		}

		public String getSTATUSPEMBAYARAN(){
			return sTATUSPEMBAYARAN;
		}

		public String getIDPEMBAYARAN(){
			return iDPEMBAYARAN;
		}

		public String getTANGGALPEMBAYARAN(){
			return tANGGALPEMBAYARAN;
		}

		public String getTOTALPEMBAYARAN(){
			return tOTALPEMBAYARAN;
		}

		public String getSISAPEMBAYARAN(){
			return sISAPEMBAYARAN;
		}
	}
}