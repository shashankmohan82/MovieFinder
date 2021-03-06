package com.androidtmdbwrapper.model.mediadetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sHIVAM on 2/15/2017.
 */

public class Video implements Parcelable {
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
    @JsonProperty("key")
    private String key;
    @JsonProperty("site")
    private String site;
    @JsonProperty("size")
    private int size;
    @JsonProperty("type")
    private String type;
    @JsonProperty("iso_639_1")
    private String language;
    @JsonProperty("iso_3166_1")
    private String country;

    public Video() {
    }

    protected Video(Parcel in) {
        name = in.readString();
        id = in.readString();
        key = in.readString();
        site = in.readString();
        size = in.readInt();
        type = in.readString();
        language = in.readString();
        country = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video)) return false;

        Video video = (Video) o;

        if (getSize() != video.getSize()) return false;
        if (!getKey().equals(video.getKey())) return false;
        if (!getSite().equals(video.getSite())) return false;
        if (!getType().equals(video.getType())) return false;
        if (!getLanguage().equals(video.getLanguage())) return false;
        return getCountry().equals(video.getCountry());

    }

    @Override
    public int hashCode() {
        int result = getKey().hashCode();
        result = 31 * result + getSite().hashCode();
        result = 31 * result + getSize();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getLanguage().hashCode();
        result = 31 * result + getCountry().hashCode();
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(key);
        dest.writeString(site);
        dest.writeInt(size);
        dest.writeString(type);
        dest.writeString(language);
        dest.writeString(country);
    }
}
