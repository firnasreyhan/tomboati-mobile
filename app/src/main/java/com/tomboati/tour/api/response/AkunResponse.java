package com.tomboati.tour.api.response;

import com.google.gson.annotations.SerializedName;

public class AkunResponse{

	@SerializedName("data_tomboati")
	private DataTomboati dataTomboati;

	@SerializedName("error")
	private Boolean error;

	@SerializedName("message")
	private String message;

	@SerializedName("data_db_dash_tombo")
	private DataDbDashTombo dataDbDashTombo;

	public Boolean isError(){
		return error;
	}

	public String getMessage(){
		return message;
	}

	public DataTomboati getDataTomboati(){
		return dataTomboati;
	}

	public DataDbDashTombo getDataDbDashTombo(){
		return dataDbDashTombo;
	}


	public static class DataTomboati{

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
		private Object nEGARA;

		@SerializedName("EMAIL")
		private Object eMAIL;

		@SerializedName("KODEPOS")
		private Object kODEPOS;

		@SerializedName("CABANG")
		private Object cABANG;

		@SerializedName("STATUS")
		private Object sTATUS;

		@SerializedName("NOMORKTP")
		private Object nOMORKTP;

		@SerializedName("KATEGORI")
		private Object kATEGORI;

		@SerializedName("USERTOKEN")
		private String uSERTOKEN;

		@SerializedName("USERNAME")
		private Object uSERNAME;

		@SerializedName("UPDATED_AT")
		private Object uPDATEDAT;

		@SerializedName("REKENING")
		private Object rEKENING;

		@SerializedName("FILEKTP")
		private Object fILEKTP;

		@SerializedName("BANK")
		private Object bANK;

		@SerializedName("KODEREFERRAL")
		private String kODEREFERRAL;

		@SerializedName("FOTO")
		private Object fOTO;

		@SerializedName("ID_CHAT_ROOM")
		private String iDCHATROOM;

		@SerializedName("IDUSERREGISTER")
		private String iDUSERREGISTER;

		@SerializedName("PROVINSI")
		private Object pROVINSI;

		@SerializedName("ALAMAT")
		private Object aLAMAT;

		@SerializedName("KOTA")
		private Object kOTA;

		@SerializedName("KECAMATAN")
		private Object kECAMATAN;

		@SerializedName("PASSWORD")
		private Object pASSWORD;

		@SerializedName("KODEREFERRALFROM")
		private Object kODEREFERRALFROM;

		@SerializedName("ATASNAMA")
		private Object aTASNAMA;

		@SerializedName("NAMALENGKAP")
		private Object nAMALENGKAP;

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

		public Object getNEGARA(){
			return nEGARA;
		}

		public Object getEMAIL(){
			return eMAIL;
		}

		public Object getKODEPOS(){
			return kODEPOS;
		}

		public Object getCABANG(){
			return cABANG;
		}

		public Object getSTATUS(){
			return sTATUS;
		}

		public Object getNOMORKTP(){
			return nOMORKTP;
		}

		public Object getKATEGORI(){
			return kATEGORI;
		}

		public String getUSERTOKEN(){
			return uSERTOKEN;
		}

		public Object getUSERNAME(){
			return uSERNAME;
		}

		public Object getUPDATEDAT(){
			return uPDATEDAT;
		}

		public Object getREKENING(){
			return rEKENING;
		}

		public Object getFILEKTP(){
			return fILEKTP;
		}

		public Object getBANK(){
			return bANK;
		}

		public String getKODEREFERRAL(){
			return kODEREFERRAL;
		}

		public Object getFOTO(){
			return fOTO;
		}

		public String getIDCHATROOM(){
			return iDCHATROOM;
		}

		public String getIDUSERREGISTER(){
			return iDUSERREGISTER;
		}

		public Object getPROVINSI(){
			return pROVINSI;
		}

		public Object getALAMAT(){
			return aLAMAT;
		}

		public Object getKOTA(){
			return kOTA;
		}

		public Object getKECAMATAN(){
			return kECAMATAN;
		}

		public Object getPASSWORD(){
			return pASSWORD;
		}

		public Object getKODEREFERRALFROM(){
			return kODEREFERRALFROM;
		}

		public Object getATASNAMA(){
			return aTASNAMA;
		}

		public Object getNAMALENGKAP(){
			return nAMALENGKAP;
		}
	}

	public static class DataDbDashTombo{

		@SerializedName("country")
		private Object country;

		@SerializedName("is_wa")
		private String isWa;

		@SerializedName("upline")
		private Object upline;

		@SerializedName("userid")
		private Object userid;

		@SerializedName("status_ec")
		private Object statusEc;

		@SerializedName("bank")
		private Object bank;

		@SerializedName("ahli_waris")
		private Object ahliWaris;

		@SerializedName("id")
		private String id;

		@SerializedName("usercode")
		private Object usercode;

		@SerializedName("paket")
		private String paket;

		@SerializedName("atasnama")
		private Object atasnama;

		@SerializedName("photostatus")
		private String photostatus;

		@SerializedName("fotoktp")
		private Object fotoktp;

		@SerializedName("traffic")
		private Object traffic;

		@SerializedName("bukti_bayar")
		private Object buktiBayar;

		@SerializedName("tglupgrade")
		private Object tglupgrade;

		@SerializedName("ktp")
		private Object ktp;

		@SerializedName("last_login")
		private Object lastLogin;

		@SerializedName("kode_pos")
		private Object kodePos;

		@SerializedName("npwp")
		private Object npwp;

		@SerializedName("g2")
		private Object g2;

