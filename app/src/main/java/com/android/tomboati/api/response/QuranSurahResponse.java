package com.android.tomboati.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuranSurahResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }

    public static class Data {

        @SerializedName("numberOfVerses")
        @Expose
        private Integer numberOfVerses;
        @SerializedName("name")
        @Expose
        private Name name;
        @SerializedName("revelation")
        @Expose
        private Revelation revelation;
        @SerializedName("verses")
        @Expose
        private List<Verse> verses = null;

        public Integer getNumberOfVerses() {
            return numberOfVerses;
        }

        public Name getName() {
            return name;
        }

        public Revelation getRevelation() {
            return revelation;
        }

        public List<Verse> getVerses() {
            return verses;
        }

        public class Name {

            @SerializedName("short")
            @Expose
            private String _short;
            @SerializedName("transliteration")
            @Expose
            private Transliteration transliteration;

            public String getShort() {
                return _short;
            }

            public Transliteration getTransliteration() {
                return transliteration;
            }

            public class Transliteration {

                @SerializedName("id")
                @Expose
                private String id;

                public String getId() {
                    return id;
                }

            }

        }

        public class Revelation {

            @SerializedName("id")
            @Expose
            private String id;

            public String getId() {
                return id;
            }


        }

        public static class Verse {

            @SerializedName("text")
            @Expose
            private Text text;
            @SerializedName("translation")
            @Expose
            private Translation translation;
            @SerializedName("audio")
            @Expose
            private Audio audio;

            public Verse(Text text, Translation translation, Audio audio) {
                this.text = text;
                this.translation = translation;
                this.audio = audio;
            }

            public Text getText() {
                return text;
            }

            public Translation getTranslation() {
                return translation;
            }

            public Audio getAudio() {
                return audio;
            }

            public static class Text {

                @SerializedName("arab")
                @Expose
                private String arab;
                @SerializedName("transliteration")
                @Expose
                private Transliteration__1 transliteration;

                public Text(String arab, Transliteration__1 transliteration) {
                    this.arab = arab;
                    this.transliteration = transliteration;
                }

                public String getArab() {
                    return arab;
                }

                public Transliteration__1 getTransliteration() {
                    return transliteration;
                }

                public static class Transliteration__1 {

                    @SerializedName("en")
                    @Expose
                    private String en;

                    public Transliteration__1(String en) {
                        this.en = en;
                    }

                    public String getEn() {
                        return en;
                    }
                }

            }

            public static class Translation {

                @SerializedName("id")
                @Expose
                private String id;

                public Translation(String id) {
                    this.id = id;
                }

                public String getId() {
                    return id;
                }
            }

            public static class Audio {

                @SerializedName("primary")
                @Expose
                private String primary;

                public Audio(String primary) {
                    this.primary = primary;
                }

                public String getPrimary() {
                    return primary;
                }
            }
        }
    }

}
