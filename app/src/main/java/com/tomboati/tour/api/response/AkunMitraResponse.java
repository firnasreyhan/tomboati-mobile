package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

public class AkunMitraResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("error")
	private Boolean error;

	@SerializedName("message")
	private String message;

	public Data getData(){
		return data;
	}

	public Boolean isError(){
		return error;
	}

	public String getMessage(){
		return message;
	}

	public static class Data{

		@SerializedName("STATUS_USER")
		private String sTATUSUSER;

		@SerializedName("NOMORHP")
		private String nOMORHP;

		@SerializedName("BUKTIBAYAR")
		private Object bUKTIBAYAR;

		@SerializedName("POIN")
		private String pOIN;

		@SerializedName("CREATED_AT")
		private String cREATEDAT;

		@SerializedName("NEGARA")
		private String nEGARA;

		@SerializedName("EMAIL")
		private String eMAIL;

		@SerializedName("KODEPOS")
		private String kODEPOS;

		@SerializedName("CABANG")
		private String cABANG;

		@SerializedName("STATUS")
		private Object sTATUS;

		@SerializedName("NOMORKTP")
		private String nOMORKTP;

		@SerializedName("KATEGORI")
		private Object kATEGORI;

		@SerializedName("USERTOKEN")
		private String uSERTOKEN;

		@SerializedName("USERNAME")
		private String uSERNAME;

		@SerializedName("UPDATED_AT")
		private String uPDATEDAT;

		@SerializedName("REKENING")
		private String rEKENING;

		@SerializedName("FILEKTP")
		private String fILEKTP;

		@SerializedName("BANK")
		private String bANK;

		@SerializedName("KODEREFERRAL")
		private String kODEREFERRAL;

		@SerializedName("FOTO")
		private String fOTO;

		@SerializedName("ID_CHAT_ROOM")
		private String iDCHATROOM;

		@SerializedName("IDUSERREGISTER")
		private String iDUSERREGISTER;

		@SerializedName("PROVINSI")
		private String pROVINSI;

		@SerializedName("ALAMAT")
		private String aLAMAT;

		@SerializedName("KOTA")
		private String kOTA;

		@SerializedName("KECAMATAN")
		private String kECAMATAN;

		@SerializedName("PASSWORD")
		private String pASSWORD;

		@SerializedName("KODEREFERRALFROM")
		private String kODEREFERRALFROM;

		@SerializedName("ATASNAMA")
		private String aTASNAMA;

		@SerializedName("NAMALENGKAP")
		private String nAMALENGKAP;

		public String getSTATUSUSER(){
			return sTATUSUSER;
		}

		public String getNOMORHP(){
			return nOMORHP;
		}

		public Object getBUKTIBAYAR(){
			return bUKTIBAYAR;
		}

		public String getPOIN(){
			return pOIN;
		}

		public String getCREATEDAT(){
			return cREATEDAT;
		}

		public String getNEGARA(){
			return nEGARA;
		}

		public String getEMAIL(){
			return eMAIL;
		}

		public String getKODEPOS(){
			return kODEPOS;
		}

		public String getCABANG(){
			return cABANG;
		}

		public Object getSTATUS(){
			return sTATUS;
		}

		public String getNOMORKTP(){
			return nOMORKTP;
		}

		public Object getKATEGORI(){
			return kATEGORI;
		}

		public String getUSERTOKEN(){
			return uSERTOKEN;
		}

		public String getUSERNAME(){
			return uSERNAME;
		}

		public String getUPDATEDAT(){
			return uPDATEDAT;
		}

		public String getREKENING(){
			return rEKENING;
		}

		public String getFILEKTP(){
			return fILEKTP;
		}

		public String getBANK(){
			return bANK;
		}

		public String getKODEREFERRAL(){
			return kODEREFERRAL;
		}

		public String getFOTO(){
			return fOTO;
		}

		public String getIDCHATROOM(){
			return iDCHATROOM;
		}

		public String getIDUSERREGISTER(){
			return iDUSERREGISTER;
		}

		public String getPROVINSI(){
			return pROVINSI;
		}

		public String getALAMAT(){
			return aLAMAT;
		}

		public String getKOTA(){
			return kOTA;
		}

		public String getKECAMATAN(){
			return kECAMATAN;
		}

		public String getPASSWORD(){
			return pASSWORD;
		}

		public String getKODEREFERRALFROM(){
			return kODEREFERRALFROM;
		}

		public String getATASNAMA(){
			return aTASNAMA;
		}

		public String getNAMALENGKAP(){
			return nAMALENGKAP;
		}
	}

}