		@SerializedName("g3")
		private Object g3;

		@SerializedName("g4")
		private Object g4;

		@SerializedName("g5")
		private Object g5;

		@SerializedName("is_seen_notifikasi_mitra")
		private String isSeenNotifikasiMitra;

		@SerializedName("g6")
		private Object g6;

		@SerializedName("g7")
		private Object g7;

		@SerializedName("status_bank")
		private Object statusBank;

		@SerializedName("g8")
		private Object g8;

		@SerializedName("g9")
		private Object g9;

		@SerializedName("name")
		private Object name;

		@SerializedName("activate")
		private String activate;

		@SerializedName("timerupgrade")
		private String timerupgrade;

		@SerializedName("status")
		private String status;

		@SerializedName("sponsor")
		private String sponsor;

		@SerializedName("is_hr")
		private Object isHr;

		@SerializedName("g10")
		private Object g10;

		@SerializedName("login")
		private Object login;

		@SerializedName("hub_ahliwaris")
		private Object hubAhliwaris;

		@SerializedName("propinsi")
		private Object propinsi;

		@SerializedName("timer")
		private String timer;

		@SerializedName("klaim")
		private String klaim;

		@SerializedName("hphone")
		private String hphone;

		@SerializedName("tpassw")
		private Object tpassw;

		@SerializedName("qr_code")
		private Object qrCode;

		@SerializedName("email")
		private Object email;

		@SerializedName("passw")
		private Object passw;

		@SerializedName("tot_ref")
		private Object totRef;

		@SerializedName("transaction_code")
		private String transactionCode;

		@SerializedName("is_seen_notifikasi")
		private String isSeenNotifikasi;

		@SerializedName("cabang")
		private Object cabang;

		@SerializedName("passenc")
		private Object passenc;

		@SerializedName("kota")
		private Object kota;

		@SerializedName("address")
		private Object address;

		@SerializedName("photo")
		private Object photo;

		@SerializedName("status_nama")
		private Object statusNama;

		@SerializedName("usertoken")
		private String usertoken;

		@SerializedName("rekening")
		private Object rekening;

		@SerializedName("hphone2")
		private Object hphone2;

		@SerializedName("kecamatan")
		private Object kecamatan;

		public Object getCountry(){
			return country;
		}

		public String getIsWa(){
			return isWa;
		}

		public Object getUpline(){
			return upline;
		}

		public Object getUserid(){
			return userid;
		}

		public Object getStatusEc(){
			return statusEc;
		}

		public Object getBank(){
			return bank;
		}

		public Object getAhliWaris(){
			return ahliWaris;
		}

		public String getId(){
			return id;
		}

		public Object getUsercode(){
			return usercode;
		}

		public String getPaket(){
			return paket;
		}

		public Object getAtasnama(){
			return atasnama;
		}

		public String getPhotostatus(){
			return photostatus;
		}

		public Object getFotoktp(){
			return fotoktp;
		}

		public Object getTraffic(){
			return traffic;
		}

		public Object getBuktiBayar(){
			return buktiBayar;
		}

		public Object getTglupgrade(){
			return tglupgrade;
		}

		public Object getKtp(){
			return ktp;
		}

		public Object getLastLogin(){
			return lastLogin;
		}

		public Object getKodePos(){
			return kodePos;
		}

		public Object getNpwp(){
			return npwp;
		}

		public Object getG2(){
			return g2;
		}

		public Object getG3(){
			return g3;
		}

		public Object getG4(){
			return g4;
		}

		public Object getG5(){
			return g5;
		}

		public String getIsSeenNotifikasiMitra(){
			return isSeenNotifikasiMitra;
		}

		public Object getG6(){
			return g6;
		}

		public Object getG7(){
			return g7;
		}

		public Object getStatusBank(){
			return statusBank;
		}

		public Object getG8(){
			return g8;
		}

		public Object getG9(){
			return g9;
		}

		public Object getName(){
			return name;
		}

		public String getActivate(){
			return activate;
		}

		public String getTimerupgrade(){
			return timerupgrade;
		}

		public String getStatus(){
			return status;
		}

		public String getSponsor(){
			return sponsor;
		}

		public Object getIsHr(){
			return isHr;
		}

		public Object getG10(){
			return g10;
		}

		public Object getLogin(){
			return login;
		}

		public Object getHubAhliwaris(){
			return hubAhliwaris;
		}

		public Object getPropinsi(){
			return propinsi;
		}

		public String getTimer(){
			return timer;
		}

		public String getKlaim(){
			return klaim;
		}

		public String getHphone(){
			return hphone;
		}

		public Object getTpassw(){
			return tpassw;
		}

		public Object getQrCode(){
			return qrCode;
		}

		public Object getEmail(){
			return email;
		}

		public Object getPassw(){
			return passw;
		}

		public Object getTotRef(){
			return totRef;
		}

		public String getTransactionCode(){
			return transactionCode;
		}

		public String getIsSeenNotifikasi(){
			return isSeenNotifikasi;
		}

		public Object getCabang(){
			return cabang;
		}

		public Object getPassenc(){
			return passenc;
		}

		public Object getKota(){
			return kota;
		}

		public Object getAddress(){
			return address;
		}

		public Object getPhoto(){
			return photo;
		}

		public Object getStatusNama(){
			return statusNama;
		}

		public String getUsertoken(){
			return usertoken;
		}

		public Object getRekening(){
			return rekening;
		}

		public Object getHphone2(){
			return hphone2;
		}

		public Object getKecamatan(){
			return kecamatan;
		}
	}
}