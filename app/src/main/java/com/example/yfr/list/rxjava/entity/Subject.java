package com.example.yfr.list.rxjava.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.yfr.list.rxjava.entity.Rating;

import java.io.Serializable;
import java.util.List;

import retrofit2.http.Field;

/**
 * @Author: fengrui.yang
 * @Description:
 * @Date: created in 下午6:30 2018/12/18
 * @Modified_By:
 */
public class Subject implements Serializable {
    private Rating rating;
    private List<String> genres;
    private String title;
    private List<Cast> casts;
    @JSONField(name = "collect_count")
    private int collectCount;
    @JSONField(name = "original_title")
    private String originalTitle;
    private String subtype;
    private List<Director> directors;
    private String year;
    private List<Image> images;
    private String alt;
    private String id;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
