package com.android.tomboati.api.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListPaketVerifyRespone {

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


	public class DataItem{

		@SerializedName("IDTRANSAKSI")
		private String iDTRANSAKSI;

		@SerializedName("KODEPENDAFTARAN")
		private String kODEPENDAFTARAN;

		@SerializedName("TANGGALKEBERANGKAT")
		private String tANGGALKEBERANGKAT;

		@SerializedName("NAMAPAKET")
		private String nAMAPAKET;

		@SerializedName("NAMAWISATA")
		private String nAMAWISATA;

		@SerializedName("IDPAKET")
		private String iDPAKET;

		@SerializedName("IDWISATAHALAL")
		private String iDWISATAHALAL;

		@SerializedName("WAKTU")
		private String wAKTU;

		@SerializedName("SHEET")
		private int sHEET;

		@SerializedName("STATUSTRANSAKSI")
		private String sTATUSTRANSAKSI;

		@SerializedName("SHEETHARGA")
		private double sHEETHARGA;

		public String getIDTRANSAKSI(){
			return iDTRANSAKSI;
		}

		public String getKODEPENDAFTARAN(){
			return kODEPENDAFTARAN;
		}

		public String getTANGGALKEBERANGKAT(){
			return tANGGALKEBERANGKAT;
		}

		public String getNAMAPAKET(){
			return nAMAPAKET;
		}

		public String getNAMAWISATA(){
			return nAMAWISATA;
		}

		public String getIDPAKET(){
			return iDPAKET;
		}

		public String getIDWISATAHALAL(){
			return iDWISATAHALAL;
		}

		public String getWAKTU(){
			return wAKTU;
		}

		public int getSHEET(){
			return sHEET;
		}

		public String getSTATUSTRANSAKSI(){
			return sTATUSTRANSAKSI;
		}

		public double getSHEETHARGA(){
			return sHEETHARGA;
		}
	}
}