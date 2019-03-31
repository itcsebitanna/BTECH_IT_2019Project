package com.agri.duraivel.myapplication;

public class CropInfoModel {
    public CropInfoModel getCropInfoModel() {
        return cropInfoModel;
    }

    public void setCropInfoModel(CropInfoModel cropInfoModel) {
        this.cropInfoModel = cropInfoModel;
    }

    CropInfoModel cropInfoModel;
    private String cropID;
    private String cropName;

    public String getCropID() {
        return cropID;
    }

    public void setCropID(String cropID) {
        this.cropID = cropID;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropSeason() {
        return cropSeason;
    }

    public void setCropSeason(String cropSeason) {
        this.cropSeason = cropSeason;
    }

    public String getCropDisease() {
        return cropDisease;
    }

    public void setCropDisease(String cropDisease) {
        this.cropDisease = cropDisease;
    }

    public String getCropPesticides() {
        return cropPesticides;
    }

    public void setCropPesticides(String cropPesticides) {
        this.cropPesticides = cropPesticides;
    }

    public String getCropRemedies() {
        return cropRemedies;
    }

    public void setCropRemedies(String cropRemedies) {
        this.cropRemedies = cropRemedies;
    }

    public String getCropArea() {
        return cropArea;
    }

    public void setCropArea(String cropArea) {
        this.cropArea = cropArea;
    }

    private String cropSeason;
    private String cropDisease;
    private String cropPesticides;
    private String cropRemedies;
    private String cropArea;
}
