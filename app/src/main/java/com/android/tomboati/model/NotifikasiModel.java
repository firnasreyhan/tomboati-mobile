package com.android.tomboati.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class NotifikasiModel{

	@SerializedName("data")
	private List<DataItem> data;

	public List<DataItem> getData(){
		return data;
	}

	public static class DataItem{

		@SerializedName("isi:")
		private String isi;

		@SerializedName("tanggal:")
		private String tanggal;

		@SerializedName("judul:")
		private String judul;

		public DataItem(String isi, String tanggal, String judul) {
			this.isi = isi;
			this.tanggal = tanggal;
			this.judul = judul;
		}

		public String getIsi(){
			return isi;
		}

		public String getTanggal(){
			return tanggal;
		}

		public String getJudul(){
			return judul;
		}
	}
}